package ad.dummies.p02datastructures.c05trees;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class E02DepthFirstTraversal {
    // Note: We could use ImmutableArrayList for our traversals, but the
    // implementation can even be faster if we apply the same same concept
    // to a linked list that is immutable on the outside but has mutable nodes
    // on the inside. The crucial point is that this allows us to implement
    // append in O(1).
    // WARNING: The downside of this approach is that it can lead to memory
    // leaks since the tail of a list may point to unused data and prevent the
    // garbage collector from freeing memory for these items.
    public static class ImmutableLinkedList implements Iterable<Integer> {
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

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                MutableNode current = firstNode;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public Integer next() {
                    int res = current.head;
                    current = current.tail;
                    return res;
                }
            };
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

    public static class BinTreeIterator implements Iterator<Integer> {
        private Stack<Node> stack;
        private BinTree tree;
        private BinTree current;

        public BinTreeIterator(BinTree tree) {
            stack = new EmptyStack<>();
            this.tree = tree;
        }

        @Override
        public boolean hasNext() {
            // FIXME: error in the book
            // wrong: t = not(tree = Empty and stack = Nil)
            // correct: not(t = Empty and stack = Nil)
            return current instanceof Node || !stack.isEmpty();
        }

        @Override
        public Integer next() {
            assert hasNext();
            while (current instanceof Node) {
                Node currentNode = (Node) current;
                stack = stack.push(currentNode);
                tree = currentNode.left;
            }
            ValueAndStack<Node> top = stack.pop();
            int nextValue = top.value.value;
            stack = top.stack;
            tree = top.value.right;
            return null;
        }
    }

    public static abstract class BinTree implements Iterable<Integer> {
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

        @Override
        public Iterator<Integer> iterator() {
            return new BinTreeIterator(this);
        }
    }
    public static class Empty extends BinTree {
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

        @Override
        public Iterator<Integer> iterator() {
            return new BinTreeIterator(this);
        }
    }

    public static void main(String[] args) {
        BinTree tree = new Node(
                4,
                new Node(1,
                        new Node(8, new Empty(), new Empty()),
                        new Node(2, new Empty(), new Empty())
                ),
                new Node(-1,
                        new Node(0, new Empty(), new Empty()),
                        new Empty()
                )
        );
        Function<Iterable<Integer>, List<Integer>> toJavaList = l ->
                StreamSupport.stream(l.spliterator(), false)
                        .collect(Collectors.toList());
        System.out.printf("       preorder: %s\n", toJavaList.apply(tree.preorder()));
        System.out.printf("        inorder: %s\n", toJavaList.apply(tree.inorder()));
        System.out.printf("      postorder: %s\n", toJavaList.apply(tree.postorder()));
        System.out.printf("BinTreeIterator: %s\n", toJavaList.apply(tree));
        List<Integer> resP = new ArrayList<>();
        tree.inorderProcess(resP::add);
        System.out.printf(" inorderProcess: %s\n", resP);
        List<Integer> resIS1 = new ArrayList<>();
        tree.inorderStack1(resIS1::add);
        System.out.printf("  inorderStack1: %s\n", resIS1);
        List<Integer> resIS2 = new ArrayList<>();
        tree.inorderStack2(resIS2::add);
        System.out.printf("  inorderStack2: %s\n", resIS2);

    }
}
