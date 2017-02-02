package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.DonationHistoryModel;
import theFridge.model.DonationPageModel;
import theFridge.model.ListModel;
import theFridge.model.StockModel;

public class DonationPageDAO {
	File donateFoodFile;
	File operationTimeFile;
	File donationHistoryFile;
	
	public DonationPageDAO(){
		donateFoodFile = new File("src/theFridge/file/foodDonate.txt");
		operationTimeFile = new File("src/theFridge/file/operationTime.txt");
		donationHistoryFile = new File("src/theFridge/file/donationHistory.txt");
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
	
	public ArrayList<DonationPageModel> getAllOperationTimes() throws FileNotFoundException{
		ArrayList<DonationPageModel> aldpm = new ArrayList<DonationPageModel>();
		
		Scanner sc = new Scanner(operationTimeFile);
		String line=null;
		String[] fields;
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split(":");
			String name = fields[0];
			int openingHours = Integer.parseInt(fields[1]);
			int closingHours = Integer.parseInt(fields[2]);
			int openingDays = Integer.parseInt(fields[3]);
			int closingDays = Integer.parseInt(fields[4]);
			try{
				int secondaryOpeningHours = Integer.parseInt(fields[5]);
				int secondaryClosingHours = Integer.parseInt(fields[6]);
				int secondaryOpeningDays = Integer.parseInt(fields[7]);
				DonationPageModel a = new DonationPageModel(name, openingHours, closingHours, openingDays, closingDays, secondaryOpeningHours, secondaryClosingHours, secondaryOpeningDays);
				aldpm.add(a);
			}catch(ArrayIndexOutOfBoundsException e){
				DonationPageModel a = new DonationPageModel(name, openingHours, closingHours, openingDays, closingDays);
				aldpm.add(a);
			}
		}
		sc.close();
		
		return aldpm;
	}
	
	public ArrayList<DonationHistoryModel> getAllHistory() throws FileNotFoundException{
		ArrayList<DonationHistoryModel> aldhm = new ArrayList<DonationHistoryModel>();
		Scanner sc = new Scanner(donationHistoryFile);
		String line=null;
		String[] fields;
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split("-");
			String userName = fields[0];
			String organizationName = fields[1];
			String foodList = fields[2];
			ListModel lm = seperateFoodList(foodList);
			String time = fields[3];
			String timeLeft = fields[4];
			DonationHistoryModel a = new DonationHistoryModel(userName, organizationName, lm, time, timeLeft);
			aldhm.add(a);
		}
		sc.close();
		return null;
	}
	
	private ListModel seperateFoodList(String foodList){
		Scanner sc = new Scanner(foodList);
		String line=null;
		String[] fields;
		
		line = sc.nextLine();
		fields = line.split(":");
		String name = fields[0];
		double amount = Double.parseDouble(fields[1]);
		sc.close();
		
		ListModel lm = new ListModel(name, amount);
		return lm;
	}
}
