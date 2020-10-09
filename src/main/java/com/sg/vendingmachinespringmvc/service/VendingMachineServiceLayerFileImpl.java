/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mirandabeamer
 */
public class VendingMachineServiceLayerFileImpl implements VendingMachineServiceLayer{
    static BigDecimal userBank = new BigDecimal(0);
    VendingMachineDao dao;
    
    public VendingMachineServiceLayerFileImpl(VendingMachineDao dao){
        this.dao=dao;
    }
    
    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(int menuSelection) {
        Item item = dao.getItem(menuSelection);
        return item;
    }

    @Override
    public BigDecimal getCurrentBalance() {
        return userBank;
    }

    @Override
    public BigDecimal addFunds(BigDecimal addedCash) {
        userBank = userBank.add(addedCash);
        return userBank;
    }

    @Override
    public BigDecimal makePurchase(Item item, BigDecimal cashGiven) throws NoItemInventoryException, InsufficientFundsException {
        BigDecimal cost = item.getCost();
        int inventory = item.getInventory();
        //check inventory
        validateInventory(item);
        //check funds
        validateFunds(item, cashGiven);
        userBank = cashGiven.subtract(cost);
        return userBank;
    }
    
    @Override
    public Item updateInventory(Item item) {
        item = dao.updateInventory(item);
        return item;
    }

    @Override
    public Change provideChange(BigDecimal currentBalance) {
        Change change = new Change(currentBalance);
        userBank = new BigDecimal(0);
        return change;
    }
    
    @Override
    public void loadItems() {
        dao.loadItems();
    }
    
    private void validateFunds(Item item, BigDecimal cashGiven) throws InsufficientFundsException {
        BigDecimal cost = item.getCost();
        int result = cost.compareTo(cashGiven);
        
        if(result == 1){
            throw new InsufficientFundsException (
            "Error: not enough cash provided");
        }
    }
    
    private void validateInventory(Item item) throws NoItemInventoryException {
        if(item.getInventory() < 1){
            throw new NoItemInventoryException(
            "Error: No Items left in inventory");
        }
    }
    
}
