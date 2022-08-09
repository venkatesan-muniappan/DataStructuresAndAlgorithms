package com.venkat.leetcode.easy

import scala.collection.mutable

object Largest3SameDigitNumber {
  /**
   * 2264. Largest 3-Same-Digit Number in String
   */
  def main(args: Array[String]): Unit = {
    val num = "6777133339"
    val lb = mutable.ListBuffer[String]()
    val splitArr = num.toCharArray.sliding(3, 1).toList
    println(s"splitArr is ${splitArr.map(el => el.toList).mkString(",")}")
    splitArr.foreach(elem => {
      if (elem.toSet.size == 1) {
        lb += elem.mkString("")
      }
    })
    println(s"lb is ${lb.mkString(",")}")
    val max = lb.map(elm => elm.toInt).max
    println(s"Max is ${max}")
  }

}
