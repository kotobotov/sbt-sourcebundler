package ru.kotobotov.bundler

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object SourceBundlerPlugin extends sbt.AutoPlugin {

  object autoImport {
    libraryDependencies += "com.geirsson" %% "scalafmt-core" % "1.3.0"
    libraryDependencies += "com.geirsson" %% "scalafmt-cli" % "1.3.0"
    lazy val bundleMain =
      settingKey[String]("start class name (Main.class) for your bundle")
    lazy val bundle = taskKey[String]("pack source code to one file")
  }

  import autoImport._
  override def trigger = allRequirements
  override def requires = JvmPlugin
  override lazy val projectSettings = bundleSettings

  def bundleSettings: Seq[Setting[_]] = Seq(
    libraryDependencies += "com.geirsson" %% "scalafmt-core" % "1.3.0",
    libraryDependencies += "com.geirsson" %% "scalafmt-cli" % "1.3.0",
    bundleMain := "all",
    bundle := {
      if (bundleMain.value.isEmpty || bundleMain.value == "all") {
        println("bundleMain not specified - try to find all Main.class'es")
        (Compile / discoveredMainClasses).value
          .foreach(cls => BundlerMain(cls.split("\\.").reverse.head))
        ""
      } else BundlerMain(bundleMain.value)
    }
  )
}

object BundlerMain {
  def apply(mainClass: String): String = {
    if (mainClass.isEmpty) {
      println("Input file name must be provided")
      sys.error("Main.class file name error.")
    }
    val mainPrepared =
      if (mainClass.contains(".scala")) mainClass else mainClass + ".scala"
    Bundler(mainPrepared, StdBundlerIo()).bundle()
    "return"
  }
}
