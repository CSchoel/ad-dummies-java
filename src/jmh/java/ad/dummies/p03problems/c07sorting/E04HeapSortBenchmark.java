package ad.dummies.p03problems.c07sorting;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
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
 * @see E04HeapSort
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
// we need to increase the stack size, because we need O(n) stack space in worst case
@Fork(1) // execute each benchmark on it's own JVM
@Warmup(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
public class E04HeapSortBenchmark {
    @State(Scope.Thread)
    public static class DescendingSetup {
        @Param({"10", "100", "1000", "10000"})
        private int length;
        private Integer[] data;

        @Setup
        public void setup() {
            data = new Integer[length];
            for(int i = 0; i < data.length; i++) {
                data[i] = data.length - i;
            }
        }
    }

    @State(Scope.Thread)
    public static class AscendingSetup {
        @Param({"10", "100", "1000", "10000"})
        private int length;
        private Integer[] data;

        @Setup
        public void setup() {
            data = new Integer[length];
            for(int i = 0; i < data.length; i++) {
                data[i] = i;
            }
        }
    }

    @State(Scope.Thread)
    public static class RandomSetup {
        @Param({"10", "100", "1000", "10000"})
        private int length;
        private Integer[] data;

        @Setup
        public void setup() {
            data = new Random(667)
                    .ints(length, -length/10, length/10)
                    .boxed().toArray(Integer[]::new);
        }
    }

    @Benchmark
    public Integer[] heapSortDescending(DescendingSetup state) {
        E04HeapSort.heapSort(state.data);
        return state.data;
    }

    @Benchmark
    public Integer[] heapSortAscending(AscendingSetup state) {
        E04HeapSort.heapSort(state.data);
        return state.data;
    }

    @Benchmark
    public Integer[] heapSortRandom(RandomSetup state) {
        E04HeapSort.heapSort(state.data);
        return state.data;
    }

    @Benchmark
    public Integer[] priorityQueueSortDescending(DescendingSetup state) {
        E04HeapSort.priorityQueueSort(state.data);
        return state.data;
    }

    @Benchmark
    public Integer[] priorityQueueSortAscending(AscendingSetup state) {
        E04HeapSort.priorityQueueSort(state.data);
        return state.data;
    }

    @Benchmark
    public Integer[] priorityQueueSortRandom(RandomSetup state) {
        E04HeapSort.priorityQueueSort(state.data);
        return state.data;
    }
}
