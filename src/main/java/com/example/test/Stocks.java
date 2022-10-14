package com.example.test;

import java.util.ArrayList;

public class Stocks {
    private ArrayList<Double> stockPrice = new ArrayList<>();
    private String stockName;
    private int trend =1;

    public Stocks(String stockName){
        this.stockName = stockName;
        stockPrice.add((double)(Math.round(Math.random()*100)));
    }
    //optional way to think about stock price change
//    public void setTrend(int change) {
//        //case statements to decide trend
//        if((int)(Math.random()*10)>3){
//            trend *=-1;
//        }
//        this.trend = this.trend+ change;
//    }

    public void changeCurrentPrice(){
//        double temp = getCurrentPrice()*(1+ trend/10.0);
        double incdec = Math.floor(Math.random()*2);
        double temp;
        if (incdec==0){
            temp = Math.floor(Math.random()*10);
        }else{
            temp = Math.floor(Math.random()*10)*-1;
        }
        double price = this.getCurrentPrice();
        price+=temp;

        stockPrice.add(price);
    }
    public Double getCurrentPrice(){
        return stockPrice.get(stockPrice.size()-1);
    }
    public ArrayList<Double> getStockPrice() {
        return stockPrice;
    }

    public String getStockName() {
        return stockName;
    }
}
