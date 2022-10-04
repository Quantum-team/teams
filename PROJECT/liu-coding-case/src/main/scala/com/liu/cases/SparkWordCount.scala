package com.liu.cases

import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCount {

  def main(args: Array[String]): Unit = {
    /**
     * Scala 2.13
     * https://scala-lang.org/download/scala2.html
     *
     * Spark spark-3.2.1-bin-hadoop3.2-scala2.13
     * https://spark.apache.org/downloads.html
     */

    /*
     * 案例实践分析
     * 统计英语文本（以" "为分隔符）的单词词频出现数
     * 用现实的思维分析Wordcount案例问题应该怎么办？
     *
     * I do love you and you love me too.
     */

    // 创建 sc 对象
    val sc = new SparkContext(new SparkConf())

    /*
     * - Scala 的链式写法
     * - 注意每一次使用
     * - 在面向数据编程时，注重表达数据处理每一个环节，通常在每一个"链环"的尾端注释步骤
     * - 减少代码量有什么优缺点？尾端注释有什么优点？
     * - 生产环境中，所有的 Spark 代码将在集群中运行
     */

    val path_hdfs = "hdfs://localhost:9000/tmp/EmployeeName.csv"
    val path_local = "/Users/sasaki/Downloads/sample.rtfd"

    // I do love you and you love me too.

    /**
     * I 1
     * do 1
     * you 1
     * love 1
     * love 3
     * I 2
     * me 3
     *
     * reduce : "减少"，"质量"不变，数量减少
     * [1, 2, 1 ... 1] => [4]
     *
     * reduceByKey
     * I 3
     * love 4
     * ..
     *
     * 注意点：
     * Spark的编程范式容易，但是分析问题的过程难！
     * 养成一个好习惯
     * 分析问题要在纸上一步一步演算！
     * 如果你在纸上演算不出来，不可能编程得出结果
     * 不要把Case当成一个编程问题，要当成实际问题
     * 把实际计算过程"转化"成Spark/Flink编程算子
     *
     * Assign 1:
     * Wordcount问题演算
     *
     * 扑克牌问题演算
     *
     * 模拟数据集
     * 演算每一步如何做？
     * 每一步的中间结果是什么格式？
     * 最终结果是什么格式？
     * 一共多少步骤能处理完？
     * 每一步的结果要非常清晰
     *
     *
     * 草稿纸或txt/doc演算过程
     *
     * Assign 2:
     * 对比 map 和 flatMap 的区别
     * 自行画一个演算的过程
     *
     */
    sc.textFile("") // 函数式、链式编程、管道
      .flatMap(_.split(" "))                          //
      .map((_, 1))                                           //
      .reduceByKey(_ + _)                                    //
      .map(o => (o._2, o._1))                                //
      .collect                                               //
      .foreach(println)                                      //



    sc.stop
  }
}


