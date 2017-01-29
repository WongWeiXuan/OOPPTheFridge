package theFridge.model;

public class ServingToGrams {
	public static int ServingToGramsReturn(double serving, String type) {
		if(type == "Fruit"){
			return (int) (serving * 150);
		}
		else if(type == "Vegetable"){
			return (int) (serving * 75);
		}
		else if(type == "Bread"){
			return (int) (serving * 50);
		}
	}

	public static double GramsToServing(int grams, String fruitsOrVege) {
		if(fruitsOrVege == "Fruit"){
			return grams / 150;
		}
		else{
			return grams / 75;
		}
	}
	
}
