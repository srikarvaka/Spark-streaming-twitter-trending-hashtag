name := "streaming-final"

version := "1.0"

scalaVersion := "2.11.6"

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
   {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
   }
}

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-streaming" % "1.5.2",
  "org.apache.spark" %% "spark-streaming-twitter" % "1.6.2"
)

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

        

        
