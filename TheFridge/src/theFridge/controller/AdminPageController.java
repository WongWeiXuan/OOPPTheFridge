package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import theFridge.model.DonationHistoryModel;
import theFridge.model.ListModel;
import theFridge.model.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXCheckBox;

public class AdminPageController {
	@FXML
	private VBox donationVbox;
	@FXML
	private Circle logout;
	@FXML JFXComboBox<Integer> yearCombo;
	@FXML JFXButton January;
	@FXML JFXButton February;
	@FXML JFXButton March;
	@FXML JFXButton April;
	@FXML JFXButton May;
	@FXML JFXButton June;
	@FXML JFXButton July;
	@FXML JFXButton August;
	@FXML JFXButton September;
	@FXML JFXButton October;
	@FXML JFXButton November;
	@FXML JFXButton December;
	@FXML Label monthLbl;
	@FXML JFXCheckBox cancelCheck;
	private boolean checked;

	@FXML
	public void initialize() throws FileNotFoundException{
        Image img = new Image("theFridge/picture/logout (1).png");
        logout.setFill(new ImagePattern(img));
        User u = new User();
        u = u.getCurrentUser();
        yearCombo.getItems().addAll(DonationHistoryModel.getAllYear());
        yearCombo.setValue(DonationHistoryModel.getAllYear().get(0));
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
        ArrayList<DonationHistoryModel> aldhm = DonationHistoryModel.getAllMonth(DonationHistoryModel.getAllYear().get(0), calendar.get(Calendar.MONTH) + 1);
        for(DonationHistoryModel dhm:aldhm){
        	if(!dhm.isCancel()){
	        	Label FoodTitleName = new Label("Name");
	            Label FoodTitleAmount = new Label("Amount");
	            FoodTitleName.setPrefWidth(300);
	            FoodTitleAmount.setPrefWidth(300);
	            FoodTitleName.setFont(Font.font("System", FontWeight.BOLD, 16));
	            FoodTitleAmount.setFont(Font.font("System", FontWeight.BOLD, 16));
	            TextFlow FoodTitleTextFlow = new TextFlow(FoodTitleName, FoodTitleAmount);
	            FoodTitleTextFlow.setTextAlignment(TextAlignment.CENTER);
	            VBox FoodVbox = new VBox(FoodTitleTextFlow);
	            FoodVbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: yellow");
	            for(ListModel lm:dhm.getFoodItems()){
	            	String name =  lm.getName();
		           	String amount = String.valueOf((int)lm.getAmount());
		           	Label nameLbl = new Label(name);
		           	Label amountLbl = new Label(amount);
		           	nameLbl.setPrefWidth(300);
		           	amountLbl.setPrefWidth(300);
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
		           	if(estimatedTime < 10){
		           		timeString = timeString.replace(String.valueOf(minutes), "0" + String.valueOf(estimatedTime));
		           	}else{
		           		timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
		           	}
		           	timeString = timeString.replace(String.valueOf(hours), String.valueOf(hours+1));
	            }else{
	            	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
	            }
	            Label etaTime = new Label("Estimated time: ");
	            Label timeStringLbl = new Label(timeString);
	            etaTime.setFont(Font.font("System", FontWeight.BOLD, 16));
	            timeStringLbl.setFont(Font.font("System", FontWeight.LIGHT, 18));
	            TextFlow txtfw = new TextFlow(etaTime, timeStringLbl);
	            txtfw.setTextAlignment(TextAlignment.CENTER);
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
	            vbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: #ffff33");
	            donationVbox.getChildren().add(vbox);
        	}
        }
	}
	
	@FXML 
	public void showMonths(ActionEvent event) throws FileNotFoundException {
		donationVbox.getChildren().clear();
		int year = yearCombo.getValue();
		//If show cancel'd
		if(checked){
			ArrayList<DonationHistoryModel> aldhm = new ArrayList<DonationHistoryModel>();
			if(event.getSource().equals(January)){
				aldhm = DonationHistoryModel.getAllMonth(year, 1);
				monthLbl.setText("January");
			}
			else if(event.getSource().equals(February)){
				aldhm = DonationHistoryModel.getAllMonth(year, 2);
				monthLbl.setText("February");
			}
			else if(event.getSource().equals(March)){
				aldhm = DonationHistoryModel.getAllMonth(year, 3);
				monthLbl.setText("March");
			}
			else if(event.getSource().equals(April)){
				aldhm = DonationHistoryModel.getAllMonth(year, 4);
				monthLbl.setText("April");
			}
			else if(event.getSource().equals(May)){
				aldhm = DonationHistoryModel.getAllMonth(year, 5);
				monthLbl.setText("May");
			}
			else if(event.getSource().equals(June)){
				aldhm = DonationHistoryModel.getAllMonth(year, 6);
				monthLbl.setText("June");
			}
			else if(event.getSource().equals(July)){
				aldhm = DonationHistoryModel.getAllMonth(year, 7);
				monthLbl.setText("July");
			}
			else if(event.getSource().equals(August)){
				aldhm = DonationHistoryModel.getAllMonth(year, 8);
				monthLbl.setText("August");
			}
			else if(event.getSource().equals(September)){
				aldhm = DonationHistoryModel.getAllMonth(year, 9);
				monthLbl.setText("September");
			}
			else if(event.getSource().equals(October)){
				aldhm = DonationHistoryModel.getAllMonth(year, 10);
				monthLbl.setText("October");
			}
			else if(event.getSource().equals(November)){
				aldhm = DonationHistoryModel.getAllMonth(year, 11);
				monthLbl.setText("November");
			}
			else if(event.getSource().equals(December)){
				aldhm = DonationHistoryModel.getAllMonth(year, 12);
				monthLbl.setText("December");
			}
			for(DonationHistoryModel dhm:aldhm){
				if(dhm.isCancel()){
		        	Label FoodTitleName = new Label("Name");
		            Label FoodTitleAmount = new Label("Amount");
		            FoodTitleName.setPrefWidth(300);
		            FoodTitleAmount.setPrefWidth(300);
		            FoodTitleName.setFont(Font.font("System", FontWeight.BOLD, 16));
		            FoodTitleAmount.setFont(Font.font("System", FontWeight.BOLD, 16));
		            TextFlow FoodTitleTextFlow = new TextFlow(FoodTitleName, FoodTitleAmount);
		            FoodTitleTextFlow.setTextAlignment(TextAlignment.CENTER);
		            VBox FoodVbox = new VBox(FoodTitleTextFlow);
		            FoodVbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: yellow");
		            for(ListModel lm:dhm.getFoodItems()){
		            	String name =  lm.getName();
			           	String amount = String.valueOf((int)lm.getAmount());
			           	Label nameLbl = new Label(name);
			           	Label amountLbl = new Label(amount);
			           	nameLbl.setPrefWidth(300);
			           	amountLbl.setPrefWidth(300);
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
			           	if(estimatedTime < 10){
			           		timeString = timeString.replace(String.valueOf(minutes), "0" + String.valueOf(estimatedTime));
			           	}else{
			           		timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
			           	}
			           	timeString = timeString.replace(String.valueOf(hours), String.valueOf(hours+1));
		            }else{
		            	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
		            }
		            Label etaTime = new Label("Estimated time: ");
		            Label timeStringLbl = new Label(timeString);
		            etaTime.setFont(Font.font("System", FontWeight.BOLD, 16));
		            timeStringLbl.setFont(Font.font("System", FontWeight.LIGHT, 18));
		            TextFlow txtfw = new TextFlow(etaTime, timeStringLbl);
		            txtfw.setTextAlignment(TextAlignment.CENTER);
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
		            ImageView cancelImg = new ImageView(new Image("theFridge/picture/error (2).png"));
		            cancelImg.setFitHeight(100);
		            cancelImg.setFitWidth(100);
		            StackPane TitleStackPane = new StackPane(TitleUserName, cancelImg);
		            StackPane.setAlignment(cancelImg, Pos.CENTER_RIGHT);
		            TitleStackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		            VBox vbox = new VBox(TitleStackPane, hidden);
		            vbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: #ffff33");
		            donationVbox.getChildren().add(vbox);
				}else{
					Label FoodTitleName = new Label("Name");
		            Label FoodTitleAmount = new Label("Amount");
		            FoodTitleName.setPrefWidth(300);
		            FoodTitleAmount.setPrefWidth(300);
		            FoodTitleName.setFont(Font.font("System", FontWeight.BOLD, 16));
		            FoodTitleAmount.setFont(Font.font("System", FontWeight.BOLD, 16));
		            TextFlow FoodTitleTextFlow = new TextFlow(FoodTitleName, FoodTitleAmount);
		            FoodTitleTextFlow.setTextAlignment(TextAlignment.CENTER);
		            VBox FoodVbox = new VBox(FoodTitleTextFlow);
		            FoodVbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: yellow");
		            for(ListModel lm:dhm.getFoodItems()){
		            	String name =  lm.getName();
			           	String amount = String.valueOf((int)lm.getAmount());
			           	Label nameLbl = new Label(name);
			           	Label amountLbl = new Label(amount);
			           	nameLbl.setPrefWidth(300);
			           	amountLbl.setPrefWidth(300);
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
			           	if(estimatedTime < 10){
			           		timeString = timeString.replace(String.valueOf(minutes), "0" + String.valueOf(estimatedTime));
			           	}else{
			           		timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
			           	}
			           	timeString = timeString.replace(String.valueOf(hours), String.valueOf(hours+1));
		            }else{
		            	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
		            }
		            Label etaTime = new Label("Estimated time: ");
		            Label timeStringLbl = new Label(timeString);
		            etaTime.setFont(Font.font("System", FontWeight.BOLD, 16));
		            timeStringLbl.setFont(Font.font("System", FontWeight.LIGHT, 18));
		            TextFlow txtfw = new TextFlow(etaTime, timeStringLbl);
		            txtfw.setTextAlignment(TextAlignment.CENTER);
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
		            vbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: #ffff33");
		            donationVbox.getChildren().add(vbox);
				}
	        }
		}
		//If dont show
		else{
			ArrayList<DonationHistoryModel> aldhm = new ArrayList<DonationHistoryModel>();
			if(event.getSource().equals(January)){
				aldhm = DonationHistoryModel.getAllMonth(year, 1);
				monthLbl.setText("January");
			}
			else if(event.getSource().equals(February)){
				aldhm = DonationHistoryModel.getAllMonth(year, 2);
				monthLbl.setText("February");
			}
			else if(event.getSource().equals(March)){
				aldhm = DonationHistoryModel.getAllMonth(year, 3);
				monthLbl.setText("March");
			}
			else if(event.getSource().equals(April)){
				aldhm = DonationHistoryModel.getAllMonth(year, 4);
				monthLbl.setText("April");
			}
			else if(event.getSource().equals(May)){
				aldhm = DonationHistoryModel.getAllMonth(year, 5);
				monthLbl.setText("May");
			}
			else if(event.getSource().equals(June)){
				aldhm = DonationHistoryModel.getAllMonth(year, 6);
				monthLbl.setText("June");
			}
			else if(event.getSource().equals(July)){
				aldhm = DonationHistoryModel.getAllMonth(year, 7);
				monthLbl.setText("July");
			}
			else if(event.getSource().equals(August)){
				aldhm = DonationHistoryModel.getAllMonth(year, 8);
				monthLbl.setText("August");
			}
			else if(event.getSource().equals(September)){
				aldhm = DonationHistoryModel.getAllMonth(year, 9);
				monthLbl.setText("September");
			}
			else if(event.getSource().equals(October)){
				aldhm = DonationHistoryModel.getAllMonth(year, 10);
				monthLbl.setText("October");
			}
			else if(event.getSource().equals(November)){
				aldhm = DonationHistoryModel.getAllMonth(year, 11);
				monthLbl.setText("November");
			}
			else if(event.getSource().equals(December)){
				aldhm = DonationHistoryModel.getAllMonth(year, 12);
				monthLbl.setText("December");
			}
			for(DonationHistoryModel dhm:aldhm){
				if(dhm.isCancel()){
		        	Label FoodTitleName = new Label("Name");
		            Label FoodTitleAmount = new Label("Amount");
		            FoodTitleName.setPrefWidth(300);
		            FoodTitleAmount.setPrefWidth(300);
		            FoodTitleName.setFont(Font.font("System", FontWeight.BOLD, 16));
		            FoodTitleAmount.setFont(Font.font("System", FontWeight.BOLD, 16));
		            TextFlow FoodTitleTextFlow = new TextFlow(FoodTitleName, FoodTitleAmount);
		            FoodTitleTextFlow.setTextAlignment(TextAlignment.CENTER);
		            VBox FoodVbox = new VBox(FoodTitleTextFlow);
		            FoodVbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: yellow");
		            for(ListModel lm:dhm.getFoodItems()){
		            	String name =  lm.getName();
			           	String amount = String.valueOf((int)lm.getAmount());
			           	Label nameLbl = new Label(name);
			           	Label amountLbl = new Label(amount);
			           	nameLbl.setPrefWidth(300);
			           	amountLbl.setPrefWidth(300);
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
			           	if(estimatedTime < 10){
			           		timeString = timeString.replace(String.valueOf(minutes), "0" + String.valueOf(estimatedTime));
			           	}else{
			           		timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
			           	}
			           	timeString = timeString.replace(String.valueOf(hours), String.valueOf(hours+1));
		            }else{
		            	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
		            }
		            Label etaTime = new Label("Estimated time: ");
		            Label timeStringLbl = new Label(timeString);
		            etaTime.setFont(Font.font("System", FontWeight.BOLD, 16));
		            timeStringLbl.setFont(Font.font("System", FontWeight.LIGHT, 18));
		            TextFlow txtfw = new TextFlow(etaTime, timeStringLbl);
		            txtfw.setTextAlignment(TextAlignment.CENTER);
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
		            ImageView cancelImg = new ImageView(new Image("theFridge/picture/error (2).png"));
		            cancelImg.setFitHeight(100);
		            cancelImg.setFitWidth(100);
		            StackPane TitleStackPane = new StackPane(TitleUserName, cancelImg);
		            StackPane.setAlignment(cancelImg, Pos.CENTER_RIGHT);
		            TitleStackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		            VBox vbox = new VBox(TitleStackPane, hidden);
		            vbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: #ffff33");
		            donationVbox.getChildren().add(vbox);
				}else{
					Label FoodTitleName = new Label("Name");
		            Label FoodTitleAmount = new Label("Amount");
		            FoodTitleName.setPrefWidth(300);
		            FoodTitleAmount.setPrefWidth(300);
		            FoodTitleName.setFont(Font.font("System", FontWeight.BOLD, 16));
		            FoodTitleAmount.setFont(Font.font("System", FontWeight.BOLD, 16));
		            TextFlow FoodTitleTextFlow = new TextFlow(FoodTitleName, FoodTitleAmount);
		            FoodTitleTextFlow.setTextAlignment(TextAlignment.CENTER);
		            VBox FoodVbox = new VBox(FoodTitleTextFlow);
		            FoodVbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: yellow");
		            for(ListModel lm:dhm.getFoodItems()){
		            	String name =  lm.getName();
			           	String amount = String.valueOf((int)lm.getAmount());
			           	Label nameLbl = new Label(name);
			           	Label amountLbl = new Label(amount);
			           	nameLbl.setPrefWidth(300);
			           	amountLbl.setPrefWidth(300);
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
			           	if(estimatedTime < 10){
			           		timeString = timeString.replace(String.valueOf(minutes), "0" + String.valueOf(estimatedTime));
			           	}else{
			           		timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
			           	}
			           	timeString = timeString.replace(String.valueOf(hours), String.valueOf(hours+1));
		            }else{
		            	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
		            }
		            Label etaTime = new Label("Estimated time: ");
		            Label timeStringLbl = new Label(timeString);
		            etaTime.setFont(Font.font("System", FontWeight.BOLD, 16));
		            timeStringLbl.setFont(Font.font("System", FontWeight.LIGHT, 18));
		            TextFlow txtfw = new TextFlow(etaTime, timeStringLbl);
		            txtfw.setTextAlignment(TextAlignment.CENTER);
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
		            vbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: #ffff33");
		            donationVbox.getChildren().add(vbox);
				}
	        }
		}
	}
	
	@FXML 
	public void showYearHistory(ActionEvent event) throws FileNotFoundException {
		allHistory(checked);
	}
	
	@FXML 
	public void showCancelOrder(ActionEvent event) throws FileNotFoundException {
		if(cancelCheck.isSelected()){
			allHistory(true);
			checked = true;
		}else{
			allHistory(false);
			checked = false;
		}
	}
	
	private void allHistory(boolean selected) throws FileNotFoundException{
		if(selected){
			String month = monthLbl.getText();
			donationVbox.getChildren().clear();
			int year = yearCombo.getValue();
			ArrayList<DonationHistoryModel> aldhm = new ArrayList<DonationHistoryModel>();
			if(month.equals("January")){
				aldhm = DonationHistoryModel.getAllMonth(year, 1);
			}
			else if(month.equals("February")){
				aldhm = DonationHistoryModel.getAllMonth(year, 2);
			}
			else if(month.equals("March")){
				aldhm = DonationHistoryModel.getAllMonth(year, 3);
			}
			else if(month.equals("April")){
				aldhm = DonationHistoryModel.getAllMonth(year, 4);
			}
			else if(month.equals("May")){
				aldhm = DonationHistoryModel.getAllMonth(year, 5);
			}
			else if(month.equals("June")){
				aldhm = DonationHistoryModel.getAllMonth(year, 6);
			}
			else if(month.equals("July")){
				aldhm = DonationHistoryModel.getAllMonth(year, 7);
			}
			else if(month.equals("August")){
				aldhm = DonationHistoryModel.getAllMonth(year, 8);
			}
			else if(month.equals("September")){
				aldhm = DonationHistoryModel.getAllMonth(year, 9);
			}
			else if(month.equals("October")){
				aldhm = DonationHistoryModel.getAllMonth(year, 10);
			}
			else if(month.equals("November")){
				aldhm = DonationHistoryModel.getAllMonth(year, 11);
			}
			else if(month.equals("December")){
				aldhm = DonationHistoryModel.getAllMonth(year, 12);
			}
			for(DonationHistoryModel dhm:aldhm){
				if(dhm.isCancel()){
		        	Label FoodTitleName = new Label("Name");
		            Label FoodTitleAmount = new Label("Amount");
		            FoodTitleName.setPrefWidth(300);
		            FoodTitleAmount.setPrefWidth(300);
		            FoodTitleName.setFont(Font.font("System", FontWeight.BOLD, 16));
		            FoodTitleAmount.setFont(Font.font("System", FontWeight.BOLD, 16));
		            TextFlow FoodTitleTextFlow = new TextFlow(FoodTitleName, FoodTitleAmount);
		            FoodTitleTextFlow.setTextAlignment(TextAlignment.CENTER);
		            VBox FoodVbox = new VBox(FoodTitleTextFlow);
		            FoodVbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: yellow");
		            for(ListModel lm:dhm.getFoodItems()){
		            	String name =  lm.getName();
			           	String amount = String.valueOf((int)lm.getAmount());
			           	Label nameLbl = new Label(name);
			           	Label amountLbl = new Label(amount);
			           	nameLbl.setPrefWidth(300);
			           	amountLbl.setPrefWidth(300);
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
			           	if(estimatedTime < 10){
			           		timeString = timeString.replace(String.valueOf(minutes), "0" + String.valueOf(estimatedTime));
			           	}else{
			           		timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
			           	}
			           	timeString = timeString.replace(String.valueOf(hours), String.valueOf(hours+1));
		            }else{
		            	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
		            }
		            Label etaTime = new Label("Estimated time: ");
		            Label timeStringLbl = new Label(timeString);
		            etaTime.setFont(Font.font("System", FontWeight.BOLD, 16));
		            timeStringLbl.setFont(Font.font("System", FontWeight.LIGHT, 18));
		            TextFlow txtfw = new TextFlow(etaTime, timeStringLbl);
		            txtfw.setTextAlignment(TextAlignment.CENTER);
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
		            ImageView cancelImg = new ImageView(new Image("theFridge/picture/error (2).png"));
		            cancelImg.setFitHeight(100);
		            cancelImg.setFitWidth(100);
		            StackPane TitleStackPane = new StackPane(TitleUserName, cancelImg);
		            StackPane.setAlignment(cancelImg, Pos.CENTER_RIGHT);
		            TitleStackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		            VBox vbox = new VBox(TitleStackPane, hidden);
		            vbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: #ffff33");
		            donationVbox.getChildren().add(vbox);
				}else{
					Label FoodTitleName = new Label("Name");
		            Label FoodTitleAmount = new Label("Amount");
		            FoodTitleName.setPrefWidth(300);
		            FoodTitleAmount.setPrefWidth(300);
		            FoodTitleName.setFont(Font.font("System", FontWeight.BOLD, 16));
		            FoodTitleAmount.setFont(Font.font("System", FontWeight.BOLD, 16));
		            TextFlow FoodTitleTextFlow = new TextFlow(FoodTitleName, FoodTitleAmount);
		            FoodTitleTextFlow.setTextAlignment(TextAlignment.CENTER);
		            VBox FoodVbox = new VBox(FoodTitleTextFlow);
		            FoodVbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: yellow");
		            for(ListModel lm:dhm.getFoodItems()){
		            	String name =  lm.getName();
			           	String amount = String.valueOf((int)lm.getAmount());
			           	Label nameLbl = new Label(name);
			           	Label amountLbl = new Label(amount);
			           	nameLbl.setPrefWidth(300);
			           	amountLbl.setPrefWidth(300);
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
			           	if(estimatedTime < 10){
			           		timeString = timeString.replace(String.valueOf(minutes), "0" + String.valueOf(estimatedTime));
			           	}else{
			           		timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
			           	}
			           	timeString = timeString.replace(String.valueOf(hours), String.valueOf(hours+1));
		            }else{
		            	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
		            }
		            Label etaTime = new Label("Estimated time: ");
		            Label timeStringLbl = new Label(timeString);
		            etaTime.setFont(Font.font("System", FontWeight.BOLD, 16));
		            timeStringLbl.setFont(Font.font("System", FontWeight.LIGHT, 18));
		            TextFlow txtfw = new TextFlow(etaTime, timeStringLbl);
		            txtfw.setTextAlignment(TextAlignment.CENTER);
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
		            vbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: #ffff33");
		            donationVbox.getChildren().add(vbox);
				}
	        }
		}else{
			String month = monthLbl.getText();
			donationVbox.getChildren().clear();
			int year = yearCombo.getValue();
			ArrayList<DonationHistoryModel> aldhm = new ArrayList<DonationHistoryModel>();
			if(month.equals("January")){
				aldhm = DonationHistoryModel.getAllMonth(year, 1);
			}
			else if(month.equals("February")){
				aldhm = DonationHistoryModel.getAllMonth(year, 2);
			}
			else if(month.equals("March")){
				aldhm = DonationHistoryModel.getAllMonth(year, 3);
			}
			else if(month.equals("April")){
				aldhm = DonationHistoryModel.getAllMonth(year, 4);
			}
			else if(month.equals("May")){
				aldhm = DonationHistoryModel.getAllMonth(year, 5);
			}
			else if(month.equals("June")){
				aldhm = DonationHistoryModel.getAllMonth(year, 6);
			}
			else if(month.equals("July")){
				aldhm = DonationHistoryModel.getAllMonth(year, 7);
			}
			else if(month.equals("August")){
				aldhm = DonationHistoryModel.getAllMonth(year, 8);
			}
			else if(month.equals("September")){
				aldhm = DonationHistoryModel.getAllMonth(year, 9);
			}
			else if(month.equals("October")){
				aldhm = DonationHistoryModel.getAllMonth(year, 10);
			}
			else if(month.equals("November")){
				aldhm = DonationHistoryModel.getAllMonth(year, 11);
			}
			else if(month.equals("December")){
				aldhm = DonationHistoryModel.getAllMonth(year, 12);
			}
			for(DonationHistoryModel dhm:aldhm){
				if(!dhm.isCancel()){
					Label FoodTitleName = new Label("Name");
		            Label FoodTitleAmount = new Label("Amount");
		            FoodTitleName.setPrefWidth(300);
		            FoodTitleAmount.setPrefWidth(300);
		            FoodTitleName.setFont(Font.font("System", FontWeight.BOLD, 16));
		            FoodTitleAmount.setFont(Font.font("System", FontWeight.BOLD, 16));
		            TextFlow FoodTitleTextFlow = new TextFlow(FoodTitleName, FoodTitleAmount);
		            FoodTitleTextFlow.setTextAlignment(TextAlignment.CENTER);
		            VBox FoodVbox = new VBox(FoodTitleTextFlow);
		            FoodVbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: yellow");
		            for(ListModel lm:dhm.getFoodItems()){
		            	String name =  lm.getName();
			           	String amount = String.valueOf((int)lm.getAmount());
			           	Label nameLbl = new Label(name);
			           	Label amountLbl = new Label(amount);
			           	nameLbl.setPrefWidth(300);
			           	amountLbl.setPrefWidth(300);
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
			           	if(estimatedTime < 10){
			           		timeString = timeString.replace(String.valueOf(minutes), "0" + String.valueOf(estimatedTime));
			           	}else{
			           		timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
			           	}
			           	timeString = timeString.replace(String.valueOf(hours), String.valueOf(hours+1));
		            }else{
		            	timeString = timeString.replace(String.valueOf(minutes), String.valueOf(estimatedTime));
		            }
		            Label etaTime = new Label("Estimated time: ");
		            Label timeStringLbl = new Label(timeString);
		            etaTime.setFont(Font.font("System", FontWeight.BOLD, 16));
		            timeStringLbl.setFont(Font.font("System", FontWeight.LIGHT, 18));
		            TextFlow txtfw = new TextFlow(etaTime, timeStringLbl);
		            txtfw.setTextAlignment(TextAlignment.CENTER);
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
		            vbox.setStyle("-fx-border-style: segments(10, 15, 15, 15)  line-cap round; -fx-border-color: #ffff33");
		            donationVbox.getChildren().add(vbox);
	        	}
	        }
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
