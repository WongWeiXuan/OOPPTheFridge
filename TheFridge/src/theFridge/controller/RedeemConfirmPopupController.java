package theFridge.controller;

import javafx.fxml.FXML;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import theFridge.model.RedeemVoucherSendEmail;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RedeemConfirmPopupController {
	@FXML
	private HBox confirm;
	@FXML
	private HBox cancel;
	@FXML
	private ImageView confirmImg;
	@FXML
	private ImageView confirm2Img;
	@FXML
	private ImageView errorImg;
	@FXML
	private ImageView error2Img;
	
	@FXML
	public void confirmRedeem(MouseEvent event) {
		String subject = "";
		String text = "";
		
		RedeemVoucherSendEmail rDVSE = new RedeemVoucherSendEmail();
		rDVSE.sendEmail(subject, text);
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
	
	@FXML
	public void cancelRedeem(MouseEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void showError2Img(MouseEvent event) {
		error2Img.setVisible(true);
		errorImg.setVisible(false);
	}
	
	@FXML
	public void hideError2Img(MouseEvent event) {
		error2Img.setVisible(false);
		errorImg.setVisible(true);
	}
}
