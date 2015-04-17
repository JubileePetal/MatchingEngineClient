package testcases;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import communication.Sender;

import control.Controller;
import control.GUI;
import control.Initializer;


public class ControllerTest {

	private Controller controller;
	
	@Before
	public void setUp() throws Exception {
		Initializer initializer = new Initializer("localhost",1337);
	}

	@Test
	public void testCreation() {
		assertNotNull(controller);
	}
}
