package testcases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import communication.Receiver;
import control.Controller;

public class ReceiverTest {

	Receiver receiver;
	
	@Before
	public void setUp() throws Exception {
		receiver = new Receiver();
	}

	@Test
	public void addControllerTest() {

	}

}
