package ad.dummies.p02datastructures.c04lists;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import static ad.dummies.p02datastructures.c04lists.E02Stack.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E02StackTest {

    @Test
    public void stack01PushAndPopYieldsPushedElement() {
        Stack<Integer> s = new Stack01Linked<>();
        s.push(9);
        assertEquals(9, s.pop());
    }

    @Test
    public void stack01TwoPushesAndTwoPopsYieldElementsInReversedOrder() {
        Stack<Integer> s = new Stack01Linked<>();
        s.push(9);
        s.push(-1);
        assertEquals(-1, s.pop());
        assertEquals(9, s.pop());
    }

    @Test
    public void stack01FivePushesAndFivePopsYieldElementsInReversedOrder() {
        Stack<Integer> s = new Stack01Linked<>();
        s.push(9);
        s.push(-1);
        s.push(8);
        s.push(0);
        s.push(1);
        assertEquals(1, s.pop());
        assertEquals(0, s.pop());
        assertEquals(8, s.pop());
        assertEquals(-1, s.pop());
        assertEquals(9, s.pop());
    }

    @Test
    public void stack01PopsBetweenPushesYieldCorrectElements() {
        Stack<Integer> s = new Stack01Linked<>();
        s.push(9);
        s.push(-1);
        s.push(8);
        assertEquals(8, s.pop());
        s.push(0);
        s.push(1);
        assertEquals(1, s.pop());

        assertEquals(0, s.pop());
        assertEquals(-1, s.pop());
        assertEquals(9, s.pop());
    }

    @Test
    public void stack02withCapacityOneAcceptsOnePush() {
        Stack<Integer> s = new Stack02Array<>(1);
        s.push(9);
        assertEquals(9, s.pop());
    }

    @Test
    public void stack02PushAndPopYieldsPushedElement() {
        Stack<Integer> s = new Stack02Array<>(10);
        s.push(9);
        assertEquals(9, s.pop());
    }

    @Test
    public void stack02TwoPushesAndTwoPopsYieldElementsInReversedOrder() {
        Stack<Integer> s = new Stack02Array<>(10);
        s.push(9);
        s.push(-1);
        assertEquals(-1, s.pop());
        assertEquals(9, s.pop());
    }

    @Test
    public void stack02FivePushesAndFivePopsYieldElementsInReversedOrder() {
        Stack<Integer> s = new Stack02Array<>(10);
        s.push(9);
        s.push(-1);
        s.push(8);
        s.push(0);
        s.push(1);
        assertEquals(1, s.pop());
        assertEquals(0, s.pop());
        assertEquals(8, s.pop());
        assertEquals(-1, s.pop());
        assertEquals(9, s.pop());
    }

    @Test
    public void stack02PopsBetweenPushesYieldCorrectElements() {
        Stack<Integer> s = new Stack02Array<>(10);
        s.push(9);
        s.push(-1);
        s.push(8);
        assertEquals(8, s.pop());
        s.push(0);
        s.push(1);
        assertEquals(1, s.pop());

        assertEquals(0, s.pop());
        assertEquals(-1, s.pop());
        assertEquals(9, s.pop());
    }

    @Test
    public void stack03PushAndPopYieldsPushedElement() {
        Stack<Integer> s = new Stack03AlgDT<>();
        s.push(9);
        assertEquals(9, s.pop());
    }

    @Test
    public void stack03TwoPushesAndTwoPopsYieldElementsInReversedOrder() {
        Stack<Integer> s = new Stack03AlgDT<>();
        s.push(9);
        s.push(-1);
        assertEquals(-1, s.pop());
        assertEquals(9, s.pop());
    }

    @Test
    public void stack03FivePushesAndFivePopsYieldElementsInReversedOrder() {
        Stack<Integer> s = new Stack03AlgDT<>();
        s.push(9);
        s.push(-1);
        s.push(8);
        s.push(0);
        s.push(1);
        assertEquals(1, s.pop());
        assertEquals(0, s.pop());
        assertEquals(8, s.pop());
        assertEquals(-1, s.pop());
        assertEquals(9, s.pop());
    }

    @Test
    public void stack03PopsBetweenPushesYieldCorrectElements() {
        Stack<Integer> s = new Stack03AlgDT<>();
        s.push(9);
        s.push(-1);
        s.push(8);
        assertEquals(8, s.pop());
        s.push(0);
        s.push(1);
        assertEquals(1, s.pop());

        assertEquals(0, s.pop());
        assertEquals(-1, s.pop());
        assertEquals(9, s.pop());
    }

    @Test
    public void stack03APushAndPopYieldsPushedElement() {
        Stack03AlgDT<Integer> s = new Stack03AlgDT<>();
        Stack03AlgDT.ElementAndStack03AlgDT popRes;
        s = Stack03AlgDT.pushA(9, s);
        popRes = Stack03AlgDT.popA(s);
        assertEquals(9, popRes.element);
    }

    @Test
    public void stack03APushADoesNotAffectOriginalStack() {
        Stack03AlgDT<Integer> s = new Stack03AlgDT<>();
        s.push(4);
        Stack03AlgDT.pushA(9, s);
        assertEquals(4, s.pop());
    }

    @Test
    public void stack03APopADoesNotAffectOriginalStack() {
        Stack03AlgDT<Integer> s = new Stack03AlgDT<>();
        s.push(4);
        Stack03AlgDT.ElementAndStack03AlgDT popRes = Stack03AlgDT.popA(s);
        assertEquals(4, s.pop());
    }

    @Test
    public void stack03APushAResultIsNotAffectedByPushesToOriginalStack() {
        Stack03AlgDT<Integer> s = new Stack03AlgDT<>();
        s.push(4);
        Stack03AlgDT<Integer> s2 = Stack03AlgDT.pushA(9, s);
        s.push(9);
        s.push(-1);
        assertEquals(9, Stack03AlgDT.popA(s2).element);
    }

    @Test
    public void stack03APopAResultIsNotAffectedByPushesToOriginalStack() {
        Stack03AlgDT<Integer> s = new Stack03AlgDT<>();
        Stack03AlgDT.ElementAndStack03AlgDT<Integer> popRes;
        Stack03AlgDT<Integer> s2 = Stack03AlgDT.pushA(9, s);
        s.push(4);
        s.push(0);
        popRes = Stack03AlgDT.popA(s2);
        assertEquals(9, popRes.element);
    }

    @Test
    public void stack03ATwoPushesAndTwoPopsYieldElementsInReversedOrder() {
        Stack03AlgDT<Integer> s = new Stack03AlgDT<>();
        Stack03AlgDT.ElementAndStack03AlgDT<Integer> popRes;
        s = Stack03AlgDT.pushA(9, s);
        s = Stack03AlgDT.pushA(-1, s);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(-1, popRes.element);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(9, popRes.element);
    }

    @Test
    public void stack03AFivePushesAndFivePopsYieldElementsInReversedOrder() {
        Stack03AlgDT<Integer> s = new Stack03AlgDT<>();
        Stack03AlgDT.ElementAndStack03AlgDT<Integer> popRes;
        s = Stack03AlgDT.pushA(9, s);
        s = Stack03AlgDT.pushA(-1, s);
        s = Stack03AlgDT.pushA(8, s);
        s = Stack03AlgDT.pushA(0, s);
        s = Stack03AlgDT.pushA(1, s);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(1, popRes.element);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(0, popRes.element);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(8, popRes.element);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(-1, popRes.element);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(9, popRes.element);
    }

    @Test
    public void stack03APopsBetweenPushesYieldCorrectElements() {
        Stack03AlgDT<Integer> s = new Stack03AlgDT<>();
        Stack03AlgDT.ElementAndStack03AlgDT<Integer> popRes;
        s = Stack03AlgDT.pushA(9, s);
        s = Stack03AlgDT.pushA(-1, s);
        s = Stack03AlgDT.pushA(8, s);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(8, popRes.element);
        s = Stack03AlgDT.pushA(0, s);
        s = Stack03AlgDT.pushA(1, s);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(1, popRes.element);

        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(0, popRes.element);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(-1, popRes.element);
        popRes = Stack03AlgDT.popA(s);
        s = popRes.stack;
        assertEquals(9, popRes.element);
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E02Stack.main(new String[0]);
    }
}