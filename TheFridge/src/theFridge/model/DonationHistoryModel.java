package theFridge.model;

public class DonationHistoryModel {
	String userName;
	String organization;
	ListModel foodItems;
	String time;
	String timeTaken;
	
	public DonationHistoryModel(String userName, String organization, ListModel foodItems, String time, String timeTaken) {
		super();
		this.userName = userName;
		this.organization = organization;
		this.foodItems = foodItems;
		this.time = time;
		this.timeTaken = timeTaken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public ListModel getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(ListModel foodItems) {
		this.foodItems = foodItems;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}
	
}
