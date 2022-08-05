package com.venkat.algo.med


object CountingSort {
  val arr: Array[Int] = Array(9, 8, 7, 6, 5, 8, 4, 2, 9, 5, 4, 6, 7, 3, 2, 1)

  def sort(k: Int): Unit = {
    val out = new Array[Int](arr.length)
    val cnt = Array.fill[Int](k + 1)(0)

    arr.foreach(el => {
      cnt(el) += 1
    })

    println(s"Count array after first step is ${cnt.mkString(",")}")
    1 to k foreach (el => {
      cnt(el) += cnt(el - 1)
    })
    println(s"Count array after second step is ${cnt.mkString(",")}")

    arr.length - 1 to 0 by -1 foreach (i => {
      out(cnt(arr(i))-1) = arr(i)
      cnt(arr(i)) -= 1
    })

    println(s"Sorted array is ${out.mkString(",")}")
  }

  def main(args: Array[String]): Unit = {
    sort(9)
    //println(s"Sorted array is ${arr.mkString(",")}")
  }
}
