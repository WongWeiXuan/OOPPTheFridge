package theFridge.controller;
import java.util.Comparator;

import theFridge.model.RecipeBook;

public class RecipeBookComparator implements Comparator<RecipeBook>{

	@Override
	public int compare(RecipeBook arg0, RecipeBook arg1) {
		int score1 = arg0.getScore();
		int score2 = arg1.getScore();
		return score2-score1;
	}
	
}