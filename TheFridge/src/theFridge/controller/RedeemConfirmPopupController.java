package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.fxml.FXML;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import theFridge.DAO.ProfileDAO;
import theFridge.model.RedeemVoucherModel;
import theFridge.model.User;
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
	public void confirmRedeem(MouseEvent event) throws FileNotFoundException {
		/*
		File file = new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		
		ProfileDAO profileDAO = new ProfileDAO();
		
		User user = new User();
		user = profileDAO.getUser(n);
		*/
		
		User user = new User();
		user = user.getCurrentUser();
		
		RedeemVoucherModel rDV = new RedeemVoucherModel();
		rDV.generatePromoCode();												//Generate promo code
		rDV.setRedeemAgainDate();												//Set the date user can redeem again after redeeming promo code
		rDV.generateBarcode();													//Generate barcode based on the promo code output
		rDV.sendEmail();														//Send email to user based on the barcode image output
		
		user.setTotalPoints(user.getTotalPoints() - rDV.getVoucherPoints());	//Deduct user's total points after redeeming voucher
		user.updateUser();														//Update the subtracted points to user text file
		
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
