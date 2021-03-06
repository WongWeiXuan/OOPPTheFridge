package theFridge.controller;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import theFridge.model.RedeemVoucherModel;
import theFridge.model.User;

public class RedeemConfirmPromoPopupController {
	@FXML
	private HBox confirm;
	@FXML
	private ImageView confirm2Img;
	@FXML
	private ImageView confirmImg;
	@FXML
	private HBox cancel;
	@FXML
	private ImageView error2Img;
	@FXML
	private ImageView errorImg;

	private String promoCode;
	
	@FXML
	public void confirmPromoRedeem(MouseEvent event) throws FileNotFoundException {
		RedeemVoucherModel rDV = new RedeemVoucherModel();
		rDV.generatePromoCode();
		promoCode = rDV.getCodeOutput();
		rDV.setRedeemAgainDate();
		
		User user = new User();
		user = user.getCurrentUser();
		user.setPromoCode(promoCode);
		user.setTotalPoints(user.getTotalPoints() - rDV.getVoucherPoints());
		user.updateUser();
		
		RedeemVoucherModel.refreshSetText(promoCode);
		RedeemVoucherModel.setItVisible();
		
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
