package theFridge.model;

import java.util.ArrayList;

import theFridge.DAO.SignupDAO;

public class SignupModel {
	private String username;
	private String password;
	private String email;
	
	public SignupModel(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return username + ";" + password + ";"+ email;
	}
	
	public static ArrayList<SignupModel> getAllPerson() {
		SignupDAO signupDAO = new SignupDAO();
		return signupDAO.getAllPerson();
	}
	
	public void getPerson() {
		SignupDAO signupDAO = new SignupDAO();
		SignupModel Someone = signupDAO.getPerson(username);
		setPassword(Someone.getPassword());
		setEmail(Someone.getEmail());
	}
	
	public void updatePerson() {
		SignupDAO signupDAO = new SignupDAO();
		signupDAO.updatePerson(this);
	}
	
	public boolean createPerson() {
		SignupDAO signupDAO = new SignupDAO();
		return signupDAO.createPerson(this);
	}
	
	public static void main(String args[]) {
		
	}
}
