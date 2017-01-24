package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.ShoppingListAddPageModel;
import theFridge.model.ShoppingListModel;
import theFridge.model.ShoppingListQRCodePageModel;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXComboBox;

public class ShoppingListController {
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
	private JFXButton addStocks;
	@FXML
	private JFXButton addList;
	@FXML 
	private JFXListView<HBox> StocklistView;
	@FXML 
	private JFXListView<HBox> ListlistView;
	@FXML
	private JFXPopup Popup;
	@FXML 
	private JFXPopup Popup1;
	@FXML 
	private JFXComboBox<String> comboBoxFamily;
	
	@FXML
	public void initialize() throws FileNotFoundException{
		ShoppingListModel first = new ShoppingListModel(StocklistView, Popup, ListlistView, Popup1);
		ShoppingListAddPageModel.model = first;
		first.createTitle();
		first.displayStocks();
		first.displayShopping();
		
		first.startPopup();
	}
	
	@FXML
	public void addItems(ActionEvent event) throws IOException{
		ShoppingListModel first = new ShoppingListModel(StocklistView, Popup, ListlistView, Popup1);
		if(event.getSource() == addStocks){
			first.showStage(first, "Stock");
		}
		else if(event.getSource() == addList){
			first.showStage(first, "List");
		}
	}
	
	@FXML 
	public void changeToGeneratePage(ActionEvent event) throws IOException, WriterException {
		ShoppingListQRCodePageModel.createQRImage();
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/FoodCalculatorNavigation.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListQRCodePage.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	@FXML
	public void showNavigation(MouseEvent event){
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	
	@FXML
	public void hideNavigation(MouseEvent event){
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), -150);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 1);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	
	@FXML
	public void changeScene(MouseEvent event) throws IOException {
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/FoodCalculatorNavigation.fxml"));
		}
		else if(event.getSource().equals(quizScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizBeginPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}
		
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
}