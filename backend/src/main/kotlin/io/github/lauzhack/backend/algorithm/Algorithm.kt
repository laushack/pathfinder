package io.github.lauzhack.backend.algorithm

import java.util.PriorityQueue

typealias Time = Int

class Algorithm(val schedule: Schedule) {
  fun run(start: Node, end: Int): List<Node> {
    val pairComparator = Comparator<Node> { a, b -> a.time - b.time }
    val priorityList = PriorityQueue(pairComparator)
    // map containing the visited nodes along with the time it took to get there
    val visited = mutableMapOf<Int, Pair<Time, Node>>()
    val done = mutableSetOf<Int>()

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

data class Node(val id: Int, val time: Time)

interface Schedule {
  fun getDepartures(node: Node, from: Time): Set<Node>
}
