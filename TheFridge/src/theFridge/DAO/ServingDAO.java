package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public ArrayList<String> getBreadList() throws FileNotFoundException{
		Scanner sc = new Scanner(breadFile);
		ArrayList<String> list = new ArrayList<String>();
		while(sc.hasNextLine()){
			list.add(sc.nextLine());
		}
		sc.close();
		return list;
	}
	
	public ArrayList<String> getCerealList() throws FileNotFoundException{
		Scanner sc = new Scanner(cerealFile);
		ArrayList<String> list = new ArrayList<String>();
		while(sc.hasNextLine()){
			list.add(sc.nextLine());
		}
		sc.close();
		return list;
	}
	
	public ArrayList<String> getFruitsList() throws FileNotFoundException{
		Scanner sc = new Scanner(fruitsFile);
		ArrayList<String> list = new ArrayList<String>();
		while(sc.hasNextLine()){
			list.add(sc.nextLine());
		}
		sc.close();
		return list;
	}
	
	public ArrayList<String> getPastaList() throws FileNotFoundException{
		Scanner sc = new Scanner(pastaFile);
		ArrayList<String> list = new ArrayList<String>();
		while(sc.hasNextLine()){
			list.add(sc.nextLine());
		}
		sc.close();
		return list;
	}
	
	public ArrayList<String> getVegetableList() throws FileNotFoundException{
		Scanner sc = new Scanner(vegetableFile);
		ArrayList<String> list = new ArrayList<String>();
		while(sc.hasNextLine()){
			list.add(sc.nextLine());
		}
		sc.close();
		return list;
	}
	
	public ArrayList<String> getAllFoodList() throws FileNotFoundException{
		ArrayList<String> foodList = new ArrayList<String>();
		foodList.addAll(getBreadList());
		foodList.addAll(getCerealList());
		foodList.addAll(getFruitsList());
		foodList.addAll(getPastaList());
		foodList.addAll(getVegetableList());
		return foodList;
	}
}
