resolvers += "Spark Packages Repo" at "http://dl.bintray.com/spark-packages/maven"

name := "json_reader_mirkos"

version := "0.0.1"

scalaVersion := "2.11.12"

val sparkVersion = "2.4.4"

libraryDependencies ++= Seq(
	"org.apache.spark"  %%  "spark-core"    % sparkVersion   % "provided",
	"org.apache.spark"  %%  "spark-sql"     % sparkVersion,
	"org.apache.spark"  %%  "spark-mllib"   % sparkVersion
)

libraryDependencies += "MrPowers" % "spark-fast-tests" % "0.20.0-s_2.11" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.2.0"

libraryDependencies += "com.typesafe.play" % "play-json_2.11" % "2.4.6"

// test suite settings
fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")
// Show runtime of tests
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")

// JAR file settings

// don't include Scala in the JAR file
//assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

// Add the JAR file naming conventions described here: https://github.com/MrPowers/spark-style-guide#jar-files
// You can add the JAR file naming conventions by running the shell script
