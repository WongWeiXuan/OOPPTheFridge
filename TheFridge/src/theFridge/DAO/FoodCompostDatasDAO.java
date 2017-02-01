package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.FoodCompostDatas;

public class FoodCompostDatasDAO {
	private static File dataFile;
	
	public FoodCompostDatasDAO() {
		Path dPath = FileSystems.getDefault().getPath("src/theFridge/file/foodCompostTwo.txt");
     	dataFile=new File(dPath.toString());     
	}
	
	public ArrayList<FoodCompostDatas> getAllFoodCompostDatas() {
		Scanner in;
		String record = null;
		String[] fields;
		//String[] s;
		ArrayList<FoodCompostDatas> user=new ArrayList<FoodCompostDatas>();
		try {
			in = new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				String title = fields[0];
				String foodType = fields[1];
				FoodCompostDatas u = new FoodCompostDatas(title, foodType);
				user.add(u);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return user;
	
	}
	
	public FoodCompostDatas getFoodCompostDatas(String title) {
		ArrayList<FoodCompostDatas> user = getAllFoodCompostDatas();
		FoodCompostDatas f1 = null;
		for (FoodCompostDatas h : user) {
			if (h.getTitle().equals(title)){
				f1 = h;
				break;
			}
		}
		return f1;
	}
	
	private static void synToFile(ArrayList<FoodCompostDatas> user) {
		if (user == null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (FoodCompostDatas s : user) {
				out.append(s.toString() + "\n" );
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createFoodCompostDatas(FoodCompostDatas f1) {
		boolean existing = false;
		ArrayList<FoodCompostDatas> user = getAllFoodCompostDatas();
		for (FoodCompostDatas s : user) {
			if (s.getTitle().equals(f1.getTitle())){
				existing = true;
				break;
			}
		}
		if (!existing) {
			user.add(f1);
			synToFile(user);
		}
		return !existing;
	}
	
	public void updateFoodCompostDatas(FoodCompostDatas f1) {
		ArrayList<FoodCompostDatas> user2 = getAllFoodCompostDatas();
		for (int i = 0; i < user2.size(); i++) {
			FoodCompostDatas s = user2.get(i);
			if (s.getTitle().equals(f1.getTitle())){
				user2.set(i, f1);
			}
		}
		synToFile(user2);
	}
	
	public void deleteFoodCompost(FoodCompostDatas f1) {
		
		ArrayList<FoodCompostDatas> user2 = getAllFoodCompostDatas();
		
		FoodCompostDatas delFriend=null;
		for (FoodCompostDatas f:user2) {
			if (f1.getTitle().equals(f.getTitle())){
				delFriend=f;			
				break;
			}
		}
		if (delFriend!=null){
		    user2.remove(delFriend);
			synToFile(user2);	
		}		
	
	}
	
	
}
