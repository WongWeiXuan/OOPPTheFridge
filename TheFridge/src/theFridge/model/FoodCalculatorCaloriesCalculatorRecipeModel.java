package theFridge.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import theFridge.DAO.FoodCalculatorCaloriesCalculatorRecipeDAO;

public class FoodCalculatorCaloriesCalculatorRecipeModel {
	/*
	 * 	BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) + 5         (man)
	 *	BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) - 161     (woman)
	 */
	private String meal;
	private int targetCalories;
	
	public FoodCalculatorCaloriesCalculatorRecipeModel(String meal, int targetCalories){
		this.meal = meal;
		this.targetCalories = targetCalories;
	}
	
	public static ArrayList<Recipe> getAllRecipes(String meal, int targetCalories) throws FileNotFoundException {
		FoodCalculatorCaloriesCalculatorRecipeDAO recipeDao=new FoodCalculatorCaloriesCalculatorRecipeDAO(meal, targetCalories);
        return recipeDao.getAllRecipes();
    }
	
	public static ArrayList<Recipe> getRecipeWithCalories(String meal, int targetCalories) throws FileNotFoundException {
		FoodCalculatorCaloriesCalculatorRecipeDAO recipeDao=new FoodCalculatorCaloriesCalculatorRecipeDAO(meal, targetCalories);
		return recipeDao.getRecipeWithCalories();
	}
	
	public static Recipe getRecipeWithinCalories(String meal, int targetCalories) throws FileNotFoundException {
		FoodCalculatorCaloriesCalculatorRecipeDAO recipeDao=new FoodCalculatorCaloriesCalculatorRecipeDAO(meal, targetCalories);
		return recipeDao.getRecipeWithinCalories();
	}
}
