package theFridge.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import theFridge.DAO.ProfileDAO;

public class CaloriesCalculatorInputModel {
	public double getBMR() throws FileNotFoundException{
		User uu = new User();
		uu = uu.getCurrentUser();
		
		double BMR = 0;
		if(uu.getGender() == "male"){
			BMR = 10 * Double.parseDouble(uu.getWeight()) + 6.25 * Double.parseDouble(uu.getHeight()) - 5 * Integer.parseInt(uu.getAge()) + 5;
			
		}
		else if(uu.getGender() == "female"){
			BMR = 10 * Double.parseDouble(uu.getWeight()) + 6.25 * Double.parseDouble(uu.getHeight()) - 5 * Integer.parseInt(uu.getAge()) - 161;
		}
	
		return BMR;
	}
}
