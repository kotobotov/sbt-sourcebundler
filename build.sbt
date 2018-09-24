ThisBuild / version := "0.1.0"
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
//publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
//publishMavenStyle := false

//publishTo := {
//  if (isSnapshot.value) Some(Resolver.sbtPluginRepo("snapshots"))
//  else Some(Resolver.sbtPluginRepo("releases"))
//}

//credentials += Credentials(Path.userHome / ".ivy2" / ".sbtcredentials")

//crossSbtVersions := Seq("0.13.17", "1.2.3")

//resolvers += Resolver.mavenLocal

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.+" % "test"
libraryDependencies += "com.github.writethemfirst" % "approvals-java" % "0.4" % "test"
libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.12.6" % "test"
libraryDependencies += "com.geirsson" %% "scalafmt-core" % "1.3.0"
libraryDependencies += "com.geirsson" %% "scalafmt-cli" % "1.3.0"
