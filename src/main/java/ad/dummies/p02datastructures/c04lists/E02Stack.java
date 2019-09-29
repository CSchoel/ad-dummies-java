package ad.dummies.p02datastructures.c04lists;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class E02Stack {
    public interface Stack<E> {
        void push(E el);
        E pop();
    }

    public static class Stack01Linked<E> implements Stack<E> {
        Cell<E> head;

        @Override
        public void push(E el) {
            head = new Cell<>(el, head);
        }

        @Override
        public E pop() {
            assert head != null; // stack must not be empty
            E res = head.element;
            head = head.next;
            return res;
        }

        private static class Cell<E> {
            E element;
            Cell<E> next;
            public Cell(E element, Cell<E> next) {
                this.element = element;
                this.next = next;
            }
        }
    }

    public static class Stack02Array<E> implements Stack<E> {
        private E[] a;
        int top;
        public Stack02Array(int n) {
            // (E[]) here is a small hack that is required because Java
            // eliminates type parameter information during compilation.
            // Essentially our class believes that a is of type E[], but
            // at runtime it will be of type Object[]. This is fine as long
            // as we do not expose ar outside of this class.
            a = (E[]) new Object[n];
            top = 0;
        }

        @Override
        public void push(E el) {
            assert top < a.length; // stack must not be full
            a[top] = el;
            top++;
        }

        @Override
        public E pop() {
            assert top > 0; // stack must not be empty
            top--;
            return a[top];
        }
    }

    public static class Stack03AlgDT<E> implements Stack<E> {
        private interface TList<E> {}
        private static class Nil<E> implements TList<E> {}
        private static class Cons<E> implements TList<E> {
            private E head;
            private TList<E> tail;
            public Cons(E head, TList<E> tail) {
                this.head = head;
                this.tail = tail;
            }
        }

        public Stack03AlgDT() {}
        private Stack03AlgDT(TList<E> stackRep) {
            this.stackRep = stackRep;
        }

        TList<E> stackRep = new Nil<>();

        @Override
        public void push(E el) {
            stackRep = new Cons<>(el, stackRep);
        }

        @Override
        public E pop() {
            assert stackRep instanceof Cons; // stack must not be empty
            Cons<E> stackRepCons = (Cons<E>) stackRep;
            E res = stackRepCons.head;
            stackRep = stackRepCons.tail;
            return res;
        }

        // helper class required to emulate two return values
        public static class ElementAndStack03AlgDT<E> {
            public E element;
            public Stack03AlgDT<E> stack;
            public ElementAndStack03AlgDT(E element, Stack03AlgDT<E> stack) {
                this.element = element;
                this.stack = stack;
            }
        }

        public static <E> Stack03AlgDT<E> pushA(E el, Stack03AlgDT<E> stack) {
            return new Stack03AlgDT<>(new Cons<>(el, stack.stackRep));
        }
        public static <E> ElementAndStack03AlgDT<E> popA(Stack03AlgDT<E> stack) {
            assert stack.stackRep instanceof Cons;
            Cons<E> stackRepCons = (Cons<E>) stack.stackRep;
            return new ElementAndStack03AlgDT<>(
                    stackRepCons.head,
                    new Stack03AlgDT<>(stackRepCons.tail)
            );
        }
    }
    public static void main(String[] args) {
        Function<Stack<Integer>, List<Integer>> f = s -> {
            s.push(5);
            s.push(4);
            s.pop();
            s.push(0);
            s.push(-3);
            return List.of(s.pop(), s.pop(), s.pop());
        };
        System.out.printf("Stack01Linked: %s\n", f.apply(new Stack01Linked<>()));
        System.out.printf(" Stack02Array: %s\n", f.apply(new Stack02Array<>(10)));
        System.out.printf(" Stack03AlgDT: %s\n", f.apply(new Stack03AlgDT<>()));
    }
}
