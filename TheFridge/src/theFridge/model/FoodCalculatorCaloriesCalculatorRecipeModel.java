package theFridge.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodCalculatorCaloriesCalculatorRecipeModel {
	private ArrayList<String>recipeList;
	
	public FoodCalculatorCaloriesCalculatorRecipeModel(){
		try {
			ArrayList<String> recipeList = new ArrayList<String>();
			
			File recipes = new File("src/theFridge/Files/Recipes.txt");
			Scanner sc = new Scanner(recipes);
			sc.useDelimiter("~");
			
			while(sc.hasNext()){
				recipeList.add(sc.next());
			}
			sc.close();
			
			this.recipeList = recipeList;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//want = Retrieve which things (1 = Retrieve Name, 2 = Retrieve Ingredients, 3 = Retrieve Calories)
	public FoodCalculatorCaloriesCalculatorRecipeModel(int index, int want){
		this();
		
		switch(want){
			case 1:
				getRecipeName(index);
				break;
			
			case 2:
				getRecipeIngredients(index);
				break;
			
			case 3:
				getRecipeCalories(index);
				break;
		}
	}
	
	public ArrayList<String> getIndiRecipe(int index){
		ArrayList<String> indiRecipe = new ArrayList<String>();
		Scanner sc = new Scanner((this.recipeList).get(index));
		sc.useDelimiter("-");
		while(sc.hasNext()){
			indiRecipe.add(sc.next());
		}
		sc.close();
		
		return indiRecipe;
	}
	
	public String getRecipeName(int index){
		ArrayList<String>indiRecipe = getIndiRecipe(index);
		return indiRecipe.get(0);
	}
	
	public ArrayList<String> getRecipeIngredients(int index){
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
	
	public String getRecipeCalories(int index){
		ArrayList<String>indiRecipe = getIndiRecipe(index);
		return indiRecipe.get(2);
	}
}
