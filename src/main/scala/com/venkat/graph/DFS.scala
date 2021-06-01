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
   * Step 1: Mark the current vertex as visited
   * Step 2: Find a neighboring vertex who is not visited
   * Step 3: Repeat Step 1 and Step 2 until all the vertices are visited
   * Step 4: Once all the vertices are visited and it is not a dead end (at least one non visited vertex) then print the path
   * Step 5: Before coming out of the stacktrace, mark the current vertex as unvisited so that it can be visited again as part of another path
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

  /**
   * findPathWithLoops finds a path in a graph without using recursion. Steps below.
   * 1. Add current element to (Tuple2(Int,String)).
   * 2. Create a while loop that exits when a stack is empty.
   * 3. Inside while loop, pop the stack and for each of them have a for loop.
   * 4. For each of the element in a for loop which are not visited yet and path exists from a current node, push it to the stack with path.
   * 5. Outside of the forloop, check if all the vertices are visited, then it is a path print it.
   */
  def findPathWithLoops(graph: Array[Array[Int]], start: Int): Unit = {
    val stack = new scala.collection.mutable.Stack[(Int, String)]
    stack.push((start, start.toString))
    while (stack.nonEmpty) {
      val i = stack.pop
      val visited = Array.fill(graph(0).length)(false)
      i._2.split(">").foreach(j => visited(j.toInt) = true) // This is the line  that makes me worried.
      for (k <- visited.indices) {
        if (!visited(k) && graph(i._1)(k) == 1) {
          stack.push((k, i._2 + ">" + k))
        }
      }
      if (!visited.contains(false)) {
        println(i._2)
      }
    }
  }

  //
  //    def findPathWithLoops(graph: Array[Array[Int]], start: Int): Unit = {
  //      //val visited = Array.fill(graph(0).length)(false)
  //      val stack = new scala.collection.mutable.Stack[(Int, String, Array[Boolean])]
  //      //visited(start) = true
  //      stack.push((start, start.toString, Array.fill(graph(0).length)(false)))
  //      stack.top._3.update(start, true)
  //      while (stack.nonEmpty) {
  //        val i = stack.top
  //        var isDeadEnd = true
  //        for (k <- i._3.indices) {
  //          if (!i._3(k) && graph(i._1)(k) == 1) {
  //            val visited = i._3.clone()
  //            visited(k) = true
  //            stack.push((k, i._2 + " -> " + k, visited))
  //            isDeadEnd = false
  //          }
  //        }
  //        if (!i._3.contains(false)) {
  //          println(i._2)
  //        }
  //        if (isDeadEnd) {
  //          stack.pop()
  //        }
  //      }
  //    }

  def main(args: Array[String]): Unit = {
    val graph: Array[Array[Int]] = Array.ofDim[Int](5, 5)
    constructGraph(graph)
    //findPath(graph, 0, Array(false, false, false, false, false), "0")
    findPathWithLoops(graph, 0)
  }
}
