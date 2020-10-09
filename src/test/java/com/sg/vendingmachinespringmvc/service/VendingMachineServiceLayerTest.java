/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mirandabeamer
 */
public class VendingMachineServiceLayerTest {
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("vendingmachineService", VendingMachineServiceLayer.class);
        
        service.loadItems();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() {
        List<Item> items = service.getAllItems();
        assertEquals(9, items.size());
    }


    /**
     * Test of makePurchase method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testAddFundsGetBalanceMakePurchase() throws Exception {
        //get item test
        Item itemToPurchase = service.getItem(1);
        BigDecimal cashToAdd = new BigDecimal(5);
        //add funds 
        service.addFunds(cashToAdd);
        //check that getcurrentbalance is correct
        assertEquals(service.getCurrentBalance(), cashToAdd);
        BigDecimal change = cashToAdd.subtract(itemToPurchase.getCost());
        //ensure makepurchase returns correct change 
        assertEquals(change, service.makePurchase(itemToPurchase, cashToAdd));
        
        //did 3 tests in one because add funds would have to be done before make purchase anyway
    }

    /**
     * Test of updateInventory method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testUpdateInventory() {
        Item item = service.getItem(1);
        //put inventory into a variable before updating inventory 
        int inventory = item.getInventory();
        item = service.updateInventory(item);
        assertEquals(inventory - 1, item.getInventory());
    }

    /**
     * Test of provideChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testProvideChange() {
        BigDecimal cash = new BigDecimal(1.25);
        String changeStr = "5 Quarters";
        Change change = service.provideChange(cash);
        assertEquals(changeStr, change.getQuarters());
    }

    
    
}
