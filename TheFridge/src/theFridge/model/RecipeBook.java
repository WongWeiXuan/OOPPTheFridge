package theFridge.model;

import java.util.ArrayList;

import theFridge.DAO.RecipeBookDAO;

public class RecipeBook {
	
	
	public RecipeBook() {
		super();
	}



	private String addedBy;
	private String recipeName;
	private String ingredient1;
	private String ingredient2;
	private String ingredient3;
	private String steps;
	private int score;
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getIngredient1() {
		return ingredient1;
	}

	public void setIngredient1(String ingredient1) {
		this.ingredient1 = ingredient1;
	}

	public String getIngredient2() {
		return ingredient2;
	}

	public void setIngredient2(String ingredient2) {
		this.ingredient2 = ingredient2;
	}

	public String getIngredient3() {
		return ingredient3;
	}

	public void setIngredient3(String ingredient3) {
		this.ingredient3 = ingredient3;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return addedBy + ";"+ recipeName +";"+ ingredient1 + ";" + ingredient2 + ";" + ingredient3 + ";" + steps;
	}
	
	public RecipeBook(String addedBy, String recipeName, String ingredient1, String ingredient2, String ingredient3,
			String steps) {
		super();
		this.addedBy = addedBy;
		this.recipeName = recipeName;
		this.ingredient1 = ingredient1;
		this.ingredient2 = ingredient2;
		this.ingredient3 = ingredient3;
		this.steps = steps;
	}
	public void deleteRecipe() {
		RecipeBookDAO Dao=new RecipeBookDAO();
		Dao.deleteRecipeBook(this);
	}
	
	//a recipe is a friend :)
	public void addRecipe(ArrayList<RecipeBook> RecipeBookList){
		RecipeBookDAO Dao=new RecipeBookDAO();
		Dao.synToFile(RecipeBookList);
	}
	
	public static ArrayList<RecipeBook> getAllRecipeBook() {
		RecipeBookDAO RecipeBookDao=new RecipeBookDAO();
		return RecipeBookDao.getAllRecipes();
	}


}


