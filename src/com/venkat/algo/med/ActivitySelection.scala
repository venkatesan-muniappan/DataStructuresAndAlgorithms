package com.venkat.algo.med

class Task(val name: String, val start: Int, val end: Int) {
  override def toString: String = {
    name
  }
}

object ActivitySelection {
  def main(args: Array[String]): Unit = {
    val a1 = new Task("a1", 1, 4)
    val a2 = new Task("a2", 3, 5)
    val a3 = new Task("a3", 0, 6)
    val a4 = new Task("a4", 5, 7)
    val a5 = new Task("a5", 3, 9)
    val a6 = new Task("a6", 5, 9)
    val a7 = new Task("a7", 6, 10)
    val a8 = new Task("a8", 8, 11)
    val a9 = new Task("a9", 8, 12)
    val a10 = new Task("a10", 2, 14)
    val a11 = new Task("a11", 12, 16)
    println(s"Activity selection from the list of all the tasks is ${activitySelection(List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11)).mkString(",")}")
  }

  def activitySelection(list: List[Task]): List[Task] = {
    println(s"Processing the list ${list.mkString(",")}")
    if (list.isEmpty) return List[Task]()
    val head :: tail = list
    val compatibleTasks = tail.filter(_.start >= head.end)
    println(s"compatible tasks for task $head is ${compatibleTasks.mkString(",")}")
    List(head) ++ activitySelection(compatibleTasks)
  }
}
