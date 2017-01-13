package theFridge.model;

import java.util.ArrayList;

public class User {
		private String username;
		private String password;
		private String email;
		private String name;
		private String country;
		private String weight;
		private String height;
		public User(String username, String password, String email, String name, String country, String weight,
				String height, int age) {
			super();
			this.username = username;
			this.password = password;
			this.email = email;
			this.name = name;
			this.country = country;
			this.weight = weight;
			this.height = height;
			this.age = age;
		}

		private int age;
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
