package theFridge.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import theFridge.model.ShoppingListAddPageModel;

public class ShoppingListAddPageController {
	
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView CloseImage;
    @FXML
    private JFXComboBox<String> nameField;
    @FXML
    private Spinner<Double> amountSpinner;
    @FXML
    private JFXButton confirm;
    
    private Stage stage;
    private double xOffset = 0;
	private double yOffset = 0;

	@FXML
	void initialize() throws FileNotFoundException{
		ShoppingListAddPageModel first = new ShoppingListAddPageModel(nameField, amountSpinner);
		amountSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 50, 1));
		first.initializeComboBox();
	}
	
    @FXML
    void Close(MouseEvent event) {
    	stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	ShoppingListAddPageModel.edit = false;
    	stage.close();
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
    void closeAndShow(ActionEvent event) throws IOException {
    	stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	ShoppingListAddPageModel a = new ShoppingListAddPageModel(nameField, amountSpinner);
    	boolean done = a.closeAndShow();
    	if(done){
    		stage.close();
    	}
    }

    @FXML
    void getPosition(MouseEvent event) {
    	xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    void moveStage(MouseEvent event) {
    	stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

}