name := """app"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
    guice,
    javaJpa,
    "mysql" % "mysql-connector-java" % "8.0.16",
    "org.hibernate" % "hibernate-core" % "5.4.30.Final"
)