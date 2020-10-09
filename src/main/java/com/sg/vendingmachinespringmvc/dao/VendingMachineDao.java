/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mirandabeamer
 */
public interface VendingMachineDao {
    Item getItem (int menuSelection);
    
    List<Item> getAllItems();
    
    Item updateInventory(Item item);
   
    void loadItems();
}
