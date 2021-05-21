package main.scala.com.venkat.graph

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
object DFS {
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
   * The findPath method takes the starting position, boolean array to keep track of what is in the stack, original graph to traverse and finally a string to hold the path constructed so far.
   *
   * Algorithm followed while finding a path is given below
   *  Step 1: Mark the current vertex as visited
   *  Step 2: Find a neighboring vertex who is not visited
   *  Step 3: Repeat Step 1 and Step 2 until all the vertices are visited
   *  Step 4: Once all the vertices are visited and it is not a dead end (at least one non visited vertex) then print the path
   *  Step 5: Before coming out of the stacktrace, mark the current vertex as unvisited so that it can be visited again as part of another path
   *
   * @param graph
   * @param i
   * @param visited
   * @param path
   */
  def findPath(graph: Array[Array[Int]], i: Int, visited: Array[Boolean], path: String): Unit = {
    //    println(s"ith position is ${i} and visited array is ${visited.mkString(",")} and path is ${path}")
    visited(i) = true
    count += 1
    for (k <- visited.indices) {
      //      println(s"  kth value is ${k}, ith value is ${i}, visited of k is ${visited(k)} and graph of i,k is ${graph(i)(k)}")
      if (!visited(k) && graph(i)(k) == 1) {
        findPath(graph, k, visited, path + "->" + k)
      }
    }
    if (!visited.contains(false)) {
      println(s"  Found a path and it is ${path}, count is ${count}")
    }
    visited(i) = false
  }

  def main(args: Array[String]): Unit = {
    val graph: Array[Array[Int]] = Array.ofDim[Int](5, 5)
    constructGraph(graph)
    findPath(graph, 0, Array(false, false, false, false, false), "0")
  }
}
