package theFridge.controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.ShoppingListQRCodePageModel;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import com.jfoenix.controls.JFXListView;

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
	private GridPane StocksGrid;
	@FXML
	private JFXButton addStocks;
	@FXML
	private GridPane ListGrid;
	@FXML
	private JFXButton addList;
	@FXML 
	private JFXListView<HBox> listView;
	
	@FXML
	public void initialize(){
		/*
		Scanner sc = new Scanner("StockList.txt");
		ArrayList<String> sl = new ArrayList<String>();
		while(sc.hasNextLine()){
			sl.add(sc.nextLine());
		}
		for(String a:sl){
			
				
			
		}
		*/
		Label lbl1 = new Label("Banana");
		Label lbl2 = new Label("1");
		Label lbl3 = new Label("3");
		HBox hbox = new HBox(lbl1, lbl2, lbl3);
		Label lbl11 = new Label("Pineapple");
		Label lbl12 = new Label("5");
		Label lbl13 = new Label("3");
		HBox hbox1 = new HBox(lbl11, lbl12, lbl13);
		listView.getItems().addAll(hbox, hbox1);
	}
	
	@FXML
	public void addItems(ActionEvent event){
		if(event.getSource() == addStocks){
			
		}
		else if (event.getSource() == addList){
			
		}
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RecipePage.fxml"));
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
			root = FXMLLoader.load(getClass().getResource(""));
		}

 		stage.setScene(new Scene(root));
 	    stage.show();
	}

	@FXML 
	public void changeToGeneratePage(ActionEvent event) throws IOException, WriterException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListPage.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListQRCodePage.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
 	    File filePath = new File("src/theFridge/picture/QrCode.png");
 	    ShoppingListQRCodePageModel.createQRImage(filePath, "Hello World!", "png");
	}
}