package theFridge.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LoginModel {
	/*@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
	File file= new File("src/theFridge/model/LoginModel.json");
	JSONObject JSONOBJECT= new JSONObject();
	JSONArray JSONARRAY=new JSONArray();
	JSONARRAY.add("Darren Quek");
	JSONARRAY.add("Wei Xuan");
	JSONARRAY.add("Xuan Zheng");
	
	JSONOBJECT.put("Username", JSONARRAY);
	
	FileWriter fileWriter = new FileWriter(file);  
    System.out.println("Writing JSON object to file");  
    System.out.println("-----------------------");  
    System.out.print(JSONOBJECT);  

    fileWriter.write(JSONOBJECT.toJSONString());  
    fileWriter.flush();  
    fileWriter.close();  
	*/
	private String username;
	private String password;
	
	public LoginModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

