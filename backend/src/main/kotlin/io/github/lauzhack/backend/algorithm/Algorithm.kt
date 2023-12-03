package io.github.lauzhack.backend.algorithm

import io.github.lauzhack.backend.data.Resources
import java.util.PriorityQueue

typealias NodeID = Int

typealias Time = Long

data class Visited(val node: Node, val previous: NodeID, val previousDeparture: Time)

class Algorithm(private val schedule: Schedule) {
  fun run(startID: NodeID, startTime: Time, endID: NodeID): List<Node>? {
    val pairComparator = Comparator<Node> { a, b -> (a.arrival - b.arrival).toInt() }
    val priorityList = PriorityQueue(pairComparator)
    // map containing the visited nodes along with the time it took to get there
    val visited = mutableMapOf<NodeID, Visited>()
    val done = mutableSetOf<NodeID>()

    val start = Node(startID, startTime)
    priorityList.add(start)
    visited[start.id] = Visited(start, start.id, startTime)

    while (priorityList.isNotEmpty()) {
      val current = priorityList.poll()
      if (current.id == endID) {
        break
      }

      if (done.contains(current.id)) {
        continue
      }
      done.add(current.id)

      val neighbors = schedule.getDepartures(current.id, current.arrival)
      for (transition in neighbors) {
        val neighbor = transition.destination
        val departure = transition.departTime

        if (!visited.containsKey(neighbor.id) ||
            neighbor.arrival < visited[neighbor.id]!!.node.arrival) {
          visited[neighbor.id] = Visited(neighbor, current.id, departure)
          priorityList.add(neighbor)
        }
      }
    }

    visited[endID]?.let {
      val path = mutableListOf<Visited>()
      var current = it
      while (current.node.id != start.id) {
        path.add(current)
        current = visited[current.previous]!!
      }
      path.add(current.copy(previousDeparture = path.last().previousDeparture))
      var previous: Visited? = null
      return path
          .map { v ->
            val node = previous?.let { p -> v.node.copy(departure = p.previousDeparture) } ?: v.node
            previous = v
            node
          }
          .reversed()
    } ?: return null
  }
}

data class Node(
    val id: NodeID,
    val arrival: Time,
    val tripID: String = "",
    val departure: Time? = null
)

data class Transition(val departTime: Time, val destination: Node)

data class StopTime(val stopSequence: Int, val id: NodeID, val arrival: Time, val departure: Time)

class Schedule(private val map: Map<NodeID, List<Transition>>) {

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

  fun getDepartures(node: NodeID, after: Time): Set<Transition> {
    return map[node]
        // We don't wait more than 1 hour in one station
        ?.filter { after <= it.departTime && it.departTime <= after + 60 }
        ?.toSet() ?: emptySet()
  }
}

fun timeToMinutes(timeStr: String): Long {
  // Split the string into hours, minutes, and seconds
  val (h, m, _) = timeStr.split(':').map { it.toInt() }

  // Convert hours and minutes to total minutes

  return h * 60L + m
}
