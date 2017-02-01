package theFridge.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import theFridge.model.RedeemVoucherModel;
import theFridge.model.User;

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
	
	public static Stage stage;
	private String promoCode;
	
	@FXML
	public void confirmRedeem(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherBarcodeSentPage.fxml"));
		stage.setScene(new Scene(root));
		
		User user = new User();
		user = user.getCurrentUser();
		
		RedeemVoucherModel rDV = new RedeemVoucherModel();
		
		//Deduct user's total points after redeeming voucher
		user.setTotalPoints(user.getTotalPoints() - rDV.getVoucherPoints());
		
		//Update the subtracted points to user text file
		user.updateUser();
		
		Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage1.close();
		
		//Generate promo code and store in user text file
		rDV.generatePromoCode();
		promoCode = rDV.getCodeOutput();
		user.setPromoCode(promoCode);
		user.updateUser();
		
		//Generate barcode based on the promo code output
		rDV.generateBarcode();
		
		//Set the date user can redeem again after redeeming promo code
		rDV.setRedeemAgainDate();
		
		//Send email to user based on the barcode image output
		rDV.sendEmail();
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
