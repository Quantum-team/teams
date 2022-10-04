package com.liu.cases

/**
 *
 * @Matthew
 *
 * Java/Scala/Hadoop/Spark大数据课程
 *
 */
class ScalaVsJavaCase

object ScalaVsJavaCase {

  /**
   * 学习 Scala 的唯一目的
   * 无缝操作 Spark
   */

  /**
   * Scala / Java 相同（相似）点
   *
   * 为什么长得很像？
   */
  /*
   * - Scala 是一门完成基于 JVM 的面向对象&函数式的开放性语言
   * - Scala 与 Java 在语法规则、特性等有很多相似之处
   * - 接口可以无缝互调
   * - 在项目中可以进行"混编"，通常操作 Spark 的模块由 Scala 语言完成，极大减少代码量
   * - 在学习中必须注意语法规则的区别点
   * - 对比学习，效率翻倍
   */

  /**
   * - 比较方法定义
   * 换行方法
   */
  def enter: Unit = println

  /**
   * - 比较 main 方法
   */
  def main(args: Array[String]): Unit = {

    /**
     * - 比较打印
     */
    println("Hello, world!")


    /**
     * - 比较方法调用
     */
    enter


    /**
     * - 比较创建变量 不变-val(99%) / 可变-var(1%)，在代码情境中体验"不变"的好处
     *
     * val 盒子
     * var 盒子
     *
     * - 比较 数组 / List 创建
     */

    val arrayInt = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // arrayInt = Array() // 测试 val
     1 to 10
     1 until 10


    /**
     * - 比较 for / foreach
     */

    /**
     * 思考两种 for 循环的方式最明显的、本质的区别是？
     */
    /**
     * - Scala 中万物皆"不变"
     * - Scala 中万物皆可"返回"，不存在 void
     * - 注意返回空与无返回的现实区别
     * - 必须返回性是链式编程的基础
     *
     * 说明什么？
     * 做事有结果，他人拿到你的不变的结果，才能继续下一步
     * 不要做个没结果（不靠谱）的人！
     */
    val list = for (o <- arrayInt) yield o * -1

    /**
     * 使用函数式算子 function
     * map，每个都"整"一下
     */
    arrayInt.map(_ * -1)

    list foreach println

    enter

    arrayInt.filter(_ > 5).foreach(println)



    /**
     * 学习 Tips
     */
    /**
     * - 注意函数式编程语言与自然语言在语义上的"相似"可读性
     * - Scala 是一门非常"简单"的语言，像说话一样编程
     * - 不要"死记"代码，多使用情境语义的方式来"表达"代码
     */

  }
}

