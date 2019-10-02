package ad.dummies.p01basics.c03datastructures;

import java.util.function.Function;

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
public class E08StructuralRecursion {
    /* Note: Since has no pattern matching features, we use an object oriented
    * approach that achieves the same recursive call structure. */

    public interface IntList {
        int listSum();
        IntList insSort();
        IntList insert(int x);
        IntList append(IntList other);
    }
    public static class Nil implements IntList {
        public int listSum() { return 0; }
        public IntList insSort() { return this; }
        public IntList insert(int x) { return new Cons(x, this); }
        public IntList append(IntList other) { return other; }
    }
    public static class Cons implements IntList {
        private final int value;
        private final IntList next;
        public Cons(int value, IntList next) {
            this.value = value;
            this.next = next;
        }
        public int value() { return value; }
        public IntList next() { return next; }
        public int listSum() {
            return value + next.listSum();
        }
        public IntList insSort() {
            return next.insSort().insert(value);
        }
        public IntList insert(int x) {
            if (x < value) {
                return new Cons(x, this);
            }
            return new Cons(value, next.insert(x));
        }
        public IntList append(IntList other) {
            return new Cons(value, next.append(other));
        }
    }

    public interface IntTree {
        int treeSum();
        IntList treeToList();
    }
    public static class Leaf implements IntTree {
        private final int value;
        public Leaf(int value) { this.value = value; }
        public int value() { return value; }
        public int treeSum() { return value; }
        public IntList treeToList() {
            return new Cons(value, new Nil());
        }
    }
    public static class Node implements IntTree {
        private final IntTree left;
        private final IntTree right;
        public Node(IntTree left, IntTree right) {
            this.right = right;
            this.left = left;
        }
        public IntTree left() { return left; }
        public IntTree right() { return right; }
        public int treeSum() {
            return left.treeSum() + right.treeSum();
        }
        public IntList treeToList() {
            return left.treeToList().append(right.treeToList());
        }
    }

    public static void main(String[] args) {
        Function<IntList, String> lToS = lst -> {
            StringBuilder sb = new StringBuilder("");
            int n = 0;
            while(lst instanceof Cons) {
                Cons lstCons = (Cons) lst;
                sb.append("Cons("+lstCons.value+", ");
                n++;
                lst = lstCons.next;
            }
            sb.append(" Nil");
            for(int i = 0; i < n; i++) { sb.append(")"); }
            return sb.toString();
        };
        IntList lst = new Nil();
        int x = 4;
        System.out.printf("%s.insert(%d) = %s\n", lToS.apply(lst), x, lToS.apply(lst.insert(x)));
        lst = new Cons(8, new Cons(-2, new Cons(17, new Nil())));
        System.out.printf("%s.listSum() = %d\n", lToS.apply(lst), lst.listSum());
        lst = new Cons(8, new Cons(-2, new Nil()));
        IntList lst2 = new Cons(17, new Cons(0, new Nil()));
        System.out.printf("%s.append(%s) = %s\n", lToS.apply(lst), lToS.apply(lst2), lToS.apply(lst.append(lst2)));
        lst = lst.append(lst2);
        System.out.printf("%s.insSort() = %s\n", lToS.apply(lst), lToS.apply(lst.insSort()));
        System.out.println();

        class TreeString {
            IntTree t;
            public TreeString(IntTree t) {
                this.t = t;
            }
            public String toString() {
                if (t instanceof Leaf) { return "Leaf("+((Leaf) t).value+")"; }
                Node tNode = (Node) t;
                return "Node(" + new TreeString(tNode.left) + ", " + new TreeString(tNode.right) + ")";
            }
        }
        IntTree tree = new Node(new Node(new Node(new Leaf(1), new Leaf(2)), new Leaf(3)), new Node(new Leaf(4), new Leaf(5)));
        System.out.printf("%s.treeSum() = %d\n", new TreeString(tree), tree.treeSum());
        System.out.printf("%s.treeToList() = %s\n", new TreeString(tree), lToS.apply(tree.treeToList()));
    }
}
