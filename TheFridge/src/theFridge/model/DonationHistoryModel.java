package theFridge.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	String comment;

	public DonationHistoryModel(User user, String organization, String contact, ArrayList<ListModel> foodItems,
			String time, int timeTaken, String comment) {
		super();
		this.user = user;
		this.organization = organization;
		this.contact = contact;
		this.foodItems = foodItems;
		this.time = time;
		this.timeTaken = timeTaken;
		this.comment = comment;
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
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void submitForm(DonationHistoryModel dhm) throws IOException{
		DonationPageDAO dao = new DonationPageDAO();
		dao.writeToDonationHistoryFile(dhm);
	}
	
	public static VBox initializeVBox(DonationHistoryModel a){
		Text txt1 = new Text("Organization Name");
		txt1.setFont(Font.font("System", FontWeight.BOLD, 24));
		Text txt2 = new Text("Food Donation");
		txt2.setFont(Font.font("System", FontWeight.BOLD, 24));
		TextFlow txtfw1 = new TextFlow(txt1);
		TextFlow txtfw2 = new TextFlow(new Text(a.getOrganization()));
		TextFlow txtfw3 = new TextFlow(txt2);
		VBox vbox2 = new VBox(); // Store Food Item
		Label lbl1 = new Label("Name");
		Label lbl2 = new Label("Amount");
		lbl1.setPrefWidth(400);
		lbl2.setPrefWidth(400);
		lbl1.setFont(Font.font("System", FontWeight.BOLD, 16));
		lbl2.setFont(Font.font("System", FontWeight.BOLD, 16));
		HBox hbox = new HBox(lbl1, lbl2);
		hbox.setAlignment(Pos.TOP_CENTER);
		vbox2.getChildren().add(0, hbox);
		for(ListModel b:a.getFoodItems()){
			Label lbl3 = new Label(b.getName());
			Label lbl4 = new Label(String.valueOf((int)b.getAmount()));
			lbl3.setPrefWidth(400);
			lbl4.setPrefWidth(400);
			HBox hbox1 = new HBox(lbl3, lbl4);
			hbox1.setAlignment(Pos.TOP_CENTER);
			vbox2.getChildren().add(hbox1);
		}
		vbox2.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round");
		VBox vbox3 = new VBox();
		vbox3.getChildren().addAll(txtfw3, vbox2);
		Text txt4 = new Text("Time");
		txt4.setFont(Font.font("System", FontWeight.BOLD, 24));
		Text txt6 = new Text("Comment");
		txt6.setFont(Font.font("System", FontWeight.BOLD, 24));
		TextFlow txtfw4 = new TextFlow(txt4);
		TextFlow txtfw5 = new TextFlow(new Label(a.getTime()));
		TextFlow txtfw6 = new TextFlow(txt6);
		String comment = a.getComment();
		comment = comment.replaceAll("@", "\n");
		TextArea txtArea = new TextArea(comment);
		txtArea.setEditable(false);
		VBox vbox1 = new VBox();
		vbox1.getChildren().addAll(txtfw1, txtfw2, vbox3, txtfw4, txtfw5, txtfw6, txtArea);
		vbox1.setAlignment(Pos.TOP_CENTER);
		vbox1.setPadding(new Insets(20, 20, 20, 20));
		vbox1.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: pink");
		return vbox1;
	}
	
	public static ArrayList<DonationHistoryModel> getAllHistoryWithOrganization(String name) throws FileNotFoundException{
		DonationPageDAO dao = new DonationPageDAO();
		return dao.getAllHistoryWithOrganization(name);
	}
	
	public static ArrayList<Integer> getAllYear(String name) throws FileNotFoundException{
		DonationPageDAO dao = new DonationPageDAO();
		ArrayList<Integer> ali = new ArrayList<Integer>();
		ArrayList<DonationHistoryModel> aldhm = dao.getAllHistoryWithOrganization(name);
		for(DonationHistoryModel a:aldhm){
			String time = a.getTime();
			time = time.substring(4, 8);
			System.out.println(time);
			ali.add(Integer.parseInt(time));
		}
		return ali;
	}
}
