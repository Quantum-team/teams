package com.liu.cases

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

import java.util.Properties

object MapReduceWordCountByScala extends App {

  val spark: SparkSession = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
    .getOrCreate();
  val sc: SparkContext = spark.sparkContext

  spark.sparkContext.setLogLevel("INFO")

  val path = "hdfs://localhost:9000/tmp/input2"

  // :paste
  val rdd = sc.textFile(path).map(
      _.replace("“", " ")
      .replace("”", " ")
      .replace("\"", " ")
      .replace(".", " ")
      .replace("’", " ")
      .replace("‘", " ")
      .replace(",", " ")
      .replace(";", " ")
      .replace("-", " ")
      .replace("_", " ")
      .replace("(", " ")
      .replace(")", " ")
      .replace(">", " ")
      .replace("<", " ")
      .replace("=", " ")
      .replace("!", " ")
      .replace("\\", " ")
      .replace("/", " ")
      .replace("|", " ")
      .replace("{", " ")
      .replace("}", " ")
      .replace("[", " ")
      .replace("]", " ")
      .replace("_", " ")
      .replace(":", " ")
      .replace("?", " ")
      .replace("@", " ")
      .replace("#", " ")
      .replace("%", " ")
      .replace("&", " ")
      .replace("*", " ")
      .replace("$", " ")
  ).flatMap(_.split(" "))
    .map((_, 1))
    .reduceByKey(_ + _)

  rdd.take(20)

  val rdd_2 = rdd.sortBy(o => o._2, false)

  rdd_2.take(20)

  spark.close()
}
