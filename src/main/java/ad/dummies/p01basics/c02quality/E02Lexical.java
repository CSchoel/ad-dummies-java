package ad.dummies.p01basics.c02quality;

import java.util.Arrays;

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
public class E02Lexical {
    public static String lexicalFirst(String[] words) {
        assert words.length > 0;
        String f = words[0];
        for (String s : words) {
            if (lexicalLess(s, f)) {
                f = s;
            }
        }
        return f;
    }

    private static boolean lexicalLess(String s1, String s2) {
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) < s2.charAt(i)) {
                return true;
            } else if (s1.charAt(i) > s2.charAt(i)) {
                return false;
            }
        }
        // s1 and s2 are equal up to min(s1.length(), s2.length())
        assert s1.substring(0, Math.min(s1.length(), s2.length()))
                .equals(s2.substring(0, Math.min(s1.length(), s2.length())));
        return (s1.length() < s2.length());
    }

    public static void main(String[] args) {
        String[] words = {"ba", "aa", "ab", "aaa"};
        System.out.printf("lexicalFirst(%s) = %s\n", Arrays.toString(words), lexicalFirst(words));
    }
}
