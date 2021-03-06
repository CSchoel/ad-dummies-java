package ad.dummies.p02datastructures.c05trees;

import java.util.ArrayList;
import java.util.List;

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
    public static class SubNode implements ExpNode {} // FIXME: SubNode is missing in book
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
            return new ArrayList<>(List.of(new ValueNode(number)));
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
        List<ExpNode> rest = nodes.subList(1, nodes.size());
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

    public static int evalNodesIter(List<ExpNode> nodes) {
        Stack<Integer> stack = new EmptyStack<>();

        while(!nodes.isEmpty()) {
            ExpNode firstNode = nodes.get(0);
            List<ExpNode> rest = nodes.subList(1, nodes.size());
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

    public static void main(String[] args) {
        Exp exp = new Add(new Value(2), new Mult(new Value(3), new Value(4)));
        class ExpString {
            Exp e;
            public ExpString(Exp e) {
                this.e = e;
            }
            public String toString() {
                if (e instanceof Value) {
                    return Integer.toString(((Value) e).number);
                } else if (e instanceof Add) {
                    return String.format(
                            "Add(%s, %s)",
                            new ExpString(((Add) e).left),
                            new ExpString(((Add) e).right)
                    );
                } else if (e instanceof Mult) {
                    return String.format(
                            "Mult(%s, %s)",
                            new ExpString(((Mult) e).left),
                            new ExpString(((Mult) e).right)
                    );
                } else if (e instanceof Sub) {
                    return String.format(
                            "Sub(%s, %s)",
                            new ExpString(((Sub) e).left),
                            new ExpString(((Sub) e).right)
                    );
                }
                return "ERROR";
            }
        }
        String expS = new ExpString(exp).toString();
        System.out.printf("                  %s.evaluate() = %d\n", expS, exp.evaluate());
        System.out.printf("    evalNodes(%s.evalToNodes()) = %d\n", expS, evalNodes(exp.evalToNodes()));
        System.out.printf("evalNodesIter(%s.evalToNodes()) = %d\n", expS, evalNodesIter(exp.evalToNodes()));
    }
}
