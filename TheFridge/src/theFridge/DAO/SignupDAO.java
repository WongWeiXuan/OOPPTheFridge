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
	private File dataFile;
	
	public SignupDAO() {
		dataFile = new File("src/theFridge/file/SignupPerson.txt"); 
	}
	
	public ArrayList<SignupModel> getAllPerson() {
		Scanner in;
		String record = null;
		String[] fields;
		ArrayList<SignupModel> Person = new ArrayList<SignupModel>();
		try {
			in = new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				String username = fields[0];
				String email = fields[1];
				String password = fields[2];
				SignupModel Someone = new SignupModel(username, email, password);
				Person.add(Someone);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return Person;
	}

	public SignupModel getPerson(String username) {
		ArrayList<SignupModel> Person = getAllPerson();
		SignupModel Someone = null;
		for (SignupModel s : Person) {
			if (s.getUsername().equals(username)){
				Someone = s;
				break;
			}
		}
		return Someone;
	}
	
	private void synToFile(ArrayList<SignupModel> Person) {
		if (Person == null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (SignupModel s : Person) {
				out.append(s.toString() + "\n" );
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean createPerson(SignupModel Someone) {
		boolean existing = false;
		ArrayList<SignupModel> Person = getAllPerson();
		for (SignupModel s : Person) {
			if (s.getUsername().equals(Someone.getUsername())){
				existing = true;
				break;
			}
		}
		if (!existing) {
			Person.add(Someone);
			synToFile(Person);
		}
		return !existing;
	}
	
	public void update(SignupModel Someone) {
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
		
		SignupModel Someone = new SignupModel("Wei Xuan", "ILoveStraightMen", "Email");
		signupDAO.createPerson(Someone);
		
		System.out.println("\nSecond user registered.");
		System.out.println("========================================");
		
		Someone = new SignupModel("Xuan Zheng", "BlueWaffles", "Email");
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
