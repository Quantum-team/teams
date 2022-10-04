package com.liu.cases

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, SparkSession}

import scala.language.postfixOps

/**
 * @author Matthew
 *
 * CCA 175 Spark Q&A Sample * 10
 */
object CCAExamCase extends App {

  // :paste
  val spark: SparkSession = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
//    .config("spark.sql.warehouse.dir", "/user/hive/warehouse/")
//    .enableHiveSupport()
    .getOrCreate()
  val sc: SparkContext = spark.sparkContext

  spark.sparkContext.setLogLevel("INFO")


  // TODO ======================================  Question 1  ======================================


  val keysWithValuesList = Array(
    "foo=A", "foo=A", "foo=A", "foo=A",
    "foo=B", "bar=C", "bar=D", "bar=D"
  )

  val data = sc.parallelize(keysWithValuesList)
  val kv = data.map(_.split("=")).map(v => (v(0), v(1))).cache()
  val initialCount = 0

  val countByKey = kv.aggregateByKey(initialCount)(addToCounts, sumPartitionCounts)


  // TODO
  //  Now define two functions (addToCounts, sumPartitionCounts)
  //  such, which will produce following results.
  //  /
  //  Output 1
  //  res3: Array[(String, Int)] = Array((foo,5), (bar,3))


  // TODO
  //  Answer
  val addToCounts = (i: Int, __ : String) => i + 1
  val sumPartitionCounts = (i: Int, i_ : Int) => i + i_

  countByKey.collect

  import scala.collection._
  val initialSet = scala.collection.mutable.HashSet.empty[String]
  val uniqueByKey = kv.aggregateByKey(initialSet)(addToSet, mergePartitionSets)


  // TODO
  //  Now define two functions (addToSet, mergePartitionSets) such,
  //  which will produce following results.
  //  /
  //  Output 2
  //  Array[(String, scala.collection.mutable.HashSet[String])] =
  //  Array((foo,Set(B, A)), (bar,Set(C, D)))


  // TODO
  //  Answer
  val addToSet = (set: mutable.HashSet[String], o: String) => set + o
  val mergePartitionSets =
    (set: mutable.HashSet[String], set_ : mutable.HashSet[String]) => set ++: set_ // 注意两个集合的合并方法！
  val mergePartitionSets_ =
    (set: mutable.HashSet[String], set_ : mutable.HashSet[String]) => set ++ set_ // 注意两个集合的合并方法！

  uniqueByKey.collect


  // TODO ======================================  Question 2  ======================================


  // TODO
  //   1001,PEN,Pen Red,5000,1.23
  //   1002,PEN,Pen Blue,8000,1.25
  //   1003,PEN,Pen Black,2000,1.25
  //   1004,PEC,Pencil 2B,10000,0.48
  //   1005,PEC,Pencil 2H,8000,0.49
  //   1006,PEC,Pencil HB,0,9999.99
  //   /
  //   hadoop dfs -copyFromLocal /Applications/hadoop-2.7.0/sbin/product_csv /tmp
  //   hadoop dfs -cat /tmp/product_csv

  case class Product(
                      productid: Int,
                      code: String,
                      name: String,
                      quantity: Int,
                      price: Float
                    )

  val file_path = "hdfs://localhost:9000/tmp/product_csv"
  val rdd_1 = sc.textFile(file_path)

  val df_1 = spark.read.text(file_path)
  val ds_1 = spark.read.textFile(file_path)

//  rdd_1 count

  // TODO
  //  注意带 schema 创建 DF 的方式
  //  df_2 该方法创建 DF 类型为空，不带泛型导致不能匹配 schema
  val rdd_2 = rdd_1.map(_.split(","))
  val df_2 = spark.createDataFrame(rdd_2, Product.getClass)

  // TODO
  //  创建 DF 正确方式
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

  // TODO
  //  root
  //  |-- productid: integer (nullable = false)
  //  |-- code: string (nullable = true)
  //  |-- name: string (nullable = true)
  //  |-- quantity: integer (nullable = false)
  //  |-- price: float (nullable = false)

  df_3.show
  // TODO
  //  +---------+----+---------+--------+-------+
  //  |productid|code|     name|quantity|  price|
  //  +---------+----+---------+--------+-------+
  //  |     1001| PEN|  Pen Red|    5000|   1.23|
  //  |     1002| PEN| Pen Blue|    8000|   1.25|
  //  |     1003| PEN|Pen Black|    2000|   1.25|
  //  |     1004| PEC|Pencil 2B|   10000|   0.48|
  //  |     1005| PEC|Pencil 2H|    8000|   0.49|
  //  |     1006| PEC|Pencil HB|       0|9999.99|
  //  +---------+----+---------+--------+-------+

  // TODO
  //  store data in hive warehouse directory
  // :paste
  df_3
    .write //
    .mode(SaveMode.Overwrite) //
    .format("orc") //
    .saveAsTable("t_orc_product")

  //  TODO
  //   以上执行后查看 HDFS /user/hive/warehouse/ 是否有数据
  //   发现放数据时只有表名 -> 文件路径被创建，数据不会自动加载到目录下，
  //   只会被创建在本地目录，/Users/sasaki/spark-warehouse/
  //   尝试解决无果，猜测是 Spark 2.0 / SparkSession / HiveContext
  //   等问题。
  //   TIPS
  //   - 对表头（目录）与数据分别操作，将数据手动上传 HDFS /user/hive/warehouse，
  //     上传后加载 Hive 表正常
  //   - 注意区别 Spark 2.0 版本与考试应用版本一致，是否使用 HiveContext/SqlContext
  //     或 SparkSession，toDF 或 createDataFrame 等等方法灵活运用
  //   - 注意 case class 属性名与 Hive schema 字段名一致（不区分大小写），否则出现空列

  // TODO
  //  hdfs dfs -copyFromLocal /Users/sasaki/spark-warehouse/t_orc_product /user/hive/warehouse

  // TODO
  //  create table using data stored in warehouse directory
  //  /
  //  SHOW TABLES;
  //    CREATE EXTERNAL TABLE t_orc_product (
  //      productid int,
  //      code string,
  //      name string,
  //      quantity int,
  //      price float
  //     )
  //     STORED AS orc
  //     LOCATION "/user/hive/warehouse/t_orc_product";

  // TODO
  //  create a parquet table
  // :paste
  df_3
    .write
    .mode(SaveMode.Overwrite)
    .format("parquet")
    .saveAsTable("t_parquet_product")

  //  spark.sql("select * from t_parquet_product").show

  // TODO
  //  hdfs dfs -copyFromLocal /Users/sasaki/spark-warehouse/t_parquet_product /user/hive/warehouse

  // TODO
  //  create table using data stored in warehouse directory
  //  SHOW TABLES;
  //    CREATE EXTERNAL TABLE t_parquet_product (
  //      productid int,
  //      code string,
  //      name string,
  //      quantity int,
  //      price float
  //     )
  //     STORED AS parquet
  //     LOCATION "/user/hive/warehouse/t_parquet_product";

  // TODO
  //  Check data has been loaded or not.
  //  select * from t_parquet_product;
  //  select count(0) from t_parquet_product;


  // TODO ======================================  Question 3  ======================================


  // TODO
  //  In Continuation of previous question, please accomplish following activities.
  //  /
  //  1. Select all the products which has product code as null
  //  2. Select all the products, whose name starts with Pen and results should be
  //     order by Price descending order.

  spark.sql("""
             select *
             from t_parquet_product
             where code is null or code=''""").show

  // TODO
  //  +---------+----+----+--------+-----+
  //  |productid|code|name|quantity|price|
  //  +---------+----+----+--------+-----+
  //  +---------+----+----+--------+-----+

  spark.sql(
    """
      |select *
      |from t_parquet_product
      |where name like 'Pen%'
      |order by price desc
      |""".stripMargin).show

  // TODO
  //  +---------+----+---------+--------+-------+
  //  |productid|code|     name|quantity|  price|
  //  +---------+----+---------+--------+-------+
  //  |     1006| PEC|Pencil HB|       0|9999.99|
  //  |     1002| PEN| Pen Blue|    8000|   1.25|
  //  |     1003| PEN|Pen Black|    2000|   1.25|
  //  |     1001| PEN|  Pen Red|    5000|   1.23|
  //  |     1005| PEC|Pencil 2H|    8000|   0.49|
  //  |     1004| PEC|Pencil 2B|   10000|   0.48|
  //  +---------+----+---------+--------+-------+

  spark.sql(
    """
      |select *
      |from t_parquet_product
      |where name like 'Pen%'
      |order by price asc
      |""".stripMargin).show

  // TODO
  //  +---------+----+---------+--------+-------+
  //  |productid|code|     name|quantity|  price|
  //  +---------+----+---------+--------+-------+
  //  |     1004| PEC|Pencil 2B|   10000|   0.48|
  //  |     1005| PEC|Pencil 2H|    8000|   0.49|
  //  |     1001| PEN|  Pen Red|    5000|   1.23|
  //  |     1003| PEN|Pen Black|    2000|   1.25|
  //  |     1002| PEN| Pen Blue|    8000|   1.25|
  //  |     1006| PEC|Pencil HB|       0|9999.99|
  //  +---------+----+---------+--------+-------+


  // TODO ======================================  Question 4  ======================================


  // TODO 测试 sqoop 连通
  //  sqoop eval \
  //  --username root \
  //  --password 12345678 \
  //  --connect jdbc:mysql://localhost:3306/case \
  //  --query "show tables"

  // TODO
  //  You have been given MySQL DB with following details.
  //  /
  //  user=retail_dba
  //  password=cloudera
  //  database=retail_db
  //  table=retail_db.categories
  //  jdbc URL = jdbc:mysql://quickstart:3306/retail_db
  //  /
  //  Please accomplish following activities.
  //  Import Single table categories (Subset data) to hive managed table,
  //  where category_id between 1 and 22
  //  /
  //  mysql -> hive by sqoop

  // TODO
  //  Import Single table (Subset data)
  //  /
  //  测试导入 hive hdfs 目录通过，删除重复目录
  //  hdfs dfs -rm -r /user/hive/warehouse/product
  //  /
  //  sqoop import \
  //  --username root \
  //  --connect jdbc:mysql://localhost:3306/case \
  //  --password 12345678 \
  //  --table product \
  //  --where "productid between 1001 and 1004" \
  //  --warehouse-dir hdfs://localhost:9000/user/hive/warehouse/
  //  /
  //  测试直接导入 Hive，注意带 --hive-import 参数
  //  /
  //  sqoop import \
  //  --username root \
  //  --connect jdbc:mysql://localhost:3306/case \
  //  --password 12345678 \
  //  --table product \
  //  --where "productid between 1001 and 1004" \
  //  --hive-import
  //  /
  //  Hive 测试导入结果
  //  show tables;
  //  select * from product;
  //  OK
  //  1001	PEN	Pen Red	5000	1.23
  //  1002	PEN	Pen Blue	8000	1.25
  //  1003	PEN	Pen Black	2000	1.25
  //  1004	PEC	Pencil 2B	10000	0.48


  // TODO ======================================  Question 5  ======================================


  // TODO
  //  1. Create a table in retailedb with following definition.
  //      CREATE table departments_export(
  //        department_id int(11),
  //        department_name varchar(45),
  //        created_date TIMESTAMP DEFAULT NOW()
  //      );
  //  2. Now import the data from following directory into departments_export table,
  //    /user/cloudera/departments
  //  /
  //  hdfs -> mysql by sqoop
  //  /
  //  mysql -u root -p
  //  Enter password:
  //  mysql> use case;
  //  mysql> CREATE table departments_export(
  //  ->         department_id int(11),
  //  ->         department_name varchar(45),
  //  ->         created_date TIMESTAMP DEFAULT NOW()
  //  ->       );
  //  Query OK, 0 rows affected, 1 warning (0.23 sec)

  // TODO Answer
  //  sqoop export \
  //  --connect jdbc:mysql://localhost:3306/case \
  //  --username root \
  //  --password 12345678 \
  //  --table departments_export \
  //  --export-dir /user/hive/warehouse/departments_export \
  //  --input-fields-terminated-by '\t' \
  //  --batch \
  //  --verbose (可选 DEBUG 参数)


  // TODO ======================================  Question 6  ======================================


  // TODO
  //  Your spark application required extra Java options as below.
  //  -XX:+PrintGCDetails
  //  -XX:+PrintGCTimeStamps
  //  /
  //  Please replace the XXX values correctly
  //  ./bin/spark-submit --name "My app" --master local[4] \
  //  --conf spark.eventLog.enabled=false \
  //  --conf XXX hadoopexam.jar

  // TODO Answer
  //  XXX 的部分替换为，在执行 spark.jar 时提供 JVM 级别的参数
  //  --conf "spark.executor.extraJavaOptions=-XX:+PrintGCDetails -XX:+PrintGCTimeStamps"
  //  /
  //  扩充参数情况，注意区分 driver 级别和 executor 级别
  //  spark.driver.extraJavaOptions -XX:-UseParallelGC -XX:+UseG1GC
  //  spark.executor.extraJavaOptions -XX:-UseParallelGC -XX:+UseG1GC


  // TODO ======================================  Question 7  ======================================


  // TODO
  //  You have been given a file named spark7/EmployeeName.csv
  //  (id,name).
  //  EmployeeName.csv
  //  E01,Lokesh
  //  E02,Bhupesh
  //  E03,Amit
  //  E04,Ratan
  //  E05,Dinesh
  //  E06,Pavan
  //  E07,Tejas
  //  E08,Sheela
  //  E09,Kumar
  //  E10,Venkat
  //  /
  //  1. Load this file from hdfs and sort it by name
  //  and save it back as (id,name) in results directory.
  //  However, make sure while saving it should be able to
  //  write In a single file.

  val rdd_4 = sc.textFile("hdfs://localhost:9000/tmp/EmployeeName.csv")
  val rdd_5 =  rdd_4.map {o =>
    val k_v = o.split(",")
    (k_v(1), k_v(0))
  }.sortByKey()

  rdd_5.coalesce(1).saveAsTextFile("hdfs://localhost:9000/tmp/employee")

  // TODO
  //  hdfs dfs -cat /tmp/employee/part-00000


  // TODO ======================================  Question 8  ======================================


  // TODO
  //  You have been given below patient data in csv format,
  //  /
  //  patientID,name,dateOfBirth,lastVisitDate
  //  1001,Ah Teck,1991-12-31,2012-01-20
  //  1002,Kumar,2011-10-29,2012-09-20
  //  1003,Ali,2011-01-30,2012-10-21
  //  /
  //  Accomplish following activities.
  //  1. Find all the patients whose lastVisitDate between current time and '2012-09-15'
  //  2. Find all the patients who born in 2011
  //  3. Find all the patients age
  //  4. List patients whose last visited more than 60 days ago
  //  5. Select patients 18 years old or younger

  case class Patient(
                      patientID     : Int,
                      name          : String,
                      dateOfBirth   : String,
                      lastVisitDate : String
                    )

  val rdd_6 = sc.textFile("hdfs://localhost:9000/tmp/patient/patient.csv")
  val rdd_7 = rdd_6.map { o =>

    val array = o.split(",")
    Patient(
      array(0).toInt,
      array(1),
      array(2),
      array(3)
    )
  }

  val df_4 = spark.createDataFrame(rdd_7)

  // TODO 考试低版本使用该方法创建临时表
  df_4.registerTempTable("patient")

  df_4.createOrReplaceTempView("patient")

  spark.sql(
    """
      |select *
      |from
      |patient
      |where lastVisitDate < now()
      |and lastVisitDate > date('2012-09-15')
      |""".stripMargin).show
  // TODO result of Q1
  //  +---------+-----+-----------+-------------+
  //  |patientID| name|dateOfBirth|lastVisitDate|
  //  +---------+-----+-----------+-------------+
  //  |     1002|Kumar| 2011-10-29|   2012-09-20|
  //  |     1003|  Ali| 2011-01-30|   2012-10-21|
  //  +---------+-----+-----------+-------------+

  spark.sql(
    """
      |select *
      |from
      |patient
      |where year(dateOfBirth) = 2011
      |""".stripMargin).show
  // TODO result of Q2
  //  +---------+-----+-----------+-------------+
  //  |patientID| name|dateOfBirth|lastVisitDate|
  //  +---------+-----+-----------+-------------+
  //  |     1002|Kumar| 2011-10-29|   2012-09-20|
  //  |     1003|  Ali| 2011-01-30|   2012-10-21|
  //  +---------+-----+-----------+-------------+

  spark.sql(
    """
      |select
      | patientID,
      | name,
      | dateOfBirth,
      | datediff(now(), date(dateOfBirth))/365 age
      |from
      | patient
      |""".stripMargin).show
  // TODO result of Q3
  //  +---------+-------+-----------+------------------+
  //  |patientID|   name|dateOfBirth|               age|
  //  +---------+-------+-----------+------------------+
  //  |     1001|Ah Teck| 1991-12-31| 30.27123287671233|
  //  |     1002|  Kumar| 2011-10-29| 10.43013698630137|
  //  |     1003|    Ali| 2011-01-30|11.175342465753424|
  //  +---------+-------+-----------+------------------+

  spark.sql(
    """
      |select
      | patientID,
      | name,
      | lastVisitDate
      |from
      | patient
      |where
      | datediff(now(), date(lastVisitDate)) > 60
      |""".stripMargin).show
  // TODO result of Q4
  //  +---------+-------+-------------+
  //  |patientID|   name|lastVisitDate|
  //  +---------+-------+-------------+
  //  |     1001|Ah Teck|   2012-01-20|
  //  |     1002|  Kumar|   2012-09-20|
  //  |     1003|    Ali|   2012-10-21|
  //  +---------+-------+-------------+

  spark.sql(
    """
      |select
      | patientID,
      | name,
      | dateOfBirth,
      | datediff(now(), date(dateOfBirth))/365 age
      |from
      | patient
      |where
      | datediff(now(), date(dateOfBirth))/365 <= 18
      |""".stripMargin).show
  // TODO result of Q5
  //  +---------+-----+-----------+------------------+
  //  |patientID| name|dateOfBirth|               age|
  //  +---------+-----+-----------+------------------+
  //  |     1002|Kumar| 2011-10-29| 10.43013698630137|
  //  |     1003|  Ali| 2011-01-30|11.175342465753424|
  //  +---------+-----+-----------+------------------+


  // TODO
  //  SparkSQL 日期时间相关查询

  spark.sql("SELECT current_date()").show
  // TODO
  //  +--------------+
  //  |current_date()|
  //  +--------------+
  //  |    2022-04-01|
  //  +--------------+

  spark.sql("SELECT now() now").show
  // TODO
  //  +--------------------+
  //  |                 now|
  //  +--------------------+
  //  |2022-04-01 21:36:...|
  //  +--------------------+

  spark.sql("SELECT current_timestamp()").show
  // TODO
  //  +--------------------+
  //  | current_timestamp()|
  //  +--------------------+
  //  |2022-04-01 21:30:...|
  //  +--------------------+

  spark.sql("SELECT datediff(now(), date('2020-08-15')) days").show
  // TODO 求两个日期差
  //  Syntax: datediff(endDate, startDate)
  //  What it does: The Spark datediff function returns the difference
  //  between two given dates, endDate and startDate.
  //  +----+
  //  |days|
  //  +----+
  //  | 594|
  //  +----+

  spark.sql("SELECT add_months('2020-08-01',4)").show
  // TODO
  //  +---------------------------------------+
  //  |add_months(CAST(2020-08-01 AS DATE), 4)|
  //  +---------------------------------------+
  //  |                             2020-12-01|
  //  +---------------------------------------+

  spark.sql("SELECT date('2020-08-16')").show
  // TODO
  //  +------------------------+
  //  |CAST(2020-08-16 AS DATE)|
  //  +------------------------+
  //  |              2020-08-16|
  //  +------------------------+

  spark.sql("SELECT date_sub('2020-08-16',5)").show
  // TODO
  //  +-------------------------------------+
  //  |date_sub(CAST(2020-08-16 AS DATE), 5)|
  //  +-------------------------------------+
  //  |                           2020-08-11|
  //  +-------------------------------------+

  spark.sql("SELECT year('2020-08-16') year").show
  // TODO
  //  +----+
  //  |year|
  //  +----+
  //  |2020|
  //  +----+

  spark.sql("SELECT month('2020-08-16') month").show
  // TODO
  //  +-----+
  //  |month|
  //  +-----+
  //  |    8|
  //  +-----+


  // TODO ======================================  Question 9  ======================================

  // TODO
  //  You have been given belwo list in scala (name,sex,cost)
  //  for each work done.
  //  List(
  //    ("Deeapak", "male", 4000),
  //    ("Deepak", "male", 2000),
  //    ("Deepika", "female", 2000),
  //    ("Deepak", "female", 2000),
  //    ("Deepak", "male", 1000) ,
  //    ("Neeta", "female", 2000)
  //  )
  //  Now write a Spark program to load this list as an RDD
  //  and do the sum of cost for combination of name and sex (as key)

  val rdd_8 = {
    // :paste
    sc.parallelize(
      List(
        ("Deeapak", "male", 4000),
        ("Deepak", "male", 2000),
        ("Deepika", "female", 2000),
        ("Deepak", "female", 2000),
        ("Deepak", "male", 1000),
        ("Neeta", "female", 2000)
      )
    )
      .map(o => ((o._1,o._2), o._3))
      .reduceByKey(_ + _)
//      .collect()
      .coalesce(1)
      .saveAsTextFile("hdfs://localhost:9000/tmp/Q9")

    // TODO result of collect
    //  Array[((String, String), Int)] =
    //  Array(
    //    ((Deeapak,male),4000),
    //    ((Deepak,female),2000),
    //    ((Neeta,female),2000),
    //    ((Deepak,male),3000),
    //    ((Deepika,female),2000)
    //  )

    // TODO result of hdfs
    //  hdfs dfs -cat /tmp/Q9/part-00000
    //  ((Deeapak,male),4000)
    //  ((Deepak,female),2000)
    //  ((Neeta,female),2000)
    //  ((Deepak,male),3000)
    //  ((Deepika,female),2000)

  }


  // TODO ======================================  Question 10  ======================================

  // TODO
  //  You have been given MySQL DB with following details.
  //  user=retail_dba
  //  password=cloudera
  //  database=retail_db
  //  table=retail_db.orders
  //  table=retail_db.order_items
  //  jdbc URL=jdbc:mysql://quickstart:3306/retail_db
  //  Columns of products table:
  //    (
  //        product_id |
  //        product_categoryid |
  //        product_name |
  //        product_description |
  //        product_pryce |
  //        product_image
  //    )
  //  Please accomplish following activities.
  //  1. Copy "retaildb.products" table to hdfs in a directory p93_products
  //  2. Filter out all the empty prices
  //  3. Sort all the products based on price
  //     in both ascending as well as descending order.
  //  4. Sort all the products based on price as well as
  //     product_id in descending order.
  //  5. Use the below functions to do data ordering or ranking and fetch
  //     top 10 elements top() takeOrdered() sortByKey()

  // TODO
  //  Q1
  //  /
  //  --columns "product_id,product_categoryid"
  //  sqoop import hdfs 必须指定 --columns 参数否则导出的文本列错位影响使用
  //  ！！！该处与CCA试题 Answer 有出入，不可直接参照试题！坑！
  //  /
  //  标准导入语句最好同时指定三个参数
  //  --columns "product_id,product_categoryid"
  //  --input-fields-terminated-by ','
  //  --input-lines-terminated-by '\n'
  //  /
  //  -m 参数是指最终 partition 的数量
  //  /
  //  sqoop import \
  //  --connect jdbc:mysql://localhost:3306/case \
  //  --username=root \
  //  --password=12345678 \
  //  --table=products \
  //  --columns "product_id,product_categoryid,product_name,product_description,product_pryce,product_image" \
  //  --target-dir=/tmp/p93_products \
  //  --input-fields-terminated-by ',' \
  //  --input-lines-terminated-by '\n' \
  //  -m 1
  //  Q2
  //  hdfs dfs -ls /tmp/p93_products

  // :paste
  val rdd_9 =
    sc.textFile("hdfs://localhost:9000/tmp/p93_products")
      .map(_.split(","))
  // TODO
  //  Array(
  //    Array(1, 2, p1, d1, 1.2, i1),
  //    Array(2, 32, p2, d2, 2, i2),
  //    Array(3, 23, p3, d3, null, i3),
  //    Array(4, 4, p4, d4, 12, i4),
  //    Array(5, 3, p5, d5, 5, i5),
  //    Array(6, 1, p6, d6, 4.1, i6),
  //    Array(7, 12, p7, d7, 215, i7),
  //    Array(8, 33, p8, d8, 32, i8),
  //    Array(9, 22, p9, d9, 13, i9),
  //    Array(10, 6, p10, d10, "", i10),
  //    Array(11, 3, p11, d11, null, i11),
  //    Array(12, 55, p12, d12, 3, i12),
  //    Array(13, 64, p13, d13, null, i13)
  //  )

  val index_product_id = 0
  val index_product_name = 2
  val index_product_price = 4

  // TODO 注意 以上导入方式""列值为null
  val rdd_10 = rdd_9.filter(_(index_product_price).equals("null"))
  rdd_10.collect()
  // TODO
  //  Array(
  //    Array(3, 23, p3, d3, null, i3),
  //    Array(11, 3, p11, d11, null, i11),
  //    Array(13, 64, p13, d13, null, i13)
  //  )

  // TODO Q3
  // :paste
  val rddWithPriceNonNull = rdd_9
    .filter(o => !o(index_product_price).equals("null") && !o(index_product_price).equals(""))
    .map(o => (o(index_product_id).toInt, o(index_product_name), o(index_product_price).toDouble))
  // TODO
  //  Array(
  //    (1,p1,1.2),
  //    (2,p2,2),
  //    (4,p4,12),
  //    (5,p5,5),
  //    (6,p6,4.1),
  //    (7,p7,215),
  //    (8,p8,32),
  //    (9,p9,13),
  //    (12,p12,3)
  //  )

  val rdd_11_ascending = rddWithPriceNonNull.sortBy(_._3, true)//.collect
  // TODO
  //  Array(
  //    (1,p1,1.2),
  //    (2,p2,2.0),
  //    (12,p12,3.0),
  //    (6,p6,4.1),
  //    (5,p5,5.0),
  //    (4,p4,12.0),
  //    (9,p9,13.0),
  //    (8,p8,32.0),
  //    (7,p7,215.0)
  //  )
  val rdd_12_descending = rddWithPriceNonNull.sortBy(_._3, false)//.collect
  // TODO
  //  Array(
  //    (7,p7,215.0),
  //    (8,p8,32.0),
  //    (9,p9,13.0),
  //    (4,p4,12.0),
  //    (5,p5,5.0),
  //    (6,p6,4.1),
  //    (12,p12,3.0),
  //    (2,p2,2.0),
  //    (1,p1,1.2)
  //  )

  // TODO WARN ===========================================================================
  //  /
  //  以下 Q4 Q5 部分根据两个排序条件的需求不明确，使用时慎重参考原题和答案！！
  //  /
  // TODO WARN ===========================================================================

  // TODO Q4
  val rdd_13_descending = rddWithPriceNonNull.sortBy(o => (o._3, o._1), false)//.collect
  // TODO
  //  Array(
  //    (7,p7,215.0),
  //    (8,p8,32.0),
  //    (9,p9,13.0),
  //    (4,p4,12.0),
  //    (5,p5,5.0),
  //    (6,p6,4.1),
  //    (12,p12,3.0),
  //    (2,p2,2.0),
  //    (1,p1,1.2)
  //  )
  val rdd_14_ascending = rddWithPriceNonNull.sortBy(o => (o._3, o._1), true)//.collect
  // TODO
  //  Array(
  //    (1,p1,1.2),
  //    (2,p2,2.0),
  //    (12,p12,3.0),
  //    (6,p6,4.1),
  //    (5,p5,5.0),
  //    (4,p4,12.0),
  //    (9,p9,13.0),
  //    (8,p8,32.0),
  //    (7,p7,215.0)
  //  )

  // TODO Q5-1
  //  - Now sort data based on product_price as well as
  //  product_id in descending order, using top() function.
  rdd_13_descending.top(10).foreach(println)
  // TODO
  //  (12,p12,3.0)
  //  (9,p9,13.0)
  //  (8,p8,32.0)
  //  (7,p7,215.0)
  //  (6,p6,4.1)
  //  (5,p5,5.0)
  //  (4,p4,12.0)
  //  (2,p2,2.0)
  //  (1,p1,1.2)

  // TODO Q5-2
  //  Now sort data based on product_price as ascending and product_id in ascending order,
  //  using takeOrdered() function.
  val rdd_15_ascending = rddWithPriceNonNull.sortBy(o => (o._3, o._1), true)
  rdd_15_ascending.takeOrdered(10).foreach(println)
  // TODO
  //  (1,p1,1.2)
  //  (2,p2,2.0)
  //  (4,p4,12.0)
  //  (5,p5,5.0)
  //  (6,p6,4.1)
  //  (7,p7,215.0)
  //  (8,p8,32.0)
  //  (9,p9,13.0)
  //  (12,p12,3.0)

  // TODO Q5-3
  //  Now sort data based on product_price as descending and product_id in ascending order,
  //  using takeOrdered() function.
  val rdd_16_ascending = rddWithPriceNonNull.sortBy(o => (o._3 * -1 /* 负排序 */, o._1), true)
  rdd_16_ascending.takeOrdered(10).foreach(println)
  // TODO
  //  (1,p1,1.2)
  //  (2,p2,2.0)
  //  (4,p4,12.0)
  //  (5,p5,5.0)
  //  (6,p6,4.1)
  //  (7,p7,215.0)
  //  (8,p8,32.0)
  //  (9,p9,13.0)
  //  (12,p12,3.0)

  spark stop
}