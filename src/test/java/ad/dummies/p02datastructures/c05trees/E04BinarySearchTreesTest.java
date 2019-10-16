package ad.dummies.p02datastructures.c05trees;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p02datastructures.c05trees.E04BinarySearchTrees.*;

/**
 * <p>Unit tests for an example from the german book "Algorithms and data
 * structures for dummies":</p>
 *
 * <p>A. Gogol-Döring and T. Letschert, <i>Algorithmen und Datenstrukturen für
 * Dummies</i>. Weinheim, Germany: Wiley-VCH, 2019.</p>
 *
 * <p>The current version of these examples with unit tests and benchmarks can
 * be found <a href="https://github.com/CSchoel/ad-dummies-java">on GitHub</a>.
 * </p>
 *
 * @author Christopher Schölzel
 * @see E04BinarySearchTrees
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E04BinarySearchTreesTest {

    @Test
    public void lookupInEmptyMapReturnsNull() {
        Map<String, Integer> m = new Map<>();
        assertNull(m.lookup("Test"));
    }

    @Test
    public void insertIOfOneItemAllowsToFindThisItemWithLookup() {
        Map<String, Integer> m = new Map<>();
        m.insertI("Foo", 17);
        assertEquals(17, m.lookup("Foo"));
    }

    @Test
    public void insertIOfSixItemsAllowsToFindEachItemWithLookup() {
        Map<String, Integer> m = new Map<>();
        m.insertI("Holberton", 3);
        m.insertI("Antonelli", 1);
        m.insertI("Meltzer", 4);
        m.insertI("Bartik", 2);
        m.insertI("Teitelbaum", 6);
        m.insertI("Spence", 5);
        assertEquals(1, m.lookup("Antonelli"));
        assertEquals(2, m.lookup("Bartik"));
        assertEquals(3, m.lookup("Holberton"));
        assertEquals(4, m.lookup("Meltzer"));
        assertEquals(5, m.lookup("Spence"));
        assertEquals(6, m.lookup("Teitelbaum"));
    }

    @Test
    public void insertIOfDuplicateIsIgnored() {
        Map<String, Integer> m = new Map<>();
        m.insertI("Antonelli", 1);
        m.insertI("Antonelli", 2);
        assertEquals(1, m.lookup("Antonelli"));
    }

    @Test
    public void insertFOfOneItemAllowsToFindThisItemWithLookup() {
        Map<String, Integer> m = new Map<>();
        m = m.insertF("Foo", 17);
        assertEquals(17, m.lookup("Foo"));
    }

    @Test
    public void insertFOfOneItemDoesNotAffectBaseObject() {
        Map<String, Integer> m = new Map<>();
        Map<String, Integer> m2 = m.insertF("Foo", 17);
        assertNull(m.lookup("Foo"));
    }

    @Test
    public void insertFOfSixItemsAllowsToFindEachItemWithLookup() {
        Map<String, Integer> m = new Map<>();
        m = m.insertF("Holberton", 3);
        m = m.insertF("Antonelli", 1);
        m = m.insertF("Meltzer", 4);
        m = m.insertF("Bartik", 2);
        m = m.insertF("Teitelbaum", 6);
        m = m.insertF("Spence", 5);
        assertEquals(1, m.lookup("Antonelli"));
        assertEquals(2, m.lookup("Bartik"));
        assertEquals(3, m.lookup("Holberton"));
        assertEquals(4, m.lookup("Meltzer"));
        assertEquals(5, m.lookup("Spence"));
        assertEquals(6, m.lookup("Teitelbaum"));
    }

    @Test
    public void insertFOfDuplicateIsIgnored() {
        Map<String, Integer> m = new Map<>();
        m = m.insertF("Antonelli", 1);
        m = m.insertF("Antonelli", 2);
        assertEquals(1, m.lookup("Antonelli"));
    }


    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}