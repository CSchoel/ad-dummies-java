package aud.dummies.benchmarks.p01basics.c02quality;

import ad.dummies.p01basics.c02quality.E01Weight;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1) // execute each benchmark on it's own JVM
@Warmup(iterations = 4, time = 1)
@Measurement(iterations = 5, time = 1) // how many measurement iterations?
public class E01WeightBenchmark {

    @State(Scope.Thread)
    public static class WeightSetup {
        @Param({"10", "100", "1000", "10000", "100000"})
        private int length;
        private double[] weights;

        @Setup
        public void setup() {
            // TODO: proper worst case
            weights = new Random().doubles().limit(length).toArray();
        }
    }

    @Benchmark
    public int benchmarkWeight1(WeightSetup state) {
        return E01Weight.minWeight1(state.weights);
    }

    @Benchmark
    public int benchmarkWeight2(WeightSetup state) {
        return E01Weight.minWeight2(state.weights);
    }
}
