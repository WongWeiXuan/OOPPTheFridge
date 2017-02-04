package theFridge.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import theFridge.DAO.FoodCalculatorCaloriesCalculatorRecipeInstructionDAO;

public class FoodCalculatorCaloriesCalculatorRecipeInstructionModel {
	private String name;
	private ArrayList<String> instructionList;
	
	public FoodCalculatorCaloriesCalculatorRecipeInstructionModel(String name, ArrayList<String> instructionList) {
		super();
		this.name = name;
		this.instructionList = instructionList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getInstructionList() {
		return instructionList;
	}

	public void setInstructionList(ArrayList<String> instructionList) {
		this.instructionList = instructionList;
	}
	
	public static String printInstruction(String name) throws FileNotFoundException{
		FoodCalculatorCaloriesCalculatorRecipeInstructionDAO dao = new FoodCalculatorCaloriesCalculatorRecipeInstructionDAO();
		String returnString = "";
		for(FoodCalculatorCaloriesCalculatorRecipeInstructionModel a: dao.getAllInstructions()){
			if(a.getName().equalsIgnoreCase(name)){
				for(int i = 0; i < a.getInstructionList().size(); i++){
					returnString += (i+1) + ". " + a.getInstructionList().get(i) + "\n";
				}
			}
		}
		
		return returnString;
	}
}
