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
	
	private boolean rememberMe;
	
	public LoginModel(boolean rememberMe) {
		super();
		this.rememberMe = rememberMe;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}

