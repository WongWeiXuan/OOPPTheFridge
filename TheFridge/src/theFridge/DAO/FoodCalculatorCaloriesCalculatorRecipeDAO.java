package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.controller.foodCalculator.CaloriesCalculatorInputController;

public class FoodCalculatorCaloriesCalculatorRecipeDAO{
	private ArrayList<String>recipeList;
	private int index;
	private File recipes;
	private String record = null;
	private String[] fields;
	
	//private static String meals;
	//private static int calories;
	
	//--------
	//Initializing recipeList...
	public FoodCalculatorCaloriesCalculatorRecipeDAO() throws FileNotFoundException{
		ArrayList<String> recipeList1 = new ArrayList<String>();
		
		recipes = new File("src/theFridge/file/Recipes.txt");
		Scanner sc = new Scanner(recipes);
		
		while(sc.hasNextLine()){
			fields = record.split(":");
			record = sc.next();
		}
		sc.close();
		
		recipeList = recipeList1;
	}
	
	//Get recipe based on Name
	public FoodCalculatorCaloriesCalculatorRecipeDAO(int index) throws FileNotFoundException{
		this();
		this.index = index;
	}
	// Must deduce recipes lower then the calories
	private ArrayList<String> getIndiRecipe(int index){
		ArrayList<String> indiRecipe = new ArrayList<String>();
		Scanner sc = new Scanner((recipeList).get(this.index));
		sc.useDelimiter("-");
		while(sc.hasNext()){
			indiRecipe.add(sc.next());
		}
		sc.close();
		
		return indiRecipe;
	}
	//---------------------------------------------
	public String getRecipeName(){
		ArrayList<String>indiRecipe = getIndiRecipe(this.index);
		return indiRecipe.get(0);
	}
	
	public ArrayList<String> getRecipeIngredients(){
		ArrayList<String>indiRecipe = getIndiRecipe(index);
		ArrayList<String> ingredientList = new ArrayList<String>();
		Scanner sc = new Scanner(indiRecipe.get(1));
		sc.useDelimiter(":");
		while(sc.hasNext()){
			ingredientList.add(sc.next());
		}
		sc.close();
		return ingredientList;
	}
	
	public String getRecipeCalories(){
		ArrayList<String>indiRecipe = getIndiRecipe(this.index);
		return indiRecipe.get(2);
	}
	
	public String getRecipeImage(){
		ArrayList<String>indiRecipe = getIndiRecipe(this.index);
		return indiRecipe.get(3);
	}
}
