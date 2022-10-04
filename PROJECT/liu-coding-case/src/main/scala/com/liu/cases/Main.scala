package com.liu.cases

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
 * @author Matthew
 *
 * Slide:
 * 4, 19, 29, 34,
 * *42 通过 Stage 描述现实问题（数据需求）
 * 61, 63, *64~67
 * *71~73
 */
object Main extends App {

  val spark: SparkSession = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
    .getOrCreate();
  val sc: SparkContext = spark.sparkContext

  spark.sparkContext.setLogLevel("INFO")

  /**
   * 从 text 文件创建 RDD
   */
  val rddFile: RDD[String] = sc.textFile("/Users/sasaki/Downloads/sample.rtfd")


  // ==================================================================================================================


  /**
   * map
   * flatMap
   * filter
   * distinct
   */


  /**
   * RDD map 切分
   * map[U](f: T => U)(implicit evidence$3: ClassTag[U]): RDD[U]
   */
  val rddWordsSeq: RDD[Array[String]] = rddFile.map(_.split(" "))
  rddFile.map(_.toUpperCase).collect

  /**
   * RDD flatMap 切分并摊平，与 map 区别
   * def flatMap[U](f: T => TraversableOnce[U])(implicit evidence$4: ClassTag[U]): RDD[U]
   */
  val rddWords: RDD[String] = rddFile.flatMap(_.split(" "))
  rddWords.count

  /**
   * 从集合创建 RDD
   * parallelize[T](seq: Seq[T], numSlices: Int = {})(implicit evidence$1: ClassTag[T]): RDD[T]
   */
  val rdd_1: RDD[String] = sc.parallelize(List("I love you", "and you love me."))
  val rdd_2: RDD[Array[String]] = rdd_1.map(_.split(" "))
  val rdd_3: RDD[String] = rdd_1.flatMap(_.split(" "))

  /**
   * CASE Result
   *
   * rdd_2.map
   * Array[(Array[String], Int)] =
   * Array((Array(I, love, you),3), (Array(and, you, love, me.),4))
   *
   * rdd_3.map
   * Array[(String, Int)] =
   * Array((I,1), (love,4), (you,3), (and,3), (you,3), (love,4), (me.,3))
   */
  rdd_2.map(o => (o, o.length)).collect
  rdd_3.map(o => (o, o.length)).collect

  /**
   * flatMap vs map
   *
   * RDD[String]    ("I love you", "and you love me.")
   * flatMap       （I, love, you, and, you, love, me.)
   * map            Array(I, love, you), Array(and, you, love, me.)
   */


  // Array[String] = Array(I love you)
  rdd_3.filter(_.contains("I")).collect

  // Array(I, love, me., you, and)
  rdd_3.distinct.collect


  // ==================================================================================================================


  /**
   * ***mapPartitions
   * ***mapPartitionsWithIndex
   *
   * 注意比较理解 map 和 mapPartitions
   */


  /**
   * mapPartitions[U](
   * f: Iterator[T] => Iterator[U],
   * preservesPartitioning: Boolean = {}
   * )(implicit evidence$6: ClassTag[U]): RDD[U]
   *
   * map[U](f: T => U)(implicit evidence$3: ClassTag[U]): RDD[U]
   *
   * 由泛型推断执行过程 mapPartitions 与 map 相似
   */


  val rdd_5: RDD[Int] = sc.parallelize(1 to 9, 3)
  rdd_5.partitions.size // 3

  // :paste
  rdd_5
    .mapPartitions(o => (List(o.next).iterator))
    /**
     * Array[Int] = Array(1, 4, 7)
     */
    .collect


  /**
   * o 的类型 Iterator[Int]，表示分区的迭代器，指向首位索引
   *
   * p0 : 1 2 3
   * p1 : 4 5 6
   * p2 : 7 8 9
   *
   * mapPartitions是在每个 partition 内部进行 map
   * 所以需要借助 iterator
   */


  // :paste
  val rdd_8 =
    sc.parallelize(List("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"), 3)
  rdd_8
    /**
     * Similar to mapPartitions, but good part is
     * that you have index to see the partition position.
     */
    .mapPartitionsWithIndex((index, iterator) => {
      iterator.toList.map(x => x + "=>" + index).iterator
    })
    /**
     * Array[String] =
     *  Array(One=>0, Two=>0, Three=>0, Four=>1, Five=>1, Six=>1, Seven=>2, Eight=>2, Nine=>2)
     */
    .collect


  /**
   * sample(withReplacement, fraction, seed)
   *
   * Generates a fraction RDD from an input RDD.
   * Note that second argument fraction doesn't represent the fraction of actual RDD.
   * It actually tells the probability of each element in the dataset getting selected for the sample.
   * Seed is optional.
   * First boolean argument decides type of sampling algorithm.
   */

  val rdd_9 = sc.parallelize(1 to 10)
  /**
   * Array[Int] = Array(1, 5, 5, 7, 10, 10)
   * Array[Int] = Array(5, 5, 10)
   * Array[Int] = Array(6, 7, 10)
   * Array[Int] = Array(1, 4)
   */
  rdd_9.sample(true, .4).collect


  /**
   * union(otherDataset)
   * Similar to SQL union, but it keeps duplicate data.
   */

  val rdd_10 = sc.parallelize(List("apple", "orange", "grapes", "mango", "orange"))
  val rdd_11 = sc.parallelize(List("red", "green", "yellow"))

  // Array[String] = Array(apple, orange, grapes, mango, orange, red, green, yellow)
  rdd_10.union(rdd_11)

  // Array[String] = Array(red, green, yellow, apple, orange, grapes, mango, orange)
  rdd_11.union(rdd_10)


  /**
   * intersection(otherDataset)
   *
   * 返回两个集合中共同的值
   */

  val rdd_12 = sc.parallelize(-5 to 5)
  val rdd_13 = sc.parallelize(1 to 10)

  // Array[Int] = Array(4, 1, 5, 2, 3)
  rdd_12.intersection(rdd_13).collect


  // ==================================================================================================================


  /**
   * - groupByKey
   * - reduceByKey
   * - ***aggregateByKey
   *
   * 注意比较理解 reduceByKey 和 aggregateByKey
   */


  // :paste
  rdd_1
    .flatMap(_.split(" "))
    .map((_, 1))
    .groupByKey

    /**
     * Array[(String, Iterable[Int])] =
     * Array((I,CompactBuffer(1)),
     * (love,CompactBuffer(1, 1))...
     */
    .collect

  // :paste
  rdd_1
    .flatMap(_.split(" "))
    .map((_, 1))
    .reduceByKey(_ + _)

    /**
     * Array[(String, Int)] =
     * Array((I,1), (love,2), (me.,1), (you,2), (and,1))
     */
    .collect


  val rdd_7: RDD[String] = sc.parallelize(List("1-A", "2-C", "3-D", "1-A", "2-B", "5-F", "4-A", "2-A"))
  val seqEmpty: Seq[String] = Seq.empty[String]

  // :paste
  rdd_7.map { o =>
    val array = o.split("-")

    /**
     * (1, A)
     * (2, C)
     * (3, D)
     * ...
     */
    (array(0), array(1))
  }
    .aggregateByKey(seqEmpty /* 初始化的空集合 */)({
      /**
       * combining function
       * 用于收集右边集合的值元素，在当前分区进行
       * 如果需要去重可使用Set集合
       * 注意为了使用动态集合添加元素的方法，使用 mutable 集合
       * 避免 immutable 集合过多产生对象
       * 为了便于识别类型，参数命名很关键，注意参数类型与命名绑定
       */
      (seq, o) => seq.+:(o)

    }, {
      /**
       * merging function
       * 在分区间进行相同 key 的集合二次合并
       */
      (seq, seq_) => seq.++:(seq_)
    })
    /**
     * Array[(String, Seq[String])] =
     * Array(
     *  (4,ArrayBuffer(A)),
     *  (5,ArrayBuffer(F)),
     *  (1,List(A, A)),
     *  (2,List(A, B, C)),
     *  (3,ArrayBuffer(D))
     * )
     */
    .collect

  // :paste
  rdd_7.map { o =>
    val array = o.split("-")
    (array(0), array(1))
  }
    .aggregateByKey(0)({
      /**
       * 分区内按 key 相同的值进行累加
       */
      (i, __) => i + 1
    }, {
      /**
       * 分区之间数量相加
       * (i, i_) => i + i_ 等价写法 (_ + _)
       */
      (_ + _)
    })

    /**
     * Array[(String, Int)] =
     * Array((4,1), (5,1), (1,2), (2,3), (3,1))
     */
    .collect


  /**
   * Datasource
   * "1-A", "2-C", "3-D", "1-A", "1-C"
   * "2-B", "5-F", "4-A", "2-A"
   *
   * Node A (1-A A)
   * Node B (1-C)
   * 1 A A C
   *
   *
   * Result 1
   * 4 -> A
   * 5 -> F
   * 1 -> A A
   * 2 -> A B C
   * 3 -> D
   *
   * Result 2
   * 4 -> 1
   * 5 -> 1
   * 1 -> 2
   * 2 -> 3
   * 3 -> 1
   */


  /**
   * - aggregateByKey(zeroValue)(seqOp, combOp, [numPartitions])
   * 是 reduceByKey 的更“高级”抽象
   *
   *  1. An initial ‘zero’ value that will not effect the total values to be collected.
   *     For example if we were adding numbers the initial value would be 0.
   *     Or in the case of collecting unique elements per key,
   *     the initial value would be an empty set.
   *  2. A combining function accepting two parameters.
   *     The second parameter is merged into the first parameter.
   *     This function combines/merges values within a partition.
   *  3. A merging function function accepting two parameters.
   *     In this case the parameters are merged into one.
   *     This step merges values across partitions.
   */


  /**
   * - RDD 的窄依赖和宽依赖
   * - Narrow transformation & Wide transformation
   *
   * - narrow transformation
   * map
   * filter
   *
   * all the elements that are required to
   * compute the records in single partition
   * live in the single partition of parent RDD.
   * A limited subset of partition is used to
   * calculate the result.
   *
   * - wide transformation
   * groupByKey
   * reduceByKey
   * aggregateByKey
   *
   * all the elements that are required to
   * compute the records in the single partition
   * may live in many partitions of parent RDD.
   * The partition may live in many partitions of parent RDD.
   */

  /**
   * - RDD 的转换和动作
   * - Transformation & Action
   *
   * Transformations are lazy in nature i.e.
   * nothing happens when the code is evaluated.
   * Meaning actual execution happens only when code is executed.
   * RDDs are computed only when an action is applied on them.
   * Also called as lazy evaluation.
   * Spark evaluates the expression only when its value is needed by action.
   *
   * When you call an action, it actually triggers transformations to act upon RDD, dataset or dataframe.
   * After that RDD, dataset or dataframe is calculated in memory.
   * In short, transformations will actually occur only when you apply an action.
   * Before that it’s just line of evaluated code.
   */


  // ==================================================================================================================


  /**
   * - Action
   *
   * collect
   * count
   * first
   * take
   * takeSample
   * takeOrdered
   * countByKey
   * saveAsTextFile
   * foreach
   */

  val rdd_14 = sc.parallelize(1 to 15)

  rdd_14.collect                  // Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
  rdd_14.first                    // Int = 1
  rdd_14.count                    // Long = 15
  rdd_14.reduce((o, o_) => o + o_)// Int = 120
  rdd_14.reduce(_ + _)            // Int = 120
  rdd_14.take(4)
  /**
   * takeSample(withReplacement: Boolean, num: Int, seed: Long = {}): Array[T]
   *
   * It returns the random sample of size n.
   * Boolean input is for with or without replacement.
   */
  rdd_14.takeSample(false, 4) // Array[Int] = Array(1, 4, 5, 11)
  rdd_14.takeSample(false, 4) // Array[Int] = Array(6, 7, 4, 8)
  rdd_14.takeSample(true, 4)  // Array[Int] = Array(6, 13, 13, 1)

  /**
   * def takeOrdered(num: Int)(implicit ord: Ordering[T]): Array[T]
   */
  rdd_14.takeOrdered(4) // Array[Int] = Array(1, 2, 3, 4)
  rdd_14.top(4)         // Array[Int] = Array(15, 14, 13, 12)

  val rdd_15: RDD[(String, Int)] = sc.parallelize(Seq("Apple", "Banana", "Grapes", "Oranges", "Grapes", "Banana")).map(k => (k, 1))

  rdd_15.countByKey() // scala.collection.Map[String,Long] = Map(Grapes -> 2, Oranges -> 1, Banana -> 2, Apple -> 1)
  rdd_15.saveAsTextFile("/Users/sasaki/Downloads/sample_text")
  rdd_15.coalesce(1, false).saveAsTextFile("/Users/sasaki/Downloads/sample_text")

  rdd_15 foreach println
  rdd_15 foreach println


  // ==================================================================================================================


  //  case class Contact(name: String, phone: String)
  //
  //  case class Person(name: String, age: Int, contacts: Seq[Contact])
  //
  //  val records = (1 to 100).map { i =>
  //    Person(s"name_$i", i, (0 to 1).map { m =>
  //      Contact(s"contact_$m", s"phone_$m")
  //    })
  //  }
  //  // Write ORC
  //  spark
  //    .createDataFrame(sc.parallelize(records))
  //    .write
  //    .format("orc")
  //    .save("people")
  //
  //  // Read ORC
  //  val people = spark.read.format("orc").load("people.json")

}
