package com.venkat.ds.med

import scala.annotation.tailrec

class Node(val key: Int, var left: Node = null, var right: Node = null, var parent: Node = null)

object BinarySearchTree {
  /**
   * Below are the features that we need to implement.
   * insert
   * in order traversal
   * Searching
   * Min
   * Max
   * Predecesor
   * Successor
   * Deletion
   */
  var root: Node = null

  def insert(node: Node): Unit = {
    if (root == null) {
      root = node
      return
    }
    var temp = root
    var prev: Node = root
    while (temp != null) {
      prev = temp
      if (node.key < temp.key)
        temp = temp.left
      else
        temp = temp.right
    }
    if (node.key < prev.key)
      prev.left = node
    else
      prev.right = node
    node.parent = prev
  }

  //inorder Traversal
  def printBST(node: Node): Unit = {
    if (node != null) {
      printBST(node.left)
      print(s" -> ${node.key}")
      printBST(node.right)
    }
  }

  def search(key: Int): Node = {
    //println(s"Searching for $key started")
    var temp = root
    while (temp != null) {
      if (temp.key == key) {
        //  println(s"The key $key is found")
        return temp
      }
      else if (key < temp.key)
        temp = temp.left
      else temp = temp.right
    }
    null
    //println(s"The key $key not found")
  }

  @tailrec
  def minimum(node: Node): Node = {
    if (node.left != null)
      minimum(node.left)
    else
      node
  }

  def maximum(node: Node): Int = {
    var temp = node
    while (temp.right != null) {
      temp = temp.right
    }
    temp.key
  }

  def successor(key: Int): Int = {
    var p1 = search(key)
    if (p1 != null && p1.right != null)
      return minimum(p1.right).key

    var p2 = if (p1 != null) p1.parent else null
    while (p2 != null && p2.right == p1) {
      p1 = p2
      p2 = p2.parent
    }
    if (p2 != null)
      p2.key
    else 0
  }

  def predecessor(key: Int): Int = {
    val p1 = search(key)
    if (p1 != null && p1.left != null)
      return maximum(p1.left)
    val p2 = p1.parent
    if (p2 != null && p2.right == p1)
      p2.key
    else 0
  }

  def transplant(u: Node, v: Node): Unit = {
    if (u == root) {
      root = v
    }
    else if (u.parent.left == u)
      u.parent.left = v
    else
      u.parent.right = v
    if (v != null)
      v.parent = u.parent
  }

  def delete(elem: Int): Unit = {
    val z = search(elem)
    if (z.left == null)
      transplant(z, z.right)
    else if (z.right == null)
      transplant(z, z.left)
    else {
      val m = minimum(z.right)
      if (m != z.right) {
        transplant(m, m.right)
        m.right = z.right
        m.right.parent = m
      }
      transplant(z, m)
      m.left = z.left
      m.left.parent = m
    }
  }

  def main(args: Array[String]): Unit = {
    insert(new Node(10))
    insert(new Node(20))
    insert(new Node(2))
    insert(new Node(5))
    insert(new Node(15))
    insert(new Node(22))
    insert(new Node(18))
    printBST(root)
    println("")
    search(10)
    search(22)
    search(23)
    search(15)
    search(19)
    //    println(s"Minimum is ${minimum(root).key}.")
    //    println(s"Maximum is ${maximum(root)}.")
    //
    //    println(s"Successor of 5 is ${successor(5)}")
    //    println(s"Successor of 10 is ${successor(10)}")
    //    println(s"Successor of 22 is ${successor(22)}")
    //    println(s"Successor of 18 is ${successor(18)}")
    //
    //    println(s"Predessor of 2 is ${predecessor(2)}")
    //    println(s"Predessor of 22 is ${predecessor(22)}")
    //    println(s"Predessor of 10 is ${predecessor(10)}")
    //    println(s"Predessor of 20 is ${predecessor(0)}")

    printBST(root)
    println("")
    delete(20)
    printBST(root)
    println("")

    delete(2)
    printBST(root)
    println("")

    delete(10)
    println(s"root is ${root.key}")
    printBST(root)
    println("")
  }
}
