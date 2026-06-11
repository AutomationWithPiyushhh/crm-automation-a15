package extra_listeners;

import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(extra_listeners.List_Imp.class)
public class SampleTest {

	@Test
	public void createCity() {
		System.out.println("city created");
	}

	@Test(dependsOnMethods = "createCity")
	public void modifyCity() {
		Assert.assertTrue(false);
		System.out.println("city modified to new city");
	}

	@Test(dependsOnMethods = "modifyCity")
	public void deleteCity() {
		System.out.println("new city deleted");
	}

}
