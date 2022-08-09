package com.venkat.leetcode.easy

import scala.collection.mutable

object UniqueEmailAddress {
  /**
   * 929. Unique Email Addresses
   */
  def main(args: Array[String]): Unit = {
    //val listEmails = List("test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com")
    val listEmails = List("a@leetcode.com","b@leetcode.com","c@leetcode.com")
    val mSet = mutable.Set[String]()
    listEmails.foreach(email => {
      val emailGroups = email.split("@")
      val localGroup = emailGroups(0)
      val domain = emailGroups(1)
      mSet.add(localGroup.replace(".", "").split("\\+")(0) + "@" + domain)
    })
    println(s"List of mSet values are ${mSet.mkString(",")}")
  }
}
