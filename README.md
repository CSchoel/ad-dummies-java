# Java-Referenzimplementierungen für "Algorithmen und Datenstrukturen für Dummies"

[![Build Status](https://travis-ci.com/CSchoel/ad-dummies-java.svg?branch=master)](https://travis-ci.com/CSchoel/ad-dummies-java)
[![codecov](https://codecov.io/gh/CSchoel/ad-dummies-java/branch/master/graph/badge.svg)](https://codecov.io/gh/CSchoel/ad-dummies-java)

[English Readme](README.en.md)

Dieses Repository enthält Referenzimplementierungen aller Algorithmen und Datenstrukturen aus dem Buch ["Algorithmen und Datenstrukturen für Dummies"](https://www.wiley-vch.de/de/fachgebiete/computer-und-informatik/algorithmen-und-datenstrukturen-fuer-dummies-978-3-527-71432-2).

## Paketstruktur

Die Paketstruktur entspricht der Struktur des Buches: Auf der obersten Ebene entsprechen die Paketnamen den Teilen des Buches beginnend mit einem `p` (für engl. *part*) und einer zweistelligen Zahl gefolgt von einem kurzen Bezeichner für den Buchteil (zum Beispiel `p01basics`).
Auf der nächsten Ebene folgen die Kapitel mit einem `c` (für engl. *chapter*) wie zum Beispiel `c01algorithms`.
Innerhalb der Kapitel werden die verschiedenen Beispiele in der Reihenfolge ihres Auftretens im Buch durchnummeriert, immer beginnend mit einem `E` (für engl. *example*).

## Codestil

Die Beispiele sind jeweils so geschrieben, dass die Quelldatei für sich selbst stehen kann ohne Querverweise zu anderen Quelldateien.
Das führt natürlich stellenweise zu dupliziertem Code, macht es aber hoffentlich leichter, die Beispiele auf den ersten Blick zu verstehen.
Aus diesem Grund sind auch zusätzliche Klassen, die unter anderem für das Realisieren von Datenstrukturen nötig sind, stets als innere Klassen des Beispiels selbst realisiert.
In einer echten Anwendung sollte man natürlich bis auf sehr wenige Ausnahmen jede Klasse in eine eigene Quelldatei schreiben.

Der gesamte Code ist inklusive der Kommentare in Englisch geschrieben.
Ich bin persönlich der Ansicht, dass das Lernen von Englisch zum Lernen von Informatik mit dazugehört.

## Tests

Neben dem Quellcode im Ordner `src/main/java` gibt es auch Unittests im Ordner `src/test/java`.
Diese sind mit [JUnit 5](https://junit.org/junit5/) geschrieben und verdeutlichen zum einen, wie die Beispiele verwendet werden können und stellen zum anderen sicher, dass sich keine Fehler einschleichen.
Die Tests können von einer Windows-, Linux- oder Mac-Konsole einfach mit dem Befehl `./gradlew test` gestartet werden.
Dabei wird mit dem bereits mitgelieferten Wrapper-Script automatisch eine aktuelle Version des Buildtools [Gradle](https://gradle.org/) heruntergeladen.
Die einzige Voraussetzung ist, dass bereits ein JDK Version 12 oder höher auf dem Rechner installiert und [über die Umgebungsvariable PATH auffindbar](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html) ist.

## Benchmarks

Da es in der Algorithmik auch oft um die Geschwindigkeit von Algorithmen geht, gibt es zu einigen Beispielen auch Benchmarks im Ordner `src/jmh/java`.
Anders als zum Beispiel C/C++ wird Java-Code in einer virtuellen Maschine (der JVM) ausgeführt, wo er sich die Prozessorzeit mit dem [Garbage Collector](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html) teilt und während der Laufzeit per [Just-in-time compilation](https://www.oracle.com/technetwork/articles/java/architect-evans-pt1-2266278.html) optimiert wird.
Das macht das Leben eines Programmierers viel bequemer, aber es macht auch die Messung der tatsächlichen Laufzeit einer Methode sehr kompliziert.
Glücklicherweise gibt es den [Java Microbenchmarking Harness (JMH)](https://openjdk.java.net/projects/code-tools/jmh/), der einem dabei hilft, viele typische Probleme zu umgehen (wie z.B. dass die JVM vor dem Benchmark "aufgewärmt" werden sollte).
Alle Benchmarks in diesem Repository wurden daher mit JMH geschrieben.
Sie lassen sich ähnlich wie die Tests mit Gradle über den Befehl `./gradlew jmh` starten.

**Vorsicht:** Die Benchmarks sind Belastungstests, die viel Leistung und Zeit in Anspruch nehmen.
In der Regel ist es daher sinnvoller, nur den einzelnen Benchmark laufen zu lassen, der einen interessiert.
Dafür gibt es den Befehl `./gradlew jmh -Pbenchmarks=Benchmarkliste`, wobei `Benchmarkliste` eine mit Komma getrennte Liste einzelner Benchmarks ist.
Die Benchmarks werden in der Form `p.c.e` angegeben, wobei `p`, `c` und `e` jeweils die Nummer des Buchteils, Kapitels und Beispiels sind.
Die Benchmarks aus der Klasse `p01basics.c02quality.E01WeightBenchmark` lassen sich also zum Beispiel mit dem Befehl `./gradlew jmh -Pbenchmarks=1.2.1` ausführen.
Es ist auch möglich, das Zeichen `*` als Platzhalter zu verwenden, um zum Beispiel alle Benchmarks aus Kapitel 2 mit `1.2.*` auszuwählen.

## Integration in IntelliJ

Um dieses Projekt in IntelliJ (oder einer anderen IDE) zu öffnen, empfiehlt es sich, es als Gradle-Projekt zu importieren.
In IntelliJ sind dazu die folgenden Schritte nötig (englische Namen für Menüitems angenommen):

* File → New → Projekt from existing sources...
* Die `build.gradle.kts` aus diesem Projekt auswählen
    * Achtung: Ich benutze die [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html), die es erst seit Gradle 5.0 gibt. Es kann also eventuell sein, dass ein Update von IntelliJ oder einer veralteten Gradle-Version auf dem System nötig ist, bevor der Import funktioniert.
* "Create separate module per source set" auswählen
* "Use default Gradle wrapper" auswählen
* Unter "Gradle JVM" das korrekte JDK (mindestens JDK 12) auswählen
* OK klicken

Wenn der Import funktioniert hat, sollte man im Gradle Tool Window (zu erreichen unter View → Tool Windows → Gradle) zum Beispiel den Task `test` (unter "verification") einfach mit einem Doppelklick starten können.