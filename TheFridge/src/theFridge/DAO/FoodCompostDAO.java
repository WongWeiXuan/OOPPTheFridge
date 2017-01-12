package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.FoodCompost;

public class FoodCompostDAO {
	private static final String Food_File="foodCompostData.txt";
	private File dataFile;
	
	public FoodCompostDAO() {
		Path dPath = FileSystems.getDefault().getPath("resources/data/",Food_File);
     	dataFile=new File(dPath.toString());          
	}
	
	public ArrayList<FoodCompost> getAllFriends() {
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
		ArrayList<FoodCompost> foodData=getAllFriends();
		FoodCompost food1=null;
		for (FoodCompost f:foodData) {
			if (f.getFoodName().equals(food)){
				food1=f;
				break;
			}
		}
		return food1;
	}
}
