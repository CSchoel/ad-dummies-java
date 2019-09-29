package ad.dummies.p02datastructures.c05trees;

public class E04BinarySearchTrees {
    public static class Map<K extends Comparable<K>, V extends Comparable<V>> {
        private BSTree<K, V> t;

        public Map() {
            t = new Empty<>();
        }
        public Map(BSTree<K, V> t) {
            this.t = t;
        }

        public interface BSTree<K extends Comparable<K>, V extends Comparable<V>> {
            V lookup(K key);
            BSTree<K, V> insertF(K key, V value);
        }
        public class Node<K extends Comparable<K>, V  extends Comparable<V>> implements BSTree<K, V> {
            K key;
            V value;
            BSTree<K, V> left;
            BSTree<K, V> right;
            public Node(K key, V value, BSTree<K, V> left, BSTree<K, V> right) {
                this.key = key;
                this.value = value;
                this.left = left;
                this.right = right;
            }

            @Override
            public V lookup(K key) {
                int comp = this.key.compareTo(key);
                if (comp == 0) {
                    return value;
                } else if (comp > 0) {
                    return left.lookup(key);
                } else {
                    return right.lookup(key);
                }
            }

            @Override
            public BSTree<K, V> insertF(K key, V value) {
                int comp = this.key.compareTo(key);
                if (comp == 0) {
                    return this; // do not allow duplicates
                } else if (comp > 0) {
                    return new Node<>(this.key, this.value, left.insertF(key, value), right);
                } else {
                    return new Node<>(this.key, this.value, left, right.insertF(key, value));
                }
            }
        }

        public class Empty<K  extends Comparable<K>, V  extends Comparable<V>> implements BSTree<K, V> {

            @Override
            public V lookup(K key) {
                return null;
            }

            @Override
            public BSTree<K, V> insertF(K key, V value) {
                return new Node<>(key, value, this, this);
            }
        }

        public void insertI(K key, V value) {
            t = t.insertF(key, value);
        }

        public Map<K, V> insertF(K key, V value) {
            return new Map<>(t.insertF(key, value));
        }

        public V lookup(K key) {
            return t.lookup(key);
        }
    }

    public static void main(String[] args) {
        String[] names = {"Hugo", "Karlheinz", "Sibylle"};
        int[] weights = {80, 90, 70};
        for(int i = 0; i < names.length; i++) {
            System.out.printf("%10s -> %2d\n", names[i], weights[i]);
        }

        Map<String, Integer> weightsI = new Map<>();
        for(int i = 0; i < names.length; i++) {
            weightsI.insertI(names[i], weights[i]);
        }
        Map<String, Integer> weightsF = new Map<>();
        for(int i = 0; i < names.length; i++) {
            weightsF = weightsF.insertF(names[i], weights[i]);
        }
        String key = "Hugo";
        System.out.printf("weightsI.lookup(%s) = %d\n", key, weightsI.lookup(key));
        System.out.printf("weightsF.lookup(%s) = %d\n", key, weightsF.lookup(key));

    }
}
