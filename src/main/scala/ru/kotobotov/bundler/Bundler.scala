package ru.kotobotov.bundler

import java.io.File

/**
 * Created by Kotobotov.ru on 24.09.2018.
 */
case class Bundler(fileName: String, io: BundlerIo) {
  type PackageContents = Map[String, String]

  def bundle(): Unit = {
    println("CREATING SOURCE CODE BUNDLE")
    val outputFileContent = buildOutput
    val formattedOutputFileContent = org.scalafmt.Scalafmt.format(outputFileContent).get
    //val formattedOutputFileContent = outputFileContent
    io.save(fileName, formattedOutputFileContent)
  }

  def buildOutput: String = {
    val file = io.findFile(fileName)
    val content = transformFile(file).mkString("\n")
    stripComments(content)
  }

  def dependentFiles(file: File, fileLines: List[String]): List[File] = {
    val filesInSameFolder = io.filesInFolder(file.getParentFile)
    val filesFromImport = fileLines.flatMap(filesFromLine)
    filesFromImport ++ filesInSameFolder
  }

  def filesList(queue: List[File],
                contents: Map[File, List[String]]): List[File] = queue match {
    case Nil => Nil
    case file :: t =>
      val fileLines = io.readFile(file)
      val needed = dependentFiles(file, fileLines)
      val nextContents = contents + (file -> fileLines)
      val nextQueue = (needed ++ t).distinct.diff(nextContents.keySet.toSeq)
      filesList(nextQueue, nextContents) ++ needed :+ file
  }

  def transformFile(file: File): List[String] = {
    val allFiles = filesList(List(file), Map.empty).distinct
    val packageContents = allFiles.foldLeft(Map.empty[String, String]) {
      case (contents, `file`) =>
        transformSingleFile(file, contents, forceToRoot = true)
      case (contents, otherFile) => transformSingleFile(otherFile, contents)
    }
    packageContents.map { case (n, c) => formatPackage(n, c) }.toList
  }

  def formatPackage(name: String, content: String): String =
    if (name == "") content
    else
      s"""package $name {
         |$content
         |}""".stripMargin

  def add(pkgName: String,
          content: String,
          contents: PackageContents): PackageContents = {
    val value = contents.getOrElse(pkgName, "") + "\n" + content
    contents.updated(pkgName, value)
  }

  def stripComments(x: String, s: String = "/*", e: String = "*/"): String = {
    val a = x indexOf s
    val b = x indexOf (e, a + s.length)
    if (a == -1 || b == -1) x
    else stripComments(x.take(a) + x.drop(b + e.length), s, e)
  }

  private def transformSingleFile(
      f: File,
      packagesContents: PackageContents,
      forceToRoot: Boolean = false): PackageContents = {
    val lines = io.readFile(f)
    val (pkgLines, rest) = lines.span(_.startsWith("package"))
    val result = rest.map(_.trim).filterNot("".==).mkString("\n")
    pkgLines match {
      case Nil => add("", result, packagesContents)
      case List(pkgLine) =>
        val pkgName = if (forceToRoot) "" else pkgLine.drop("package ".size)
        add(pkgName, result, packagesContents)
      case _ =>
        throw new Exception(
          "Bundler does not support multiple packages declaration")
    }
  }

  private def filesFromLine(line: String): List[File] =
    if (!line.startsWith("import") || Seq("scala", "java").exists(i =>
          line.startsWith(s"import $i")))
      Nil
    else {
      val imported = line.split(" ").tail.mkString
      val packageElements =
        imported.replaceAll("_", "").replaceAll("\\{.*\\}", "").split("\\.")
      val subFolder = io.findFolder(packageElements)
      io.filesInFolder(subFolder)
    }
}
