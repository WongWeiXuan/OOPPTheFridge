package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.User;

public class ProfileDAO {
	private static final String User_File="UserDatas.txt";
	private File dataFile;
	
	public ProfileDAO() {
		Path dPath = FileSystems.getDefault().getPath("resources/data/",User_File);
     	dataFile=new File(dPath.toString());     
	}
	
	public ArrayList<User> getAllFriends() {
		Scanner in;
		String record=null;
		String[] fields;
		ArrayList<User> user=new ArrayList<User>();
		try {
			in=new Scanner(dataFile);
			while (in.hasNextLine()) {
				record=in.nextLine();
				fields=record.split(";");
				String name=fields[0];
				String username=fields[1];
				String password=fields[2];
				String email=fields[3];
				String location=fields[4]; 
				String height=fields[5];
				String weight=fields[6];
				int age=Integer.parseInt(fields[7]);
				User u=new User(name,username,password,email,location,height,weight,age);
				user.add(u);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return user;
	
	}
}
