package com.homeaway.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.homeaway.testcases.SubmitOrderValidations;

@RunWith(Suite.class)
@SuiteClasses({ SubmitOrderValidations.class })
public class AllTests {

}
