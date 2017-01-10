package theFridge.model;

public class user {
	private String userName;
	private String password;
	private String eMail;
	private String name;
	private String country;
	
	public user(String userName, String password, String eMail) {
		super();
		this.userName = userName;
		this.password = password;
		this.eMail = eMail;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String geteMail() {
		return eMail;
	}
	
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public void checkUser(){
		
	}
}
