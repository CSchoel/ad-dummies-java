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

//    public interface List {
//        List append(List preorder);
//    }
//    public class Nil implements List {}
//    public class Cons implements List {
//        public final int head;
//        public final List tail;
//        public Cons(int head, List tail) {
//            this.head = head;
//            this.tail = tail;
//        }
//    }
    // Note: We could use ImmutableArrayList for our traversals, but the
    // implementation can even be faster if we apply the same same concept
    // to a linked list that is immutable on the outside but has mutable nodes
    // on the inside. The crucial point is that this allows us to implement
    // append in O(1).
    // WARNING: The downside of this approach is that it can lead to memory
    // leaks since the tail of a list may point to unused data and prevent the
    // garbage collector from freeing memory for these items.
    public static class ImmutableLinkedList {
        private class MutableNode {
            public int head;
            public MutableNode tail;
            public MutableNode(int head, MutableNode tail) {
                this.head = head;
                this.tail = tail;
            }
        }
        private MutableNode firstNode;
        private MutableNode lastNode;
        public ImmutableLinkedList() {
            firstNode = null;
            lastNode = null;
        }
        private ImmutableLinkedList(MutableNode firstNode, MutableNode lastNode) {
            this.firstNode = firstNode;
            this.lastNode = lastNode;
        }
        public ImmutableLinkedList addFirst(int x) {
            MutableNode newNode = new MutableNode(x, firstNode);
            return new ImmutableLinkedList(
                    newNode,
                    lastNode == null ? newNode : lastNode
            );
        }
        public ImmutableLinkedList addLast(int x) {
            MutableNode newNode = new MutableNode(x, null);
            return append(new ImmutableLinkedList(newNode, newNode));
        }
        public ImmutableLinkedList append(ImmutableLinkedList other) {
            if (other.firstNode == null) { return this; }
            if (lastNode == null) { return other; }
            MutableNode[] borderNodes = {firstNode, lastNode};
            if (lastNode.tail != null) {
                // tail link is already used => we need to copy list
                borderNodes = copy();
            }
            borderNodes[1].tail = other.firstNode;
            return new ImmutableLinkedList(borderNodes[0], other.lastNode);
        }
        private MutableNode[] copy() {
            MutableNode[] borderNodes = new MutableNode[2];
            borderNodes[0] = new MutableNode(firstNode.head, null);
            borderNodes[1] = borderNodes[0];
            MutableNode curNode = firstNode.tail;
            do {
                borderNodes[1].tail = new MutableNode(curNode.head, null);
                borderNodes[1] = borderNodes[1].tail;
                curNode = curNode.tail;
            } while(curNode != lastNode);
            return borderNodes;
        }
    }

    public static class ValueAndStack<E> {
        public final E value;
        public final Stack<E> stack;
        public ValueAndStack(E value, Stack<E> stack) {
            this.value = value;
            this.stack = stack;
        }
    }
    public interface Stack<E> {
        ValueAndStack<E> pop();
        Stack<E> push(E el);
        boolean isEmpty();
    }
    public static class EmptyStack<E> implements Stack<E> {

        @Override
        public ValueAndStack<E> pop() {
            throw new IndexOutOfBoundsException("cannot pop empty stack");
        }

        @Override
        public Stack<E> push(E el) {
            return new Page<>(el, this);
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
    public static class Page<E> implements Stack<E> {
        public final E value;
        public final Stack<E> next;
        public Page(E value, Stack<E> next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public ValueAndStack<E> pop() {
            return new ValueAndStack<>(value, next);
        }

        @Override
        public Stack<E> push(E el) {
            return new Page<>(el, this);
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
    public static abstract class BinTree {
        public abstract int sum();
        public abstract int depth();
        public abstract ImmutableLinkedList preorder();
        public abstract ImmutableLinkedList inorder();
        public abstract void inorderProcess(Consumer<Integer> consumer);
        public abstract void inorderStack2(Consumer<Integer> consumer);
        public abstract ImmutableLinkedList postorder();
        public void inorderStack1(Consumer<Integer> consumer) {
            inorderStack1(new EmptyStack<>(), consumer);
        }
        protected abstract void inorderStack1(Stack<Node> stack, Consumer<Integer> consumer);
        protected void processStack(Stack<Node> stack, Consumer<Integer> consumer) {
            if (stack.isEmpty()) { return; }
            ValueAndStack<Node> top = stack.pop();
            consumer.accept(top.value.value);
            top.value.left.inorderStack1(stack, consumer);
        }
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

        @Override
        public ImmutableLinkedList preorder() {
            return left.preorder().append(right.preorder()).addFirst(value);
        }

        @Override
        public ImmutableLinkedList inorder() {
            return left.inorder().append(right.preorder().addFirst(value));
        }

        @Override
        public void inorderProcess(Consumer<Integer> consumer) {
            left.inorderProcess(consumer);
            consumer.accept(value);
            right.inorderProcess(consumer);
        }

        @Override
        public void inorderStack1(Stack<Node> stack, Consumer<Integer> consumer) {
            left.inorderStack1(stack.push(this), consumer);
        }

        @Override
        public ImmutableLinkedList postorder() {
            return left.postorder().append(right.postorder()).addLast(value);
        }

        @Override
        public void inorderStack2(Consumer<Integer> consumer) {
            Stack<Node> stack = new EmptyStack<>();
            BinTree tree = this;
            while(tree instanceof Node || !stack.isEmpty()) {
                while(tree instanceof Node) {
                    stack = stack.push((Node) tree);
                    tree = ((Node) tree).left;
                }
                if (!stack.isEmpty()) {
                    ValueAndStack<Node> stackNode = stack.pop();
                    consumer.accept(stackNode.value.value);
                    tree = stackNode.value.right;
                }
            }
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

        @Override
        public ImmutableLinkedList preorder() {
            return new ImmutableLinkedList();
        }

        @Override
        public ImmutableLinkedList inorder() {
            return new ImmutableLinkedList();
        }

        @Override
        public void inorderProcess(Consumer<Integer> consumer) {
            // nothing to do
        }

        @Override
        public void inorderStack2(Consumer<Integer> consumer) {
            // nothing to do
        }

        @Override
        public ImmutableLinkedList postorder() {
            return new ImmutableLinkedList();
        }

        @Override
        protected void inorderStack1(Stack<Node> stack, Consumer<Integer> consumer) {
            processStack(stack, consumer);
        }
    }
}
