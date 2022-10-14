package com.example.test;

import java.util.ArrayList;

public class Person {
    private String personName;
    private double cash;

    private ArrayList<IndividualStockOwned> stocksOwned = new ArrayList<>();


    public Person(String personName, double cash) {
        this.personName = personName;
        this.cash = cash;
    }

    public String buyStock(Stocks stockBought,int numBought) {
        boolean  owned = false;
        if(this.cash>=stockBought.getCurrentPrice()*numBought){
            for (IndividualStockOwned x:stocksOwned) {
                if(x.getCurrentStock().equals(stockBought)){
                    owned = true;
                    x.addStock(numBought);
                    this.cash -=stockBought.getCurrentPrice()*numBought;
//                IndividualStockOwned temp = x;
                    return "bought stock";
                }
            }
            if (!owned){
                stocksOwned.add(new IndividualStockOwned(stockBought,numBought));
                this.cash -=stockBought.getCurrentPrice()*numBought;
                return "bought stock";
            }
        }
        return "can't buy stock";


    }
    public String sellStock(Stocks stockSold,int numSold){

        for (IndividualStockOwned x:stocksOwned) {
            if(x.getCurrentStock().equals(stockSold)){

                if(x.getNumowned()>=numSold){
                    x.subtractStock(numSold);
                    this.cash+=stockSold.getCurrentPrice()*numSold;
                    return "you just sold " + numSold + "shares of " + stockSold.getStockName();
                }else{
                    return "You only own " + x.getNumowned() + " shares";
                }
            }


        }

            return "you don't own the stock";

    }

    public ArrayList<IndividualStockOwned> getStocksOwned() {
        return stocksOwned;
    }

    public String getPersonName() {
        return personName;
    }

    public double getCash() {
        return cash;
    }
}

