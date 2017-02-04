package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
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
