package com.venkat.ds.med

object PriorityQueue {
  val h = Heap

  def peek(): Int = {
    h.arr(0)
  }

  def insert(elm: Int): Unit = {
    h.arr(h.size) = elm
    h.size += 1
    h.bubbleUp(h.p(h.size - 1))
  }

  def incVal(idx: Int, v: Int): Unit = {
    if (h.arr(idx) >= v) {
      println(s"The value ${h.arr(idx)} is less than $v")
    //  return
    }
    else {
      h.arr(idx)=v
      h.bubbleUp(h.p(idx))
      println(s"The heap after inc value at idx $idx to $v is ")
      h.printHeap()
    }

  }

  def main(args: Array[String]): Unit = {
    h.contstructHeap()
    insert(101) //122,10,101,8,9,12,11,1,4,2,5,6,7,3
    /* insert(12)
     insert(122)
     insert(11)*/
    h.printHeap()
    incVal(3,11)
    incVal(4,12)
  }
}
