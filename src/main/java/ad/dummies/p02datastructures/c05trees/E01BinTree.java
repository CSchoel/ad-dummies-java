package ad.dummies.p02datastructures.c05trees;

import java.util.Arrays;

/**
 * <p>Example from the german book "Algorithms and data structures for
 * dummies":</p>
 *
 * <p>A. Gogol-Döring and T. Letschert, <i>Algorithmen und Datenstrukturen für
 * Dummies</i>. Weinheim, Germany: Wiley-VCH, 2019.</p>
 *
 * <p>The current version of these examples with unit tests and benchmarks can
 * be found <a href="https://github.com/CSchoel/ad-dummies-java">on GitHub</a>.
 * </p>
 *
 * @author Christopher Schölzel
 */
public class E01BinTree {
    // Note: Since the algorithm does not specify which kind of List we
    // should use I chose an immutable array list that makes excessive use
    // of data sharing between instances to increase the performance of
    // splitInHalf().
    // WARNING: The downside of this approach is that it can lead to memory
    // leaks if you create very large lists. Even if you only use a small part
    // of such a list in your code, it still uses the original large array
    // internally and thus prevents the garbage collector from freeing memory.
    public static class ImmutableArrayList {
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
            assert lastIndex < data.length;
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
            if (i < 0 || i >= size()) {
                String msg = String.format(
                        "Cannot access index %d for list of length %d.",
                        i, size()
                );
                throw new IndexOutOfBoundsException(msg);
            }
            return data[firstIndex + i];
        }

        public ImmutableArrayList[] splitInHalf() {
            if (size() == 1) {
                //FIXME: this special case is only needed to yield a left-heavy
                // tree for a two-element list. The function would be more
                // consistent if we just removed this if and produced a right-
                // heavy tree.
                return new ImmutableArrayList[]{
                        new ImmutableArrayList(data, firstIndex, firstIndex + 1),
                        new ImmutableArrayList(0)
                };
            }
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

    public static void main(String[] args) {
        int[] data = {-5, 1, 8, 6, 2};
        ImmutableArrayList lst = new ImmutableArrayList(10);
        for(int x: data) { lst = lst.add(x); }
        BinTree tree = lst.toBTree();
        class TreeString {
            BinTree t;
            public TreeString(BinTree t) {
                this.t = t;
            }
            public String toString() {
                if (t instanceof Empty) {
                    return "Empty";
                } else {
                    Node tNode = (Node) t;
                    return String.format(
                            "Node(%d, %s, %s)",
                            tNode.value,
                            new TreeString(tNode.left),
                            new TreeString(tNode.right)
                    );
                }
            }
        }
        System.out.printf("%s.toBTree() = %s\n", Arrays.toString(data), new TreeString(tree));
        System.out.printf("  %s.sum() = %d\n", new TreeString(tree), tree.sum());
        System.out.printf("%s.depth() = %d\n", new TreeString(tree), tree.depth());
    }
}
