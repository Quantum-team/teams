package com.liu.cases

import com.liu.cases.JavaVsScalaLambdaCase

object ScalaLambdaCase {

  def main(args: Array[String]): Unit = {

    val caze = new JavaVsScalaLambdaCase

    caze.call(new IPureFunction {
      override def echo(content: String): String = content
    })

    caze.call(new INonPureFunction {

      override def say(): Unit = println("你好！")

      override def echo(content: String): String = content
    })

  }
}
