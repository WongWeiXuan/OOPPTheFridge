package theFridge.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import javafx.stage.StageStyle;
//import javafx.application.Platform;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.DAO.SignupDAO;
import theFridge.model.SignupModel;

public class LoginSignupPageController {
	@FXML
	private JFXButton loginBtn;
	@FXML
	private JFXButton signupBtn;
	@FXML
	private JFXButton minimiseBtn;
	@FXML
	private JFXButton closeBtn;
	@FXML
	private JFXTextField tFUsername;
	@FXML
	private JFXPasswordField pFPassword;
	@FXML
	private JFXCheckBox loginCheckBox;
	@FXML
	private Label comment;
	@FXML
	private JFXSpinner spinner;
	@FXML
	private VBox loginField;
	// === Stackpaned the white box from signup with login's one ===
	@FXML
	private StackPane fieldForm;
	@FXML
	private JFXTextField tFUsername1;
	@FXML
	private JFXTextField tFEmail;
	@FXML
	private JFXPasswordField pFPassword1;
	@FXML
	private JFXButton createAccount;
	@FXML
	private Label comment1;
	@FXML
	private JFXButton loginBtn1;
	@FXML
	private VBox signupField; // Initial opacity = 0
	// =============================================================
	
	private ArrayList<SignupModel> personList;
	
	/*
	@FXML
	void closePlatform(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
    void minimise(ActionEvent event) {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
    }
    */
	
	public void setPersonList(ArrayList<SignupModel> personList) {
		if (personList !=null || personList.size() > 0) {
			this.personList = personList;
		}
	}
	
	@FXML
	public void goToHomePage(ActionEvent event) throws IOException, ParseException {
		String Username = tFUsername.getText();
		String Password = pFPassword.getText();
		
		
		/*
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(new FileReader("src/theFridge/file/people.json"));
		JSONObject jsonObject = (JSONObject) obj;
		
		JSONArray usernameArray = (JSONArray) jsonObject.get("Username"); 
		
		JSONArray passwordArray = (JSONArray) jsonObject.get("Password"); 
		*/
		
		// If username is empty
		if (Username.equals("") || Username.equals(null)) {
			comment.setText("Please fill in your username!");
		}
		// If password is empty
		else if (Password.equals("") || Password.equals(null)) {
			comment.setText("Please fill in your password!");
		}
		else if (!Username.equals("") || !Username.equals(null) && !Password.equals("") || !Password.equals(null)) {
			// Checking JSON username & password
			//for (int i = 0; i < personList.size(); i++)
			for (SignupModel s : personList) {
				if (Username.equals(s.getUsername()) && Password.equals(s.getPassword())) {
					spinner.setOpacity(1);
					loginBtn.setOpacity(0);
					
					Timeline timeline = new Timeline();
					KeyFrame keyFrame = new KeyFrame(
							Duration.seconds(2), 
							first -> {
									Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
									Parent root = null;
									try {
										root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									Screen screen = Screen.getPrimary();
									Rectangle2D bounds = screen.getVisualBounds();
									stage.setX(bounds.getMinX());
									stage.setY(bounds.getMinY());
									stage.setWidth(bounds.getWidth());
									stage.setHeight(bounds.getHeight());
									stage.setMaximized(true);
									stage.setScene(new Scene(root));
							 	    stage.show();
									
							 	    //Quack2 is the new Quack
									String quack = "src/theFridge/sound/quack2.mp3";

									Media sound = new Media(new File(quack).toURI().toString());
									MediaPlayer mediaPlayer = new MediaPlayer(sound);
									mediaPlayer.play();
							});
			    	timeline.getKeyFrames().addAll(keyFrame);
					timeline.play();
					
					comment.setOpacity(0);
				}
				else {
					comment.setText("Error! You are not registered yet.");
				}
			}
		}
	}
	
	@FXML
	void createAccount(ActionEvent event) throws IOException, ParseException, ParseException{
		System.out.println("eneter signup");
		String Username = tFUsername1.getText();
		String Password = pFPassword1.getText();
		String Email = tFEmail.getText();
		
		if (Username.equals("") || Username.equals(null)) {
			comment1.setText("Please fill in your username!");
		}
		else if (Password.equals("") || Password.equals(null)) {

			comment1.setText("Please fill in your password!");
		}
		else if (Email.equals("") || Email.equals(null)) {
			comment1.setText("Please fill in your email!");
		}
		else {
			SignupModel Someone = new SignupModel(Username, Password, Email);
			Someone.createPerson();
			
			/*
			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(new FileReader("src/theFridge/file/people.json"));
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray usernameArray = (JSONArray) jsonObject.get("Username"); 
			usernameArray.add(Username);
			
			JSONArray passwordArray = (JSONArray) jsonObject.get("Password"); 
			passwordArray.add(Password);
			
			JSONArray emailArray = (JSONArray) jsonObject.get("Email"); 
			emailArray.add(Email);
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("Username", usernameArray);
			jsonobject.put("Password", passwordArray);
			jsonobject.put("Email", emailArray);
			
			try {
				FileWriter fw = new FileWriter("src/theFridge/file/people.json");
				fw.write(jsonobject.toJSONString());
				fw.flush();
				fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			*/
			
		}
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
			
			stage.setScene(new Scene(root));
	 	    stage.show();
	}
	
	@FXML
	public void showSignupPart(ActionEvent event) throws IOException {
		Timeline timeline = new Timeline();
		KeyValue K1 = new KeyValue(fieldForm.layoutXProperty(), 500);
		KeyValue K2 = new KeyValue(loginField.opacityProperty(), 0);
		KeyValue K3 = new KeyValue(loginField.visibleProperty(), false);
		KeyValue K4 = new KeyValue(signupField.opacityProperty(), 1);
		KeyFrame keyFrame0 = new KeyFrame(Duration.millis(10), K4);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(200), K1, K2, K3);
		
		timeline.getKeyFrames().addAll(keyFrame0, keyFrame);
		timeline.play();
		
		/*Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/SignupPage.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();*/
	}
	
	@FXML
	public void showLoginPart(ActionEvent event) throws IOException {
		Timeline timeline = new Timeline();
		KeyValue K1 = new KeyValue(fieldForm.layoutXProperty(), 170);
		KeyValue K2 = new KeyValue(loginField.opacityProperty(), 1);
		KeyValue K3 = new KeyValue(loginField.visibleProperty(), true);
		KeyValue K4 = new KeyValue(signupField.opacityProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(200), K1, K2, K4);
		KeyFrame keyFrame1 = new KeyFrame(Duration.millis(10), K3);
		
		timeline.getKeyFrames().addAll(keyFrame, keyFrame1);
		timeline.play();
	}
}
