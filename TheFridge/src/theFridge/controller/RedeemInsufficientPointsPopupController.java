package theFridge.controller;

import javafx.fxml.FXML;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

public class RedeemInsufficientPointsPopupController {
	@FXML
	private HBox OK;
	@FXML
	private ImageView confirm2Img;
	@FXML
	private ImageView confirmImg;

	
	@FXML
	public void closePopup(MouseEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void showConfirm2Img(MouseEvent event) {
		confirm2Img.setVisible(true);
		confirmImg.setVisible(false);
	}
	
	@FXML
	public void hideConfirm2Img(MouseEvent event) {
		confirm2Img.setVisible(false);
		confirmImg.setVisible(true);
	}
}
