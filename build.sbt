enablePlugins(JavaAppPackaging)

name := """akka-http-rest"""

version := "1.0"

organization := "io.github.asepsaep"

scalaVersion := "2.11.8"

lazy val versions = new {
  val akkaHttp = "10.0.4"
  val akkaHttpSession = "0.4.0"
  val reactiveMongo = "0.12.1"
  val slf4j = "1.7.16"
  val scalatest = "3.0.1"
}

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % versions.akkaHttp,
  "com.typesafe.akka" %% "akka-http-spray-json" % versions.akkaHttp,
  "org.reactivemongo" %% "reactivemongo" % versions.reactiveMongo,
  "com.softwaremill.akka-http-session" %% "core" % versions.akkaHttpSession,
  "com.softwaremill.akka-http-session" %% "jwt" % versions.akkaHttpSession,
  "org.slf4j" % "slf4j-api" % versions.slf4j,
  "org.scalatest" %% "scalatest" % versions.scalatest % "test"
)

scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation")

