package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.SignupModel;

public class SignupDAO {
	private static File dataFile;
	
	public SignupDAO() {
		Path dPath = FileSystems.getDefault().getPath("src/theFridge/file/SignupPerson.txt");
		dataFile=new File(dPath.toString()); 
	}
	
	public static ArrayList<SignupModel> getAllPerson() {
		Scanner in;
		String record = null;
		String[] fields;
		ArrayList<SignupModel> personList = new ArrayList<SignupModel>();
		try {
			in = new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				String username = fields[0];
				String email = fields[1];
				String password = fields[2];
				SignupModel Someone = new SignupModel(username, email, password);
				personList.add(Someone);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return personList;
	}

	public static SignupModel getPerson(String username) {
		ArrayList<SignupModel> personList = getAllPerson();
		SignupModel Someone = null;
		for (SignupModel s : personList) {
			if (s.getUsername().equals(username)){
				Someone = s;
				break;
			}
		}
		return Someone;
	}
	
	private void synToFile(ArrayList<SignupModel> personList) {
		if (personList == null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (SignupModel s : personList) {
				out.append(s.toString() + "\n" );
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createPerson(SignupModel Someone) {
		boolean existing = false;
		ArrayList<SignupModel> personList = getAllPerson();
		for (SignupModel s : personList) {
			if (s.getUsername().equals(Someone.getUsername())){
				existing = true;
				break;
			}
		}
		if (!existing) {
			personList.add(Someone);
			synToFile(personList);
		}
		return !existing;
	}
	
	public void updatePerson(SignupModel Someone) {
		ArrayList<SignupModel> Person = getAllPerson();
		for (int i = 0; i < Person.size(); i++) {
			SignupModel s = Person.get(i);
			if (s.getUsername().equals(Someone.getUsername())){
				Person.set(i, Someone);
			}
		}
		synToFile(Person);
	}
	
	public static void main(String[] args) {
		SignupDAO signupDAO = new SignupDAO();
		
		System.out.println("\nFirst user registered.");
		System.out.println("========================================");
		
		SignupModel Someone = new SignupModel("Wei Xuan", "Email", "ILoveStraightMen");
		signupDAO.createPerson(Someone);
		
		System.out.println("\nSecond user registered.");
		System.out.println("========================================");
		
		Someone = new SignupModel("Xuan Zheng", "Email", "BlueWaffles");
		signupDAO.createPerson(Someone);
		
		ArrayList<SignupModel> list = signupDAO.getAllPerson();
		for (SignupModel s : list) {
			System.out.println("Username: " + s.getUsername());
			System.out.println("Email: " + s.getEmail());
			System.out.println("Password: " + s.getPassword());
		}
		
		System.out.println("\nRetrieving first user's information...");
		System.out.println("========================================");
		
		Someone = signupDAO.getPerson("Xuan Zheng");
		System.out.println("Username: " + Someone.getUsername());
		System.out.println("Email: " + Someone.getEmail());
		System.out.println("Password: " + Someone.getPassword());
		
		list = signupDAO.getAllPerson();
		for (SignupModel s : list) {
			System.out.println("Username: " + s.getUsername());
			System.out.println("Email: " + s.getEmail());
			System.out.println("Password: " + s.getPassword());
		}
	}

}
