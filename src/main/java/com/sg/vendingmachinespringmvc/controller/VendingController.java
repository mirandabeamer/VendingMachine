/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mirandabeamer
 */
@Controller
public class VendingController {
    private VendingMachineServiceLayer service;
    //variables that need to be maintained/updated
    Item itemSelected;
    String message;
    BigDecimal userBank = new BigDecimal(0).setScale(2);
    Change change = new Change("", "", "");
    
    @Inject
    public VendingController(VendingMachineServiceLayer service){
        this.service= service;
        loadItems();
    }
    
    
    @RequestMapping(value={"/displayVendingItems", "/"}, method = RequestMethod.GET)
    public String displayVendingItems(Model model) {
        //first need to get all items from DAO to display
        List<Item> itemList = service.getAllItems();
        model.addAttribute("itemList", itemList);
        model.addAttribute("itemSelected", itemSelected);
        model.addAttribute("userBank", userBank);
        model.addAttribute("message", message);
        model.addAttribute("change", change);
        return "index";
    }
    
    @RequestMapping(value="/getItemSelection", method=RequestMethod.GET)
    public String getItemSelection(HttpServletRequest request){
        String menuSelectionParameter = request.getParameter("itemSelection");
        int menuSelection = Integer.parseInt(menuSelectionParameter);
        itemSelected = service.getItem(menuSelection);
        //reset message and change when new item selected
        message = "";
        change.setQuarters("");
        change.setNickels(""); 
        change.setDimes("");
        return "redirect:/";
    }
    
    @RequestMapping(value="/makePurchase", method=RequestMethod.GET)
    public String makePurchase(){
        try{
            try{
                userBank = service.makePurchase(itemSelected, service.getCurrentBalance());
                itemSelected = service.updateInventory(itemSelected);
                message = "Thank you!";
            } catch(InsufficientFundsException e){
                BigDecimal cashNeeded = itemSelected.getCost().subtract(userBank);
                message = "Please deposit: $" + cashNeeded;
            } 
        } catch(NoItemInventoryException e){
            message = "SOLD OUT!!";
        }
        //model.addAttribute("itemSelected", itemSelected);
        //model.addAttribute("message", message);
        return "redirect:/";
    }
    
    @RequestMapping(value="/addMoney", method=RequestMethod.GET)
    public String addMoney(HttpServletRequest request){
        //reset change when more money added
        change.setQuarters("");
        change.setNickels(""); 
        change.setDimes("");
        //instantiate money added
         BigDecimal moneyAdded = new BigDecimal(0);
         
         
         if(request.getParameter("dollar") != null){
             moneyAdded = moneyAdded.add(new BigDecimal(1).setScale(2));
         }
        if(request.getParameter("quarter") != null){
             moneyAdded = moneyAdded.add(new BigDecimal(0.25).setScale(2, RoundingMode.UP));
         }
        if(request.getParameter("dime") != null){
             moneyAdded = moneyAdded.add(new BigDecimal(0.10).setScale(2, RoundingMode.DOWN));
         }
        if(request.getParameter("nickel") != null){
             moneyAdded = moneyAdded.add(new BigDecimal(0.05).setScale(2, RoundingMode.DOWN));
         }
        
        service.addFunds(moneyAdded);
        moneyAdded = new BigDecimal(0);
        userBank = service.getCurrentBalance();
        //model.addAttribute("userBank", userBank);
        return "redirect:/";
    }
    
    @RequestMapping(value="/getChange", method=RequestMethod.GET)
    public String getChange(){
        change = service.provideChange(userBank);
        //model.addAttribute("change", change);
        userBank = new BigDecimal(0);
        return "redirect:/";
    }
    
    private void loadItems(){
        service.loadItems();
    }
    
}
