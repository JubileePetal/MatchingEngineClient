package testcases;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import communication.Receiver;
import control.Initializer;


public class InitializerTest {

	private Initializer initializer;
	
	@Before
	public void setUp() throws Exception {
		initializer = new Initializer("localhost", 1337);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
