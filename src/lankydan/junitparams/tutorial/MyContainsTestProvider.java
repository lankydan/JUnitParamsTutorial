package lankydan.junitparams.tutorial;

import java.util.Arrays;

/**
 * Created by dan on 03/03/2017.
 */
public class MyContainsTestProvider {
    public static Object[] containsTrue() {
        return new Object[] {
                new Object[] {Arrays.asList("a","b","c","d","e"), "c", true},
                new Object[] {Arrays.asList("a","b","c","d","e"), "e", true},
                new Object[] {Arrays.asList("a","b"), "b", true},
                new Object[] {Arrays.asList("a"), "a", true}
        };
    }

    public static Object[] containsFalse() {
        return new Object[] {
                new Object[] {Arrays.asList("a","b","c","d","e"), "f", false},
                new Object[] {Arrays.asList("a","b","c","d","e"), "z", false},
                new Object[] {Arrays.asList("a","b"), "e", false},
                new Object[] {Arrays.asList(), "e", false}
        };
    }
}
