package ad.dummies.p01basics.c02quality;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

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
 * @see E02Lexical
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E02LexicalTest {

    @Test
    void lexicalFirstOneElement() {
        assertEquals("alpha", E02Lexical.lexicalFirst(new String[]{
                "alpha"
        }));

    }

    @Test
    void lexicalFirstFirstOfTwo() {
        assertEquals("alpha", E02Lexical.lexicalFirst(new String[]{
                "alpha", "beta"
        }));

    }

    @Test
    void lexicalFirstSecondOfTwo() {
        assertEquals("alpha", E02Lexical.lexicalFirst(new String[]{
                "beta", "alpha"
        }));
    }

    @Test
    void lexicalFirstSecondOfTwoBySecondChar() {
        assertEquals("aa", E02Lexical.lexicalFirst(new String[]{
                "ab", "aa"
        }));
    }

    @Test
    void lexicalFirstSecondOfTwoByLength() {
        assertEquals("aa", E02Lexical.lexicalFirst(new String[]{
                "aa", "aaa"
        }));
    }

    @Test
    void lexicalFirstAscending() {
        assertEquals("aa", E02Lexical.lexicalFirst(new String[]{
                "aa", "ab", "ac", "ba", "bb", "bc", "ca", "cb", "cc"
        }));
    }

    @Test
    void lexicalFirstDescending() {
        assertEquals("aa", E02Lexical.lexicalFirst(new String[]{
                "cc", "cb", "ca", "bc", "bb", "ba", "ac", "ab", "aa"
        }));
    }

    @Test
    void lexicalFirstUnordered() {
        assertEquals("aa", E02Lexical.lexicalFirst(new String[]{
                "cb", "bb", "ab", "ca", "cc", "bc", "aa", "ba", "ac"
        }));
    }

    @Test
    void lexicalFirstFirstExistsTwice() {
        assertEquals("aa", E02Lexical.lexicalFirst(new String[]{
                "cb", "bb", "aa", "ca", "cc", "bc", "aa", "ba", "ac"
        }));
    }

    @Test
    void lexicalFirstAllEqual() {
        assertEquals("bb", E02Lexical.lexicalFirst(new String[]{
                "bb", "bb", "bb", "bb", "bb", "bb", "bb", "bb", "bb"
        }));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E02Lexical.main(new String[0]);
    }
}