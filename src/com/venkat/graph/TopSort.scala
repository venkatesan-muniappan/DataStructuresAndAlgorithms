package com.venkat.graph

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Vertex(val name: String, var visited: Boolean = false) {
  override def toString: String = {
    name
  }
}

object TopSort {

  val adjList = mutable.Map[String, List[Vertex]]()

  val topSortList = new ListBuffer[String]()

  def main(args: Array[String]): Unit = {
    //val adjList = mutable.Map[Vertex, List[Vertex]]()
    addEdge("A", new Vertex("D"))
    addEdge("A", new Vertex("E"))
    addEdge("B", new Vertex("E"))
    addEdge("E", new Vertex("G"))
    addEdge("D", new Vertex("G"))
    addEdge("G", new Vertex("H"))
    addEdge("F", new Vertex("H"))
    addEdge("C", new Vertex("F"))
    adjList.put("H", List[Vertex]())
    println(s"After adding all the edges, the graph is ${adjList.mkString("\n")}")
    topSort()
    println(s"List after topSort is ${topSortList.reverse.mkString(",")}")
  }

  def addEdge(sourceVertex: String, targetVertex: Vertex): Unit = {
    val tarList = List(targetVertex)
    adjList.put(sourceVertex, adjList.getOrElse(sourceVertex, List[Vertex]()) ::: tarList)
  }

  def deleteEdge(sourceVertex: String): Unit = {
    //    val tarList = adjList.getOrElse(sourceVertex, List[Vertex]()).filter(_.name != sourceVertex)
    //    adjList.put(sourceVertex, tarList)
    for ((k, v) <- adjList) {
      adjList.put(k, v.filter(v => v.name != sourceVertex))
    }
    adjList.remove(sourceVertex)
  }

  def findEmptyVertexList(): String = {
    val k = adjList.filter(k => k._2.isEmpty).keys.head
    k
  }

  def topSort(): Unit = {
    while (adjList.nonEmpty) {
      val noSuccessorVertex = findEmptyVertexList()
      topSortList.append(noSuccessorVertex)
      deleteEdge(noSuccessorVertex)
    }
  }
}
