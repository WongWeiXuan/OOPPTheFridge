package theFridge.model;

import java.util.ArrayList;

public class User {
		private String username;
		private String password;
		private String email;
		private String name;
		private String country;
		private ArrayList promoCode;
		
		public User(String userName, String password, String eMail) {
			super();
			this.username = userName;
			this.password = password;
			this.email = eMail;
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
		
		public String getemail() {
			return email;
		}
		
		public void setemail(String email) {
			this.email = email;
		}
		
		public void checkUser(){
			
		}
	
}
