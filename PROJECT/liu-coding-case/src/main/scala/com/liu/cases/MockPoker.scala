package com.liu.cases

import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import java.io.{File, PrintWriter}
import scala.util.Random

/**
 * 扑克牌问题
 * 生成扑克牌随机数据
 *
 * @author Matthew
 */
object MockPoker {

  def main(args: Array[String]): Unit = {

    val poker = new Poker(100000 + 1).deal
    val pokerDirty =
      poker
        //
        .remove((Suit.__, Point.BLACK_JOKER))
        .remove((Suit.spade, Point._2))
        .remove((Suit.club, Point.QUEEN))
        .remove((Suit.heart, Point._2))
        .shuffle

    val pokers = (1 to 100000).map(new Poker(_).deal.shuffle)
    val pokers_ = pokers.+:(pokerDirty)
    val jsonPokers_ = pokers_.map(_.buildJson)
    val randomJsonPokers_ = Random.shuffle(jsonPokers_)

    lazy val file_ = new File("/Users/sasaki/pokers_10W.json")
    val writer = new PrintWriter(file_)
    val builder = new StringBuilder
    randomJsonPokers_.foreach(builder.append(_).append("\n"))
    writer.write(builder.toString())

    writer.flush()
    writer.close()

    println(randomJsonPokers_.size)
  }
}


/**
 * 单一扑克类
 *
 * @param id
 * @param cards
 */
case class Poker(id: Long) {

  private var cards: Seq[Card] = _

  /**
   * 发牌
   */
  def deal: Poker = {
    var index = 0
    val listCard_52: Seq[Card] =
      for (
        index_suit <- 1 to 4;
        index_point <- 1 to 13 /* 除大小王 */
      ) yield {

        index += 1
        Card(
          index,
          Suit(index_suit),
          Point(index_point)
        )
      }

    val black_joker = Card(0, Suit.__, Point.BLACK_JOKER)
    val red_joker = Card(0, Suit.__, Point.RED_JOKER)
    val listCard_54 =
      listCard_52
        .toBuffer
        .+=(black_joker.setId(53))
        .+=(red_joker.setId(54))
//        .addOne(black_joker.setId(53)) // 添加小王
//        .addOne(red_joker.setId(54)) // 添加大王
        .toSeq

    this.setCards(listCard_54)
  }


  /**
   * 洗牌
   */
  def shuffle: Poker = {

    val cards_ = Random.shuffle(this.getCards())
    setCards(cards_)
  }


  /**
   * 按 (suit/花色, point/点数) 移除单张牌
   */
  def remove(suit_point: (Suit.SuitType, Point.PointType)) = {

    val cards_ =
      cards.filterNot(o =>
        o.suit.equals(suit_point._1) // 花色一致
          &&
          o.point.equals(suit_point._2) // 点数一致
      )

    setCards(cards_)
  }

  /**
   * 生成 JSON
   */
  def buildJson: String = {

    val jsonPoker = ("id" -> this.id) ~ ("cards" -> this.getCards().map { o =>
      (
        ("id" -> o.id) ~
          ("suit" -> o.suit.toString) ~
          ("point" -> o.point.toString)
        )
    })

    compact(render(jsonPoker))
  }

  def setCards(cards: Seq[Card]) = {
    this.cards = cards
    this
  }

  def getCards() = cards

}

/**
 *
 * 单一纸牌类
 *
 * @param id
 * @param suit
 * @param point
 */
case class Card(
                 var id: Long,
                 suit: Suit.SuitType,
                 point: Point.PointType) {

  def setId(id: Int): Card = {
    this.id = id
    this
  }

}

/**
 * 花色枚举
 */
object Suit extends Enumeration {

  type SuitType = Value

  val spade = Value(1, "spade") // 黑桃
  val heart = Value(2, "heart") // 红心
  val club = Value(3, "club") // 梅花
  val diamond = Value(4, "diamond") // 方块
  val __ = Value(0, "") // 空，用于大小王
}

/**
 * 点数枚举
 */
object Point extends Enumeration {

  type PointType = Value

  val ACE = Value(1, "A")
  val _2 = Value(2, "2")
  val _3 = Value(3, "3")
  val _4 = Value(4, "4")
  val _5 = Value(5, "5")
  val _6 = Value(6, "6")
  val _7 = Value(7, "7")
  val _8 = Value(8, "8")
  val _9 = Value(9, "9")
  val _10 = Value(10, "10")
  val JACK = Value(11, "J")
  val QUEEN = Value(12, "Q")
  val KING = Value(13, "K")
  val BLACK_JOKER = Value(14, "BLACK_JOKER")
  val RED_JOKER = Value(15, "RED_JOKER")

}

