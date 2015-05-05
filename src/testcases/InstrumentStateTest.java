package testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import models.BookStatus;
import models.InstrumentState;
import models.OpCodes;
import models.Order;
import models.PartialTrade;
import models.TreeSetCreator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.OrderCollections;

public class InstrumentStateTest {

	InstrumentState instrumentState;
	
	@Before
	public void setUp() {
		instrumentState = new InstrumentState("Ericsson B");
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testAddOrder() {
		Order order = OrderCollections.simpleBuyOrder();
		instrumentState.addOrder(order);
		assertEquals(instrumentState.getTrueOrders().get(order.getId()), order);
	}
	
	@Test
	public void testAddTrade() {
		PartialTrade partialTrade = new PartialTrade();
		Order order = OrderCollections.simpleBuyOrder();
		partialTrade.setOrder(order);
		instrumentState.addTrade(partialTrade);
		assertEquals(instrumentState.getTrueTrades().get(0), partialTrade);
	}
	
	@Test
	public void testAddTradeLowersQuantity() {
		
		Order order = OrderCollections.simpleBuyOrder();
		order.setId(0);
		order.setOrderQuantity(10);
		instrumentState.addOrder(order);
			
		Order tradedOrder = OrderCollections.simpleBuyOrder();
		tradedOrder.setId(0);
		tradedOrder.setOrderQuantity(5);
		PartialTrade partialTrade = new PartialTrade();
		partialTrade.setOrder(tradedOrder);		
		
		instrumentState.addTrade(partialTrade);
		
		int newQuantity = instrumentState.getTrueOrders().get(order.getId()).getQuantity();
		
		assertEquals(newQuantity, 5);
	}
	
	@Test
	public void testAddTradeRemovesOrder() {
		
		Order order = OrderCollections.simpleBuyOrder();
		order.setId(0);
		order.setOrderQuantity(10);
		instrumentState.addOrder(order);
			
		Order tradedOrder = OrderCollections.simpleBuyOrder();
		tradedOrder.setId(0);
		tradedOrder.setOrderQuantity(10);
		PartialTrade partialTrade = new PartialTrade();
		partialTrade.setOrder(tradedOrder);		
		
		instrumentState.addTrade(partialTrade);
		
		assertTrue(instrumentState.getTrueOrders().isEmpty());
	}

	@Test
	public void testGetOrders() {
		
		Order order = OrderCollections.simpleBuyOrder();
		instrumentState.addOrder(order);
		
		ArrayList<Object[]> orders = instrumentState.getOrders();
		
		assertEquals(orders.get(0)[0], order.getId());
		
		if(order.isBuyOrSell() == OpCodes.BUY_ORDER) {
			assertEquals(orders.get(0)[1], "Buy");
		} else {
			assertEquals(orders.get(0)[1], "Sell");
		}

		assertEquals(orders.get(0)[2], order.getPrice());
		assertEquals(orders.get(0)[3], order.getQuantity());
		
	}

	@Test
	public void testGetTrades() {
		
		PartialTrade partialTrade = new PartialTrade();
		Order order = OrderCollections.simpleBuyOrder();
		partialTrade.setOrder(order);
		instrumentState.addTrade(partialTrade);
		
		ArrayList<Object[]> trades = instrumentState.getTrades();
		
		assertEquals(trades.get(0)[0], partialTrade.getTradeID());
		
		if(order.isBuyOrSell() == OpCodes.BUY_ORDER) {
			assertEquals(trades.get(0)[1], "Buy");
		} else {
			assertEquals(trades.get(0)[1], "Sell");
		}
		
		assertEquals(trades.get(0)[2], partialTrade.getOrder().getPrice());
		assertEquals(trades.get(0)[3], partialTrade.getOrder().getQuantity());
	}
	
	@Test
	public void testGetMarketData() {
		
		TreeSet<Order> buyOrders = TreeSetCreator.createBuyOrderSet();
		
		Order buyOrderOne = OrderCollections.simpleBuyOrder();
		buyOrderOne.setPrice(10);
		buyOrders.add(buyOrderOne);
		
		Order buyOrderTwo = OrderCollections.simpleBuyOrder();
		buyOrderTwo.setPrice(5);
		buyOrders.add(buyOrderTwo);
		
		TreeSet<Order> sellOrders = TreeSetCreator.createSellOrderSet();
		
		Order sellOrderOne = OrderCollections.simpleSellOrder();
		sellOrderOne.setPrice(15);
		sellOrders.add(sellOrderOne);
		
		BookStatus bookStatus = new BookStatus("Test");
		bookStatus.generateBuyLevels(buyOrders);
		bookStatus.generateSellLevels(sellOrders);
		
		instrumentState.newMD(bookStatus);
		ArrayList<Object[]> MD = instrumentState.getMarketData();
		
		assertEquals(MD.get(0)[0], buyOrderOne.getQuantity());
		assertEquals(MD.get(0)[1], buyOrderOne.getPrice());
		assertEquals(MD.get(0)[2], sellOrderOne.getPrice());
		assertEquals(MD.get(0)[3], sellOrderOne.getQuantity());
		assertEquals(MD.get(1)[0], buyOrderTwo.getQuantity());
		assertEquals(MD.get(1)[1], buyOrderTwo.getPrice());
		assertEquals(MD.get(1)[2], "");
		assertEquals(MD.get(1)[3], "");
		
	}
}
