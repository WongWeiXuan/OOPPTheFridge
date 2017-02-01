package theFridge.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SignupPageController {
	@FXML
	private JFXButton loginBtn;
	@FXML
	private JFXButton createAccount;
	@FXML
	private JFXTextField tFUsername;
	@FXML
	private JFXTextField tFEmail;
	@FXML
	private JFXPasswordField pFPassword;
	@FXML
	private Label comment;
	
	@SuppressWarnings("unchecked")
	@FXML
	public void createAccount(ActionEvent event) throws IOException, ParseException, ParseException{
		String Username = tFUsername.getText();
		String Password = pFPassword.getText();
		String Email = tFEmail.getText();
		
		if (Username.equals("") || Username.equals(null)) {
			comment.setText("Please fill in your username!");
		}
		else if (Password.equals("") || Password.equals(null)) {
			comment.setText("Please fill in your password!");
		}
		else if (Email.equals("") || Email.equals(null)) {
			comment.setText("Please fill in your email!");
		}
		else {
			/* 
			SignupModel Person = new SignupModel(String username, String password, String email);
			Person.setUsername(Username);
			Person.setPassword(Password);
			Person.setEmail(Email);
			
			ArrayList<SignupModel> personList = new ArrayList<SignupModel>();
			personList.add(Person);
			 */
			
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
		}
		    
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
			
			stage.setScene(new Scene(root));
	 	    stage.show();
	}
	
	@FXML
	public void goToLoginPage(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
}
