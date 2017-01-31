package theFridge.model;

public class ServingToGrams {
	public int ServingToGramsReturn(double serving, String type) {
		if(type == "Fruits"){
			return (int) (serving * 150);
		}
		else if(type == "Cereal"){
			return (int) (serving * 120);
		}
		else if(type == "Pasta"){
			return (int) (serving * 120);
		}
		else if(type == "Vegetable"){
			return (int) (serving * 75);
		}
		else if(type == "Bread"){
			return (int) (serving * 50);
		}
		return 1;
	}

	public double GramsToServing(int grams, String type) {
		if(type == "Fruits"){
			return grams / 150;
		}
		else if(type == "Cereal"){
			return  grams / 120;
		}
		else if(type == "Pasta"){
			return  grams / 120;
		}
		else if(type == "Vegetable"){
			return  grams / 75;
		}
		else if(type == "Bread"){
			return  grams / 50;
		}
		return 1;
	}
	
}
