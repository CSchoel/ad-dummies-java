package aud.dummies.benchmarks.p01basics.c01algorithms;

import ad.dummies.p01basics.c01algorithms.E01Summation;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

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