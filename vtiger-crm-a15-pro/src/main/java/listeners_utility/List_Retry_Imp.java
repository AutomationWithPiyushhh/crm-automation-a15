package listeners_utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class List_Retry_Imp implements IRetryAnalyzer {

	int initCount = 0;
	int count = 7;

	@Override
	public boolean retry(ITestResult result) {
		while (count > initCount) {
			initCount++;
			return true;
		}
		return false;
	}
}
