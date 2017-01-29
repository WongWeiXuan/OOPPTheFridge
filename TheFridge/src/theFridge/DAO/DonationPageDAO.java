package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.StockModel;

public class DonationPageDAO {
	File donateFoodFile;
	
	public DonationPageDAO(){
		donateFoodFile = new File("src/theFridge/file/foodDonate.txt");
	}
	
	public ArrayList<StockModel> getAllFood() throws FileNotFoundException{
		Scanner sc = new Scanner(donateFoodFile);
		String line=null;
		String[] fields;
		ArrayList<StockModel> stocks=new ArrayList<StockModel>();
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split("~");
			String name = fields[0];
			double amount = Double.parseDouble(fields[1]);
			int serving = Integer.parseInt(fields[2]);
			StockModel a = new StockModel(name, amount, serving);
			stocks.add(a);
		}
		sc.close();

		return stocks;
	}
}
