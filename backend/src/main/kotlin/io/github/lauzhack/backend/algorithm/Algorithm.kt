package io.github.lauzhack.backend.algorithm

import io.github.lauzhack.backend.data.Resources
import java.util.PriorityQueue

typealias NodeID = Int

typealias Time = Long

class Algorithm(private val schedule: Schedule) {
  fun run(start: Node, end: NodeID): List<Node>? {
    val pairComparator = Comparator<Node> { a, b -> (a.arrival - b.arrival).toInt() }
    val priorityList = PriorityQueue(pairComparator)
    // map containing the visited nodes along with the time it took to get there
    val visited = mutableMapOf<NodeID, Pair<Node, NodeID>>()
    val done = mutableSetOf<NodeID>()

    priorityList.add(start)
    visited[start.id] = Pair(start, start.id)

    while (priorityList.isNotEmpty()) {
      val current = priorityList.poll()
      if (current.id == end) {
        break
      }

      if (done.contains(current.id)) {
        continue
      }
      done.add(current.id)

      val neighbors = schedule.getDepartures(current.id, current.arrival)
      for (neighbor in neighbors) {

        if (!visited.containsKey(neighbor.id) ||
            neighbor.arrival < visited[neighbor.id]!!.first.arrival) {
          visited[neighbor.id] = Pair(neighbor, current.id)
          priorityList.add(neighbor)
        }
      }
    }

    visited[end]?.let {
      val path = mutableListOf<Node>()
      var current = it
      while (current.first.id != start.id) {
        path.add(current.first)
        current = visited[current.second]!!
      }
      // Don't look
      val second = path.last()
      val departure =
          schedule.map[start.id]!!
              .filter { it.destination.id == second.id && it.departTime >= start.arrival }
              .sortedBy { it.departTime }
              .first()
      val first = Node(start.id, departure.departTime, second.tripID)
      path.add(first) // TODO: not add regular start but the one of the train
      path.reverse()
      return path
    } ?: return null
  }
}

data class Node(val id: NodeID, val arrival: Time, val tripID: String)

data class Transition(val departTime: Time, val destination: Node)

data class StopTime(val stopSequence: Int, val id: NodeID, val arrival: Time, val departure: Time)


class Schedule(val map: Map<NodeID, List<Transition>>) {

  companion object {
    fun fromData(): Schedule {
      val m =
          Resources.StopTimes.data()
              .groupBy(
                  { it[Resources.StopTimes.TripId] },
                  {
                    try {
                      StopTime(
                          it[Resources.StopTimes.StopSequence].toInt(),
                          it[Resources.StopTimes.StopId].split(":")[0].toInt(),
                          timeToMinutes(it[Resources.StopTimes.ArrivalTime]),
                          timeToMinutes(it[Resources.StopTimes.DepartureTime]))
                    } catch (e: NumberFormatException) {
                      null
                    }
                  })
              .flatMap {
                // sort by sequence number
                val nodesOnTrip = it.value.filterNotNull().sortedBy { i -> i.stopSequence }
                val noLast = nodesOnTrip.subList(0, nodesOnTrip.size - 1).withIndex()
                // map all nodes to their neighbor
                noLast.map { (n, node) ->
                  val nextStop = nodesOnTrip[n + 1]
                  val nextNode = Node(nextStop.id, nextStop.arrival, it.key)
                  Pair(node.id, Transition(node.departure, nextNode))
                }
              }
              .groupBy({ it.first }, { it.second })

      return Schedule(m)
    }
  }

  fun getDepartures(node: NodeID, after: Time): Set<Node> {
    return map[node]
        // We don't wait more than 1 hour in one station
        ?.filter { after <= it.departTime && it.departTime <= after + 60 }
        ?.map { it.destination }
        ?.toSet() ?: emptySet()
  }
}

fun timeToMinutes(timeStr: String): Long {
  // Split the string into hours, minutes, and seconds
  val (h, m, _) = timeStr.split(':').map { it.toInt() }

  // Convert hours and minutes to total minutes

  return h * 60L + m
}
