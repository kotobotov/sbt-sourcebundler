sbt-sourcebundler
====================

sbt-sourcebundler is an sbt plugin to merge all source code into one bundle file.

usually heavy used in competition platform where you need submit all code in one file

setup
-----

This is an auto plugin, so you need sbt 0.13.5+. Put this in `project/plugin.sbt`:

```sbtshell
addSbtPlugin("ru.kotobotov" % "sbt-sourcebundler" % "0.1.0")
```

usage
-----

sbt-javaversioncheck is a triggered plugin that is enabled automatically for all projects.
in your `build.sbt` file write this:

```sbtshell
bundleMain := "Main.scala"
```

change `bundleMain` - if you need different starting point than Main.class (Main.class - default value)

to write a bundle just use `sbt bundle` in console