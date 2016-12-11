package theFridge.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class HomePageController {
	
	@FXML
	private Text text;
    @FXML
    private JFXButton getStartedBtn;
	@FXML 
	private Circle profileCircle;
    
	@FXML
	public void initialize(){
		Image img = new Image("theFridge/picture/Profile Image.jpg");
		profileCircle.setFill(new ImagePattern(img));
	}
	
	@FXML 
	public void showProfileDropdown(MouseEvent event) {
		
	}

	@FXML 
	public void changeGetStartedScene(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/GetStartedPage.fxml"));
		stage.setMaximized(true);
 		stage.setScene(new Scene(root));
 	    stage.show();
	}

}
