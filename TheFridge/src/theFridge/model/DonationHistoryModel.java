package theFridge.model;

import java.io.IOException;
import java.util.ArrayList;

import theFridge.DAO.DonationPageDAO;

public class DonationHistoryModel {
	User user;
	String organization;
	String contact;
	ArrayList<ListModel> foodItems;
	String time;
	int timeTaken;
	
	public DonationHistoryModel(User user, String organization, String contact, ArrayList<ListModel> foodItems, String time, int timeTaken) {
		super();
		this.user = user;
		this.contact = contact;
		this.organization = organization;
		this.foodItems = foodItems;
		this.time = time;
		this.timeTaken = timeTaken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public ArrayList<ListModel> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(ArrayList<ListModel> foodItems) {
		this.foodItems = foodItems;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}
	
	public void submitForm(DonationHistoryModel dhm) throws IOException{
		DonationPageDAO dao = new DonationPageDAO();
		dao.writeToDonationHistoryFile(dhm);
	}
}
