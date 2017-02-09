package theFridge.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import theFridge.DAO.DonationPageDAO;

public class DonationHistoryModel {
	User user;
	String organization;
	String contact;
	ArrayList<ListModel> foodItems;
	String time;
	int timeTaken;
	String comment;
	boolean cancel;
	int i = 0;

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
	
	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
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
		if(a.isCancel()){
			TextFlow txtfw1 = new TextFlow(txt1);
			TextFlow txtfw2 = new TextFlow(new Text(a.getOrganization()));
			VBox txtfw12Vbox = new VBox(txtfw1, txtfw2);
			ImageView cancelImg = new ImageView(new Image("theFridge/picture/error (2).png"));
			cancelImg.setFitHeight(100);
			cancelImg.setFitWidth(100);
			StackPane.setAlignment(cancelImg, Pos.CENTER_RIGHT);
			StackPane.setAlignment(txtfw1, Pos.TOP_LEFT);
			StackPane imageViewStack = new StackPane(txtfw12Vbox, cancelImg);
			vbox1.getChildren().addAll(imageViewStack, vbox3, txtfw4, txtfw5, txtfw6, txtArea);
			vbox1.setOpacity(0.3);
		}else{
			TextFlow txtfw1 = new TextFlow(txt1);
			TextFlow txtfw2 = new TextFlow(new Text(a.getOrganization()));
			JFXButton cancelBtn = new JFXButton("Cancel Donation");
			cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							DonationPageDAO dao = new DonationPageDAO();
							User u = new User();
							u = u.getCurrentUser();
							DonationHistoryModel dhm= dao.getHistoryWithUserTime(u.getName(), a.getTime());
							dhm.setCancel(true);
							dao.writeToEditDonationHistoryFile(dhm);
							Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
							Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile3.fxml"));
							stage.setScene(new Scene(root));
							stage.show();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				 });
			TextFlow cancelFlow = new TextFlow(cancelBtn);
			cancelFlow.setTextAlignment(TextAlignment.CENTER);
			vbox1.getChildren().addAll(txtfw1, txtfw2, vbox3, txtfw4, txtfw5, txtfw6, txtArea, cancelFlow);
		}
		
		vbox1.setAlignment(Pos.TOP_CENTER);
		vbox1.setPadding(new Insets(20, 20, 20, 20));
		vbox1.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: pink");
		return vbox1;
	}
	
	public static ArrayList<DonationHistoryModel> getAllHistoryWithOrganization(String name) throws FileNotFoundException{
		DonationPageDAO dao = new DonationPageDAO();
		return dao.getAllHistoryWithOrganization(name);
	}
	
	public static ArrayList<Integer> getAllYear() throws FileNotFoundException{
		User u = new User();
		u = u.getCurrentUser();
		DonationPageDAO dao = new DonationPageDAO();
		ArrayList<Integer> ali = new ArrayList<Integer>();
		ArrayList<DonationHistoryModel> aldhm = dao.getAllHistoryWithOrganization(u.getUsername());
		for(DonationHistoryModel a:aldhm){
			String time = a.getTime();
			time = time.substring(0, 4);
			Integer timeNum = Integer.parseInt(time);
			ali.add(timeNum);
		}
		Set<Integer> hs = new HashSet<>();
		hs.addAll(ali);
		ali.clear();
		ali.addAll(hs);
		Collections.reverse(ali);
		return ali;
	}
	
	public static ArrayList<DonationHistoryModel> getAllYearHistory(int year) throws FileNotFoundException{
		User u = new User();
		u = u.getCurrentUser();
		DonationPageDAO dao = new DonationPageDAO();
		ArrayList<DonationHistoryModel> ali = new ArrayList<DonationHistoryModel>();
		ArrayList<DonationHistoryModel> aldhm = dao.getAllHistoryWithOrganization(u.getUsername());
		for(DonationHistoryModel a:aldhm){
			String time = a.getTime();
			time = time.substring(0, 4);
			Integer timeNum = Integer.parseInt(time);
			if(timeNum == year){
				ali.add(a);
			}
		}
		return ali;
	}
	
	public static ArrayList<DonationHistoryModel> getAllMonth(int year, int month) throws FileNotFoundException{
		ArrayList<DonationHistoryModel> aldhm = new ArrayList<DonationHistoryModel>();
		ArrayList<DonationHistoryModel> history = getAllYearHistory(year);
		for(DonationHistoryModel a:history){
			String time = a.getTime();
			if(month < 10){
				time = time.substring(6, 7);
			}
			else{
				time = time.substring(5, 7);
			}
			Integer timeNum = Integer.parseInt(time);
			if(timeNum == month){
				aldhm.add(a);
			}
		}
		return aldhm;
	}
}
