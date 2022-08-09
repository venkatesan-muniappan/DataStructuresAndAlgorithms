package com.venkat.test

object Test {
  def main(args: Array[String]): Unit = {
    val arr = Array(3, 2, 1, 5, 6, 4)
    val k = 4 // Index should be valid.

    println(s"Result of countSort is ${countSort(arr).mkString(",")}")
    //    val pq = scala.collection.mutable.PriorityQueue[Int]()
    //
    //    arr.foreach(elm => pq += elm)
    //
    //    println(s"priority queue is ${pq.mkString(",")}")
    //
    //    var currentElem = Integer.MAX_VALUE
    //    1 to k foreach { i =>
    //      currentElem = pq.dequeue()
    //    }
    //    println(s"${currentElem}")


  }

  def countSort(arr: Array[Int]): Array[Int] = {
    //3 steps
    //1. Counter
    //2. construct index
    //3. We need to fil the result based on index

    val outArr = new Array[Int](arr.length)

    val countArr = Array.fill[Int](arr.max + 1)(0)

    arr.foreach(elm => {
      countArr(elm) += 1
    })
    //[0,1,1,1,1,1,1]

    //2. Build index
    1 until countArr.length foreach (i => {
      countArr(i) += countArr(i - 1)
    })
    //[0,1,1,1,1,1,1]

    //3. Building final output
    outArr.length - 1 to 0 by -1 foreach (i => {
      outArr(countArr(i)) = i
      countArr(i) -= 1
    })

    return outArr
  }


}
