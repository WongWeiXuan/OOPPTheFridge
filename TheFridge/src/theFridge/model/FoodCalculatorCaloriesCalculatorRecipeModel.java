package theFridge.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import theFridge.DAO.FoodCalculatorCaloriesCalculatorRecipeDAO;

public class FoodCalculatorCaloriesCalculatorRecipeModel {
	/*
	 * 	BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) + 5         (man)
	 *	BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) - 161     (woman)
	 */
	private static String meal;
	private static int targetCalories;

	public FoodCalculatorCaloriesCalculatorRecipeModel(String mel, int targetCalorie){
		meal = mel;
		targetCalories = targetCalorie;
	}
	
	public ArrayList<Recipe> getAllRecipes() throws FileNotFoundException {
		FoodCalculatorCaloriesCalculatorRecipeDAO recipeDao=new FoodCalculatorCaloriesCalculatorRecipeDAO(meal, targetCalories);
        return recipeDao.getAllRecipes();
    }
	
	public ArrayList<Recipe> getRecipeWithCalories() throws FileNotFoundException {
		FoodCalculatorCaloriesCalculatorRecipeDAO recipeDao=new FoodCalculatorCaloriesCalculatorRecipeDAO(meal, targetCalories);
		return recipeDao.getRecipeWithCalories();
	}
	
	public Recipe getRecipeWithinCalories() throws FileNotFoundException {
		FoodCalculatorCaloriesCalculatorRecipeDAO recipeDao=new FoodCalculatorCaloriesCalculatorRecipeDAO(meal, targetCalories);
		return recipeDao.getRecipeWithinCalories();
	}
	
	public static String getMeal() {
		return meal;
	}

	public static void setMeal(String mel) {
		meal = mel;
	}

	public static int getTargetCalories() {
		return targetCalories;
	}

	public static void setTargetCalories(int targetCalorie) {
		targetCalories = targetCalorie;
	}
	
	public static void main(String args[]) throws FileNotFoundException{
		FoodCalculatorCaloriesCalculatorRecipeModel i = new FoodCalculatorCaloriesCalculatorRecipeModel("breakfast", 133);
		System.out.println("Printing recipe...");
		System.out.println("Meal: " + meal);
		System.out.println("Calories: " + i.getRecipeWithinCalories().getCalories() + " Calories");
		System.out.println("Measurement: " + i.getRecipeWithinCalories().getServing() + " Serving");
		System.out.println("Name: " + i.getRecipeWithinCalories().getName());
		System.out.println("Image: " + (i.getRecipeWithinCalories().getPicture()));
	}
}
