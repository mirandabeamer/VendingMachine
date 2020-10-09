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

/**
 *
 * @author mirandabeamer
 */
public interface VendingMachineServiceLayer {
    public void loadItems();
    
    public List<Item> getAllItems();

    public Item getItem(int menuSelection);

    public BigDecimal getCurrentBalance();
    
    public BigDecimal addFunds(BigDecimal addedCash);

    public BigDecimal makePurchase(Item item, BigDecimal cashGiven) throws NoItemInventoryException, InsufficientFundsException;
    
    public Item updateInventory(Item item);

    public Change provideChange(BigDecimal currentBalance);
}
