package me.srikar.spark.streaming.twitter

/**
  * Created by Srikar Vaka on 10/15/2017.
  */

import  org.apache.spark.rdd

import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.DStream






object twitterStreaming {

  val sparkConf = new SparkConf().
    setAppName("spark-twitter-stream-example").
    setMaster("local[*]")
  val sparkContext = new SparkContext(sparkConf)

  val streamingContext = new StreamingContext(sparkContext, Seconds(5))

  def main(args: Array[String]): Unit = {

    sparkContext.setLogLevel("WARN")

    //val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret) = args.take(4)
    //val filters = args.takeRight(args.length - 4)

//    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
//    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
//    System.setProperty("twitter4j.oauth.accessToken", accessToken)
//    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)


    //count top tweets in last 't' seconds



    val stream = TwitterUtils.createStream(streamingContext, None)

    val hashTags: DStream[String] = stream.flatMap(status => status.getText.split(" ").filter(_.startsWith("#")))


    //Top tweets in the window of  10 seconds

    def tweetCounts(tags:DStream[String], t:Int): DStream[(Int,String)] = {

      val topTags: DStream[(Int, String)] = tags.map((_, 1)).reduceByKeyAndWindow(_ + _, Seconds(t))
        .map { case (topic, count) => (count, topic) }
        .transform(_.sortByKey(false))
      return  topTags

    }

    def printTopTopics(topTags:DStream[(Int,String)]): Unit = {
      topTags.foreachRDD( rdd => {
        val topList = rdd.take(10)
        println("\nPopular topics in last 10 seconds (%s total):".format(rdd.count()))
        topList.foreach { case (count, tag) => println("%s (%s tweets)".format(tag, count)) }
      })
    }


    val topCounts10 = tweetCounts(hashTags, 10)

    printTopTopics(topCounts10)


    //start the streaming operation and continue till killed

    streamingContext.start()
    streamingContext.awaitTermination()



  }



}
