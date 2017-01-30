package theFridge.model;

import java.util.ArrayList;

public class UserStockListModel {
	private String name;
	private ArrayList<StockModel> stockList;
	
	public UserStockListModel(String name, ArrayList<StockModel> stockList) {
		super();
		this.name = name;
		this.stockList = stockList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<StockModel> getStockList() {
		return stockList;
	}

	public void setStockList(ArrayList<StockModel> stockList) {
		this.stockList = stockList;
	}
	
}
