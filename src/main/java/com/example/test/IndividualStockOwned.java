package com.example.test;

public class IndividualStockOwned {
    private int numowned=0;
    Stocks currentStock;

    public IndividualStockOwned(Stocks stock,int numBought){
        currentStock = stock;
        numowned= numBought;

    }


    public void  addStock(int amt){
        numowned+=amt;
    }
    public void subtractStock(int amt){
        numowned-=amt;
    }
    public int getNumowned() {
        return numowned;
    }

    public Stocks getCurrentStock() {
        return currentStock;
    }
}
