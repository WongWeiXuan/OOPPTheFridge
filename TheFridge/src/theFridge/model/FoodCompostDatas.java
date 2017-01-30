package theFridge.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import theFridge.DAO.FoodCompostDatasDAO;
import theFridge.DAO.ProfileDAO;

public class FoodCompostDatas {
	String title;
	String foodType;
	
	public FoodCompostDatas() {
		super();
	}
	public FoodCompostDatas(String title, String foodType) {
		super();
		this.title = title;
		this.foodType = foodType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	
	public String toString() {
		return title + ";" + foodType;
	}
	
	public void updateFoodCompostDatas() {
		FoodCompostDatasDAO foodCompostDatasDAO = new FoodCompostDatasDAO();
		foodCompostDatasDAO.updateFoodCompostDatas(this);
	}
	
	public boolean createFoodCompostDatas() {
		FoodCompostDatasDAO foodCompostDatasDAO = new FoodCompostDatasDAO();
		return foodCompostDatasDAO.createFoodCompostDatas(this);
	}
	
	/*public static void main(String[] args) throws FileNotFoundException{
		FoodCompostDatas f = new FoodCompostDatas();
		FoodCompostDatasDAO g = new FoodCompostDatasDAO();
		f.setFoodType("Chicken");
		f.setTitle("First");
		f.createFoodCompostDatas();
		FoodCompostDatas ff = new FoodCompostDatas();
		ff.setFoodType("Duck");
		ff.setTitle("HEY");
		ff.createFoodCompostDatas();
		
		File file=new File("src/theFridge/file/foodCompostTwo.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		FoodCompostDatas fff = new FoodCompostDatas();
		fff = g.getFoodCompostDatas("First");
		System.out.println(fff.getFoodType());
		System.out.println(fff.getTitle());
	}
	*/
	
}
