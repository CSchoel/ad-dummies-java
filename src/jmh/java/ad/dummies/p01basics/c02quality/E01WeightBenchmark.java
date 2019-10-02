package ad.dummies.p01basics.c02quality;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
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
 * @see E01Weight
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1) // execute each benchmark on it's own JVM
@Warmup(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
public class E01WeightBenchmark {

    @State(Scope.Thread)
    public static class WeightSetup {
        @Param({"10", "100", "1000", "10000"})
        private int length;
        private double[] weights;

        @Setup
        public void setup() {
            // TODO: proper worst case
            weights = new double[length];
            Arrays.fill(weights, 80);
            weights[length - 1] = 75;
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
