package theFridge.controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.First;



public class Page1Controller{

    @FXML
    private TableColumn<First, String> table1;
    @FXML
    private TableColumn<First, String> table2;
    @FXML
    private TableView<First> tableView1;
    @FXML
    private Button btnGenerateMultiple;
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
    private JFXComboBox<String> choice;
    @FXML
    private JFXListView<String> listView;
    

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
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		
		
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}

 		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	@FXML
	public void generateTwo(ActionEvent event)throws IOException {
		String f="src/theFridge/file/foodcheck.txt";
		try{
			PrintWriter writer = new PrintWriter(f);
			writer.print("");
			for(int i = 0; i<a.size(); i++){
			writer.print(a.get(i));
			}
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		if(a.size() == 1){
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page2.fxml"));
			stage.setScene(new Scene(root));
	 	    stage.show();
		}
		else if(a.size() == 0){
			System.out.println("Please enter a food");
		}
		else{
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page3.fxml"));
			stage.setScene(new Scene(root));
	 	    stage.show();
		}
	}
	
	
	@FXML
	public void showMultipleFood(ActionEvent event){
		
	}

	public ObservableList<First> list = FXCollections.observableArrayList(
		            new First("Seaweed", "Tea and Coffee Bags"),
		            new First("Rabbit Manure", "Diseased plants"),
		            new First("Coffee Grounds", "Onions"),
		            new First("Mouldy Cheese", "Fish Scraps"),
		            new First("Crab or Lobster Shell", "Meat Scraps"),
		            new First("Fish bones", "Cooking Oil"),
		            new First("Citrus Peel", "Rice"),
		            new First("Apple", "Walnuts"),
		            new First("Old Pasta", "Milk Products")
		            );
	
	
	ObservableList<String> data = FXCollections.observableArrayList("Seaweed","Rabbit Manure","Coffee Grounds","Mouldy Cheese","Crab or Lobster Shell",
			"Fish bones","Citrus Peel","Apple","Old Pasta");
	ObservableList<String> a = FXCollections.observableArrayList();
	
	    public void initialize() {
	        table1.setCellValueFactory(new PropertyValueFactory<First, String>("foodCanCompost"));
	        table2.setCellValueFactory(new PropertyValueFactory<First, String>("foodCannotCompost"));
	        tableView1.setItems(list);
	        choice.setItems(data);  
	        listView.setItems(a);
	        //foodCom.setItems(a);
	        
	    }
	    
	   
	    public void chosen1(ActionEvent event){
	    	String s = choice.getValue().toString();
	    	a.add(s);
	    }
	    
	   
}
