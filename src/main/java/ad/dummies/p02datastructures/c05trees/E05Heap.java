package ad.dummies.p02datastructures.c05trees;

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
    }
}
