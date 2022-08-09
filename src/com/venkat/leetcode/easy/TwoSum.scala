package com.venkat.leetcode.easy

import scala.collection.mutable

object TwoSum {

  /**
   * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
   * You may assume that each input would have exactly one solution, and you may not use the same element twice.
   * You can return the answer in any order.
   */
  def main(args: Array[String]): Unit = {

    val nums = Array(1, 2, 3, 4, 5, 6, 7, 2, 3)
    //val arr2 = Array.fill[String](10, 10)("Empty")
    //arr2.map(el => el.toList).toList.foreach(println)
    val target = 11
    //    val sortArr = nums.sorted
    //    val slArr = sortArr.sliding(2, 1)
    //    val res = slArr.filter(el => el(0) + el(1) == target).toList.head
    //    val finalRes = res.map(el => nums.indexOf(el))

    //println(s"${finalRes.mkString(",")}")

    val diffMap = mutable.Map[Int, Int]()
    nums.foreach(el => {
      val df = target - el
      if (diffMap.contains(df)) {
        println(s"${nums.indexOf(el)}, ${diffMap(df)}")
        return
      } else
        diffMap.put(el, nums.indexOf(el))
    })
    println(s"Not found")
  }

}
