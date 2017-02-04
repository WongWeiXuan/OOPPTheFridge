package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.FoodCalculatorCaloriesCalculatorRecipeInstructionModel;

public class FoodCalculatorCaloriesCalculatorRecipeInstructionDAO {
	File instructionFile;
	
	public FoodCalculatorCaloriesCalculatorRecipeInstructionDAO(){
		instructionFile = new File("src/theFridge/file/RecipeInstructions.txt");
	}
	
	public ArrayList<FoodCalculatorCaloriesCalculatorRecipeInstructionModel> getAllInstructions() throws FileNotFoundException{
		Scanner sc = new Scanner(instructionFile);
		String line=null;
		String[] fields;
		ArrayList<FoodCalculatorCaloriesCalculatorRecipeInstructionModel> stringLine=new ArrayList<FoodCalculatorCaloriesCalculatorRecipeInstructionModel>();
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split("-");
			String name = fields[0];
			String instructionList = fields[1];
			ArrayList<String> il = seperateString(instructionList);
			FoodCalculatorCaloriesCalculatorRecipeInstructionModel a = new FoodCalculatorCaloriesCalculatorRecipeInstructionModel(name, il);
			stringLine.add(a);
		}
		sc.close();
		
		return stringLine;
	}
	
	private ArrayList<String> seperateString(String line){
		ArrayList<String> als = new ArrayList<String>();
		Scanner sc = new Scanner(line);
		sc.useDelimiter("@");
		while(sc.hasNext()){
			als.add(sc.next());
		}
		sc.close();
		
		return als;
	}
}
