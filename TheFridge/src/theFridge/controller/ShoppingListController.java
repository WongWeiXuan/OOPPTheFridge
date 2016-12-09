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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private JFXButton addStocks;
	@FXML
	private JFXButton addList;
	@FXML 
	private JFXListView<GridPane> StocklistView;
	@FXML 
	private JFXListView<GridPane> ListlistView;
	
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
		
		GridPane GridPaneTitle = new GridPane();
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
		GridPaneTitle.add(lbl1, 0, 0);
		GridPaneTitle.add(lbl2, 1, 0);
		GridPaneTitle.add(lbl3, 2, 0);
		GridPaneTitle.setPadding(new Insets(10, 10, 10, 10));
		StocklistView.getItems().add(GridPaneTitle);
		
		for(String a:sl){
			Scanner in = new Scanner(a);
			int i = 0;
			GridPane gridpane = new GridPane();
			in.useDelimiter("~");
			while(in.hasNext()){
				Label lbl = new Label(in.next());
				gridpane.add(lbl, i, 0);
				if(i == 0){
					lbl.setMinWidth(500);
					lbl.setPrefWidth(500);
					lbl.setAlignment(Pos.CENTER_LEFT);
				}else{
					lbl.setMinWidth(100);
					lbl.setPrefWidth(100);
					lbl.setAlignment(Pos.CENTER);
				}
				i++;
			}
			StocklistView.getItems().add(gridpane);
			in.close();
		}
		//List listView
		ArrayList<String> sl1 = new ArrayList<String>();
		sl1.add("Banana~2");
		sl1.add("Carrot~1");
		
		GridPane GridPaneTitle1 = new GridPane();
		Label lbl11 = new Label("Shopping List");
		lbl11.setMinWidth(200);
		lbl11.setPrefWidth(200);
		lbl11.setAlignment(Pos.CENTER_LEFT);
		Label lbl12 = new Label("Amount");
		lbl12.setMinWidth(100);
		lbl12.setPrefWidth(100);
		lbl12.setAlignment(Pos.CENTER);
		GridPaneTitle1.add(lbl11, 0, 0);
		GridPaneTitle1.add(lbl12, 1, 0);
		GridPaneTitle1.setPadding(new Insets(10, 10, 10, 10));
		ListlistView.getItems().add(GridPaneTitle1);
		
		for(String a:sl1){
			Scanner in = new Scanner(a);
			int i = 0;
			GridPane gridpane = new GridPane();
			in.useDelimiter("~");
			while(in.hasNext()){
				Label lbl = new Label(in.next());
				gridpane.add(lbl, i, 0);
				if(i == 0){
					lbl.setMinWidth(200);
					lbl.setPrefWidth(200);
					lbl.setAlignment(Pos.CENTER_LEFT);
				}else{
					lbl.setMinWidth(100);
					lbl.setPrefWidth(100);
					lbl.setAlignment(Pos.CENTER);
				}
				i++;
			}
			ListlistView.getItems().add(gridpane);
			in.close();
		}
		
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
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