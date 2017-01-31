package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.ServingModel;

public class ServingDAO {
	File breadFile;
	File cerealFile;
	File fruitsFile;
	File pastaFile;
	File vegetableFile;
	
	public ServingDAO(){
		breadFile = new File("src/theFridge/file/servings/Bread.txt");
		cerealFile = new File("src/theFridge/file/servings/Cereal.txt");
		fruitsFile = new File("src/theFridge/file/servings/Fruits.txt");
		pastaFile = new File("src/theFridge/file/servings/Pasta.txt");
		vegetableFile = new File("src/theFridge/file/servings/Vegetables.txt");
	}
	
	public ArrayList<ServingModel> getBreadList() throws FileNotFoundException{
		Scanner sc = new Scanner(breadFile);
		ArrayList<ServingModel> list = new ArrayList<ServingModel>();
		String fields[];
		String lines = null;
		
		while(sc.hasNextLine()){
			lines = sc.nextLine();
			fields = lines.split("-");
			String name = fields[0];
			double serving = Double.parseDouble(fields[1]);
			ServingModel a = new ServingModel(name, serving);
			list.add(a);
		}
		sc.close();
		return list;
	}
	
	public ArrayList<ServingModel> getCerealList() throws FileNotFoundException{
		Scanner sc = new Scanner(cerealFile);
		ArrayList<ServingModel> list = new ArrayList<ServingModel>();
		String fields[];
		String lines = null;
		
		while(sc.hasNextLine()){
			lines = sc.nextLine();
			fields = lines.split("-");
			String name = fields[0];
			int serving = Integer.parseInt(fields[1]);
			ServingModel a = new ServingModel(name, serving);
			list.add(a);
		}
		sc.close();
		return list;
	}
	
	public ArrayList<ServingModel> getFruitsList() throws FileNotFoundException{
		Scanner sc = new Scanner(fruitsFile);
		ArrayList<ServingModel> list = new ArrayList<ServingModel>();
		
		while(sc.hasNextLine()){
			String name = sc.nextLine();
			ServingModel a = new ServingModel(name, 1);
			list.add(a);
		}
		sc.close();
		return list;
	}
	
	public ArrayList<ServingModel> getPastaList() throws FileNotFoundException{
		Scanner sc = new Scanner(pastaFile);
		ArrayList<ServingModel> list = new ArrayList<ServingModel>();
		String fields[];
		String lines = null;
		
		while(sc.hasNextLine()){
			lines = sc.nextLine();
			fields = lines.split("-");
			String name = fields[0];
			int serving = Integer.parseInt(fields[1]);
			ServingModel a = new ServingModel(name, serving);
			list.add(a);
		}
		sc.close();
		return list;
	}
	
	public ArrayList<ServingModel> getVegetableList() throws FileNotFoundException{
		Scanner sc = new Scanner(vegetableFile);
		ArrayList<ServingModel> list = new ArrayList<ServingModel>();
		
		while(sc.hasNextLine()){
			String name = sc.nextLine();
			ServingModel a = new ServingModel(name, 1);
			list.add(a);
		}
		sc.close();
		return list;
	}
	
	public ArrayList<ServingModel> getAllFoodList() throws FileNotFoundException{
		ArrayList<ServingModel> foodList = new ArrayList<ServingModel>();
		foodList.addAll(getBreadList());
		foodList.addAll(getCerealList());
		foodList.addAll(getFruitsList());
		foodList.addAll(getPastaList());
		foodList.addAll(getVegetableList());
		return foodList;
	}
	
	public double getServingWithName(String name) throws FileNotFoundException{
		for(ServingModel a:getAllFoodList()){
			if(a.getName().equalsIgnoreCase(name)){
				return a.getNumOfServing();
			}
		}
		return 1;
	}
	
	public ArrayList<String> getAllFoodName() throws FileNotFoundException{
		ArrayList<String> foodList = new ArrayList<String>();
		for(ServingModel a:getBreadList()){
			foodList.add(a.getName());
		}
		for(ServingModel a:getCerealList()){
			foodList.add(a.getName());
		}
		for(ServingModel a:getFruitsList()){
			foodList.add(a.getName());
		}
		for(ServingModel a:getPastaList()){
			foodList.add(a.getName());
		}
		for(ServingModel a:getVegetableList()){
			foodList.add(a.getName());
		}
		return foodList;
	}
	
	public String checkType(String name) throws FileNotFoundException{
		for(ServingModel a:getBreadList()){
			if(a.getName().equalsIgnoreCase(name)){
				return "Bread";
			}
		}
		for(ServingModel a:getCerealList()){
			if(a.getName().equalsIgnoreCase(name)){
				return "Cereal";
			}
		}
		for(ServingModel a:getFruitsList()){
			if(a.getName().equalsIgnoreCase(name)){
				return "Fruits";
			}
		}
		for(ServingModel a:getPastaList()){
			if(a.getName().equalsIgnoreCase(name)){
				return "Pasta";
			}
		}
		for(ServingModel a:getVegetableList()){
			if(a.getName().equalsIgnoreCase(name)){
				return "Vegetable";
			}
		}
		return null;
	}
}
