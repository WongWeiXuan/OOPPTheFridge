package theFridge.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JComboBox;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.fxml.Initializable;
import theFridge.model.First;
import theFridge.model.Last;



public class Page1Controller implements Initializable{

    @FXML
    private TableColumn<First, String> table1;
    @FXML
    private TableColumn<First, String> table2;
    @FXML
    private TableView<First> tableView1;
    @FXML
    private TableView<String> foodCom;
    @FXML
    private TableColumn foodCom1;//<Last, String>
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
	public void generateTwo(ActionEvent event)throws IOException {
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page2.fxml"));
			stage.setScene(new Scene(root));
	 	    stage.show();
	}
	
	
	@FXML
	public void showMultipleFood(ActionEvent event){
		
	}

	public ObservableList<First> list = FXCollections.observableArrayList(
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("mushroom", "cheese"),
		            new First("pizza", "pasta")
		            );
	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	        table1.setCellValueFactory(new PropertyValueFactory<First, String>("foodCanCompost"));
	        table2.setCellValueFactory(new PropertyValueFactory<First, String>("foodCannotCompost"));
	        tableView1.setItems(list);
	        choice.setItems(data);  
	        foodCom.setItems(a);
	        
	    }
	    
	    ObservableList<String> data = FXCollections.observableArrayList("Chicken","Fish","Vegetable","Rice","Pasta","tomato","apple","sotongs");
	    ObservableList<String> a = FXCollections.observableArrayList();
	    public void chosen1(ActionEvent event){
	    	String s = choice.getValue().toString();
	    	a.add(s);
	    	
	    	
	    	
	    	//ObservableList<String> a = FXCollections.observableArrayList(s);
	    	//Last p = new Last(s);
	    	//foodCom.setItems(a);
	       // foodCom1.setCellValueFactory(new PropertyValueFactory<Last, String>("foodCombi"));
	    	
	    }
	    
	   
}
