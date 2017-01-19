package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.User;

public class ProfileDAO {
	private static File dataFile;
	
	public ProfileDAO() {
		Path dPath = FileSystems.getDefault().getPath("src/theFridge/file/UserDatas.txt");
     	dataFile=new File(dPath.toString());     
	}
	
	public ArrayList<User> getAllUser() {
		Scanner in;
		String record = null;
		String[] fields;
		ArrayList<User> user=new ArrayList<User>();
		try {
			in = new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				String name = fields[0];
				String username = fields[1];
				String password = fields[2];
				String email = fields[3];
				String location = fields[4]; 
				String height = fields[5];
				String weight = fields[6];
				String age = fields[7];
				int totalPoints = Integer.parseInt(fields[8]);
				String promoCode = fields[9];
				ArrayList<String> arrayPromoCode = promoCodeConverter(promoCode);
				User u = new User(name, username, password, email, location, height, weight, age, totalPoints, arrayPromoCode);
				user.add(u);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return user;
	
	}
	
	//For redeem voucher
	public static ArrayList<String> promoCodeConverter(String promoCode) {
		Scanner sc = new Scanner(promoCode);
		sc.useDelimiter("-");
		ArrayList<String> arrayPromoCode = new ArrayList<String>();
		if (sc.hasNext()) {
			arrayPromoCode.add(sc.next());
		}
		sc.close();
		return arrayPromoCode;
	}
	
	//For redeem voucher
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
	
	public User getUser(String username) {
		ArrayList<User> user = getAllUser();
		User user1 = null;
		for (User h : user) {
			if (h.getUsername().equals(username)){
				user1 = h;
				break;
			}
		}
		return user1;
	}
	
	private static void synToFile(ArrayList<User> user) {
		if (user == null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (User s : user) {
				out.append(s.toString() + "\n" );
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createUser(User user1) {
		boolean existing = false;
		ArrayList<User> user = getAllUser();
		for (User s : user) {
			if (s.getUsername().equals(user1.getUsername())){
				existing = true;
				break;
			}
		}
		if (!existing) {
			user.add(user1);
			synToFile(user);
		}
		return !existing;
	}
	
	public void updateUser(User user1) {
		ArrayList<User> user2 = getAllUser();
		for (int i = 0; i < user2.size(); i++) {
			User s = user2.get(i);
			if (s.getUsername().equals(user1.getUsername())){
				user2.set(i, user1);
			}
		}
		synToFile(user2);
	}
}
