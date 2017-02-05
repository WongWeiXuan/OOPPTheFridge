package theFridge.model;

import java.io.FileNotFoundException;

public class CaloriesCalculatorInputModel {
	public double getBMR() throws FileNotFoundException{
		User uu = new User();
		uu = uu.getCurrentUser();
		
		double BMR = 0;
		if(uu.getGender().equalsIgnoreCase("male")){
			BMR = 10 * Double.parseDouble(uu.getWeight()) + 6.25 * Double.parseDouble(uu.getHeight()) - 5 * Integer.parseInt(uu.getAge()) + 5;
			
		}
		else if(uu.getGender().equalsIgnoreCase("female")){
			BMR = 10 * Double.parseDouble(uu.getWeight()) + 6.25 * Double.parseDouble(uu.getHeight()) - 5 * Integer.parseInt(uu.getAge()) - 161;
		}
		BMR /= 4;
		return BMR;
	}
}
