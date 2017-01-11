package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.SignupModel;

public class SignupDAO {
	private static final String PERSON_FILE = "Person.txt";
	private File dataFile;
	
	public SignupDAO() {
		Path dPath = FileSystems.getDefault().getPath("theFridge.file", PERSON_FILE);
		dataFile=new File(dPath.toString()); 
	}
	
	public ArrayList<SignupModel> getAllPerson() {
		Scanner in;
		String record=null;
		String[] fields;
		ArrayList<SignupModel> Person =new ArrayList<SignupModel>();
		try {
			in=new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				String username = fields[0];
				String password = fields[1];
				String email = fields[2];
				SignupModel S=new SignupModel(username, password, email);
				Person.add(S);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return Person;
	}

	
	
	public static void main(String[] args) {
		
	}

}
