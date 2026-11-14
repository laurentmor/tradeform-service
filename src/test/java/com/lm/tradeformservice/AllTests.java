package com.lm.tradeformservice;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * JUnit Platform suite that discovers and runs tests in the application package tree.
 *
 * <p>Run the suite through Maven with {@code ./mvnw -Dtest=AllTests test}, or on Windows with
 * {@code .\mvnw.cmd -Dtest=AllTests test}.
 */
@Suite
@SelectPackages("com.lm.tradeformservice")
@SuiteDisplayName("Full Test Suite")
public class AllTests {

    /** Prevents instantiation of this JUnit suite class. */
    private AllTests() {}

    /** Display name returned for this test suite. */
    private static final String SUITE_NAME = "Full Test Suite";

    /**
     * Returns the display name associated with the test suite.
     *
     * @return the suite display name
     */
    public static String getSuiteName() {
        return SUITE_NAME;
    }
}
