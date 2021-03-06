sbt-sourcebundler [ ![Download](https://api.bintray.com/packages/kotobotov/sbt-sourcebundler/sbt-sourcebundler/images/download.svg) ](https://bintray.com/kotobotov/sbt-sourcebundler/sbt-sourcebundler/_latestVersion)
====================

sbt-sourcebundler is an sbt plugin to merge all source code into one source file (many scala to 1 scala).
it's useful to submit solutions in competition platform (like codingame, topcoders, codeforces)
only working for your scala sourcecode (not packing any jars and so on), result is another scala file 

based on [codingame-scala-kit](https://github.com/huiwang/codingame-scala-kit)

motivation
============
usually code competition platform accept submits only in one single file, so if you like reuse you library code you need bundle it into single file. Let's sbt do this job.

setup
-----

This is an auto plugin, so you need sbt 0.13.5+. Put this in `project/plugin.sbt`:

```sbtshell
addSbtPlugin("ru.kotobotov" % "sbt-sourcebundler" % "0.1.1")
```

usage
-----

there's no need to configure

to write a bundle just use `bundle` in sbt console

sbt-sourcebundler is a triggered plugin that is enabled automatically for all projects.

by default plugin search for all Main.classes and generate bundle for them into project target folder
but if you like you can specify your Main.class and destination folder in `build.sbt` file like this:

```sbtshell
bundleMain := "Main.scala" // name of your main class
bundleTarget := "./target" // existing destination folder
bundleWithoutPackages := "true" // fixing wierd bug in codingame with loading packeges
 //(so than it's true - we just don't use packeges and put everything in one namespase
 // but it may cause problem having the same names) default is FALSE
```


competition platform
-----------------
top competition platform where you can practicing with scala (and use sourcebundler):

- ![#696969](https://static.codingame.com/assets/spritesheets/cg-footer-huge.c6333cce.png)
[www.codingame.com](https://www.codingame.com/servlet/urlinvite?u=1717345)
personal my favorite for good visualisation, huge community, and not only solving puzzle also ability competition with ai bots

- [www.codeforces.com](https://www.codeforces.com)
the largest competition site with a good reputation

honourable mention:

- [www.codewars.com](https://www.codewars.com)
- [www.hackerrank.com](https://www.hackerrank.com)
- [www.codechef.com](https://www.codechef.com)
