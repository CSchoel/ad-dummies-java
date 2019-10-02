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
public class E05Heap {


    public static class Heap {
        private static class Node {
            int value;
            Node left; // FIXME: typo in book: Heap -> Node
            Node right;
            public Node(int value, Node left, Node right) {
                this.value = value;
                this.left = left;
                this.right = right;
            }
        }
        private Node root = null;

        public void insert(int x) {
            insert(root, x);
        }

        public void insert(Node node, int x) {
            if (root == null) {
                root = new Node(x, null, null);
                return;
            }
            int valueDown;
            if (x >= node.value) {
                valueDown = node.value;
                node.value = x;
            } else {
                valueDown = x;
            }
            if (node.left == null) {
                node.left = new Node(x, null, null);
            } else if (node.right == null) {
                node.right = new Node(x, null, null);
            } else {
                insert(node.left.value > node.right.value ? node.left : node.right, valueDown);
            }
        }

        public int max() {
            return root.value;
        }
    }

    public static void main(String[] args) {
        Heap h = new Heap();
        int[] data = {2, 42, 9, 30, 4, 5, 60};
        for (int x: data) { h.insert(x); }
        System.out.printf("Heap(%s).max() = %d\n", Arrays.toString(data), h.max());
    }
}
