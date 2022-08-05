package com.venkat.algo.med

import scala.collection.mutable

object RodCutt {
  val pMap = Map(1 -> 1, 2 -> 3, 3 -> 3, 4 -> 4, 5 -> 4)

  def rec_rod_cut(length: Int, cMap: mutable.Map[Int, Int]): Int = {
    println(s" rod_cut started for the length $length")
    if (length <= 0) return 0
    if (cMap.contains(length)) {
      println(s"Found the key $length in cMap. cMap is ${cMap.mkString(",")}")
      return cMap(length)
    }
    var maxProfit = 0
    1 to length foreach (i => {
      println(s"  current maxProfit is $maxProfit.i is $i, We are going to call rod_cut for ${length - i}")
      maxProfit = Math.max(maxProfit, pMap(i) + rec_rod_cut(length - i, cMap))
    })
    cMap.put(length, maxProfit)
    maxProfit
  }

  def iter_rod_cut(length: Int): Int = {
    val costArr = Array.fill[Int](length + 1)(0)
    1 to length foreach (i => {
      var max = 0
      println(s"Going to find the max profit for $i")
      i to 1 by -1 foreach (j => {
        println(s" For the current iteration $j, max is $max")
        max = Math.max(max, pMap(j) + costArr(i - j))
      })
      costArr(i) = max
      println(s"max profit for $i is $max and costArr is ${costArr.mkString(",")}\n")
    })
    costArr(length)
  }

  def main(args: Array[String]): Unit = {
    //println(s"recursive solution for the rod of length 5 is ${rec_rod_cut(5, mutable.Map[Int, Int]())}")
    println(s"iterative solution for the rod of length 5 is ${iter_rod_cut(5)}")
  }
}
