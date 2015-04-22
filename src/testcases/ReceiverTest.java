package testcases;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import models.Instrument;
import models.Message;
import models.OpCodes;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import communication.Receiver;
import control.Controller;
import static org.mockito.Mockito.*;

public class ReceiverTest {

	Receiver receiver;
	
	Instrument instrument;	
	String json;	
	String abbrev;
	String fullName;
	int type;
	
	Message message;
	String msg;
	int msgType;
	
	@Before
	public void setUp() throws Exception {
		receiver = new Receiver();
		
		abbrev = "ERIC A";
		fullName = "Ericsson A";
		type = OpCodes.SHARE;
		
		instrument = new Instrument();
		instrument.setAbbreviation(abbrev);
		instrument.setName(fullName);
		instrument.setType(type);
		
		Gson gson = new Gson();
		
		json = gson.toJson(instrument);
		
		message = new Message();
		message.setType(OpCodes.LOG_IN);
		message.setJson(json);
		
		msg = gson.toJson(message);
		
        byte[] payload = (msg + '\n').getBytes();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload);
        
		final Socket socket = mock(Socket.class);
	
		try {
			when(socket.getInputStream()).thenReturn(byteArrayInputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		InputStream inputStream = null;
		try {
			inputStream = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		assertNotNull(inputStream);
		receiver.addBufferedReader(reader);
	}

	@Test
	public void testReadFromServer() {
		
		String received = receiver.readFromServer();
		assertEquals(msg, received);
	}
	
	@Test
	public void testUnpackMessageNotNull() {
		
		String received = receiver.readFromServer();
		Message unpackedMsg = receiver.unpackMessage(received);
		assertNotNull(unpackedMsg);
		
	}
	
	@Test
	public void testUnpackMessageTypeEquals() {
		
		String received = receiver.readFromServer();
		Message unpackedMsg = receiver.unpackMessage(received);
		assertEquals(unpackedMsg.getType(),message.getType());
		
	}
	
	@Test
	public void testUnpackMessageJsonEquals() {
		
		String received = receiver.readFromServer();
		Message unpackedMsg = receiver.unpackMessage(received);
		assertEquals(unpackedMsg.getJson(),message.getJson());
		
	}
	/*
	@Test
	public void testRouteToDestination() {
		Receiver receiver = mock(Receiver.class);
		receiver.routeToDestination(message);
		verify(receiver,atLeastOnce()).logInAccepted(message);
		
	}
	*/
}
