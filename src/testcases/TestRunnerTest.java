package testcases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
		{
		RegisterTest.class,
		LoginTest.class,
		PostAddTest.class		
			
		}
		
)
public class TestRunnerTest {

}