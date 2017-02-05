package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import theFridge.model.DonationHistoryModel;
import theFridge.model.DonationPageModel;
import theFridge.model.ListModel;
import theFridge.model.StockModel;
import theFridge.model.User;

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
			String user = fields[0];
			User userName = seperateUser(user);
			String organizationName = fields[1];
			String contact = fields[2];
			String foodList = fields[3];
			ArrayList<ListModel> lm = seperateFoodList(foodList);
			String time = fields[4];
			int timeLeft = Integer.parseInt(fields[5]);
			String comment = fields[6];
			DonationHistoryModel a = new DonationHistoryModel(userName, organizationName, contact, lm, time, timeLeft, comment);
			aldhm.add(a);
		}
		sc.close();
		return aldhm;
	}
	
	public ArrayList<DonationHistoryModel> getAllHistoryWithUser(String userName) throws FileNotFoundException{
		ArrayList<DonationHistoryModel> medium = new ArrayList<DonationHistoryModel>();
		for(DonationHistoryModel a:getAllHistory()){
			if(a.getUser().getName().equals(userName)){
				medium.add(a);
			}
		}
		
		return medium;
	}
	
	public ArrayList<DonationHistoryModel> getAllHistoryWithOrganization(String organization) throws FileNotFoundException{
		ArrayList<DonationHistoryModel> medium = new ArrayList<DonationHistoryModel>();
		for(DonationHistoryModel a:getAllHistory()){
			if(a.getOrganization().equalsIgnoreCase(organization)){
				medium.add(a);
			}
		}
		return medium;
	}
	
	private ArrayList<ListModel> seperateFoodList(String foodList){
		ArrayList<ListModel> allm = new ArrayList<ListModel>();
		Scanner sc = new Scanner(foodList);
		String line=null;
		String[] fields;
		
		sc.useDelimiter(";");
		while(sc.hasNext()){
			line = sc.next();
			fields = line.split(":");
			String name = fields[0];
			double amount = Double.parseDouble(fields[1]);
			
			ListModel lm = new ListModel(name, amount);
			allm.add(lm);
		}
		sc.close();
		return allm;
	}
	
	private User seperateUser(String user){
		Scanner sc = new Scanner(user);
		String line=null;
		String[] fields;
		
		line = sc.nextLine();
		fields = line.split(":");
		String name = fields[0];
		String email = fields[1];
		sc.close();
		
		User lm = new User();
		lm.setName(name);
		lm.setEmail(email);
		return lm;
	}
	
	public void writeToDonationHistoryFile(DonationHistoryModel aldhm) throws IOException{
		ArrayList<DonationHistoryModel> aldhmm = getAllHistory();
		if(getAllHistory().isEmpty()){
			aldhmm = new ArrayList<DonationHistoryModel>();
		}
		//System.out.println(aluslm.get(0).getName());
		aldhmm.add(0, aldhm);
		
		String line = "";
		for(DonationHistoryModel a:aldhmm){
			line += a.getUser().getName() + ":" + a.getUser().getEmail() + "-" + a.getOrganization() + "-" + a.getContact() + "-";
			boolean first = true;
			for(ListModel b:a.getFoodItems()){
				String name = b.getName();
				String amount = String.valueOf((int)b.getAmount());
				if(first == true){
					line += name + ":" + amount;
					first = false;
				}else{
					line += ";" + name + ":" + amount;
				}
			}
			line +=  "-" + a.getTime() + "-" + a.getTimeTaken() + "-" + a.getComment() + "\n";
		}
		FileWriter writer = new FileWriter(donationHistoryFile);
		writer.write(line);
		writer.flush();
		writer.close();
	}
}
