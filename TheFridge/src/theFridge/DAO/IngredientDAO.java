package theFridge.DAO;

import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.Ingredient;

public class IngredientDAO {
	private String ingredientsLine;
	private ArrayList<Ingredient>ingredientArray;
	
	public IngredientDAO(String ingredientsLine){
		this.ingredientsLine = ingredientsLine;
	}
	
	public IngredientDAO(ArrayList<Ingredient>ingredientArray){
		this.ingredientArray = ingredientArray;
	}
	
	public ArrayList<Ingredient> getAllIngredients(){
		Scanner sc = new Scanner(ingredientsLine);
		sc.useDelimiter("~");
		
		String line=null;
		String[] fields;
		ArrayList<Ingredient> ingredient=new ArrayList<Ingredient>();
		
		while (sc.hasNext()) {
			line = sc.next();
			fields = line.split(":");
			double amount = Double.parseDouble(fields[0]);
			String unit = fields[1];
			String name = fields[2];
			
			Ingredient a = new Ingredient(amount, unit, name);
			ingredient.add(a);
		}
		sc.close();

		return ingredient;
	}
	
	public void printIngredients(){
		for(Ingredient i:ingredientArray)
			i.printFood();
	}
	
	/*
	public static void main(String args[]){
		IngredientDAO friendDao=new IngredientDAO("1:large ripe:banana~500:ml:almond milk~4:ripe:passion Fruit");
		
		Ingredient f = new Ingredient(1, "large ripe", "banana");
		f.printFood();
		
		System.out.println("\nGet all ingredients");
		System.out.println("=============================");
		ArrayList<Ingredient> list=friendDao.getAllIngredients();
		for (Ingredient a:list) {
			System.out.println("Amount: "+a.getAmount());
			System.out.println("Unit: "+a.getUnit());
			System.out.println("Name: "+a.getName());
		}
	}
	*/
}
