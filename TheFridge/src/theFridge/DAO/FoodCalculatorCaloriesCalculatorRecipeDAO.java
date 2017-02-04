package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import theFridge.model.Ingredient;
import theFridge.model.Recipe;

public class FoodCalculatorCaloriesCalculatorRecipeDAO{
	private File recipeFile;
	private int targetCalories;
	
	//Initializing recipeFile...
	public FoodCalculatorCaloriesCalculatorRecipeDAO(String meal, int targetCalories) throws FileNotFoundException{
		if(meal.equalsIgnoreCase("breakfast")){
			recipeFile = new File("src/theFridge/file/Recipes.txt");
		}
		else if(meal.equalsIgnoreCase("lunch")){
			recipeFile = new File("src/theFridge/file/RecipeLunch.txt");
		}
		else if(meal.equalsIgnoreCase("dinner")){
			recipeFile = new File("src/theFridge/file/RecipeDinner.txt");
		}
		else if(meal.equalsIgnoreCase("snacks")){
			recipeFile = new File("src/theFridge/file/RecipeSnacks.txt");
		}
		this.targetCalories = targetCalories;
	}
	
	//Return All recipes inside file
	public ArrayList<Recipe> getAllRecipes() throws FileNotFoundException{
		Scanner sc = new Scanner(recipeFile);
		String line=null;
		String[] fields;
		ArrayList<Recipe> recipes=new ArrayList<Recipe>();
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split("-");
			String name = fields[0];
			String ingredientsLine = fields[1];
			//Change (String)(ingredientsLine) to (ArrayList<Ingredient>)(ingredientsArray)
			IngredientDAO i = new IngredientDAO(ingredientsLine);
			ArrayList<Ingredient>ingredientsArray = i.getAllIngredients();
			//----------------------------------------------------------------------------
			int calories = Integer.parseInt(fields[2]);
			String picture = fields[3];
			int serving = Integer.parseInt(fields[4]);
			Recipe a = new Recipe(name, ingredientsArray, calories, picture, serving);
			recipes.add(a);
		}
		sc.close();

		return recipes;
	}
	
	public Recipe getRecipeWithinCalories() throws FileNotFoundException{
		Random rand = new Random();
		ArrayList<Recipe> recipeLowList = getRecipesWithinCalories(targetCalories - 25, targetCalories + 25);
		try{
			int index = rand.nextInt(recipeLowList.size());
			Recipe a = recipeLowList.get(index);
			return a;
		}
		catch(Exception e){
			try{
				recipeLowList = getRecipesWithinCalories(targetCalories - 50, targetCalories + 50);
				//Display warning...
				int index = rand.nextInt(recipeLowList.size());
				Recipe a = recipeLowList.get(index);
				System.out.println("Warning! 50 calories away due to small database of recipes");
				return a;
			}
			catch(Exception ex){
				try{
					recipeLowList = getRecipesWithinCalories(targetCalories - 100, targetCalories + 100);
					//Display warning...
					int index = rand.nextInt(recipeLowList.size());
					Recipe a = recipeLowList.get(index);
					System.out.println("Warning! 100 calories away due to small database of recipes");
					return a;
				}
				catch(Exception exc){
					try{
						recipeLowList = getRecipesWithinCalories(targetCalories - 250, targetCalories + 250);
						//Display warning...
						int index = rand.nextInt(recipeLowList.size());
						Recipe a = recipeLowList.get(index);
						System.out.println("Warning! 250 calories away due to small database of recipes");
						return a;
					}
					catch(Exception exce){
						//Recipe a = new Recipe("Death", , -1, "src/theFridge/picture/Strawberry smoothie.jpg", 1);
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Calories Out Of Bound");
						alert.setContentText("Ooops, We dont cater to fat people");

						alert.showAndWait();
						System.out.println("Anymore and you will be dead... Sorry :(");
						System.out.println("Stop playing with the system, enter something realistic please...");
						return null;
					}
				}
			}
		}
	}
	
	//Get recipe within 50 calories
	private ArrayList<Recipe> getRecipesWithinCalories(int min, int max) throws FileNotFoundException{
		ArrayList<Recipe>recipes = new ArrayList<Recipe>();

		ArrayList<Recipe>recipeList = getAllRecipes();
		for(Recipe a:recipeList){
			int calorie = a.getCalories();
			if(calorie >= min && calorie <= max){
				recipes.add(a);
			}
		}
		
		return recipes;
	}
	
	//Deduces recipes ,lower then the calories until 0 (or out of recipes), with randomness
		public ArrayList<Recipe> getRecipeWithCalories() throws FileNotFoundException{
			int caloriesTotal = targetCalories;
			ArrayList<Recipe> recipesArray = new ArrayList<Recipe>();
			
			Random rand = new Random();
			ArrayList<Recipe> recipeLowList = getRecipesLowerCalories(caloriesTotal);
			while(recipeLowList.size() > 0 && caloriesTotal > 0){
				int index = rand.nextInt(recipeLowList.size());
				Recipe a = recipeLowList.get(index);
				if(caloriesTotal - a.getCalories() >= 0){
					recipesArray.add(a);
					caloriesTotal -= a.getCalories();
				}
				recipeLowList = getRecipesLowerCalories(caloriesTotal);
			}
			
			return recipesArray;
		}
	
	//List of recipes lower then calories
	private ArrayList<Recipe> getRecipesLowerCalories(int calories) throws FileNotFoundException{
		ArrayList<Recipe>recipes = new ArrayList<Recipe>();

		ArrayList<Recipe>recipeList = getAllRecipes();
		for(Recipe a:recipeList){
			int calorie = a.getCalories();
			if(calorie <= calories){
				recipes.add(a);
			}
		}
		
		return recipes;
	}
	
	//---------------------------------------------
	
	/*
	public static void main(String args[]) throws FileNotFoundException{
		FoodCalculatorCaloriesCalculatorRecipeDAO testing = new FoodCalculatorCaloriesCalculatorRecipeDAO("breakfast", 383);
		Recipe recipe = testing.getRecipeWithinCalories();
		System.out.println("Printing recipelist...");
		
		System.out.println("\n------ Printing recipe ------");
		IngredientDAO i = new IngredientDAO(recipe.getIngredient());
		System.out.println("Name: " + recipe.getName());
		System.out.println("Serving: " + recipe.getServing());
		System.out.println("Ingredients: ");
		i.printIngredients();
		System.out.println("Calories: " + recipe.getCalories());
		System.out.println("Picture: " + recipe.getPicture());
		System.out.println("------ End of recipe ------");
	}
	*/
}
