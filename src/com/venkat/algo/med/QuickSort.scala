package com.venkat.algo.med

object QuickSort {
  val arr: Array[Int] = Array(9, 8, 7, 6, 5, 4, 3, 2, 1)

  def main(args: Array[String]): Unit = {
    sort(0, arr.length - 1)
    println(s"Sorted array is ${arr.mkString(",")}")
  }

  def exchangeTwo(i: Int, j: Int): Unit = {
    val t = arr(j)
    arr(j) = arr(i)
    arr(i) = t
  }

  def partition(s: Int, e: Int, p: Int): Int = {
    if (e == s || s > p) {
      return e
    }
    var j = s - 1
    s to e foreach (k => {
      if (arr(k) < arr(p)) {
        j += 1
        exchangeTwo(j, k)
      }
    })
    exchangeTwo(j + 1, p)
    println(s"  Value of s is $s and e is $e and arr is ${arr.mkString(",")} and j is ${j + 1}")
    j + 1
  }

  def sort(s: Int, e: Int): Unit = {
    val p = e
    println(s"sort called for s is $s, e is $e and p is $p")
    if (s < e) {
      val m = partition(s, e, p)
      sort(s, m - 1)
      sort(m + 1, e)
    }

  }
}
