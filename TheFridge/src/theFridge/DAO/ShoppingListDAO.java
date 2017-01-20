package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.ListModel;
import theFridge.model.StockModel;

public class ShoppingListDAO {
	private File stockFile;
	private File listFile;
	private int numberOfPeople;
	
	public ShoppingListDAO(){
		stockFile = new File("src/theFridge/file/StockList.txt");
		listFile = new File("src/theFridge/file/Listlist.txt");
	}
	
	public ArrayList<StockModel> getAllStock() throws FileNotFoundException{
		Scanner sc = new Scanner(stockFile);
		String line=null;
		String[] fields;
		ArrayList<StockModel> stocks=new ArrayList<StockModel>();
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split("~");
			String name = fields[0];
			double amount = Double.parseDouble(fields[1]);
			int serving = Integer.parseInt(fields[2]);
			StockModel a = new StockModel(name, amount, serving);
			stocks.add(a);
		}
		sc.close();

		return stocks;
	}
	
	public ArrayList<ListModel> getAllList(int numberOfPeople) throws FileNotFoundException{
		this.numberOfPeople = numberOfPeople;
		Scanner sc = new Scanner(listFile);
		if(sc.hasNextLine() == false){
			sc.close();
			return initializeList(numberOfPeople);
		}
		else{
			String line=null;
			String[] fields;
			ArrayList<ListModel> lists=new ArrayList<ListModel>();
			
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				fields = line.split("~");
				String name = fields[0];
				double amount = Double.parseDouble(fields[1]);
				ListModel a = new ListModel(name, amount);
				lists.add(a);
			}
			sc.close();
	
			return lists;
		}
	}
	
	public ArrayList<ListModel> initializeList(int numberOfPeople) throws FileNotFoundException{
		this.numberOfPeople = numberOfPeople;
		ArrayList<ListModel> lists=new ArrayList<ListModel>();
		ArrayList<StockModel> stocks = getAllStock();
		for(StockModel s: stocks){
			int maximum = s.getServing() * numberOfPeople;
			double amount = maximum - s.getAmount();
			if(amount == 0){
				break;
			}
			else if(amount > 0){
				lists.add(new ListModel(s.getName(), amount));
			}
			else if(amount < 0){
				break;
			}
		}

		return lists;
	}
	
	public ListModel getListWithStock(StockModel stock){
		int maximum = stock.getServing() * numberOfPeople;
		double amount = maximum - stock.getAmount();
		if(amount == 0){
			return null;
		}
		else if(amount > 0){
			ListModel listModel = new ListModel(stock.getName(), amount);
			return listModel;
		}
		else if(amount < 0){
			return null;
		}
		else{
			return null;
		}
	}
	
	public void writeToStockFile(ArrayList<StockModel> stocks) throws IOException{
		FileWriter writer = new FileWriter(stockFile);
		String lineLine = "";
		boolean first = true;
		for(StockModel s:stocks){
			String line = s.getName() + "~" + s.getAmount() + "~" + s.getServing() + "~" + s.getMaxAmount();
			if(first == true){
				lineLine += line;
				first = false;
			}
			else{
				lineLine += "\n" + line;
			}
		}
		writer.write(lineLine);
		writer.flush();
		writer.close();
	}
	
	public void writeToListFile(ArrayList<ListModel> lists) throws IOException{
		FileWriter writer = new FileWriter(listFile);
		String lineLine = "";
		boolean first = true;
		for(ListModel l:lists){
			String line = l.getName() + "~" + l.getAmount();
			if(first == true){
				lineLine += line;
				first = false;
			}
			else{
				lineLine += "\n" + line;
			}
		}
		writer.write(lineLine);
		writer.flush();
		writer.close();
	}
	
	/*
	private void printList(ArrayList<ListModel>list){
		for(ListModel l:list){
			System.out.println("Name: " + l.getName() + "\nAmount: " + l.getAmount() + "\n");
		}
	}
	
	public static void main(String args[]) throws IOException{
		ShoppingListDAO a = new ShoppingListDAO();
		ArrayList<StockModel>stocklist = new ArrayList<StockModel>();
		stocklist.add(new StockModel("First", 1, 1));
		stocklist.add(new StockModel("Second", 2, 2));
		stocklist.add(new StockModel("Third", 3, 3));
		stocklist.add(new StockModel("Fourth", 4, 5));

		a.printList(a.getAllList(4));
	}
	*/
	
}
