package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.BaseClass;
import resources.ExtentManager;
import tests.VerifyCreate;


public class TestListener extends BaseClass implements ITestListener {

	// Extent Report Declarations
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Extent Reports Version 3 Test Suite started!");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Extent Reports Version 3  Test Suite is ending!"));

		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " started!"));
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		String className = result.getTestClass().getName();
		if (className.contains("VerifyCreate")) {
			VerifyCreate getinst = (VerifyCreate) result.getInstance();
			String respval = getinst.Responsestr;
			test.get().pass(MarkupHelper.createCodeBlock(respval));

		}
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		String className = result.getTestClass().getName();
		if (className.contains("VerifyCreate")) {
			VerifyCreate getinst = (VerifyCreate) result.getInstance();
			String respval = getinst.Responsestr;
			Throwable err = result.getThrowable();
			String Console = err + respval;
			test.get().fail(MarkupHelper.createCodeBlock(Console));
		}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

}
