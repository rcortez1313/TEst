package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class HelloController {
    @FXML
    LineChart priceChart;
    @FXML
    Label lstInfo;
    @FXML
    private TextField txtPName, txtSName, txtAmount;
    @FXML
    private ListView lstPeople, lstStocksAvailable, lstStocksOwned;
    private ArrayList<Person> people = new ArrayList<>();
    private ArrayList<Stocks> stock = new ArrayList<>();

    Stocks stockPicked;
    Person personPicked;
    public void handleLstPeople(MouseEvent mouseEvent) {
        System.out.println("test");
        String personName  = lstPeople.getSelectionModel().getSelectedItem().toString();
        System.out.println(personName);
        personName = personName.substring(0,personName.indexOf(","));
        System.out.println(personName);
        for(Person x : people){
            if(personName.equals(x.getPersonName())){
                personPicked = x;
            }
        }
        lstStocksOwned.getItems().clear();
        for(IndividualStockOwned x:  personPicked.getStocksOwned()){
            lstStocksOwned.getItems().add(x.getCurrentStock().getStockName() + ",  Number of Shares Owned: "
                    +x.getNumowned());
        }
    }

    public void handleLstStocksAvailable(MouseEvent mouseEvent) {
        String stockName  = lstStocksAvailable.getSelectionModel().getSelectedItem().toString();
        stockName = stockName.substring(0,stockName.indexOf(","));
        System.out.println(stockName);
        for(Stocks x : stock){
            if(stockName.equals(x.getStockName())){
                stockPicked = x;
            }
        }
        priceChart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName("Stock Price");

        series.getData().clear();
        for (int i = 0; i < stockPicked.getStockPrice().size(); i++) {
            series.getData().add(new XYChart.Data(i, stockPicked.getStockPrice().get(i)));
        }
        priceChart.getData().add(series);

    }

    public void handlelstStocksOwned(MouseEvent mouseEvent) {
    }

    public void handleAddStock(ActionEvent actionEvent) {
        if(!txtSName.getText().equals("")){
            stock.add(new Stocks(txtSName.getText()));
        }
        lstStocksAvailable.getItems().clear();
        for(Stocks x: stock){
            lstStocksAvailable.getItems().add(x.getStockName() + ",  Current Price: " + x.getCurrentPrice());
        }
        
    }

    public void handleAddPerson(ActionEvent actionEvent) {
        if(!txtPName.getText().equals("")){
            people.add(new Person(txtPName.getText(), Math.floor(Math.random()*500)));
        }
        lstPeople.getItems().clear();
        for(Person x: people){
            lstPeople.getItems().add(x.getPersonName() + ",  Cash Balance: " + x.getCash());
        }
    }
    @FXML
    public void handleBuy() {
//        System.out.println(stockPicked.getStockName());
//        System.out.println(personPicked.getPersonName());
        if(stockPicked == null || personPicked == null){
            System.out.println("no stock or person picked");
            return;
        }else{
            lstInfo.setText(personPicked.buyStock(stockPicked,Integer.parseInt(txtAmount.getText())));
        }
        lstStocksOwned.getItems().clear();
        lstPeople.getItems().clear();
        for(IndividualStockOwned x:  personPicked.getStocksOwned()){
            lstStocksOwned.getItems().add(x.getCurrentStock().getStockName() + ",  Number of Shares Owned: "
            +x.getNumowned());
        }

        for(Person x: people){
            lstPeople.getItems().add(x.getPersonName() + ",  Cash Balance: " + x.getCash());
        }
    }
    @FXML
    public void handleSell() {
        if(stockPicked == null || personPicked == null){
            System.out.println("no stock or person picked");
            return;
        }else{
            lstInfo.setText(personPicked.sellStock(stockPicked,Integer.parseInt(txtAmount.getText())));
        }
        for(IndividualStockOwned x:  personPicked.getStocksOwned()){
            lstStocksOwned.getItems().add(x.getCurrentStock().getStockName() + ",  Number of Shares Owned: "
                    +x.getNumowned());
        }
        lstPeople.getItems().clear();
        for(Person x: people){
            lstPeople.getItems().add(x.getPersonName() + ",  Cash Balance: " + x.getCash());
        }
    }
    @FXML
    public void handleDay() {
        for(Stocks x: stock){
//            x.setTrend(1);
            x.changeCurrentPrice();
        }
        lstStocksAvailable.getItems().clear();
        for(Stocks x: stock){
            lstStocksAvailable.getItems().add(x.getStockName() + ",  Current Price: " + x.getCurrentPrice());
        }
    }
}

