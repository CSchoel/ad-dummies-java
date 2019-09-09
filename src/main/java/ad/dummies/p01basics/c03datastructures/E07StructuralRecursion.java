package ad.dummies.p01basics.c03datastructures;

public class E07StructuralRecursion {
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
        public int treeSum() { return 0; }
        public IntList treeToList() {
            return new Nil();
        }
    }
    public static class Node implements IntTree {
        private final int value;
        private final IntTree left;
        private final IntTree right;
        public Node(int value, IntTree right, IntTree left) {
            this.value = value;
            this.right = right;
            this.left = left;
        }
        public int value() { return value; }
        public IntTree left() { return left; }
        public IntTree right() { return right; }
        public int treeSum() {
            return value + left.treeSum() + right.treeSum();
        }
        public IntList treeToList() {
            return left.treeToList().append(new Cons(value, left.treeToList()));
        }
    }
}
