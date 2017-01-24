package theFridge.model;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import theFridge.DAO.ProfileDAO;

public class User {
		private String username;
		private String password;
		private String email;
		private String name;
		private String country;
		private String weight;
		private String height;
		private String age;
		private String gender;
		private int totalPoints;  //RedeemVoucher
		private String promoCode; //RedeemVoucher
		private long endTime;	  //Redeem24HourTimer
		
		public User() {
			super();
		}
		
		public User(String username, String password, String eMail) {
			super();
			this.username = username;
			this.password = password;
			this.email = eMail;
		}
		
		public User(String username, int totalPoints, String promoCode) {
			this.username = username;
			this.totalPoints = totalPoints;
			this.promoCode = promoCode;
		}
		
		public User(String name, String username, String password, String email, String country, String height,
				String weight, String age, String gender, int totalPoints, String promoCode, long endTime) {
			super();
			this.username = username;
			this.password = password;
			this.email = email;
			this.name = name;
			this.country = country;
			this.weight = weight;
			this.height = height;
			this.age = age;
			this.gender = gender;
			this.totalPoints = totalPoints;
			this.promoCode = promoCode;
			this.endTime = endTime;
		}
		
		public String toString() {
			return name + ";" + username + ";" + password + ";"+ email + ";" + country + ";" + height + ";" + weight + ";" + age + ";" + gender + ";" + totalPoints + ";" + promoCode + ";" + endTime;
		}
		
		public static ArrayList<User> getAllUser() {
			ProfileDAO profileDAO = new ProfileDAO();
			return profileDAO.getAllUser();
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
			setGender(user1.getGender());
			setTotalPoints(user1.getTotalPoints());
			setPromoCode(user1.getPromoCode());
			setEndTime(user1.getEndTime());
		}
		
		public void updateUser() {
			ProfileDAO profileDAO = new ProfileDAO();
			profileDAO.updateUser(this);
		}
		
		public boolean createUser() {
			ProfileDAO profileDAO = new ProfileDAO();
			return profileDAO.createUser(this);
		}
		
		public boolean addPromoCode() {
			ProfileDAO profileDAO = new ProfileDAO();
			return profileDAO.addPromoCode(this);
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

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
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
		
		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public int getTotalPoints() {
			return totalPoints;
		}

		public void setTotalPoints(int totalPoints) {
			this.totalPoints = totalPoints;
		}
		
		public String getPromoCode() {
			return promoCode;
		}

		public void setPromoCode(String promoCode) {
			this.promoCode = promoCode;
		}

		public long getEndTime() {
			return endTime;
		}

		public void setEndTime(long endTime) {
			this.endTime = endTime;
		}
		
}
