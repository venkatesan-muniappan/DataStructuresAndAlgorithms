package com.venkat.leetcode.easy

object MinDiff2Scores {
  /**
   * 1984. Minimum Difference Between Highest and Lowest of K Scores
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val arr = Array(3, 2, 4, 5, 1, 6, 3, 4, 5)
    val n = 3
    var min = Integer.MAX_VALUE
    val possibleArrays = arr.sorted.sliding(n, 1)
    possibleArrays.foreach(list => {
      val m1 = list.min
      val m2 = list.max
      val diff = m2 - m1
      if (diff < min)
        min = diff
    })
    println(min)
  }
}
