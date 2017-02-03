package theFridge.model;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
	
	public static VBox initializeVBox(DonationHistoryModel a){
			TextFlow txtfw1 = new TextFlow(new Text("Organization Name"));
			TextFlow txtfw2 = new TextFlow(new Text(a.getOrganization()));
			TextFlow txtfw3 = new TextFlow(new Text("Food Donation"));
			VBox vbox2 = new VBox();
			Label lbl1 = new Label("Name");
			Label lbl2 = new Label("Amount");
			HBox hbox = new HBox(lbl1, lbl2);
			vbox2.getChildren().add(0, hbox);
			for(ListModel b:a.getFoodItems()){
				Label lbl3 = new Label(b.getName());
				Label lbl4 = new Label(String.valueOf((int)b.getAmount()));
				HBox hbox1 = new HBox(lbl3, lbl4);
				vbox2.getChildren().add(hbox1);
			}
			VBox vbox3 = new VBox();
			vbox3.getChildren().addAll(txtfw3, vbox2);
			TextFlow txtfw4 = new TextFlow(new Label("Time"));
			TextFlow txtfw5 = new TextFlow(new Label(a.getTime()));
			VBox vbox1 = new VBox();
			vbox1.getChildren().addAll(txtfw1, txtfw2, vbox3, txtfw4, txtfw5);
			return vbox1;
	}
}
