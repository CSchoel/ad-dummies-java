package ad.dummies.p01basics.c01algorithms;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>JMH benchmarks for an example from the german book "Algorithms and data
 * structures for dummies":</p>
 *
 * <p>A. Gogol-Döring and T. Letschert, <i>Algorithmen und Datenstrukturen für
 * Dummies</i>. Weinheim, Germany: Wiley-VCH, 2019.</p>
 *
 * <p>The current version of these examples with unit tests and benchmarks can
 * be found <a href="https://github.com/CSchoel/ad-dummies-java">on GitHub</a>.
 * </p>
 *
 * @author Christopher Schölzel
 * @see E01Summation
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1) // execute each benchmark on it's own JVM
@Warmup(iterations = 2, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.MILLISECONDS)
public class E01SummationBenchmark {

    @State(Scope.Thread)
    public static class SumSetup {
        @Param({"10", "100", "1000", "10000", "100000"})
        private int upTo;
    }

    @Benchmark
    public int benchmarkSummation1(SumSetup state) {
        return E01Summation.summation1(state.upTo);
    }

    @Benchmark
    public int benchmarkSummation2(SumSetup state) {
        return E01Summation.summation2(state.upTo);
    }
}