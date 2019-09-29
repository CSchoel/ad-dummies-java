package ad.dummies.p01basics.c03datastructures;

import java.util.Arrays;

public class E01Lexical {
    public static String lexicalFirst(String[] words) {
        assert words.length > 0;
        // FIXME: start value of f would need to change if we consider lengths
        // because then "" would be found as the lexically first word
        String f = "";
        for (String s : words) {
            if (before(s, f)) {
                f = s;
            }
        }
        return f;
    }

    private static boolean before(String s1, String s2) {
        // FIXME: book forgot to increment counter
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) > s2.charAt(i)) {
                return false;
            }
            // FIXME: book version does not include "else if" part
            // This means it will fail for values of s1 that should return
            // true due to the first non-matching character, but return false
            // due to a further following character mismatch (e.g.
            // before("ab","ba") will return false instead of true).
            // You can test this behavior by commenting the following three
            // lines.
            else if (s1.charAt(i) < s2.charAt(i)) {
                return true;
            }
        }
        // FIXME: book version does not consider sequences of different lengths
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"ba", "aa", "ab", "aaa"};
        System.out.printf("lexicalFirst(%s) = %s\n", Arrays.toString(words), lexicalFirst(words));
    }
}
