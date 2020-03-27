package com.github.mrpowers.my.cool.project

import org.apache.spark.{SparkConf, SparkContext}

import org.json4s._
import org.json4s.jackson.JsonMethods._

object JsonReader extends App {
  case class Data (
      id: String,
      country: String,
      points: String,
      title: String,
      variety: String,
      winery: String
  )

  val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("CountingSheep")

  val sc = new SparkContext(conf)

  val lines = sc.textFile("./data/winemag-data-130k-v2.json")

  implicit val formats = DefaultFormats

  println(lines.count())

  for (line <- lines) {
    val decode = parse(line).extract[Data]

    println(decode.title)
  }

  //lines.filter(f => f.contains("erm")).foreach(println)
}

