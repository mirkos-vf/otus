package com.github.mrpowers.my.cool.project

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait Context {

	lazy val sparkConf: SparkConf = new SparkConf()
		.setAppName("Learn Spark")
		.setMaster("local[*]")
		.set("spark.cores.max", "2")

	lazy val sc: SparkSession = SparkSession
		.builder()
		.config(sparkConf)
		.getOrCreate()
}

object Uber extends App with Context {

	sc.sparkContext.setLogLevel("ERROR")

    //val crime = sc.read.option("header", "true").csv("./data/crime.csv")
    val crime = sc.read.option("header", "true").csv("./data/crime.csv")
    //val codes = sc.read.option("header", "true").csv("./data/offense_codes.csv")
    val codes = sc.read.option("header", "true").csv("./data/offense_codes.csv")

	val dfCrime = crime.toDF().as("crime")
	val dfCodes = codes.toDF().as("codes")

	val df = dfCrime.join(dfCodes, dfCrime("OFFENSE_CODE") === dfCodes("CODE"), "inner")

	val crimesTotal = df
			.select( "DISTRICT")
    		.groupBy("DISTRICT")
    		.count()
    		.as("crimesTotal")

	crimesTotal.show()

	val frequent_crime_types = df
            .groupBy("DISTRICT", "OFFENSE_CODE")
            .count()
            .select("*")

	frequent_crime_types.show(100)
}
