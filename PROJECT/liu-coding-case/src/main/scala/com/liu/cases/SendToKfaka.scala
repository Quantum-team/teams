package com.liu.cases

import com.liu.util.LogFactory

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import scala.language.postfixOps
import scala.util.Random

object SendToKfaka extends LogFactory {

  def main(args: Array[String]): Unit = {
    //配置信息
    val props = new Properties()
    //kafka服务器地址
    props.put("bootstrap.servers", "localhost:9092")
    props.put("acks", "1")
    props.put("retries", "3")
    //设置数据key和value的序列化处理类
    props.put("key.serializer", classOf[StringSerializer].getName)
    props.put("value.serializer", classOf[StringSerializer].getName)
    //创建生产者实例
    val producer = new KafkaProducer[String, String](props)

    // TODO 生成订单数相关的数据源，统计所有用户每分钟订单总数与订单总额
    // TODO ============================================================================================================


    //    //生成随机数
    val random = new Random()
    val user_ids = Array("gxzy", "xyzy", "gzsl", "tszy", "wzkj", "pds", "lazy", "dsgy", "lyzy", "lwzy", "ynnz", "ynlg")
    val status = Array("1001", "1002", "1003", "1004", "1005", "1006", "1004", "1002", "1004", "1001", "1004", "1002")
    var did = 0
    var order_id = 100000
    var time = 1577818800 //测试时间起点

    val topic = "t1"

    for (i <- 1 to 100001) {
      // 随机产生一条数据
      order_id += 1 //订单id
      val amount = random.nextInt(17805) //订单金额
      val order_status = status(random.nextInt(status.size)) //订单状态
      val user_name = user_ids(random.nextInt(user_ids.size)) //用户id
      val feight_fee = random.nextInt(20) //运输费
      val comments = "描述#" + i
      //O代表订单数据：订单id、收货人、总金额、订单状态、用户id、描述、运送费
      val order_message = "O:" + order_id + "," + user_name + "," + amount + "," + order_status + "," + comments + "," + feight_fee + "," + time
      time += random.nextInt(50) //每次时间增加随机50秒,代表下一次订单的时间

      //发送记录
      val record = new ProducerRecord[String, String](topic, order_message)

      logger.info("order_message :" + did + " " + order_message)

      producer.send(record)

      val num = random.nextInt(10) + 1 //最多4条记录

      for (j <- 0 to num) {
        //订单明细
        did += 1
        val nums = random.nextInt(3) + 1 //商品购买数量
        val detail_price = amount / num / nums //详细金额
        // L代表订单明细数据：明细id、订单id、商品价格、购买数量
        val detail_message = "L:" + did + "," + order_id + "," + detail_price + "," + nums

        logger.info("detail_message :" + order_id + " " + detail_message)

        //发送记录
        val record = new ProducerRecord[String, String](topic, detail_message)
        producer.send(record)
        Thread.sleep(random.nextInt(500))
      }

      Thread.sleep(100) //休眠1秒
    }


    // TODO ============================================================================================================


    // 关闭连接
    producer.close()
  }
}
