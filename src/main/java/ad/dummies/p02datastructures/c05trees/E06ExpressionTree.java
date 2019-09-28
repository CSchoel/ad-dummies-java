package ad.dummies.p02datastructures.c05trees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class E06ExpressionTree {
    public interface ExpNode {

    }
    public static class ValueNode implements ExpNode {
        public final int number;
        public ValueNode(int number) {
            this.number = number;
        }
    }
    public static class AddNode implements ExpNode {}
    public static class SubNode implements ExpNode {} // FIXME: SubNode is mussing in book
    public static class MultNode implements ExpNode {}

    public interface Exp {
        int evaluate();
        List<ExpNode> evalToNodes();
    }
    public static class Value implements Exp {
        public final int number;
        public Value(int number) {
            this.number = number;
        }

        @Override
        public int evaluate() {
            return number;
        }

        @Override
        public List<ExpNode> evalToNodes() {
            return List.of(new ValueNode(number));
        }
    }

    public static class Add implements Exp {
        public final Exp left;
        public final Exp right;
        public Add(Exp left, Exp right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int evaluate() {
            return left.evaluate() + right.evaluate();
        }

        @Override
        public List<ExpNode> evalToNodes() {
            List<ExpNode> nodes = left.evalToNodes();
            nodes.addAll(right.evalToNodes());
            nodes.add(new AddNode());
            return nodes;
        }
    }

    public static class Sub implements Exp {
        public final Exp left;
        public final Exp right;
        public Sub(Exp left, Exp right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int evaluate() {
            return left.evaluate() - right.evaluate();
        }

        @Override
        public List<ExpNode> evalToNodes() {
            List<ExpNode> nodes = left.evalToNodes();
            nodes.addAll(right.evalToNodes());
            nodes.add(new SubNode());
            return nodes;
        }
    }

    public static class Mult implements Exp {
        public final Exp left;
        public final Exp right;
        public Mult(Exp left, Exp right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int evaluate() {
            return left.evaluate() * right.evaluate();
        }

        @Override
        public List<ExpNode> evalToNodes() {
            List<ExpNode> nodes = left.evalToNodes();
            nodes.addAll(right.evalToNodes());
            nodes.add(new MultNode()); // FIXME: book adds SubNode instead
            return nodes;
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

    public static int evalNodes(List<ExpNode> nodes) {
        return evalNodes(nodes, new EmptyStack<>());
    }
    private static int evalNodes(List<ExpNode> nodes, Stack<Integer> stack) {
        if (nodes.isEmpty()) { return stack.pop().value; }
        ExpNode firstNode = nodes.get(0);
        List<ExpNode> rest = nodes.subList(1, nodes.size() - 1);
        if (firstNode instanceof ValueNode) {
            return evalNodes(
                    rest,
                    stack.push(((ValueNode) firstNode).number)
            );
        }
        ValueAndStack<Integer> vs = stack.pop();
        stack = vs.stack;
        int x = vs.value;
        vs = stack.pop();
        stack = vs.stack;
        int y = vs.value;
        if (firstNode instanceof AddNode) {
            return evalNodes(rest, stack.push(y + x));
        } else if (firstNode instanceof SubNode) {
            return evalNodes(rest, stack.push(y - x));
        } else if (firstNode instanceof MultNode) {
            return evalNodes(rest, stack.push(y * x));
        }
        throw new IllegalArgumentException(String.format(
                "Got unexpected class %s",
                firstNode.getClass().getSimpleName()
        ));
    }

    private static int evalNodesIter(List<ExpNode> nodes) {
        Stack<Integer> stack = new EmptyStack<>();

        while(!nodes.isEmpty()) {
            ExpNode firstNode = nodes.get(0);
            List<ExpNode> rest = nodes.subList(1, nodes.size() - 1);
            if (firstNode instanceof ValueNode) {
                stack = stack.push(((ValueNode) firstNode).number);
            } else {
                ValueAndStack<Integer> vs = stack.pop();
                stack = vs.stack;
                int x = vs.value;
                vs = stack.pop();
                stack = vs.stack;
                int y = vs.value;
                if (firstNode instanceof AddNode) {
                    stack =  stack.push(y + x);
                } else if (firstNode instanceof SubNode) {
                    stack = stack.push(y - x);
                } else if (firstNode instanceof MultNode) {
                    stack = stack.push(y * x);
                } else {
                    throw new IllegalArgumentException(String.format(
                            "Got unexpected class %s",
                            firstNode.getClass().getSimpleName()
                    ));
                }
            }
            nodes = rest;
        }
        return stack.pop().value;
    }
}
