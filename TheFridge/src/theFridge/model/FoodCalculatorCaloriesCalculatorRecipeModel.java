package theFridge.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.controller.foodCalculator.CaloriesCalculatorInputController;

public class FoodCalculatorCaloriesCalculatorRecipeModel{
	private ArrayList<String>recipeList;
	private int index;
	private static int meals;
	private static int calories;
	
	//Getter
	public static int getMeals() {
		return meals;
	}

	public static int getCalories() {
		return calories;
	}
	//--------
	//Initializing recipeList...
	public FoodCalculatorCaloriesCalculatorRecipeModel() throws FileNotFoundException{
		ArrayList<String> recipeList1 = new ArrayList<String>();
		
		File recipes = new File("src/theFridge/file/Recipes.txt");
		Scanner sc = new Scanner(recipes);
		sc.useDelimiter("~");
		
		while(sc.hasNext()){
			recipeList1.add(sc.next());
		}
		sc.close();
		
		recipeList = recipeList1;
		FoodCalculatorCaloriesCalculatorRecipeModel.meals = CaloriesCalculatorInputController.NumOfMeals;
		FoodCalculatorCaloriesCalculatorRecipeModel.calories = CaloriesCalculatorInputController.NumOfCalories;
	}
	
	//Get recipe based on Index
	public FoodCalculatorCaloriesCalculatorRecipeModel(int index) throws FileNotFoundException{
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
