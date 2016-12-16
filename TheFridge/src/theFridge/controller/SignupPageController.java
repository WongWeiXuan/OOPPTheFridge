package theFridge.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	void createAccount(ActionEvent event) throws IOException {
		String Username = tFUsername.getText();
		String Password = pFPassword.getText();
		String Email = tFEmail.getText();
		
		if (Username.equals("") || Username.equals(null)) {
			tFUsername.setPromptText("Please fill in your username!");
		}
		else if (Password.equals("") || Password.equals(null)) {
			pFPassword.setPromptText("Please fill in your password!");
		}
		else if (Email.equals("") || Email.equals(null)) {
			tFEmail.setPromptText("Please fill in your email!");
		}
		else {
			File file = new File("src/theFridge/Files/user.json");
			file.createNewFile();
			FileWriter fw= new FileWriter(file);
			JSONObject JSONOBJECT= new JSONObject();
			JSONArray userdata = new JSONArray();
			JSONArray JSONARRAY1=new JSONArray();
			JSONArray JSONARRAY2=new JSONArray();
			JSONArray JSONARRAY3=new JSONArray();
				JSONARRAY1.add(Username);
				JSONOBJECT.put("Username", JSONARRAY1);
				JSONARRAY2.add(Password);
				JSONOBJECT.put("Password", JSONARRAY2);
				JSONARRAY3.add(Email);
				JSONOBJECT.put("Email", JSONARRAY3);
				fw.write(JSONOBJECT.toJSONString());
		    fw.flush();  
		    fw.close();
		    
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
			
			stage.setScene(new Scene(root));
	 	    stage.show();
	 	    
		}
		
	}
	
	@FXML
	void goToLoginPage(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
}
