package theFridge.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

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
import theFridge.model.ShoppingListModel;
import theFridge.model.StockModel;

public class ShoppingListAddPageController {
	
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView CloseImage;
    @FXML
    private JFXTextField nameField;
    @FXML
    private Spinner<Integer> amountSpinner;
    @FXML
    private JFXButton confirm;
    
    private Stage stage;
    private double xOffset = 0;
	private double yOffset = 0;

	@FXML
	void initialized(){
		amountSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 1));
	}
	
    @FXML
    void Close(MouseEvent event) {
    	stage = (Stage)((Node) event.getSource()).getScene().getWindow();
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
    void closeAndShow(ActionEvent event) {
    	String name = nameField.getText();
    	int amount = amountSpinner.getValue();
    	StockModel s = new StockModel(name, amount, 1);
    	
		model.addStocks(model);
		stage.close();
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