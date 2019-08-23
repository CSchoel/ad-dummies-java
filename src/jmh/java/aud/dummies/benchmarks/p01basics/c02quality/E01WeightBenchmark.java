package aud.dummies.benchmarks.p01basics.c02quality;

import ad.dummies.p01basics.c02quality.E01Weight;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1) // execute each benchmark on it's own JVM
@Warmup(iterations = 5, time = 2)
@Measurement(iterations = 10, time = 2) // how many measurement iterations?
public class E01WeightBenchmark {
    @Benchmark
    public int benchmarkWeight1() {
        double[] weights = new Random().doubles().limit(10000).toArray();
        return E01Weight.minWeight1(weights);
    }
}
