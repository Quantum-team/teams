## Hello markdown!

# Day 1
# 配置环境的过程记录下来
# 注意！错误的过程 

# 错误路径千万条，正确路径只有一条！

# 过程的作业
# - 跟着步骤清单完成国赛题配置
# 注意收集错误步骤 进行troubleshooting
# 把所有的步骤按照你的规范格式写入markdown
# 所有的文件放git发布，统一管理
# 视频只是辅助，正确步骤只看结果，错误步骤才是重点！
# 比赛不同于项目开发，在特定的时间内没有过多的调试冗余时间，
而且当前步骤出错不会立即反应到下一步骤！增加了出错机率
Hadoop搭建有异常，当前不会立即反应出异常
可能到后边执行spark on yarn的时候run不成功
# 细心！细心！还是细心！
# 检查！检查！还是检查！
# 后边制定精细化的working flow，若干检查步骤！

# 配置的参数复杂、多种多样、各种组合、N种格式
# 没有"规范"、没有标准
# 比赛时配置是盲写的！
# 所以我们的目标配置是
# 最简化Min、规范、标准

# SQL决定第二部分成败，不可掉以轻心！！

# Day 2
# Java Scala Hadoop Spark/SparkSQL Flink

# 如何快速学Scala 
# Java -> Scala 用Java学Scala
# Case1 用Scala重写JDBC，任意找一个Java程序
# 重点是在程序中体验Scala语言操作Array 数组、List Seq Map Tuple 集合！！

# Case2 
Resilient Distributed Datasets (RDDs)
Spark revolves around the concept of a resilient distributed dataset (RDD), which is a fault-tolerant collection of elements that can be operated on in parallel. There are two ways to create RDDs: parallelizing an existing collection in your driver program, or referencing a dataset in an external storage system, such as a shared filesystem, HDFS, HBase, or any data source offering a Hadoop InputFormat.


RDD Operations
RDDs support two types of operations: transformations, which create a new dataset from an existing one, and actions, which return a value to the driver program after running a computation on the dataset. For example, map is a transformation that passes each dataset element through a function and returns a new RDD representing the results. On the other hand, reduce is an action that aggregates all the elements of the RDD using some function and returns the final result to the driver program (although there is also a parallel reduceByKey that returns a distributed dataset).
All transformations in Spark are lazy, in that they do not compute their results right away. Instead, they just remember the transformations applied to some base dataset (e.g. a file). The transformations are only computed when an action requires a result to be returned to the driver program. This design enables Spark to run more efficiently. For example, we can realize that a dataset created through map will be used in a reduce and return only the result of the reduce to the driver, rather than the larger mapped dataset.
By default, each transformed RDD may be recomputed each time you run an action on it. However, you may also persist an RDD in memory using the persist (or cache) method, in which case Spark will keep the elements around on the cluster for much faster access the next time you query it. There is also support for persisting RDDs on disk, or replicated across multiple nodes.


Printing elements of an RDD
Another common idiom is attempting to print out the elements of an RDD using rdd.foreach(println) or rdd.map(println). On a single machine, this will generate the expected output and print all the RDD’s elements. However, in cluster mode, the output to stdout being called by the executors is now writing to the executor’s stdout instead, not the one on the driver, so stdout on the driver won’t show these! To print all elements on the driver, one can use the collect() method to first bring the RDD to the driver node thus: rdd.collect().foreach(println). This can cause the driver to run out of memory, though, because collect() fetches the entire RDD to a single machine; if you only need to print a few elements of the RDD, a safer approach is to use the take(): rdd.take(100).foreach(println).


# Day 3
# 画范围 学习一定要精准打击
# 站在巨人的肩膀上前进

# Function 算子/函数  <~>  Method OOP 方法 
# 函数或者方法的称谓，都是描述如何做一件事情、事件的过程，
# 在编程里描述一个过程：一进一出
# 进去什么：参数列表，类型&值
# 出来什么：返回，类型&值
# 编程语言三要素：变量/Variable（属性/Attribute）、控制（条件if,循环for,顺序）、函数（方法）
# 面向对象OOP中的方法 <= 面向过程/结构、结构化编程当中的函数
# 没有明确的边界、也不需要纠结
# 
# 面向数据的编程 => 函数式，在数据处理中有天生的优势
# 尤其是大数据处理 
# 所以诞生了以Spark为代表的优秀的分布式计算框架
# 链式编程 管道
# 
# Jacla 
# 
# Void  我不返回
# Scala特性：万物皆返回！万物必返回！
# Any 是一切类型的超类/父类
# Nothing 是一切类型的子类
# Unit -> Option / Nothing 我"不"返回，我返回了一个"不返回"

# Scala特性：万物皆不变！val -> var
# object 同名伴生对象
# 纸牌Case 一副poke 54cards 
# 设想有100w副pokes 54*100w cards
# 随机抽走n张Cards，要求人数不限，如何快速找出缺失的cards

# 既然是分布式，为何要有共享变量？
# 1000是一个变量 Broadcast Variables
# Global Variable "0/Null/Empty" => Accumulators
# "加法"

# 07.21复盘
# 从业务视角理解 静态分区 动态分区
# 全量数据 => 静态分区，不是说只能静态分区不可以动态分区
# 赛题表述是为了"省事"

模拟静态分区业务
CUSTOMER、NATION、PART、PARTSUPP、REGION、SUPPLIER
客户、国家、"部分"、"供应商部分"、"地区"、"供应商"
数据相对稳定，不会有太大密度的变幅

抽取shtd_store库中SUPPLIER的全量数据进入Hive的ods库中表supplier。
同时添加静态分区，
分区字段类型为String，
且值为某日期（分区字段格式为yyyyMMdd）
id  timestamp
1 2022.01.01
2 2022.01.01
3 2022.01.02
4 2022.01.04
5 2022.01.06
6 2022.01.08
7 2022.01.08
...
999 2022.07.20
以上是 partition_date="20220721"
————————————————————————
1000 2022.07.21
~
2300 2022.07.22
2360 2022.07.23 00:00:14

2379 2022.07.23 20:09:12
2380 2022.07.24
2400 2022.07.25
这部分是用于增量的，按日期每天建一个动态分区ORDERDATE
——————————

扩充知识点：
Hive是数据仓库 DW Data Warehouse 大数据的基础
MySql是数据库 DB Database
生产环境中、或者任何角度来讲，都是先有DB后有DW

存量和增量
大数据项目给企业实施时
把DB数据一次性"搬到"DW，解决存量数据问题，用于分析、各种
然后每天有多少搬多少到DW，解决增量数据问题，用于分析、实时计算、各种


全量抽取后，指定当天的日期
1~7 全部抽 => partition_date="20220721"

模拟动态分区业务
ORDERS、LINEITEM
订单、产品线
高频变动，几乎每天、每时每刻都有数据增长！
所以要增量抽取，最好的分区方式就是按天(Date)动态分区、

动态分区两个关键点，非常重要：
1 根据哪个点来定义增长数据？
就是根据ID，或者任意"自增长"的列，比如日期（不是生日）

2 抽到增长数据后如何放，放哪里？
每天"自动"加一个分区，存储当天来的增量的数据！

# 07.23 复盘
统计各个学校下的班级最多的前三名（单表）
统计各个学校下的总人数，按人数降序输出（两个表）
统计各个学校考试的总次数，平均分，并且按平均分进行排名（三个表）

这种实际问题有几种解决形式：
- Pure SQL
- Spark Function / DataFrame
- Spark RDD

RDD with Schema with Relation => DataFrame with SQL
MySql(DB) <=> SQL <=> Hive(DW)

**定SparkSql的玩转策略
1 先上普通SQL，做一个基准Basic Line，SQL靠谱，得到一个自认为OK的结果
作为参照，
2 再上有难度的RDD Programming by Func，相办法用算子去"逼近"
基准的参照结果
3 或者仅在Mysql当中得到了结果集，再用RDD编程去重复实现，这样"对比"
学习可以高效提升
4 注意SQL是通用的标准，"普通"SQL和SparkSQL其实是一回事

**
（相对正确的参照结果）先在Mysql中用"普通"SQL得到结果 => 
然后把SQL搬到SparkSQL当中得到DF结果 =>
（陈老师教的）然后再SparkSQL中用DF算子得到结果 =>
（可选）然后再SparkS中用RDD算子得到结果

RDD 操作RDD需要大量的Programming、熟练函数式的算子
Array((foo,Set(B, A)), (bar,Set(C, D)))

Schema : for structured data processing
//  |-- productid: integer (nullable = false)
//  |-- code: string (nullable = true)
//  |-- name: string (nullable = true)
//  |-- quantity: integer (nullable = false)
//  |-- price: float (nullable = false)

DataFrame 操作数据库可以使用简单的编程逻辑控制+SQL（熟悉数据库操作）
就像操作数据库一样的来操作Spark的数据
数据库关系 => 二维表 Row(Data) / Column(Attribute)
不是持久的表，df.createOrReplaceTempView
.show
val a:DataFrame = df.sql("select * from ...")

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

* 聚合函数 用于数理计算 简单加减乘除数学运算
groupBy count sort sum having aggregate avg
distinct


# 07.25 复盘 聊聊Streaming流式计算

enables scalable, high-throughput, fault-tolerant stream processing
live data 实时数据、基于时间 / h -> min -> s -> ms
金融、股指证券
<=> 
batch data 强调量级、全量、增量
live-stream/直播 live version
Dataflows Datastream

实时数据  => Kafka / window 开窗函数
........
————————
由点成线 => "由离散到线性"

流式数据和计算一定要考虑时间！！！

Sources / input -> Sinks / output / 水槽

Kafka : 
分布式消息队列 用高级的计算框架比如SparkStreaming Flink处理
生产者&消费者模式
发布（写）&订阅（读）
Producer Consumer Event/事件 Topic/主题、话题

一个大水池，上游有若干个水龙头在进水，下流有很多人在喝水
旋转火锅自助
厨师放菜 -> 转盘流水线 -> 顾客订阅（吃）
顾客每取一盘菜、每吃一盘菜就是一个事件、事件是一条一条的、基于时间Timestamp增长的

早高峰
地铁口、火车站那个回形排队阵 Kafka队列
5个匝机口

解耦、缓冲

生产者：Producers are those client applications that publish (write) events to Kafka, 
消费者：Consumers are those that subscribe to (read and process) these events.

流式三步曲 F -> K -> F -> Redis内存数据库 k-v
Flume/distributed logs 日志收集 -> Kafka/message queues -> Flink (new FlinkKafkaConsumer())
设应用平台每s产生10w~1kw条logs 

# 交互式 CMD 所见所得，有Context Session 调试，编译级别，单脚本，得出阶段结果 
# 脚本式 IDE 写 .java .scala  -> .class 打包 deploy publish 生产环境

# 08.08 流式计算实战 
Kafka -> Flink -> Redis

Driver Programing 驱动程序

流式计算：有key三步曲，无key两步曲

## Keyed Windows

stream
.keyBy(...)               <-  keyed versus non-keyed windows
.window(...)              <-  required: "assigner"
.reduce/aggregate/fold/apply()      <-  required: "function"

## Non-Keyed Windows

stream
.windowAll(...)           <-  required: "assigner"
.reduce/aggregate/fold/apply()      <-  required: "function"

## Tumbling Windows
滚动窗口，最容易理解，直观，很自然，无重叠
1分钟60秒，每秒一个窗口

## 数据处理项目一定要先分析（手算草稿）推演数据流，再用算子func编程
数据流就是每一步的输入和输出类型和值

基本上面向数据过程的编程"没有"异常处理 try-catch
约定大于补救

# Redis vs MemCache
Non-Sql K-V
C语言，性能非常高，可靠性高，高并发，高吞吐量

MySql 