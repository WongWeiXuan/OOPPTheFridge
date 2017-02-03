package theFridge.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
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
		private int totalPoints;
		private String promoCode;
		private long endTime;
		private String chosenFC;
		private boolean rememberMe;
		private int endPointsAttained;
		private String pastDate;
		private String profileImage;
		
		public String getProfileImage() {
			return profileImage;
		}

		public void setProfileImage(String profileImage) {
			this.profileImage = profileImage;
		}

		public User() {
			super();
		}

		public String getChosenFC() {
			return chosenFC;
		}

		public void setChosenFC(String chosenFC) {
			this.chosenFC = chosenFC;
		}

		public boolean isRememberMe() {
			return rememberMe;
		}

		public void setRememberMe(boolean rememberMe) {
			this.rememberMe = rememberMe;
		}

		public int getEndPointsAttained() {
			return endPointsAttained;
		}

		public void setEndPointsAttained(int endPointsAttained) {
			this.endPointsAttained = endPointsAttained;
		}

		public String getPastDate() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
			String pastDate = sdf.format(new Date());
			return pastDate;
		}

		public void setPastDate(String pastDate) {
			this.pastDate = pastDate;
		}

		public User(String name, String username, String password, String email, String country, String height,
				String weight, String age, String gender, int totalPoints, String promoCode, long endTime, String chosenFC, int endPointsAttained, String pastDate, boolean rememberMe, String profileImage) {
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
			this.chosenFC = chosenFC;
			this.endPointsAttained = endPointsAttained;
			this.pastDate = pastDate;
			this.rememberMe = rememberMe;
			this.profileImage = profileImage;
		}
		
		public String toString() {
			return name + ";" + username + ";" + password + ";"+ email + ";" + country + ";" + height + ";" + weight + ";" + age + ";" + gender + ";" + totalPoints + ";" + promoCode + ";" + endTime + ";" + chosenFC + ";" + endPointsAttained + ";" + pastDate + ";" + rememberMe + ";" + profileImage;
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
			setChosenFC(user1.getChosenFC());
			setRememberMe(user1.isRememberMe());
			setEndPointsAttained(user1.getEndPointsAttained());
			setPastDate(user1.getPastDate());
			setProfileImage(user1.getProfileImage());
		}
		
		public void updateUser() {
			ProfileDAO profileDAO = new ProfileDAO();
			profileDAO.updateUser(this);
		}
		
		public boolean createUser() {
			ProfileDAO profileDAO = new ProfileDAO();
			return profileDAO.createUser(this);
		}
		
		public User getCurrentUser() throws FileNotFoundException {
			File file = new File("src/theFridge/file/confirm.txt");
			Scanner sc = new Scanner(file) ;
			String n = sc.nextLine();
			sc.close();

			ProfileDAO profileDAO = new ProfileDAO();
			User user = new User();
			user = profileDAO.getUser(n);
			
			return user;
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

