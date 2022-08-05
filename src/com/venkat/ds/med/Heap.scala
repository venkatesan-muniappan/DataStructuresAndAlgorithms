package com.venkat.ds.med

import scala.annotation.tailrec

object Heap {
  val arr: Array[Int] = new Array[Int](100) //Array(9, 8, 7, 6, 5, 4, 3, 2, 1)
  var size = 0

  val p: Int => Int = (i: Int) => if (i < 1) -1 else
    (i - 1) / 2
  val l: Int => Int = (i: Int) => {
    val ret = 2 * i + 1
    //if (ret > arr.length - 1) 0 else
    ret
  }
  val r: Int => Int = (i: Int) => {
    val ret = 2 * i + 2
    //if (ret > arr.length - 1) 0 else
    ret
  }


  @tailrec
  def heapify(i: Int): Unit = {
    var la = i
    if (l(i) < size && arr(l(i)) > arr(la)) la = l(i)
    if (r(i) < size && arr(r(i)) > arr(la)) la = r(i)
    if (i != la) {
      val t = arr(la)
      arr(la) = arr(i)
      arr(i) = t
      //if (p(i) >= 0)
      heapify(la)
      // heapify(i)
    }
  }

  def printHeap(): Unit = {
    println(s"${arr.slice(0, size).mkString(",")}")
  }

  def extractMax(): Int = {
    //We need to return the max element and remove that element from the heap.
    // println(s"extract max called and the size is $size and heap is ")
    //printHeap()
    val ret = if (size > 0) {
      //size -= 0
      arr(0)
    } else -1

    if (size > 1) {
      arr(0) = arr(size - 1)
      heapify(0)
      size -= 1
    } else if (size == 1)
      size = 0

    ret
  }

  @tailrec
  def bubbleUp(i: Int): Unit = {
    if (i == -1) return
    var la = i
    if (l(i) < size && arr(l(i)) > arr(la)) la = l(i)
    if (r(i) < size && arr(r(i)) > arr(la)) la = r(i)
    if (i != la) {
      val t = arr(la)
      arr(la) = arr(i)
      arr(i) = t
      bubbleUp(p(i))
    }
  }

  def contstructHeap(): Unit = {
    0 to 9 foreach (i => {
      arr(i) = i + 1
      size += 1
    })

    size / 2 to 0 by -1 foreach (i => {
      heapify(i)
      //println(s"After heapify of $i the value of arr is ${printHeap()}")
    })
  }

  def main(args: Array[String]): Unit = {
    /*arr.indices foreach (i => {
      println(s"parent of ${arr(i)} is ${arr(p(i))} and i is $i")
      println(s"  left of ${arr(i)} is ${arr(l(i))}")
      println(s"  right of ${arr(i)} is ${arr(r(i))}")
    })*/
    contstructHeap()
    val sortArr = new Array[Int](size)

    size - 1 to 0 by -1 foreach (i => {
      sortArr(i) = extractMax()
    })
    println(s"Sorted Array is ${sortArr.mkString(",")}")
    //println(s"After heapify arr is ${printHeap()}")
  }

}
