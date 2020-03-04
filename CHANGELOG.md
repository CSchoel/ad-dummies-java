# Change Log
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/) 
and this project adheres to [Semantic Versioning](http://semver.org/).

## \[Unreleased\]

### Added

* Examples 3.8.1-2

### Changed

\[nothing\]

### Fixed

\[nothing\]

## \[0.6.1\]

### Added

* Examples 3.7.*
* Benchmarks for examples 3.7.*
* Example 2.5.4: test for duplicate inserts

### Changed

* Example 2.5.4: changes order of inserts in test to get better balanced tree and better code coverage

### Fixed

* Example 2.5.4: wrong main method was tested

## \[0.6.0\]

### Added

* tests for examples 2.6.*

### Changed

* Example 2.6.2: name of vertex should be public

### Fixed

* Example 2.6.2: `reachList` should use `Set` instead of `List` and propagate accumulator

## \[0.5.1\]

### Added

* tests for examples 2.5.*

### Changed

* Example 2.5.1: toBTree now produces left-heavy tree if list has size 2
* Example 2.5.6: evalNodesIter should have been public

### Fixed

* Example 2.5.1: one-off error in add-method of ImmutableArrayList

## \[0.4.2\]

### Added

* main methods for all examples in part 1 and 2 that allow to call the
    implemented functions with parameters that can be adjusted in the code
* Javadoc comment about author and source of examples in all java files
* Tests for rest of chapter 4

### Changed

\[nothing\]

### Fixed

* some small bugs discovered in the code for part 2 during the
    implementation of the main methods

## \[0.3.3\]

### Added

* Full implementations for chapter 6

### Changed

\[nothing\]

### Fixed

\[nothing\]

## \[0.3.2\]

### Added

* Full implementations and tests of part 1
* JMH benchmarks for part 1
* Full implementations for chapter 4-5
* Tests for chapter 4 up to example no. 2
* continuous builds with Travis CI
* code coverage reports with Jacoco and Codecov.io

### Changed

\[nothing\]

### Fixed

\[nothing\]