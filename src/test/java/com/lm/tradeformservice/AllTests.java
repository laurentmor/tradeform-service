package com.lm.tradeformservice;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * JUnit Platform suite that discovers and runs tests in the application package tree.
 *
 * <p>Run the suite through Maven with {@code ./mvnw -Dtest=AllTests test}.
 */
@Suite
@SelectPackages("com.lm.tradeformservice")
@SuiteDisplayName("Full Test Suite")
public class AllTests {}
