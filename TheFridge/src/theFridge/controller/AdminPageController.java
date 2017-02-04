package theFridge.controller;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import theFridge.model.User;

public class AdminPageController {
	@FXML
	private VBox donationVbox;
	@FXML
	private Circle logout;

	@FXML
	public void initialize() throws FileNotFoundException{
        Image img = new Image("logout (1).png");
        logout.setFill(new ImagePattern(img));
	}
	
	// Event Listener on Circle[#logout].onMouseClicked
	@FXML
	public void logout(MouseEvent event) {
		
	}
}
