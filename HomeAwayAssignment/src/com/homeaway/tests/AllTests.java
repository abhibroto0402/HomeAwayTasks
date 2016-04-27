package com.homeaway.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.homeaway.testsuite.SubmitOrderValidations;

@RunWith(Suite.class)
@SuiteClasses({ SubmitOrderValidations.class })
public class AllTests {

}
