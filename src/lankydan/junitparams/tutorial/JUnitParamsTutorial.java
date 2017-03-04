package lankydan.junitparams.tutorial;

import java.util.List;

/**
 * Created by dan on 03/03/2017.
 */
public class JUnitParamsTutorial {

    public int add(final int a, final int b) {
        return a + b;
    }

    public boolean contains(final List<String> list, final String a) {
        return list.contains(a);
    }

}
