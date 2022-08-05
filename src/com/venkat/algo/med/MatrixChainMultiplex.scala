package com.venkat.algo.med

class Matrix(val name: String, val rows: Int, val cols: Int) {
  override def toString: String = {
    name
  }
}

object MatrixChainMultiplex {
  def main(args: Array[String]): Unit = {
    /*val a1 = new Matrix("A1", 10, 100)
    val a2 = new Matrix("A2", 100, 5)
    val a3 = new Matrix("A3", 5, 50)
    val a4 = new Matrix("A4", 50, 5)
    val a5 = new Matrix("A5", 5, 2)*/
    val a1 = new Matrix("A1", 30, 35)
    val a2 = new Matrix("A2", 35, 15)
    val a3 = new Matrix("A3", 15, 5)
    val a4 = new Matrix("A4", 5, 10)
    val a5 = new Matrix("A5", 10, 20)
    val a6 = new Matrix("A6", 20, 25)
    println(s"Min products required for 5 matrices given are ${minProducts(Array(a1, a2, a3,a4,a5,a6))}")
  }

  def minProducts(list: Array[Matrix]): (Int, String) = {
    println(s"Processing started for list ${list.mkString(",")}")
    if (list.isEmpty) return (0, "")
    if (list.length <= 1) return (0, list(0).name)
    //    if (list.length == 2) {
    //      val mat1 = list.head
    //      val mat2 = list.tail.head
    //      return mat1.rows * mat1.cols * mat2.cols
    //    }
    var minP = Integer.MAX_VALUE
    var minStr = ""
    0 to list.length - 2 foreach (i => {
      //val curP = (minProducts(list.slice(0, i + 1)) + minProducts(list.slice(i + 1, list.length))) // list(i).cols
      val firstHalf = list.slice(0, i + 1)
      val secondHalf = list.slice(i + 1, list.length)
      val (fRes, fStr) = minProducts(firstHalf)
      val (sRes, sStr) = minProducts(secondHalf)
      /*val fVal = if (firstHalf.length < secondHalf.length) fRes * list(list.length - 1).cols else fRes
      val sVal = if (secondHalf.length < firstHalf.length) sRes * list(0).rows else sRes*/
      val curP = fRes + sRes + list(0).rows * list(list.length - 1).cols * list(i + 1).rows
      if (curP < minP) {
        minP = curP
        minStr = s"($fStr$sStr)"
      }
      println(s" Processing completed for indx $i, list is ${list.mkString(",")} and the val of curP is $curP and value of minP is $minP and minStr is $minStr")
    }
      )
    println("")
    (minP, minStr)
  }
}
