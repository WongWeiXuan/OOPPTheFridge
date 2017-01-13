package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.FoodCompost;
import theFridge.model.SignupModel;

public class FoodCompostDAO {
	private static final String Food_File="foodCompostData.txt";
	private File dataFile;
	
	public FoodCompostDAO() {
		Path dPath = FileSystems.getDefault().getPath("/theFridge/files",Food_File);
     	dataFile=new File(dPath.toString());          
	}
	
	public ArrayList<FoodCompost> getAllFood() {
		Scanner in;
		String record=null;
		String[] fields;
		ArrayList<FoodCompost> foodData=new ArrayList<FoodCompost>();
		try {
			in=new Scanner(dataFile);
			while (in.hasNextLine()) {
				record=in.nextLine();
				fields=record.split(";");
				String food=fields[0];
				String video=fields[1];
				String instruction=fields[2];
				FoodCompost f=new FoodCompost(food,video,instruction);
				foodData.add(f);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return foodData;
	}
	
	public FoodCompost getFoodCompost(String food) {
		// TODO Auto-generated method stub
		ArrayList<FoodCompost> foodData=getAllFood();
		FoodCompost food1=null;
		for (FoodCompost f:foodData) {
			if (f.getFoodName().equals(food)){
				food1=f;
				break;
			}
		}
		return food1;
	}
	
	private void synToFile(ArrayList<FoodCompost> FoodData) {
		if (FoodData == null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (FoodCompost s : FoodData) {
				out.append(s.toString() + "\n" );
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean createFood(FoodCompost Something) {
		boolean existing = false;
		ArrayList<FoodCompost> foodData = getAllFood();
		for (FoodCompost s : foodData) {
			if (s.getFoodName().equals(Something.getFoodName())){
				existing = true;
				break;
			}
		}
		if (!existing) {
			foodData.add(Something);
			synToFile(foodData);
		}
		return !existing;
	}
	
	public void updateFood(FoodCompost Something) {
		ArrayList<FoodCompost> foodData = getAllFood();
		for (int i = 0; i < foodData.size(); i++) {
			FoodCompost s = foodData.get(i);
			if (s.getFoodName().equals(Something.getFoodName())){
				foodData.set(i, Something);
			}
		}
		synToFile(foodData);
	}
}
