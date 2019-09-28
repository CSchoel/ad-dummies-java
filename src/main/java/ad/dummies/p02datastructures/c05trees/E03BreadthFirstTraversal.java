package ad.dummies.p02datastructures.c05trees;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class E03BreadthFirstTraversal {
    public interface BinTree extends Iterable<Integer> {
    }
    public static class Node implements BinTree {
        public final int value;
        public final BinTree left;
        public final BinTree right;
        public Node(int value, BinTree left, BinTree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }


        @Override
        public Iterator<Integer> iterator() {
            return new BreadthFirstIterator(this);
        }
    }
    public static class Empty implements BinTree {

        @Override
        public Iterator<Integer> iterator() {
            return new BreadthFirstIterator(this);
        }
    }
    public static class BreadthFirstIterator implements Iterator<Integer> {
        Queue<Node> queue;
        BinTree tree;

        public BreadthFirstIterator(BinTree tree) {
            queue = new LinkedList<>();
            this.tree = tree;
        }

        @Override
        public boolean hasNext() {
            return tree instanceof Node;
        }

        @Override
        public Integer next() {
            Node curNode = (Node) tree;
            if (curNode.left instanceof Node) {
                queue.add((Node) curNode.left);
            }
            if (curNode.right instanceof Node) {
                queue.add((Node) curNode.right);
            }
            if (!queue.isEmpty()) {
                tree = queue.remove();
            } else {
                tree = new Empty();
            }
            return curNode.value;
        }
    }

    public static void breadthFirst(BinTree tree, Consumer<Integer> consumer) {
        Queue<Node> queue = new LinkedList<>();
        BinTree curTree = tree;
        while (curTree instanceof Node) {
            Node curNode = (Node) curTree;
            if (curNode.left instanceof Node) {
                queue.add((Node) curNode.left);
            }
            if (curNode.right instanceof Node) {
                queue.add((Node) curNode.right);
            }
            consumer.accept(curNode.value);
            if (!queue.isEmpty()) {
                curTree = queue.remove();
            } else {
                curTree = new Empty();
            }
        }
    }
    // FIXME: book versions of breadthFirst are unnecessarily complicated
    // and ugly ;P
    // compare with the following versions and see for yourself ;)

    public static class BreadthFirstIterator2 implements Iterator<Integer> {
        Queue<BinTree> queue;

        public BreadthFirstIterator2(BinTree tree) {
            queue = new LinkedList<>();
            queue.add(tree);
        }

        @Override
        public boolean hasNext() {
            skipEmpties();
            return !queue.isEmpty();
        }

        private void skipEmpties(){
            while (!queue.isEmpty() && queue.peek() instanceof Empty) {
                queue.remove();
            }
        }

        @Override
        public Integer next() {
            skipEmpties();
            Node curNode = (Node) queue.remove();
            queue.add(curNode.left);
            queue.add(curNode.right);
            return curNode.value;
        }
    }

    public static void breadthFirst2(BinTree tree, Consumer<Integer> consumer) {
        Queue<BinTree> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            BinTree curTree = queue.remove();
            if (curTree instanceof Empty) { continue; }
            Node curNode = (Node) curTree;
            queue.add(curNode.left);
            queue.add(curNode.right);
            consumer.accept(curNode.value);
        }
    }
}
