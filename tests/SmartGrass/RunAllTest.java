package SmartGrass;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunAllTest {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(IrrigationServerTest.class);

		System.out.println(result.getRunCount() + " tests ran with " + result.getFailureCount() + " failure. " + result.getIgnoreCount() + " tests ignored!");
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println("All tests was successful: " + result.wasSuccessful());

	}
}
