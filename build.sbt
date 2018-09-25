ThisBuild / version := "0.1.1"
ThisBuild / organization := "ru.kotobotov"
ThisBuild / description := "sbt plugin to merge all source code into one bundle file"

ThisBuild / licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

lazy val root = (project in file("."))
  .settings(
    sbtPlugin := true,
    name := "sbt-sourcebundler",
    publishMavenStyle := false,
    bintrayRepository := "sbt-sourcebundler",
    bintrayOrganization in bintray := None
  )

scalacOptions := Seq("-deprecation", "-unchecked")
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.+" % "test"
libraryDependencies += "com.github.writethemfirst" % "approvals-java" % "0.4" % "test"
libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.12.6" % "test"