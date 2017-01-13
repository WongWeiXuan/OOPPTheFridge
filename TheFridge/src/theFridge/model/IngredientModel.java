package theFridge.model;

import java.util.ArrayList;

import theFridge.DAO.IngredientDAO;

public class IngredientModel {
	
	public ArrayList<Ingredient> getAllIngredients(String ingredientsLine){
		IngredientDAO ingredientDao=new IngredientDAO(ingredientsLine);
        return ingredientDao.getAllIngredients();
	}
	
	public ArrayList<Ingredient> getAllIngredients(ArrayList<Ingredient>ingredientArray){
		IngredientDAO ingredientDao=new IngredientDAO(ingredientArray);
        return ingredientDao.getAllIngredients();
	}
	
}
