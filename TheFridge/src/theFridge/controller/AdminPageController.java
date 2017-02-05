package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import theFridge.DAO.DonationPageDAO;
import theFridge.model.DonationHistoryModel;
import theFridge.model.ListModel;
import theFridge.model.User;

public class AdminPageController {
	@FXML
	private VBox donationVbox;
	@FXML
	private Circle logout;

	@FXML
	public void initialize() throws FileNotFoundException{
        Image img = new Image("theFridge/picture/logout (1).png");
        logout.setFill(new ImagePattern(img));
        User u = new User();
        u = u.getCurrentUser();
        DonationPageDAO dao = new DonationPageDAO();
        ArrayList<DonationHistoryModel> aldhm = dao.getAllHistoryWithOrganization(u.getUsername());
        for(DonationHistoryModel dhm:aldhm){
        	Label FoodTitleName = new Label("Name");
            Label FoodTitleAmount = new Label("Amount");
            FoodTitleName.setPrefWidth(400);
            FoodTitleAmount.setPrefWidth(400);
            TextFlow FoodTitleTextFlow = new TextFlow(FoodTitleName, FoodTitleAmount);
            FoodTitleTextFlow.setTextAlignment(TextAlignment.CENTER);
            VBox FoodVbox = new VBox(FoodTitleTextFlow);
            for(ListModel lm:dhm.getFoodItems()){
            	String name =  lm.getName();
	           	String amount = String.valueOf((int)lm.getAmount());
	           	Label nameLbl = new Label(name);
	           	Label amountLbl = new Label(amount);
	           	nameLbl.setPrefWidth(400);
	           	amountLbl.setPrefWidth(400);
	           	TextFlow FoodTextFlow = new TextFlow(nameLbl, amountLbl);
	           	FoodTextFlow.setTextAlignment(TextAlignment.CENTER);
	           	FoodVbox.getChildren().add(FoodTextFlow);
	           	FoodVbox.setAlignment(Pos.CENTER);
            }
            
            Label email = new Label("Email: " + dhm.getUser().getEmail());
            Label contact = new Label("Contact: " + dhm.getContact());
            String comment = dhm.getComment();
    		comment = comment.replaceAll("@", "\n");
    		TextArea commentArea = new TextArea(comment);
    		commentArea.setEditable(false);
            VBox userDetails = new VBox(email, contact, commentArea);
            userDetails.setAlignment(Pos.TOP_CENTER);
            userDetails.setFillWidth(true);
            
            String timeString = dhm.getTime();
            int minutes = Integer.parseInt(timeString.substring(14, 16));
            int estimatedTime = minutes + dhm.getTimeTaken();
            if(estimatedTime >= 60){
            	estimatedTime -= 60;
	           	int hours = Integer.parseInt(timeString.substring(11, 13));
	           	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
	           	timeString = timeString.replace(String.valueOf(hours), String.valueOf(hours+1));
            }else{
            	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
            }
            TextFlow txtfw = new TextFlow(new Label("Estimated time: "), new Label(timeString));
            VBox secondPart = new VBox(userDetails, txtfw);
            secondPart.setAlignment(Pos.TOP_CENTER);
            secondPart.setFillWidth(true);
            HBox hidden = new HBox(FoodVbox, secondPart);
            hidden.setVisible(false);
            userDetails.setMinWidth(450);
            secondPart.setMinWidth(450);
            HBox.setHgrow(FoodVbox, Priority.ALWAYS);
            HBox.setHgrow(secondPart, Priority.ALWAYS);
            
        	Label userName = new Label(dhm.getUser().getName());
        	HBox userbox = new HBox(userName);
        	Label time = new Label(dhm.getTime());
        	HBox timebox = new HBox(time);
        	userName.setFont(Font.font("System", FontWeight.BOLD, 48));
        	time.setFont(Font.font("System", FontWeight.NORMAL, 48));
        	userbox.setAlignment(Pos.CENTER);
        	timebox.setAlignment(Pos.CENTER);
        	HBox.setHgrow(userbox, Priority.ALWAYS);
        	HBox.setHgrow(timebox, Priority.ALWAYS);
            VBox TitleUserName = new VBox(userbox, timebox);
            TitleUserName.setOnMouseClicked(new EventHandler<MouseEvent>() {
            	boolean open = false;
				@Override
				public void handle(MouseEvent event) {
					if(open == true){
						hidden.setVisible(false);
						open = false;
					}else{
						hidden.setVisible(true);
						open = true;
					}
				}
			 });
            TitleUserName.setCursor(Cursor.HAND);
            TitleUserName.setAlignment(Pos.CENTER);
            TitleUserName.setFillWidth(true);
            VBox vbox = new VBox(TitleUserName, hidden);
            donationVbox.getChildren().add(vbox);
        }
       
	}
	
	// Event Listener on Circle[#logout].onMouseClicked
	@FXML
	public void logout(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		User user = new User();
		user = user.getCurrentUser();
		user.setRememberMe(false);
		user.updateUser();
		
		stage.setX(450);
		stage.setY(128);
		stage.setWidth(1020);
		stage.setHeight(650);
		
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
		stage.setMaximized(false);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
