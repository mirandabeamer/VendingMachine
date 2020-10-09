/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;

/**
 *
 * @author mirandabeamer
 */
public class Change {

    private String quarters;
    private String dimes;
    //private int pennies = 0;
    private String nickels;

    public Change(BigDecimal change) {
        int quartersNum = 0;
        int dimesNum = 0;
        int nickelsNum = 0;

        BigDecimal op = new BigDecimal(100);
        int changeInPennies = (change.multiply(op)).intValue();
        int leftOverchange;

        while (changeInPennies > 24) {
            leftOverchange = changeInPennies - 25;
            quartersNum++;
            changeInPennies = leftOverchange;
        }
        while (changeInPennies > 9) {
            leftOverchange = changeInPennies - 10;
            dimesNum++;
            changeInPennies = leftOverchange;
        }
        while (changeInPennies > 4) {
            leftOverchange = changeInPennies - 5;
            nickelsNum++;
            changeInPennies = leftOverchange;
        }
        
        quarters = quartersNum + " Quarters";
        dimes = dimesNum + " Dimes";
        nickels = nickelsNum + " Nickels";
        //pennies = changeInPennies;
    }

    /**
     *
     * @param quarters
     * @param dimes
     * @param nickels
     */
    public Change(String quarters, String dimes, String nickels) {
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        //this.pennies = pennies;
    }

    public String getQuarters() {
        return quarters;
    }

    public void setQuarters(String quarters) {
        this.quarters = quarters;
    }

    public String getDimes() {
        return dimes;
    }

    public void setDimes(String dimes) {
        this.dimes = dimes;
    }

    public String getNickels() {
        return nickels;
    }

    public void setNickels(String nickels) {
        this.nickels = nickels;
    }



}
