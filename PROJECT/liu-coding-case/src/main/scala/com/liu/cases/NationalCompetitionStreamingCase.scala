package com.liu.cases

import com.liu.util.LogFactory
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisMapper}

import java.util.Properties

/**
 * @author 老刘Matthew
 * @timestamp Tue Aug 2, 11: 24
 * 本项目源码仅供大数据国赛班教学内阅，禁止传阅！
 */
object NationalCompetitionStreamingCase extends LogFactory {

  // TODO
  //  --------------------------------------------------------------------------------------------------------
  //  /
  //    模块D：数据采集与实时计算（20分）
  //  /
  //    任务一：实时数据采集
  //     1、
  //      在Master节点使用Flume采集实时数据生成器XXXX端口的socket数据，
  //      将数据存入到Kafka的自定义Topic中，将Flume的配置截图粘贴至对应报告中。
  //  /
  //  --------------------------------------------------------------------------------------------------------

  def main(args: Array[String]) {

    val topic = "t1"

    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "g1")

    val env: StreamExecutionEnvironment =
      StreamExecutionEnvironment
        .getExecutionEnvironment

    val dataStreamSource =
      env.addSource(new FlinkKafkaConsumer[String](topic, new SimpleStringSchema, properties))

    //    case class Order(
    //                      order_id: Integer,
    //                      user_name: String,
    //                      amount: Integer,
    //                      order_status: Integer,
    //                      comments: String,
    //                      feight_fee: Integer,
    //                      timestamp: Long
    //                    )

    // TODO
    //  https://nightlies.apache.org/flink/flink-docs-release-1.10/dev/stream/operators/windows.html
    //  /
    //  #Windows
    //  Windows are at the heart of processing infinite streams.
    //  Windows split the stream into “buckets” of finite size, over which we can apply computations.
    //  /
    //    Keyed Windows
    //    stream
    //      .keyBy(...)                   <-  keyed versus non-keyed windows
    //    .window(...)                    <-  required: "assigner"
    //    .reduce/aggregate/fold/apply()  <-  required: "function"
    //    Non-Keyed Windows
    //    stream
    //      .windowAll(...)           <-  required: "assigner"
    //  # Keyed vs Non-Keyed Windows
    //  - The first thing to specify is whether your stream should be keyed or not.
    //    This has to be done before defining the window. Using the keyBy(...) will split your
    //    infinite stream into logical keyed streams. If keyBy(...) is not called, your stream
    //    is not keyed.
    //  - In the case of keyed streams, any attribute of your incoming events can be used as a key
    //    (more details here). Having a keyed stream will allow your windowed computation to be performed
    //    in parallel by multiple tasks, as each logical keyed stream can be processed independently from the rest.
    //    All elements referring to the same key will be sent to the same parallel task.
    //  - In case of non-keyed streams, your original stream will not be split into multiple logical streams
    //    and all the windowing logic will be performed by a single task, i.e. with parallelism of 1.
    //  /
    //  # Tumbling Windows
    //  - A tumbling windows assigner assigns each element to a window of a specified window size.
    //    Tumbling windows have a fixed size and do not overlap. For example, if you specify
    //    a tumbling window with a size of 5 minutes, the current window will be evaluated
    //    and a new window will be started every five minutes as illustrated by the following figure.
    //  /
    //  # Sliding Windows
    //  - The sliding windows assigner assigns elements to windows of fixed length.
    //    Similar to a tumbling windows assigner, the size of the windows is configured by
    //    the window size parameter. An additional window slide parameter controls how frequently
    //    a sliding window is started. Hence, sliding windows can be overlapping if the slide is smaller than
    //    the window size. In this case elements are assigned to multiple windows.
    //  - For example, you could have windows of size 10 minutes that slides by 5 minutes.
    //    With this you get every 5 minutes a window that contains the events that arrived during
    //    the last 10 minutes as depicted by the following figure.
    //  /
    //  https://nightlies.apache.org/flink/flink-docs-release-1.10/concepts/programming-model.html
    //  /
    //  # Windows
    //  - Aggregating events (e.g., counts, sums) works differently on streams than in batch processing.
    //    For example, it is impossible to count all elements in a stream, because streams are in general
    //    infinite (unbounded). Instead, aggregates on streams (counts, sums, etc), are scoped by windows,
    //    such as “count over the last 5 minutes”, or “sum of the last 100 elements”.
    //  - Windows can be time driven (example: every 30 seconds) or data driven (example: every 100 elements).
    //    One typically distinguishes different types of windows, such as tumbling windows (no overlap),
    //    sliding windows (with overlap), and session windows (punctuated by a gap of inactivity).
    //  /
    //  # Time
    //  When referring to time in a streaming program (for example to define windows),
    //  one can refer to different notions of time:
    //    - Event Time is the time when an event was created. It is usually described by a timestamp
    //      in the events, for example attached by the producing sensor, or the producing service.
    //      Flink accesses event timestamps via timestamp assigners.
    //    - Ingestion time is the time when an event enters the Flink dataflow at the source operator.
    //    - Processing Time is the local time at each operator that performs a time-based operation.
    //  /
    //  # Stateful Operations
    //  - While many operations in a dataflow simply look at one individual event at a time
    //    (for example an event parser), some operations remember information across multiple events
    //    (for example window operators). These operations are called stateful.
    //  - The state of stateful operations is maintained in what can be thought of as an embedded
    //    key/value store. The state is partitioned and distributed strictly together with the streams
    //    that are read by the stateful operators. Hence, access to the key/value state is only possible
    //    on keyed streams, after a keyBy() function, and is restricted to the values associated with
    //    the current event’s key. Aligning the keys of streams and state makes sure that all state updates
    //    are local operations, guaranteeing consistency without transaction overhead. This alignment also allows
    //    Flink to redistribute the state and adjust the stream partitioning transparently.

    // TODO
    //  # Flink三方连接 Redis
    //  https://nightlies.apache.org/flink/flink-docs-release-1.10/dev/connectors/

    // TODO 函数式算子对比OOP
    //  /
    //    dataStreamSource
    //    .map(new MapFunction[String, (String, Long)] {
    //      override def map(input: String): (String, Long) = {
    //        val o = input.substring(2).split(",")
    //        (o(2), o(6).toLong)
    //      }
    //    })
    //    .keyBy(2)

    // TODO
    //  --------------------------------------------------------------------------------------------------------
    //  /
    //    任务二：使用Flink处理Kafka中的数据
    //     1、
    //      编写Scala工程代码，使用Flink消费kafka中的数据，统计所有用户每分钟订单总数与订单总额，
    //      然后计算出每分钟订单平均金额，将key设置成avgprice存入redis中。
    //      使用redis cli以get key方式获取avgprice值，将结果截图粘贴至对应报告中。
    //  /
    //  --------------------------------------------------------------------------------------------------------

    // TODO
    //  Data Sample
    //  O代表订单数据：订单id、收货人、总金额、订单状态、用户id、描述、运送费
    //  "O:" + order_id + "," + user_name + "," + amount + "," + order_status + "," + comments + "," + feight_fee + "," + time
    //  O:100014,lwzy,14792,1001,描述#14,13,1577819134
    //  -
    //  L代表订单明细数据：明细id、订单id、商品价格、购买数量
    //  "L:" + did + "," + order_id + "," + detail_price + "," + nums
    //  L:101,100014,1643,3
    //  L:102,100014,1643,3
    //  L:103,100014,4930,1
    //  L:104,100014,1643,3

    //    import org.apache.flink.streaming.api.scala._
    import org.apache.flink.api.scala._
    import org.apache.flink.streaming.api.windowing.time.Time

    val stream =
      dataStreamSource
      .map { line =>

        logger.info("step 1 ------------> " + line)

        val result = if(line.startsWith("O:")) {
          val o = line.substring(2).split(",")
          (1, o(2).toInt)
        } else
          (0, 0)

        logger.info("step 2 ------------> " + result)

        result
      }
//      .keyBy(_._2)
//      .window(TumblingTimeWindows.of(Time.minutes(1)))
//      .window(TumblingEventTimeWindows.of(Time.minutes(1)))
//      .windowAll(TumblingEventTimeWindows.of(Time.seconds(3)))
      .timeWindowAll(Time.seconds(5))
      // TODO 链式精简写法，十分熟悉数据流和过界输出细节
//       .reduce((o, o_) => (o._1 + o_._1, (o._2 + o_._2) / (o._1 + o_._1)))
      // TODO 常规写法，详细表述细节
      .reduce { (o, o_) =>
        // o 上一个元素， o_ 下一个元素

        val count = o._1 + o_._1
        val amountTotal = o._2 + o_._2
        val avgprice = amountTotal / count

        logger.info("step 3 ------------> count = " + count + ", amountTotal = " + amountTotal + ", avgprice" + avgprice)

        (count, avgprice)
      }

//      .print()


    val confRedis = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build()

    // 接口、实现类（匿名内部类） -> 函数式接口
    stream.addSink(new RedisSink[(Int, Int)](confRedis, new RedisMapper[(Int, Int)] {

      override def getCommandDescription: RedisCommandDescription =
        new RedisCommandDescription(RedisCommand.SET, "avgprice")

      override def getKeyFromData(t: (Int, Int)): String = "avgprice"

      override def getValueFromData(t: (Int, Int)): String = t._2.toString
    }))

    env.execute(this.getClass.getName)

  }
}