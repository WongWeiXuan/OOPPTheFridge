package theFridge.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import theFridge.model.CharityFoodDonationGoogleMapModel;
import theFridge.model.DonationHistoryModel;
import theFridge.model.DonationPageModel;
import theFridge.model.ListModel;
import theFridge.model.User;

public class DonationSubmitConfirmPopupPageController {
	@FXML
	private VBox foodItemListVBox;
	@FXML
	private Text locationLbl;
	@FXML
	private Text nameLbl;
	@FXML
	private Text locationLbl2;
	@FXML
	private Text emailLbl;
	@FXML
	private Text locationLbl3;
	@FXML
	private Text contactLbl;
	@FXML
	private Text locationLbl1;
	@FXML 
	private TextArea commentArea;
	@FXML
	private JFXButton changeBtn;
	@FXML
	private JFXButton confirmBtn;
	static String name;
	static String email;
	static String contact;
	static String location;
	static ArrayList<ListModel> allm = new ArrayList<ListModel>();
	static String time;
	static int timeTaken;
	static Stage stage1;
	

	public void initialize(){
		nameLbl.setText(name);
		emailLbl.setText(email);
		contactLbl.setText(contact);
		locationLbl.setText(location);
		for(ListModel a:allm){
			Label name = new Label(a.getName());
			name.setPrefWidth(400);
			Label amount = new Label(String.valueOf((int)a.getAmount()));
			amount.setPrefWidth(200);
			HBox hbox = new HBox(name, amount);
			hbox.setAlignment(Pos.CENTER);
			hbox.setPadding(new Insets(10));
			foodItemListVBox.getChildren().add(hbox);
		}
	}
	
	// Event Listener on JFXButton[#changeBtn].onAction
	@FXML
	public void closeStage(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	// Event Listener on JFXButton[#confirmBtn].onAction
	@FXML
	public void submitForm(ActionEvent event) throws IOException {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setCountry(location);
		String comment = commentArea.getText();
		comment = comment.replaceAll("\n", "@");
		DonationHistoryModel dhm = new DonationHistoryModel(user, DonationPageController.OrganizationTxt1, contact, allm, time, timeTaken, comment);
		dhm.setCancel(false);
		dhm.submitForm(dhm);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/CharityFoodDonationPage.fxml"));
		stage1.setScene(new Scene(root));
		
		DonationPageModel.updateStockList(allm);
	}
}
