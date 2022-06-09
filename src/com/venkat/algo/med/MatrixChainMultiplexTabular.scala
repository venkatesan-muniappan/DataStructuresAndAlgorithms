package com.venkat.algo.med


object MatrixChainMultiplexTabular {
  def main(args: Array[String]): Unit = {
    val a1 = new Matrix("A1", 30, 35)
    val a2 = new Matrix("A2", 35, 15)
    val a3 = new Matrix("A3", 15, 5)
    val a4 = new Matrix("A4", 5, 10)
    val a5 = new Matrix("A5", 10, 20)
    val a6 = new Matrix("A6", 20, 25)

    /* val a1 = new Matrix("A1", 10, 100)
     val a2 = new Matrix("A2", 100, 5)
     val a3 = new Matrix("A3", 5, 50)
     val a4 = new Matrix("A4", 50, 5)
     val a5 = new Matrix("A5", 5, 2)*/

    println(s"Min products required for 5 matrices given are ${minProducts(Array(a1, a2, a3, a4, a5, a6))}")
  }

  def minProducts(list: Array[Matrix]): Unit = {
    val l = list.length
    val m = Array.fill[Int](l, l)(0)
    val s = Array.fill[Int](l, l)(0)

    2 to l foreach (p => {
      0 to l - p foreach (i => {
        val j = i + p - 1
        i until j foreach (k => {
          println(s"Processing started for i as $i, j as $j, k as $k and m(i)(j) is ${m(i)(j)} ")
          val curP = m(i)(k) + m(k + 1)(j) + list(i).rows * list(k).cols * list(j).cols
          println(s"Processing completed for i as $i, j as $j, k as $k and m(i)(j) is ${m(i)(j)} and curP is $curP\n")
          if (m(i)(j) > curP || m(i)(j) == 0) {
            m(i)(j) = curP
            s(i)(j) = k
          }
        })
      })
    })
    println(s"The minimum value of m is ${m(0)(l - 1)} all values of m is \n${m.map(_.mkString(",")).mkString("\n")}")

    println(s"Value of parenthesized is ")

    printPar(0, l - 1)
    println

    def printPar(i: Int, j: Int): Unit = {
      if (i == j)
        print(list(i).name)
      else {
        print("(")
        printPar(i, s(i)(j))
        printPar(s(i)(j) + 1, j)
        print(")")
      }
    }
  }
}
