package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.User;

public class RedeemVoucherDAO {
	private File dataFile;
	
	public RedeemVoucherDAO() {
		dataFile =  new File("src/theFridge/file/UserDatas.txt");
	}
	
	public ArrayList<User> getAllUser() {
		Scanner in;
		String record=null;
		String[] fields;
		ArrayList<User> userList = new ArrayList<User>();
		try {
			in = new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				String username = fields[2];
				int totalPoints = Integer.parseInt(fields[8]);
				String promoCode = fields[9];
				ArrayList<String> arrayPromoCode = promoCodeConverter(promoCode);
				User u = new User(username, totalPoints, arrayPromoCode);
				userList.add(u);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return userList;
	}
	
	public ArrayList<String> promoCodeConverter(String promoCode) {
		Scanner sc = new Scanner(promoCode);
		sc.useDelimiter("-");
		ArrayList<String> arrayPromoCode = new ArrayList<String>();
		if (sc.hasNext()) {
			arrayPromoCode.add(sc.next());
		}
		return arrayPromoCode;
	}
	
	public void addPromoCode(User user) {
		boolean existing = false;
		ArrayList<User> userList = new ArrayList<User>();
		for (User u : userList) {
			if (u.getPromoCode().equals(user.getPromoCode())) {
				existing = true;
				break;
			}
		}
		if (!existing) {
			userList.add(user);
			synToFile(userList);
		}
	}
	
	private void synToFile(ArrayList<User> userList) {
		if (userList == null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (User u : userList) {
				out.append(u.toString()+"\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
		
	
	public static void main(String[] args) {
		
	}

}
