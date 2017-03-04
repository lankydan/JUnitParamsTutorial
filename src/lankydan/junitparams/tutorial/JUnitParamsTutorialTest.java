package lankydan.junitparams.tutorial;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dan on 03/03/2017.
 */
@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTutorialTest {

    private JUnitParamsTutorial testSubject = new JUnitParamsTutorial();

    // takes parameters from the inside the annotation
    @Test
    @Parameters({"1, 2, 3",
            "3, 4, 7",
            "5, 6, 11",
            "7, 8, 15"})
    public void addProducesCorrectValue_usingAnnotatedParameters(final int a, final int b, final int
            expectedResult) {
        assertEquals(expectedResult, testSubject.add(a, b));
    }

    // takes parameters from the addParameters method
    @Test
    @Parameters(method = "addParameters")
    public void addProducesCorrectValue_usingNamedMethodParameters(final int a, final int b, final int
            expectedResult) {
        assertEquals(expectedResult, testSubject.add(a, b));
    }

    private Object[] addParameters() {
        return new Object[]{
                new Object[]{1, 2, 3},
                new Object[]{3, 4, 7},
                new Object[]{5, 6, 11},
                new Object[]{7, 8, 15}
        };
    }

    // equivalent of version two but no method is defined
    // takes method that is named "parametersFor" + "name of the test"
    @Test
    @Parameters
    public void addProducesCorrectValue_usingMethodParametersWithoutName(final int a, final int b, final int
            expectedResult) {
        assertEquals(expectedResult, testSubject.add(a, b));
    }

    private Object[] parametersForAddProducesCorrectValue_usingMethodParametersWithoutName() {
        return new Object[]{
                new Object[]{1, 2, 3},
                new Object[]{3, 4, 7},
                new Object[]{5, 6, 11},
                new Object[]{7, 8, 15}
        };
    }

    // takes parameters from a CSV file
    @Test
    @FileParameters("resources/JUnitParamsTutorialParameters.csv")
    public void addProducesCorrectValue_usingCSV(final int a, final int b, final int
            expectedResult) {
        assertEquals(expectedResult, testSubject.add(a, b));
    }

    // takes parameters from the containsParameters method
    @Test
    @Parameters(method = "containsParameters")
    public void testContains_usingNamedMethodParameters(final List<String> list, final String a,
                                                        final boolean expectedResult) {
        assertEquals(expectedResult, testSubject.contains(list, a));
    }

    private Object[] containsParameters() {
        return new Object[]{
                new Object[]{Arrays.asList("a", "b", "c", "d", "e"), "c", true},
                new Object[]{Arrays.asList("a", "b", "c", "d", "e"), "e", true},
                new Object[]{Arrays.asList("a", "b"), "e", false},
                new Object[]{Arrays.asList(), "e", false}
        };
    }

    // takes parameters from the methods in MyContainsTestProvider
    @Test
    @Parameters(source = MyContainsTestProvider.class)
    public void testContains_usingSeperateClass(final List<String> list, final String a, final
    boolean expectedResult) {
        assertEquals(expectedResult, testSubject.contains(list, a));
    }

    public static class MyContainsTestProvider {
        public static Object[] provideContainsTrueParameters() {
            return new Object[]{
                    new Object[]{Arrays.asList("a", "b", "c", "d", "e"), "c", true},
                    new Object[]{Arrays.asList("a", "b", "c", "d", "e"), "e", true},
                    new Object[]{Arrays.asList("a", "b"), "b", true},
                    new Object[]{Arrays.asList("a"), "a", true}
            };
        }

        public static Object[] provideContainsFalseParameters() {
            return new Object[]{
                    new Object[]{Arrays.asList("a", "b", "c", "d", "e"), "f", false},
                    new Object[]{Arrays.asList("a", "b", "c", "d", "e"), "z", false},
                    new Object[]{Arrays.asList("a", "b"), "e", false},
                    new Object[]{Arrays.asList(), "e", false}
            };
        }
    }
}
