package com.homeaway.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.homeaway.testcases.CartValidation;
import com.homeaway.testcases.MyAccountValidations;
import com.homeaway.testcases.SubmitOrderValidations;
/**
 * This is the test Suite class
 * 
 * Run as JUnit to run all the tests together
 * @author Abhibroto
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ SubmitOrderValidations.class, CartValidation.class, MyAccountValidations.class })
public class AllTests {

}
