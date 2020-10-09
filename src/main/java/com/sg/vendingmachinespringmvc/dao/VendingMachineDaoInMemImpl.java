/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mirandabeamer
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao{
    private Map<Integer, Item> itemMap = new HashMap<>();
//    public static final String VENDING_FILE = "items.txt";
//    public static final String DELIMITER = "::";

    @Override
    public Item getItem(int menuSelection) {
       //loadItemsFromFile();
       Item item= itemMap.get(menuSelection);
       return item;
    }

    @Override
    public List<Item> getAllItems() {
        //loadItemsFromFile();
        Collection<Item> c = itemMap.values();
        return new ArrayList(c);
    }
    
    @Override
    public Item updateInventory(Item item) {
        int itemInventory = item.getInventory();
        item.setInventory(itemInventory - 1);
        return item;
    }

    @Override
    public void loadItems() {
        Item item = new Item();
        item.setMenuSelection(1);
        item.setItemName("Snickers");
        BigDecimal cost = new BigDecimal(1.25).setScale(2);
        item.setCost(cost);
        item.setInventory(10); 
        itemMap.put(1, item);
        
        Item item2 = new Item();
        item2.setMenuSelection(2);
        item2.setItemName("Twix");
        item2.setCost(cost);
        item2.setInventory(10); 
        itemMap.put(2, item2);
        
        Item item3 = new Item();
        item3.setMenuSelection(3);
        item3.setItemName("Skittles");
        item3.setCost(cost);
        item3.setInventory(10); 
        itemMap.put(3, item3);
        
        Item item4 = new Item();
        item4.setMenuSelection(4);
        item4.setItemName("Cheetos");
        cost = new BigDecimal(1.50).setScale(2);
        item4.setCost(cost);
        item4.setInventory(10); 
        itemMap.put(4, item4);
        
        Item item5 = new Item();
        item5.setMenuSelection(5);
        item5.setItemName("Doritos");
        item5.setCost(cost);
        item5.setInventory(10); 
        itemMap.put(5, item5);
        
        Item item6 = new Item();
        item6.setMenuSelection(6);
        item6.setItemName("Pretzels");
        item6.setCost(cost);
        item6.setInventory(10); 
        itemMap.put(6, item6);
        
        Item item7 = new Item();
        item7.setMenuSelection(7);
        item7.setItemName("Coke");
        cost = new BigDecimal(1.75).setScale(2);
        item7.setCost(cost);
        item7.setInventory(10); 
        itemMap.put(7, item7);
        
        Item item8 = new Item();
        item8.setMenuSelection(8);
        item8.setItemName("Sprite");
        item8.setCost(cost);
        item8.setInventory(10); 
        itemMap.put(8, item8);
        
        Item item9 = new Item();
        item9.setMenuSelection(9);
        item9.setItemName("Powerade");
        cost = new BigDecimal(2.00).setScale(2);
        item9.setCost(cost);
        item9.setInventory(10); 
        itemMap.put(9, item9);
    }
    
//    //results in null pointer exception 
//    private void loadItemsFromFile() {
//        Scanner scanner = null;
//        
//        try{
//            scanner = new Scanner(new BufferedReader(new FileReader(VENDING_FILE)));
//        } catch(FileNotFoundException e){
//            
//        }
//        
//        String currentLine;
//        String[] currentTokens;
//        //no next line found
//        while(scanner.hasNextLine()){
//            currentLine=scanner.nextLine();
//            currentTokens = currentLine.split(DELIMITER);
//            int menuSelection = Integer.parseInt(currentTokens[0]);
//            Item currentItems = new Item();
//            currentItems.setMenuSelection(menuSelection);
//            currentItems.setItemName(currentTokens[1]);
//            BigDecimal cost = new BigDecimal(currentTokens[2]);
//            currentItems.setCost(cost);
//            int inventory = Integer.parseInt(currentTokens[3]);
//            currentItems.setInventory(inventory);
//            
//            itemMap.put(currentItems.getMenuSelection(), currentItems);
//        }
//        scanner.close();
//    }
//    
//    private void writeItems() {
//        PrintWriter out = null;
//        
//        try{
//            out = new PrintWriter(new FileWriter(VENDING_FILE));
//        } catch (IOException e){
//            
//        }
//        
//        List<Item> items = this.getAllItems();
//        for(Item currentItem : items){
//            out.println(currentItem.getMenuSelection() + DELIMITER +
//                    currentItem.getItemName() + DELIMITER  + 
//                    currentItem.getCost() + DELIMITER + 
//                    currentItem.getInventory());
//            out.flush();
//        }
//        out.close();
//    }

}
