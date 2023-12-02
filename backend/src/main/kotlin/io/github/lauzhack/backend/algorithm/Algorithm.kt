package io.github.lauzhack.backend.algorithm

import io.github.lauzhack.backend.data.Resources
import java.util.PriorityQueue

typealias NodeID = String

typealias Time = Long

class Algorithm(private val schedule: Schedule) {
  fun run(start: Node, end: NodeID): List<Node> {
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

    val path = mutableListOf<Node>()
    var current = Node(end, visited[end]!!.first)
    while (current.id != start.id) {
      path.add(current)
      current = visited[current.id]!!.second
    }
    path.add(start)
    path.reverse()
    return path
  }

  private fun getNeighbors(node: Node): Set<Node> {
    return schedule.getDepartures(node, node.time)
  }
}

data class Node(val id: NodeID, val time: Time)

class Schedule(private val map: Map<NodeID, List<Node>>) {

  companion object {
    private val data = Resources.StopTimesTrain.data()

    fun build(): Schedule {
      val m =
          data
              .groupBy(
                  { it[Resources.StopTimesTrain.TripId] },
                  {
                    Pair(
                        it[Resources.StopTimesTrain.StopSequence].toInt(),
                        Node(
                            it[Resources.StopTimesTrain.StopId],
                            timeToMinutes(it[Resources.StopTimesTrain.DepartureTime])))
                  })
              .flatMap {
                // remove the last position as it does not have a neighbor
                val list = it.value.filter { v -> v.first != it.value.size - 1 }
                // map all nodes to their neighbor
                list.map { (pos, node) ->
                  Pair(node.id, it.value[pos + 1].second)
                } // TODO: check l'ordre
              }
              .groupBy({ it.first }, { it.second })

      return Schedule(m)
    }
  }

  fun getDepartures(node: Node, from: Time): Set<Node> {
    return map[node.id]!!.filter { from <= it.time && it.time <= from + 60 }.toSet()
  }
}

fun timeToMinutes(timeStr: String): Long {
  // Split the string into hours, minutes, and seconds
  val (h, m, s) = timeStr.split(':').map { it.toInt() }

  // Convert hours and minutes to total minutes

  return h * 60L + m
}
