sbt-sourcebundler
====================

sbt-sourcebundler is an sbt plugin to merge all source code into one bundle file.

usually heavy used in competition platform where you need submit all code in one file

setup
-----

This is an auto plugin, so you need sbt 0.13.5+. Put this in `project/plugin.sbt`:

```sbtshell
addSbtPlugin("ru.kotobotov" % "sbt-sourcebundler" % "0.1.1")
```

usage
-----

to write a bundle just use `sbt bundle` in console

sbt-sourcebundler is a triggered plugin that is enabled automatically for all projects.

by default plugin search for all Main.classes and generate bundle for them
but if you like you can specify your Main.class by  
writing in your `build.sbt` file this:

```sbtshell
bundleMain := "Main.scala"
```

where `bundleMain` - is pointing to name of your main class.

