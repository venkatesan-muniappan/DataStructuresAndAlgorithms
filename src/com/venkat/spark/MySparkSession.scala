package com.venkat.spark

import org.apache.spark.sql.SparkSession

object MySparkSession {
  def create(name: String): SparkSession = {
    System.setProperty("hadoop.home.dir", "C:\\hadoop")
    System.setProperty("spark.files.overwrite", "true")
    val spark = SparkSession.builder().master("local[3]").appName(name).getOrCreate()
    println("SparkSession is created")
    spark
  }
}
