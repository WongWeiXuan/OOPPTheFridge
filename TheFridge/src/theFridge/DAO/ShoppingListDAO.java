package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import theFridge.model.ListModel;
import theFridge.model.StockModel;
import theFridge.model.User;
import theFridge.model.UserListListModel;
import theFridge.model.UserStockListModel;

public class ShoppingListDAO {
	private File stockFile;
	private File listFile;
	private int numberOfPeople;
	private static String name;
	
	public ShoppingListDAO(){
		stockFile = new File("src/theFridge/file/StockList.txt");
		listFile = new File("src/theFridge/file/Listlist.txt");
	}
	
	public ArrayList<UserStockListModel> getAllUserAndList() throws FileNotFoundException{
		ArrayList<UserStockListModel> arrayList = new ArrayList<UserStockListModel>();
		Scanner sc = new Scanner(stockFile);
		while(sc.hasNextLine()){
			UserStockListModel a = getStockWithString(sc.nextLine());
			arrayList.add(a);
		}
		sc.close();

		return arrayList;
	}
	
	public ArrayList<UserListListModel> getAllUserAndListList() throws FileNotFoundException{
		ArrayList<UserListListModel> arrayList = new ArrayList<UserListListModel>();
		Scanner sc = new Scanner(listFile);
		while(sc.hasNextLine()){
			UserListListModel a = getListWithString(sc.nextLine());
			arrayList.add(a);
		}
		sc.close();

		return arrayList;
	}
	
	public ArrayList<StockModel> getAllStock(String Username) throws FileNotFoundException{
		UserStockListModel g = getUser(Username);
		ArrayList<StockModel> List = g.getStockList();
	
		return List;
	}
	
	public ArrayList<ListModel> getAllListWithName(String Username) throws FileNotFoundException{
		UserListListModel g = getUserList(Username);
		ArrayList<ListModel> List = g.getListList();
	
		return List;
	}
	
	public UserStockListModel getStockWithString(String stringLine) throws FileNotFoundException{
		int i = 0;
		Scanner sc = new Scanner(stringLine);
		String line=null;
		String[] fields;
		ArrayList<StockModel> stocks=new ArrayList<StockModel>();
		String userName = null;
		sc.useDelimiter(":");
		while (sc.hasNext()) {
			if(i == 0){
				userName = sc.next();
				i++;
			}
			try{
				line = sc.next();
				fields = line.split("~");
				String name = fields[0];
				double amount = Double.parseDouble(fields[1]);
				double serving = Double.parseDouble(fields[2]);
				int grams = Integer.parseInt(fields[3]);
				double maxAmount = Double.parseDouble(fields[4]);
				StockModel a = new StockModel(name, amount, serving, grams, maxAmount, false);
				stocks.add(a);
			}catch(NoSuchElementException e){}
		}
		sc.close();
		UserStockListModel b = new UserStockListModel(userName, stocks);

		return b;
	}
	
	public UserListListModel getListWithString(String stringLine) throws FileNotFoundException{
		int i = 0;
		Scanner sc = new Scanner(stringLine);
		String line=null;
		String[] fields;
		ArrayList<ListModel> lists = new ArrayList<ListModel>();
		String userName = null;
		sc.useDelimiter(":");
		while (sc.hasNext()) {
			if(i == 0){
				userName = sc.next();
				i++;
			}
			try{
				line = sc.next();
				fields = line.split("~");
				String name = fields[0];
				double amount = Double.parseDouble(fields[1]);
				double maxAmount = Double.parseDouble(fields[2]);
				ListModel a = new ListModel(name, amount, maxAmount);
				lists.add(a);
			}catch(NoSuchElementException e){}
		}
		sc.close();
		UserListListModel b = new UserListListModel(userName, lists);

		return b;
	}
	
	public ArrayList<ListModel> getAllList() throws FileNotFoundException{
		return getUserList(name).getListList();
	}
	
	public ArrayList<ListModel> getAllList(int numberOfPeople) throws FileNotFoundException{
		this.numberOfPeople = numberOfPeople;
		User u = new User();
		u = u.getCurrentUser();
		name = u.getUsername();
		try{
			ArrayList<ListModel> lm = getUserList(name).getListList();
			return lm;
		}catch(NullPointerException e){
			return null;
		}
		
	}
	
	public ArrayList<ListModel> initializeList(int numberOfPeople) throws FileNotFoundException{
		this.numberOfPeople = numberOfPeople;
		ArrayList<ListModel> lists=new ArrayList<ListModel>();
		ArrayList<StockModel> stocks = getAllStock(name);
		for(StockModel s: stocks){
			int maximum = (int) (s.getServing() * numberOfPeople);
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
		int maximum = (int) (stock.getServing() * numberOfPeople);
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
		User u = new User();
		u = u.getCurrentUser();
		StockModel model = null;
		for(StockModel sm:getAllStock(u.getUsername())){
			if(sm.getName().equalsIgnoreCase(name)){
				model = sm;
			}
		}
		return model;
	}
	
	public ListModel getListModelByName(String name) throws FileNotFoundException{
		User u = new User();
		u = u.getCurrentUser();
		ListModel model = null;
		for(ListModel lm:getAllListWithName(u.getUsername())){
			if(lm.getName().equalsIgnoreCase(name)){
				model = lm;
			}
		}
		return model;
	}
	
	public void writeToStockFile(ArrayList<StockModel> stocks) throws IOException{
		ArrayList<UserStockListModel> aluslm = getAllUserAndList();
		//System.out.println(aluslm.get(0).getName());
		UserStockListModel uslm = new UserStockListModel(name, stocks);
		if(checkUser(name)){
			aluslm.set(getUserIndex(), uslm);
		}
		else{
			aluslm.add(uslm);
		}
		
		boolean first = true;
		String line = "";
		for(UserStockListModel a:aluslm){
			line += a.getName() + ":";
			for(StockModel b:a.getStockList()){
				if(first == true){
					line += b.getName() + "~" + b.getAmount() + "~" + b.getServing() + "~" + b.getGrams() + "~" + b.getMaxAmount();
					first = false;
				}
				else{
					line += ":" + b.getName() + "~" + b.getAmount() + "~" + b.getServing() + "~" + b.getGrams() + "~" + b.getMaxAmount();
				}
			}
			line += "\n";
			first = true;
		}
		FileWriter writer = new FileWriter(stockFile);
		writer.write(line);
		writer.flush();
		writer.close();
	}
	
	public void writeToListFile(ArrayList<ListModel> lists) throws IOException{
		ArrayList<UserListListModel> aluslm = getAllUserAndListList();
		User u = new User();
		u = u.getCurrentUser();
		name = u.getUsername();
		UserListListModel uslm = new UserListListModel(name, lists);
		if(checkListUser(name)){
			aluslm.set(getListUserIndex(), uslm);
		}
		else{
			aluslm.add(uslm);
		}
		
		boolean first = true;
		String line = "";
		for(UserListListModel a:aluslm){
			line += a.getName() + ":";
			for(ListModel b:a.getListList()){
				if(first == true){
					line += b.getName() + "~" + b.getAmount() + "~" + b.getMaxAmount();
					first = false;
				}
				else{
					line += ":" + b.getName() + "~" + b.getAmount() + "~" + b.getMaxAmount();
				}
			}
			line += "\n";
			first = true;
		}
		FileWriter writer = new FileWriter(listFile);
		writer.write(line);
		writer.flush();
		writer.close();
	}
	
	public UserStockListModel getUser(String name1) throws FileNotFoundException{
		name = name1;
		//System.out.println("GETUSER:(name) " + name);
		for(UserStockListModel uslm:getAllUserAndList()){
			//System.out.println("GETUSERLIST: " + uslm.getName());
			if(uslm.getName().equalsIgnoreCase(name)){
				return uslm;
			}
		}
		return null;
	}
	
	public UserListListModel getUserList(String name1) throws FileNotFoundException{
		name = name1;
		//System.out.println("GETUSERLIST:(name) " + name);
		for(UserListListModel ullm:getAllUserAndListList()){
			//System.out.println("GETUSERLIST: " + ullm.getName());
			if(ullm.getName().equalsIgnoreCase(name)){
				return ullm;
			}
		}
		return null;
	}
	
	public boolean checkUser(String name1) throws FileNotFoundException{
		name = name1;
		for(UserStockListModel a:getAllUserAndList()){
			if(a.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkListUser(String name1) throws FileNotFoundException{
		name = name1;
		for(UserListListModel a:getAllUserAndListList()){
			if(a.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	public int getUserIndex() throws FileNotFoundException{
		int index = 0;
		for(UserStockListModel a:getAllUserAndList()){
			if(a.getName().equalsIgnoreCase(name)){
				return index;
			}
			index++;
		}
		return 0;
	}
	
	public int getListUserIndex() throws FileNotFoundException{
		int index = 0;
		for(UserListListModel a:getAllUserAndListList()){
			if(a.getName().equalsIgnoreCase(name)){
				return index;
			}
			index++;
		}
		return 0;
	}
	
	/*
	public static void main(String[] args) throws FileNotFoundException{
		ShoppingListDAO dao = new ShoppingListDAO();
		ArrayList<UserListListModel> alallm = dao.getAllUserAndListList();
		dao.checkUser("xxx");
		for(ListModel a: alallm.get(0).getListList()){
			System.out.println(a.getName());
			System.out.println(a.getAmount());
			System.out.println(a.getMaxAmount());
			System.out.println("-----------------");
		}
		dao.checkUser("xxy");
		ArrayList<ListModel>allm = new ArrayList<ListModel>();
		dao.writeToListFile(allm);
	}
	*/
}
