package com.liu.cases

import java.io.{File, PrintWriter}
import scala.io.Source

object MockBigFileCase {

  def main(args: Array[String]): Unit = {

    val file = Source.fromFile("/Applications/hadoop-2.7.0/logs/hadoop-Sasaki-datanode-_Nicholas_.local.log")
    val lines = file.getLines().toSeq
    //    println(file.size) // 4668536 ~ 4m

    val file_ = new File("/Users/sasaki/hadoop-Sasaki-datanode-_Nicholas_.local_10m.log")
    val writer = new PrintWriter(file_)
    val builder = new StringBuilder

    for (i <- 1 to 5) {
      lines.foreach(builder.append(_))
      println(i + " " + builder.size)
    }

    writer.write(builder.toString())
    writer.close()
  }
}
