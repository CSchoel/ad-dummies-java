# Java reference implementations for "Algorithms and data structures for dummies"

[German Readme](README.md)

This repository contains reference implementations of all algorithms and data structures in the book ["Algorithms and Data Structures for Dummies"](https://www.wiley-vch.de/de/fachgebiete/computer-und-informatik/algorithmen-und-datenstrukturen-fuer-dummies-978-3-527-71432-2) (only available in German).

## Package structure

The package structure resembles the structure of the book: On the top level the package names correspond to the parts of the book, starting with a `p` and a two digit number followed by a short identifier for the book part (e.g. `p01basics`).
On the next level ar the chapters with a leading `c` like `c01algorithms`.
Within the chapter the examples are numbered in the order in which they appear in the book with a leading `E`.

## Code style

The examples are written so that the source file is self sufficient without any links to other source files.
This of course leads to duplicated code, but hopefully it also facilitates understanding the examples at first glance.
For this reason additional classes, which are for instance required for the realization of data structures, are always implemented as inner classes of the example.
In a real application one should of course (barring very few exceptions) write each class into its own source file.

## Tests

Additional to the source code in the folder `src/main/java` there are also unit tests in the folder `src/test/java`.
They are written using [JUnit 5](https://junit.org/junit5/) to illustrate how the examples can be applied and to ensure that the code is free of bugs.
On Windows-, Linux- and Mac-terminals the tests can be run with the command `./gradlew test`
This command will use the existing wrapper script to download a current version of the build tool [Gradle](https://gradle.org/).
The only requirement is that JDK version 12 or higher has to be installed and [accesible through the environment variable PATH](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html).

## Benchmarks

Due to the nature of algorithmics to talk about the speed of algorithms there are also benchmarks for some examples in the folder `src/jmh/java`.
In contrast to C/C++, Java code is executed in a virtual machine (the JVM), where it shares CPU time with the [garbage collector](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html) and is optimized by the [just-in-time compiler](https://www.oracle.com/technetwork/articles/java/architect-evans-pt1-2266278.html) at runtime.
This makes life easier for the programmer, but it also makes measuring the actual execution time of a method very complicated.
Thankfully we have the [Java Microbenchmarking Harness (JMH)](https://openjdk.java.net/projects/code-tools/jmh/), which helps to alleviate some of the typical pitfalls (such as the fact that the JVM should be "warmed up" before the benchmark).
Therefore, all benchmarks in this repository are written using JMH.
Similar to the tests they can be run with the command `./gradlew jmh`.

**Caution**: Benchmarks are strain tests that demand both performance and time.
Therefore it is usually more sensible to only run the single benchmark that you are interested in.
For this you can use the command `./gradlew jmh -Pbenchmarks=ListOfBenchmarks` where `ListOfBenchmarks` is a comma separated list of benchmarks.
Each benchmark is given in the form `p.c.e` where `p`, `c` and `e` stand for the number of the part, chapter and example in the book.
For example, the benchmarks defined in the class `p01basics.c02quality.E01WeightBenchmark` can be run with the command `./gradlew jmh -Pbenchmarks=1.2.1`.
It is also possible to use `*` as a wildcard character, i.e. to run all benchmarks from chapter 2 with `1.2.*`.

## Integration in IntelliJ

To open this project in IntelliJ (or any other IDE) it is advisable to import it as a Gradle project.
In IntelliJ this requires the following steps:

* File → New → Projekt from existing sources...
* Choose the file `build.gradle.kts` from this project
    * Caution: I use the [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) which is only available since Gradle 5.0.
        Due to this an update of IntelliJ or Gradle can be required.
* Select "Create separate module per source set"
* Select "Use default Gradle wrapper"
* Under "Gradle JVM" select the correct JDK (at least JDK 12)
* click "OK"

If the import works, you should be able to open the Gradle Tool Window (using View → Tool Windows → Gradle) and start the task `test` with a double click.