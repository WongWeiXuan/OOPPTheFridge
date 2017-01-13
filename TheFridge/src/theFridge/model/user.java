package theFridge.model;

import java.util.ArrayList;

import theFridge.DAO.ProfileDAO;

public class User {
		private String username;
		private String password;
		private String email;
		private String name;
		private String country;
		private String weight;
		private String height;
		private int age;
		private ArrayList promoCode;
		
		public User(String name, String username, String password, String email, String country, String weight,
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
		
		public User(String userName, String password, String eMail) {
			super();
			this.username = userName;
			this.password = password;
			this.email = eMail;
		}

		
		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getWeight() {
			return weight;
		}


		public void setWeight(String weight) {
			this.weight = weight;
		}


		public String getHeight() {
			return height;
		}


		public void setHeight(String height) {
			this.height = height;
		}


		public int getAge() {
			return age;
		}


		public void setAge(int age) {
			this.age = age;
		}


		public ArrayList getPromoCode() {
			return promoCode;
		}


		public void setPromoCode(ArrayList promoCode) {
			this.promoCode = promoCode;
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
		
		
		public User() {
			super();
		}


		public String toString() {
			return name + ";" + username + ";" + password + ";"+ email + ";" + country + ";" + height + ";" + weight + ";" +age;
		}
		
		public static ArrayList<User> getAllUser() {
			ProfileDAO profileDAO = new ProfileDAO();
			return ProfileDAO.getAllUser();
		}
		
		public void getUser() {
			ProfileDAO userDAO = new ProfileDAO();
			User user1 = userDAO.getUser(username);
			setName(user1.getName());
			setPassword(user1.getPassword());
			setEmail(user1.getEmail());
			setCountry(user1.getCountry());
			setWeight(user1.getWeight());
			setHeight(user1.getHeight());
			setAge(user1.getAge());
		}
		
		public void updateUser() {
			ProfileDAO profileDAO = new ProfileDAO();
			ProfileDAO.updateUser(this);
		}
		
		public boolean createUser() {
			ProfileDAO profileDAO = new ProfileDAO();
			return profileDAO.createUser(this);
		}
	
}
