package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.StockModel;

public class ShoppingListDAO {
	private File stockFile;
	
	public ShoppingListDAO(){
		stockFile = new File("src/theFridge/file/StockList.txt");
	}
	
	public ArrayList<StockModel> getAllStock() throws FileNotFoundException{
		Scanner sc = new Scanner(stockFile);
		String line=null;
		String[] fields;
		ArrayList<StockModel> stocks=new ArrayList<StockModel>();
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split("-");
			String name = fields[0];
			int amount = Integer.parseInt(fields[1]);
			StockModel a = new StockModel(name, amount);
			stocks.add(a);
		}
		sc.close();

		return stocks;
	}
	
	
}
