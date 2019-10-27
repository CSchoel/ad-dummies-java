/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java Library project to get you started.
 * For more details take a look at the Java Libraries chapter in the Gradle
 * User Manual available at https://docs.gradle.org/5.5.1/userguide/java_library_plugin.html
 */

plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
    `jacoco`
    id("org.sonarqube") version "2.8"
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}


sourceSets.create("jmh") {
    java.setSrcDirs(listOf("src/jmh/java"))
}

dependencies {
    // Use JUnit Jupiter API for testing.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.1")

    // Use JUnit Jupiter Engine for testing.
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.1")

    "jmhImplementation"(project)
    "jmhImplementation"("org.openjdk.jmh:jmh-core:1.21")
    "jmhAnnotationProcessor"("org.openjdk.jmh:jmh-generator-annprocess:1.21")
}

val test by tasks.getting(Test::class) {
    // Use junit platform for unit tests
    useJUnitPlatform()
}

fun numbersToRegex(numbers: String): String {
    val numberArray = numbers.split(".")
    var regex = ""
    regex += "p${numberArray[0].padStart(2, '0')}.*"
    if (numberArray.size > 1) {
        regex += "c${numberArray[1].padStart(2, '0')}.*"
    }
    if (numberArray.size > 2) {
        regex += "E${numberArray[2].padStart(2, '0')}.*"
    }
    return regex
}

// https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html
tasks {
    register("jmhVerify", type=JavaExec::class) {
        group = "benchmark"
        dependsOn("jmhClasses")
        main = "org.openjdk.jmh.Main"
        classpath = sourceSets["jmh"].runtimeClasspath
        // To enable the built-in stacktrace sampling profiler
        // args(listOf("-prof", "stack"))
        //args(listOf("-h"))
        args(listOf("-i", "1", "-r", "1ms", "-wi", "1", "-w", "1ms"))
    }
    register("jmh", type=JavaExec::class) {
        group = "benchmark"
        dependsOn("jmhClasses")
        main = "org.openjdk.jmh.Main"
        classpath = sourceSets["jmh"].runtimeClasspath
        val benchmarks = project.findProperty("benchmarks")
                ?.toString()
                ?.split(",")
                ?.stream()
                ?.map{x -> numbersToRegex(x)}
                ?.toArray()
        if (benchmarks != null) {
            args(benchmarks.toMutableList())
        }
        // To enable the built-in stacktrace sampling profiler
        // args(listOf("-prof", "stack"))
        //args(listOf("-h"))
        //args(listOf("-i", "1", "-r", "100ms", "-wi", "1", "-w", "100ms"))
    }
}

val jacocoTestReport by tasks.getting(JacocoReport::class) {
    reports {
        xml.isEnabled = true
        html.isEnabled = false
    }
    dependsOn(test)
}

sonarqube {
    properties {
        property("sonar.projectKey", "CSchoel_ad-dummies-java")
        property("sonar.organization", "cschoel")
    }
}