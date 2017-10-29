name := "streaming-final"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-streaming" % "2.0.1",
  "org.apache.spark" %% "spark-streaming-twitter" % "1.6.3"
)


        