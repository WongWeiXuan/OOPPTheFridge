package theFridge.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodCalculatorCaloriesCalculatorRecipeModel {
	public static void main(String[] args) throws FileNotFoundException{
		ArrayList<String> IndividualRecipes = new ArrayList<String>();
		
		File recipes = new File("src/theFridge/Files/Recipes.txt");
		Scanner sc = new Scanner(recipes);
		sc.useDelimiter("~");
		
		while(sc.hasNext()){
			IndividualRecipes.add(sc.next());
		}
		for(String a:IndividualRecipes){
			System.out.print(a);
		}
	}
}
