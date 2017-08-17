package utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGListener extends TestListenerAdapter{

	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailure(tr);
//		Actions.takeScreenShot(tr.getName());
	}

	
}
