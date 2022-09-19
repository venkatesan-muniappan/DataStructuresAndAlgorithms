package com.venkat.spark

import org.apache.spark.sql.{Row, SaveMode}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object SparkSQL extends App {
  val spark = MySparkSession.create(this.getClass.getName)
  val df = spark.sql(
    """select 'Venkat' as Name, 'Kavi' as Wife union all
      |select 'Sasi' as Name,'Deepthi' as Wife
      |""".stripMargin)
  df.show(false)

  //val personDF = spark.read.format("jdbc").option("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver").option("url", "jdbc:sqlserver://localhost:1433;databaseName=learning;Trusted_Connection=yes;trustServerCertificate=true;integratedSecurity=true").option("dbtable", "dbo.person").load()

  println(s"write to target table")
  df.write.mode(SaveMode.Overwrite).format("jdbc")
    .option("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
    .option("url", "jdbc:sqlserver://localhost:1433;databaseName=learning;trustServerCertificate=true;")
    .option("dbtable", "dbo.person")
    .option("user", "test")
    .option("password", "test1")
    .save

  import spark.implicits._

  val namesRDD = spark.sparkContext.parallelize(List(List("Ravi", "Chandini"), List("Arun", "Vovuniya")).map(list => Row(list.head, list(1))))

  println(s"another_names rdd is ${namesRDD.collect().mkString("|")}")
  val name_schema = StructType(List(StructField("Name", StringType), StructField("Wife", StringType)))
  val another_names_df = spark.createDataFrame(namesRDD, name_schema)
  println(s"Another names RDD is ")
  another_names_df.show(false)

  another_names_df.write.mode(SaveMode.Append).format("jdbc")
    .option("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
    .option("url", "jdbc:sqlserver://localhost:1433;databaseName=learning;trustServerCertificate=true;")
    .option("dbtable", "dbo.person")
    .option("user", "test")
    .option("password", "test1")
    .save

  val personDF = spark.read.format("jdbc")
    .option("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
    .option("url", "jdbc:sqlserver://localhost:1433;databaseName=learning;trustServerCertificate=true;")
    .option("dbtable", "dbo.person")
    .option("user", "test")
    .option("password", "test1")
    .load

  println("select from dbo.person table is ")
  personDF.show(false)


}
