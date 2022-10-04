package com.liu.cases

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, SparkSession}

import scala.language.postfixOps

/**
 * 使用该脚本，模拟避免 spark-shell
 * 不能初始化 HiveContext 以致不能创建 Hive 表
 * 不能输出数据到 HDFS 等问题
 */
object SparkSql2HiveCTL extends App {


  // :paste
  val spark: SparkSession = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
    .config("spark.sql.warehouse.dir", "hdfs://localhost:9000/user/hive/warehouse/")
    .enableHiveSupport()
    .getOrCreate()
  val sc: SparkContext = spark.sparkContext

  spark.sparkContext.setLogLevel("DEBUG")

  spark.sqlContext.setConf("hive.exec.dynamic.partition", "false")
  spark.sqlContext.setConf("hive.exec.dynamic.partition.mode", "nonstrict")

  // message:Hive Schema version 1.2.0 does not match metastore's schema version 2.3.0 Metastore is not upgraded or corrupt)
  spark.sqlContext.setConf("hive.metastore.schema.verification", "false")

  /*
   *
   * 1001,PEN,Pen Red,5000,1.23
   * 1002,PEN,Pen Blue,8000,1.25
   * 1003,PEN,Pen Black,2000,1.25
   * 1004,PEC,Pencil 2B,10000,0.48
   * 1005,PEC,Pencil 2H,8000,0.49
   * 1006,PEC,Pencil HB,0,9999.99
   *
   * hadoop dfs -copyFromLocal /Applications/hadoop-2.7.0/sbin/product_csv /tmp
   *
   * hadoop dfs -cat /tmp/product_csv
   *
   */

  // Schema Class
  case class Product(
                      productid: Int,
                      code: String,
                      name: String,
                      quantity: Int,
                      price: Float
                    )

  val hdfs = "hdfs://localhost:9000"
  val file_path = s"${hdfs}/tmp/product_csv"
  val rdd_1 = sc.textFile(file_path)

  // 创建 DF 正确方式
  val rdd_3: RDD[Product] = rdd_1.map { o =>
    val array = o.split(",")
    Product(
      array(0).toInt,
      array(1),
      array(2),
      array(3).toInt,
      array(4).toFloat
    )
  }
  val df_3 = spark.createDataFrame(rdd_3)
  df_3.printSchema()

  // :paste
  df_3
    .write //
    .mode(SaveMode.Overwrite) //
    .format("orc") //
    .saveAsTable("t_orc_product")


  spark stop
}