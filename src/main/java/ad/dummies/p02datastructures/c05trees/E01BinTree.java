package ad.dummies.p02datastructures.c05trees;

import java.util.function.Consumer;

public class E01BinTree {
    // Note: Since the algorithm does not specify which kind of List we
    // should use I chose an immutable array list that makes excessive use
    // of data sharing between instances to increase the performance of
    // splitInHalf().
    // WARNING: The downside of this approach is that it can lead to memory
    // leaks if you create very large lists. Even if you only use a small part
    // of such a list in your code, it still uses the original large array
    // internally and thus prevents the garbage collector from freeing memory.
    public class ImmutableArrayList {
        private final int[] data; // must be private to preserve immutability
        public final int firstIndex; // first index inclusively
        public final int lastIndex;  // last index exclusively
        public ImmutableArrayList(int capacity) {
            this.data = new int[capacity];
            firstIndex = 0;
            lastIndex = 0;
        }
        private ImmutableArrayList(int[] data, int firstIndex, int lastIndex) {
            this.data = data;
            this.firstIndex = firstIndex;
            this.lastIndex = lastIndex;
        }
        public int size() {
            return lastIndex - firstIndex;
        }

        public ImmutableArrayList add(int x) {
            assert lastIndex < data.length - 1;
            data[size()] = x;
            return new ImmutableArrayList(data, firstIndex, lastIndex + 1);
        }

        public ImmutableArrayList withoutFirst() {
            return new ImmutableArrayList(data, firstIndex + 1, lastIndex);
        }

        public ImmutableArrayList sublist(int from, int to) {
            return new ImmutableArrayList(data, firstIndex + from, firstIndex + to);
        }

        public int get(int i) {
            if (i < firstIndex || i >= lastIndex) {
                String msg = String.format(
                        "Cannot access index %d for list of length %d.",
                        i, size()
                );
                throw new IndexOutOfBoundsException(msg);
            }
            return data[firstIndex + i];
        }

        public ImmutableArrayList[] splitInHalf() {
            int splitIndex = firstIndex + size() / 2;
            return new ImmutableArrayList[]{
                    new ImmutableArrayList(data, firstIndex, splitIndex),
                    new ImmutableArrayList(data, splitIndex, lastIndex)
            };
        }

        public BinTree toBTree() {
            if (size() == 0) { return new Empty(); }
            ImmutableArrayList[] split = withoutFirst().splitInHalf();
            return new Node(get(0), split[0].toBTree(), split[1].toBTree());
        }
    }

    public static abstract class BinTree {
        public abstract int sum();
        public abstract int depth();
    }
    public static class Node extends BinTree {
        public final int value;
        public final BinTree left;
        public final BinTree right;
        public Node(int value, BinTree left, BinTree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public int sum() {
            return left.sum() + right.sum() + value;
        }

        @Override
        public int depth() {
            return Math.max(left.depth(), right.depth()) + 1;
        }
    }
    public static class Empty extends BinTree {
        @Override
        public int sum() {
            return 0;
        }

        @Override
        public int depth() {
            return 0;
        }
    }
}
