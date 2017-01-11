package theFridge.model;

import java.util.ArrayList;

public class RecipeModel {
	private String name;
	private ArrayList<IngredientModel> ingredient;
	private int calories;
	private String picture;
	private int serving;

	public RecipeModel(String name, ArrayList<IngredientModel> ingredient, int calories, String picture, int serving) {
		super();
		this.name = name;
		this.ingredient = ingredient;
		this.calories = calories;
		this.picture = picture;
		this.serving = serving;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<IngredientModel> getIngredient() {
		return ingredient;
	}

	public void setIngredient(ArrayList<IngredientModel> ingredient) {
		this.ingredient = ingredient;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getServing() {
		return serving;
	}

	public void setServing(int serving) {
		this.serving = serving;
	}
	
}
