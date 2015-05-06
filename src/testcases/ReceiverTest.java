package testcases;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import models.DataHolder;
import models.Instrument;
import models.Message;
import models.OpCodes;
import models.Order;
import models.PartialTrade;

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
		receiver = spy(new Receiver());
		
		abbrev = "ERIC A";
		fullName = "Ericsson A";
		type = OpCodes.SHARE;
		

		instrument = new Instrument();
		instrument.setAbbreviation(abbrev);
		instrument.setName(fullName);
		instrument.setType(type);
		PartialTrade partialTrade = new PartialTrade();
		Order order = new Order();
		order.setInstrument(instrument);
		partialTrade.setOrder(order);

		Gson gson = new Gson();
		
		json = gson.toJson(partialTrade);
		
		
		message = new Message();
		message.setType(OpCodes.PARTIAL_TRADE);
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
		receiver.addDataHolder(new DataHolder());
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
	
	@Test
	public void testRouteToDestination() {
		receiver.routeToDestination(message);
		verify(receiver, times(1)).tradeMade(message.getJson());
		
	}
	
}
