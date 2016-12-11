package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.ShoppingListQRCodePageModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

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
		//Stock listView
		File sklt = new File("src/theFridge/controller/StockList.txt");
		Scanner sc = new Scanner(sklt);
		ArrayList<String> sl = new ArrayList<String>();
		while(sc.hasNextLine()){
			sl.add(sc.nextLine());
		}
		sc.close();
		
		HBox HBoxTitle = new HBox();
		Label lbl1 = new Label("Stock");
		lbl1.setMinWidth(500);
		lbl1.setPrefWidth(500);
		lbl1.setAlignment(Pos.CENTER_LEFT);
		Label lbl2 = new Label("Amount");
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setAlignment(Pos.CENTER);
		Label lbl3 = new Label("Minimum");
		lbl3.setMinWidth(100);
		lbl3.setPrefWidth(100);
		lbl3.setAlignment(Pos.CENTER);
		HBoxTitle.getChildren().addAll(lbl1, lbl2, lbl3);
		HBoxTitle.setPadding(new Insets(10, 10, 10, 10));
		StocklistView.getItems().add(HBoxTitle);
		
		for(String a:sl){
			Scanner in = new Scanner(a);
			int i = 0;
			HBox hbox = new HBox();
			in.useDelimiter("~");
			while(in.hasNext()){
				Label lbl = new Label(in.next());
				hbox.getChildren().add(lbl);
				if(i == 0){
					lbl.setMinWidth(500);
					lbl.setPrefWidth(500);
					lbl.setAlignment(Pos.CENTER_LEFT);
				}else{
					lbl.setMinWidth(100);
					lbl.setPrefWidth(100);
					lbl.setAlignment(Pos.CENTER);
				}
				hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if(event.getButton() == MouseButton.SECONDARY){
							Popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
						}
					}
		        });
				i++;
			}
			StocklistView.getItems().add(hbox);
			in.close();
		}
		//List listView
		ArrayList<String> sl1 = new ArrayList<String>();
		sl1.add("Banana~2");
		sl1.add("Carrot~1");
		
		HBox HBoxTitle1 = new HBox();
		Label lbl11 = new Label("Shopping List");
		lbl11.setMinWidth(200);
		lbl11.setPrefWidth(200);
		lbl11.setAlignment(Pos.CENTER_LEFT);
		Label lbl12 = new Label("Amount");
		lbl12.setMinWidth(100);
		lbl12.setPrefWidth(100);
		lbl12.setAlignment(Pos.CENTER);
		HBoxTitle1.getChildren().addAll(lbl11, lbl12);
		HBoxTitle1.setPadding(new Insets(10, 10, 10, 10));
		HBox.setHgrow(lbl11, Priority.ALWAYS);
		ListlistView.getItems().add(HBoxTitle1);
		
		for(String a:sl1){
			Scanner in = new Scanner(a);
			int i = 0;
			HBox hbox = new HBox();
			in.useDelimiter("~");
			while(in.hasNext()){
				Label lbl = new Label(in.next());
				hbox.getChildren().add(lbl);
				if(i == 0){
					lbl.setMinWidth(200);
					lbl.setPrefWidth(200);
					lbl.setAlignment(Pos.CENTER_LEFT);
					HBox.setHgrow(lbl, Priority.ALWAYS);
				}else{
					lbl.setMinWidth(100);
					lbl.setPrefWidth(100);
					lbl.setAlignment(Pos.CENTER);
				}
				hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if(event.getButton() == MouseButton.SECONDARY){
							Popup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
						}
					}
		        });
				i++;
			}
			ListlistView.getItems().add(hbox);
			in.close();
		}
		
		startPopup();
		startPopup1();
		
		ObservableList<String> data = FXCollections.observableArrayList("1","2","3","4","5","6","7","8");
		comboBoxFamily.setItems(data);
	}
	
	private void startPopup() {
		Label lbl1 = new Label("Edit");
		lbl1.setMinWidth(100);
		lbl1.setPrefWidth(100);
		lbl1.setPadding(new Insets(10));
		//Edit Items
		lbl1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				
			}
        });
		Label lbl2 = new Label("Delete");
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setPadding(new Insets(10));
		//Remove Items
		lbl2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				int selectedIdx = StocklistView.getSelectionModel().getSelectedIndex();
				if(selectedIdx != 0){
					StocklistView.getItems().remove(selectedIdx);
				}
				Popup.close();
			}
        });
		VBox vbox = new VBox(lbl1, lbl2);
		Popup.setContent(vbox);
		Popup.setSource(StocklistView);
	}
	
	private void startPopup1() {
		Label lbl1 = new Label("Edit");
		lbl1.setMinWidth(100);
		lbl1.setPrefWidth(100);
		lbl1.setPadding(new Insets(10));
		//Edit Items
		lbl1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				
			}
        });
		Label lbl2 = new Label("Delete");
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setPadding(new Insets(10));
		//Remove Items
		lbl2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				int selectedIdx = ListlistView.getSelectionModel().getSelectedIndex();
				if(selectedIdx != 0){
					ListlistView.getItems().remove(selectedIdx);
				}
				Popup1.close();
			}
        });
		VBox vbox = new VBox(lbl1, lbl2);
		Popup1.setContent(vbox);
		Popup1.setSource(ListlistView);
	}
	
	@FXML
	public void addItems(ActionEvent event){
		if(event.getSource() == addStocks){
			HBox hbox = new HBox();
			Label lbl1 = new Label("Corn");
			lbl1.setMinWidth(500);
			lbl1.setPrefWidth(500);
			lbl1.setAlignment(Pos.CENTER_LEFT);
			Label lbl2 = new Label("3");
			lbl2.setMinWidth(100);
			lbl2.setPrefWidth(100);
			lbl2.setAlignment(Pos.CENTER);
			Label lbl3 = new Label("2");
			lbl3.setMinWidth(100);
			lbl3.setPrefWidth(100);
			lbl3.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(lbl1, lbl2, lbl3);
			StocklistView.getItems().add(hbox);
			hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.SECONDARY){
						Popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
					}
				}
	        });
		}
		else if (event.getSource() == addList){
			HBox hbox = new HBox();
			Label lbl1 = new Label("Corn");
			lbl1.setMinWidth(500);
			lbl1.setPrefWidth(500);
			lbl1.setAlignment(Pos.CENTER_LEFT);
			Label lbl2 = new Label("3");
			lbl2.setMinWidth(100);
			lbl2.setPrefWidth(100);
			lbl2.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(lbl1, lbl2);
			ListlistView.getItems().add(hbox);
			hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.SECONDARY){
						Popup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
					}
				}
	        });
		}
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}
		
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
}