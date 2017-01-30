package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.ListModel;
import theFridge.model.StockModel;
import theFridge.model.UserStockListModel;

public class ShoppingListDAO {
	private File stockFile;
	private File listFile;
	private int numberOfPeople;
	private String name;
	
	public ShoppingListDAO(){
		stockFile = new File("src/theFridge/file/StockList.txt");
		listFile = new File("src/theFridge/file/Listlist.txt");
	}
	
	public ArrayList<UserStockListModel> getAllUserAndList() throws FileNotFoundException{
		ArrayList<UserStockListModel> arrayList = new ArrayList<UserStockListModel>();
		Scanner sc = new Scanner(stockFile);
		sc.useDelimiter("|");
		while(sc.hasNext()){
			String line=null;
			String[] fields;
			while (sc.hasNext()) {
				line = sc.nextLine();
				fields = line.split("+");
				String name = fields[0];
				String alsm = fields[1];
				ArrayList<StockModel> uslm = getStockWithString(alsm);
				
				UserStockListModel a = new UserStockListModel(name, uslm);
				arrayList.add(a);
			}
		}
		sc.close();

		return arrayList;
	}
	
	public ArrayList<StockModel> getAllStock(String Username) throws FileNotFoundException{
		UserStockListModel g = getUser(Username);
		ArrayList<StockModel> List = g.getStockList();
	
		return List;
	}
	
	public ArrayList<StockModel> getStockWithString(String stringLine) throws FileNotFoundException{
		Scanner sc = new Scanner(stringLine);
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
	
	public ArrayList<ListModel> getAllList() throws FileNotFoundException{
		Scanner sc = new Scanner(listFile);
		String line=null;
		String[] fields;
		ArrayList<ListModel> lists=new ArrayList<ListModel>();
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split("~");
			String name = fields[0];
			double amount = Double.parseDouble(fields[1]);
			double maxAmount = Double.parseDouble(fields[2]);
			ListModel a = new ListModel(name, amount, maxAmount);
			lists.add(a);
		}
		sc.close();

		return lists;
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
				double maxAmount = Double.parseDouble(fields[2]);
				ListModel a = new ListModel(name, amount, maxAmount);
				lists.add(a);
			}
			sc.close();
	
			return lists;
		}
	}
	
	public ArrayList<ListModel> initializeList(int numberOfPeople) throws FileNotFoundException{
		this.numberOfPeople = numberOfPeople;
		ArrayList<ListModel> lists=new ArrayList<ListModel>();
		ArrayList<StockModel> stocks = getAllStock(name);
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
			ListModel listModel = new ListModel(stock.getName(), amount, stock.getMaxAmount());
			return listModel;
		}
		else if(amount < 0){
			return null;
		}
		else{
			return null;
		}
	}
	
	public StockModel getStockModelByName(String name) throws FileNotFoundException{
		StockModel model = null;
		for(StockModel sm:getAllStock(name)){
			if(sm.getName().equalsIgnoreCase(name)){
				model = sm;
			}
		}
		return model;
	}
	
	public ListModel getListModelByName(String name) throws FileNotFoundException{
		ListModel model = null;
		for(ListModel lm:getAllList()){
			if(lm.getName().equalsIgnoreCase(name)){
				model = lm;
			}
		}
		return model;
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
			String line = l.getName() + "~" + l.getAmount() + "~" + l.getMaxAmount();
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
	
	public UserStockListModel getUser(String name) throws FileNotFoundException{
		this.name = name;
		for(UserStockListModel uslm:getAllUserAndList()){
			if(uslm.getName() == name){
				return uslm;
			}
		}
		return null;
	}
	
	public boolean checkUser(String name) throws FileNotFoundException{
		for(UserStockListModel a:getAllUserAndList()){
			if(a.getName() == name){
				return true;
			}
		}
		return false;
	}
	
	private void printList(ArrayList<ListModel>list){
		for(ListModel l:list){
			System.out.println("Name: " + l.getName() + "\nAmount: " + l.getAmount() + "\n");
		}
	}
	
	public static void main(String args[]) throws IOException{
		ShoppingListDAO a = new ShoppingListDAO();
		a.getAllUserAndList();
		a.getAllStock("xxx");

		System.out.println(a.getAllStock("xxx"));
	}
	
	
}
