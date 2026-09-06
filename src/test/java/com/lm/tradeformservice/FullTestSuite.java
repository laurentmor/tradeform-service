package com.lm.tradeformservice;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import com.lm.tradeformservice.dto.TradeformTest;
import com.lm.tradeformservice.dto.TradeFormStatusTest;

@Suite
@SelectClasses({
	TradeformTest.class,
	TradeFormStatusTest.class
})
@SuiteDisplayName("Full Test Suite")
public class FullTestSuite {

}
