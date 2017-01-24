package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import theFridge.DAO.ProfileDAO;
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
		
		File file = new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		
		ProfileDAO profileDAO = new ProfileDAO();
		
		User user = new User();
		user = profileDAO.getUser(n);
		user.setPromoCode(promoCode);
		user.addPromoCode();
		user.setTotalPoints(user.getTotalPoints() - rDV.getVoucherPoints());
		user.updateUser();
		
		RedeemVoucher12CharacterPageController rDV12C = new RedeemVoucher12CharacterPageController();
		rDV12C.refresh(promoCode);
		
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
