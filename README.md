# Spark-streaming-twitter-trending-hashtag
Real time twitter stream data analysis using Spark-streaming

# Build definition
- Sbt is used to build .jar files and include all dependencies
- Assembly plugin is used to make *Uber Jar* (fat .jar file) with all needed dependencies

# Dependencies and Spark versions
## With Spark 1.6.x or lower (used in this project)
- use "org.apache.spark" as organization
- use "spark-streaming-twitter" as artifact ID (V 1.6.2)
## With Spark 2.x
- use "org.apache.bahir" as org
- use "spark-streaming-twitter" as artifact ID (V 1.6.2)

# Possible exceptions

1. *org.apache.spark.twitter.twitterutils* := java.lang.ClassNotFoundException

This usually occures during runtime. Sbt includes twitterutils .jar during compilation but excludes it during runtime. This can be addressed by including assembly.sbt in ./project and add sbt-plugin "com.eed3si9n".

For more information, you can use this https://stackoverflow.com/questions/28165032/java-lang-noclassdeffounderror-org-apache-spark-streaming-twitter-twitterutils

2. *org.apache.spark.logging* := java.lang.ClassNotFoundException

This can be solved by using right version of spark streaming as discussed above.

One can refer to this for more information
https://stackoverflow.com/questions/38893655/spark-twitter-streaming-exception-org-apache-spark-logging-classnotfound

# Setup and Run the project
- install sbt
- Clone the project at: git clone https://github.com/Sri7Techies/Spark-streaming-twitter-trending-hashtag.git
- Change directory to Spark-streaming-twitter-trending-hashtag and run sbt assembly 
- This will build a fat .jar file in target/scala_2.11 directory (takes some time)
- use spark-submit \
  --class classpath \
  --master local[*] \
  \</path to JAR files\> 
  to run the app (assuming you've setup spark-submit in your environment variables)
- Dont forget to include twitter oauth details in twitter4j.properties file

# Author
- Srikar Reddy Vaka (vakasrikar\@gmail\.com)
- For any clarifications you can contact to above email address 

:+1: Happy to help and cheers 
