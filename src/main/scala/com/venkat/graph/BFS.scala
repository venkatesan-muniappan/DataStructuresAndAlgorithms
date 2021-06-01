package main.scala.com.venkat.graph

import scala.collection.mutable

/**
 * Traversing a given graph using Depth First Search traversal mechanism.
 * Graph representation will use Adjacency Matrix.
 * Starting point of the graph will be given.
 * Travesals which uses recursion and without recursion will be explored.
 *
 * Sample UnDirected Graph
 * 0 - 1 - 2
 * | / |  /
 * 4/ -3/
 *
 * Possible Paths Starting at Root 0
 * 0 -> 1 -> 2 -> 3 -> 4
 * 0 -> 1 -> 4 -> 3 -> 2
 * 0 -> 4 -> 1 -> 3 -> 2
 * 0 -> 4 -> 1 -> 2 -> 3
 * 0 -> 4 -> 3 -> 1 -> 2
 * 0 -> 4 -> 3 -> 2 -> 1
 */
object BFS {
  var count = 0

  def constructGraph(array: Array[Array[Int]]): Unit = {
    array(0)(1) = 1
    array(1)(0) = 1
    array(1)(2) = 1
    array(2)(1) = 1
    array(0)(4) = 1
    array(4)(0) = 1
    array(1)(4) = 1
    array(4)(1) = 1
    array(4)(3) = 1
    array(3)(4) = 1
    array(1)(3) = 1
    array(3)(1) = 1
    array(2)(3) = 1
    array(3)(2) = 1
  }

  def displayGraph(array: Array[Array[Int]]): Unit = {
    println(s"Graph is constructed and it is ${array.map(a => a.mkString("(", ",", ")")).mkString("(", ",", ")")}")
  }

  /**
   * The findPath method takes the starting position, boolean array to keep track of what is in the Queue, original graph to traverse and finally a string to hold the path constructed so far.
   * Algorithm followed while finding a path is given below
   * 1. Add the starting vertex to the Queue.
   * 2. Have a while loop that iterates until Queue is not empty
   * 3. For each element in Queue, dequeue it and create a for loop to check if there are any paths which are unvisited yet.
   * 4. All such unvisited vertex will be added to the Queue and it will be traversed later.
   *
   */
  def findPath(graph: Array[Array[Int]], i: Int): Unit = {
    val queue = new mutable.Queue[(Int, String)]()
    queue.enqueue((i, i.toString))
    while (queue.nonEmpty) {
      val i = queue.dequeue()
      val visited = Array.fill[Boolean](graph(0).length)(false)
      i._2.split(">").foreach(k => visited.update(k.toInt, true))
      for (l <- visited.indices) {
        if (!visited(l) && graph(i._1)(l) == 1) {
          queue.enqueue((l, i._2 + ">" + l))
        }
      }
      if (!visited.contains(false)) {
        println(i._2)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val graph: Array[Array[Int]] = Array.ofDim[Int](5, 5)
    constructGraph(graph)
    findPath(graph, 0)
  }
}
