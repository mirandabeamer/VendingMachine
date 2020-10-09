/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
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
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao;
    
    public VendingMachineDaoTest() {
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
        dao = ctx.getBean("vendingmachineDao", VendingMachineDao.class);
        
        
        
        dao.loadItems();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() {
        List<Item> items = dao.getAllItems();
        assertEquals(items.size(), 9);
    }

    /**
     * Test of updateInventory method, of class VendingMachineDao.
     */
    @Test
    public void testGetUpdateInventory() {
        Item item = dao.getItem(1);
        //get inventory first to compare
        int inventory = item.getInventory();
        item = dao.updateInventory(item);
        int inv = item.getInventory();
        assertEquals(inventory - 1, item.getInventory());
    }

    
}
