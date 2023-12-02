package io.github.lauzhack.backend.algorithm

import io.github.lauzhack.backend.data.Resources
import java.util.PriorityQueue

typealias NodeID = Int

typealias Time = Long

class Algorithm(private val schedule: Schedule) {
  fun run(start: Node, end: NodeID): List<Node>? {
    val pairComparator = Comparator<Node> { a, b -> (a.time - b.time).toInt() }
    val priorityList = PriorityQueue(pairComparator)
    // map containing the visited nodes along with the time it took to get there
    val visited = mutableMapOf<NodeID, Pair<Time, Node>>()
    val done = mutableSetOf<NodeID>()

    priorityList.add(start)

    while (priorityList.isNotEmpty()) {
      val current = priorityList.poll()
      if (current.id == end) {
        break
      }

      if (done.contains(current.id)) {
        continue
      }
      done.add(current.id)

      val neighbors = getNeighbors(current)
      for (neighbor in neighbors) {
        if (!visited.containsKey(neighbor.id) || neighbor.time < visited[neighbor.id]!!.first) {
          visited[neighbor.id] = Pair(neighbor.time, current)
          priorityList.add(neighbor)
        }
      }
    }

    visited[end]?.let {
      val path = mutableListOf<Node>()
      var current = Node(end, it.first, "") // TODO: correct tripID
      while (current.id != start.id) {
        path.add(current)
        current = visited[current.id]!!.second
      }
      path.add(start)
      path.reverse()
      return path
    } ?: return null
  }

  private fun getNeighbors(node: Node): Set<Node> {
    return schedule.getDepartures(node, node.time)
  }
}

data class Node(val id: NodeID, val time: Time, val tripID: String)

class Schedule(private val map: Map<NodeID, List<Node>>) {

  companion object {
    private val data = Resources.StopTimesTrain.data()

    fun build(): Schedule {
      val m =
          data
              .groupBy(
                  { it[Resources.StopTimesTrain.TripId] },
                  {
                    try {
                      Pair(
                          it[Resources.StopTimesTrain.StopSequence].toInt(),
                          Node(
                              it[Resources.StopTimesTrain.StopId].split(":")[0].toInt(),
                              timeToMinutes(it[Resources.StopTimesTrain.DepartureTime]),
                              it[Resources.StopTimesTrain.TripId]))
                    } catch (e: NumberFormatException) {
                      null
                    }
                  })
              .flatMap {
                // remove the last position as it does not have a neighbor
                // remove the biggest po
                val list = it.value.filterNotNull().sortedBy { i -> i.first }.map { i -> i.second }
                val noLast = list.subList(0, list.size - 1).withIndex()
                // map all nodes to their neighbor
                noLast.map { n -> Pair(n.value.id, list[n.index + 1]) }
              }
              .groupBy({ it.first }, { it.second })

      return Schedule(m)
    }
  }

  fun getDepartures(node: Node, from: Time): Set<Node> {
    return map[node.id]?.filter { from <= it.time && it.time <= from + 60 }?.toSet() ?: emptySet()
  }
}

fun timeToMinutes(timeStr: String): Long {
  // Split the string into hours, minutes, and seconds
  val (h, m, _) = timeStr.split(':').map { it.toInt() }

  // Convert hours and minutes to total minutes

  return h * 60L + m
}
