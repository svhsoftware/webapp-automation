package com.svhsoftware.automation.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, ISuiteListener {

	protected static final Logger log = LogManager.getLogger("Automation");
	
	@Override
	public void onFinish(ISuite suite) {
		log.info("Finishing suite : " + suite.getName());
	}

	@Override
	public void onStart(ISuite suite) {
		log.info("Starting suite : " + suite.getName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		log.info("Finishing test : " + context.getName());
	}

	@Override
	public void onStart(ITestContext context) {
		log.info("Starting test : " + context.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.error("Test Failed : " + result.toString());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
}

