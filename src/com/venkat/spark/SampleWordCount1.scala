package com.venkat.spark

object SampleWordCount1 {
  def main(args: Array[String]) = {
    //    val txt =
    //      """
    //        |This is a test
    //        |program to demonstrate
    //        |spark ability to do
    //        |word count example. This is also a nicer paragraph for spark.
    //        |""".stripMargin
    //    println(s"text is ${txt} and split version is ")
    //    val c = txt.split("\n").toList
    //    println(s"c is ${c}")

    val spark = MySparkSession.create(this.getClass.toString)
    val sc = spark.sparkContext

    val strRDD = sc.textFile("C:\\Users\\mvenk\\OneDrive\\Desktop\\MyFiles\\Projects\\Texts\\tortoise_hare.txt")
    println(s"Original text is ${strRDD.collect().mkString(",")}")

    val kvRDD = strRDD.flatMap(_.split(" ")).filter(word => word.length > 3).map(word => (cleanWord(word), 1))
    println(s"kvRDD is ${kvRDD.collect().mkString(",")}")

    val kvRDD2 = kvRDD.reduceByKey(_ + _).sortBy(_._2, false)
    //    println(s"The result of word count is ${kvRDD2.collect.mkString("|")}")
    println(s"The result of word count is ")
    kvRDD2.coalesce(1).saveAsTextFile("C:\\Users\\mvenk\\OneDrive\\Desktop\\MyFiles\\Projects\\Texts\\tortoise_hare_output.txt")
  }

  def cleanWord(input: String): String = {
    val regex = "\\w+".r
    regex.findFirstIn(input).getOrElse("")
  }
}
