package theFridge.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class ShoppingListAddPageController {
	
    @FXML // fx:id="CloseImage"
    private ImageView CloseImage;
    @FXML 
    private AnchorPane anchorPane;
    
    private Stage stage;
    private double xOffset = 0;
	private double yOffset = 0;
	
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    }
    
    @FXML
    void changeToClose(MouseEvent event) throws FileNotFoundException {
    	Image close = new Image(new FileInputStream("src/theFridge/picture/error.png"));
    	CloseImage.setImage(close);
    }
    
    @FXML
    void changeToClose1(MouseEvent event) throws FileNotFoundException {
    	Image close1 = new Image(new FileInputStream("src/theFridge/picture/error (1).png"));
    	CloseImage.setImage(close1);
    }
   
    @FXML
    void Close(MouseEvent event) {
    	stage.close();
    }

	@FXML 
	public void moveStage(MouseEvent event) {
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
	}

	@FXML 
	public void getPosition(MouseEvent event) {
		xOffset = event.getSceneX();
        yOffset = event.getSceneY();
	}
}
