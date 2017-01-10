package theFridge.controller;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;

public class RedeemVoucherPageController {
	@FXML
	private AnchorPane Anchor;
	@FXML
	private VBox naviPreview;
	@FXML
	private VBox navi;
	@FXML
	private VBox homeScene;
	@FXML
	private VBox recipeScene;
	@FXML
	private VBox compostScene;
	@FXML
	private VBox foodScene;
	@FXML
	private VBox quizScene;
	@FXML
	private VBox prizeScene;
	@FXML 
	private JFXButton TwelveCharacterBtn1;
	@FXML 
	private JFXButton TwelveCharacterBtn2;
	@FXML 
	private JFXButton TwelveCharacterBtn3;
	@FXML 
	private JFXButton TwelveCharacterBtn4;
	@FXML
	private Label totalPoints;
	@FXML
	private VBox voucher1;
	@FXML
	private VBox voucher2;
	@FXML
	private VBox voucher3;
	@FXML
	private VBox voucher4;
	@FXML
	private VBox main1;
	@FXML
	private VBox main2;
	@FXML
	private VBox main3;
	@FXML
	private VBox main4;
	@FXML
	private VBox show1;
	@FXML
	private VBox show2;
	@FXML
	private VBox show3;
	@FXML
	private VBox show4;
	
	@FXML
	public void show(MouseEvent event) {
		if (event.getSource().equals(voucher1)) {
			main1.setVisible(false);
			show1.setVisible(true);
		}
		else if (event.getSource().equals(voucher2)) {
			main2.setVisible(false);
			show2.setVisible(true);
		}
		else if (event.getSource().equals(voucher3)) {
			main3.setVisible(false);
			show3.setVisible(true);
		}
		else if (event.getSource().equals(voucher4)) {
			main4.setVisible(false);
			show4.setVisible(true);
		}
	}
	
	@FXML
	public void hide(MouseEvent event) {
		if (event.getSource().equals(voucher1)) {
			main1.setVisible(true);
			show1.setVisible(false);
		}
		else if (event.getSource().equals(voucher2)) {
			main2.setVisible(true);
			show2.setVisible(false);
		}
		else if (event.getSource().equals(voucher3)) {
			main3.setVisible(true);
			show3.setVisible(false);
		}
		else if (event.getSource().equals(voucher4)) {
			main4.setVisible(true);
			show4.setVisible(false);
		}
	}

	@FXML 
	public void goToPromoPage(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucher12CharacterPage.fxml"));
		
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML
	public void showNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	
	@FXML
	public void hideNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), -150);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 1);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}

	@FXML
	public void changeScene(MouseEvent event) throws IOException{
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = null; //(Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		
		
		if(event.getSource().equals(homeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		}
		else if(event.getSource().equals(recipeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/FindingDish.fxml"));
		}
		else if(event.getSource().equals(compostScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
		}
		else if(event.getSource().equals(foodScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListPage.fxml"));
		}
		else if(event.getSource().equals(quizScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
}
