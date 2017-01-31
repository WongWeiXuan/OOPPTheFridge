package theFridge.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.DAO.FoodCompostDatasDAO;
import theFridge.DAO.ProfileDAO;
import theFridge.model.First;
import theFridge.model.FoodCompostDatas;
import theFridge.model.User;



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
    private Label alert;
    @FXML
    private JFXButton clear;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXComboBox showFiles;
    @FXML
    private JFXButton showing;
    @FXML
    private JFXButton buton;
    

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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/GetStartedPage.fxml"));
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
	
	@FXML
	public void generateTwo(ActionEvent event)throws IOException {
		String f="src/theFridge/file/foodcheck.txt";
		try{
			PrintWriter writer = new PrintWriter(f);
			writer.print("");
			for(int i = 0; i<a.size(); i++){
			writer.println(a.get(i));
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
			alert.setText("Please Select An Ingredient");
			alert.setFont(Font.font("Amble CN", 22));
			alert.setVisible(true);
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
	
	
	ObservableList<String> data = FXCollections.observableArrayList("Seaweed","Rabbit-Manure","Coffee-Grounds","Mouldy-Cheese","Crab-or-Lobster-Shell",
			"Fish-bones","Citrus-Peel","Apple","Old-Pasta");
	ObservableList<String> a = FXCollections.observableArrayList();
	ObservableList<String> b = FXCollections.observableArrayList();
	ObservableList<String> c = FXCollections.observableArrayList();
	
	    public void initialize() throws FileNotFoundException {
	    	File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc=new Scanner(file) ;
			String n = sc.nextLine();
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			String aa = uu.getChosenFC();
			String replace = aa.replace("[","");
			String replace1 = replace.replace("]","");
			ArrayList<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
			ObservableList<String> cc = FXCollections.observableArrayList(myList);
			for(int i=0; i<cc.size();i++){
				String vv = cc.get(i);
				vv = vv.replaceAll("\\s+", "");
				if(vv.equals("null") || vv.equals("")){
					cc.remove(i);
				}
					
			}
			showFiles.setItems(cc);
			
	        table1.setCellValueFactory(new PropertyValueFactory<First, String>("foodCanCompost"));
	        table2.setCellValueFactory(new PropertyValueFactory<First, String>("foodCannotCompost"));
	        tableView1.setItems(list);
	        tableView1.setMouseTransparent(true);
	        choice.setItems(data); 
	        listView.setItems(a);
	        //foodCom.setItems(a);
	        
	    }
	   public void deleteAll(ActionEvent event){
		   listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
		   //System.out.println("gg");
	   }
	   /*public void deleteOne(MouseEvent event){
		   System.out.println("haha");
	   }
	   */
	    
	   public void clearAll(ActionEvent event){
		   listView.getItems().clear();
	   }
	   
	   public void deleting(ActionEvent event){
		   String de = showFiles.getValue().toString();
		   de = de.replaceAll("\\s+", "");
		   
	   }
	   
	    public void chosen1(ActionEvent event){
	    	String s = choice.getValue().toString();
	    	//a.add(s);
	    	String newItem = "";
	    	c=listView.getItems();
	    	if(c.size() == 0){
	    		a.add(s);
	    	}
	    	else{
	    		for(int i=0; i<c.size(); i++){
	    			if(c.get(i).equals(s) ){
	    				alert.setText("Cannot Duplicate Ingredient");
	    				alert.setFont(Font.font("Amble CN", 22));
	    				alert.setVisible(true);
	    				newItem = "";
	    				break;
	    			}
	    			else{
	    				newItem =s;
	    				alert.setVisible(false);
	    				
	    			}
	    		}
	    		if (!(newItem.equals(""))){
	    		a.add(newItem);
	    		}
	    	}
	    	delete.setOpacity(1);
	    	clear.setOpacity(1);
	    }
	    public void again(ActionEvent event) throws IOException{
	    	String ee = showFiles.getValue().toString();
	    	ee = ee.replaceAll("\\s+", "");
	    	System.out.println(ee);
	    	FoodCompostDatas fc = new FoodCompostDatas();
	    	FoodCompostDatasDAO fcc = new FoodCompostDatasDAO();
	    	fc = fcc.getFoodCompostDatas(ee);
	    	String nope = fc.getFoodType();
	    	String replace = nope.replace("[","");
			String replace1 = replace.replace("]","");
			ArrayList<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
			System.out.println(myList);
			if(myList.size() == 1){
				
				String f="src/theFridge/file/foodcheck.txt";
				try{
					PrintWriter writer = new PrintWriter(f);
					writer.print("");
					for(int i = 0; i<myList.size(); i++){
					writer.println(myList.get(i));
					}
					writer.close();
				}catch (IOException e){
					e.printStackTrace();
				}
				
				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page2.fxml"));
				stage.setScene(new Scene(root));
		 	    stage.show();
			}
			else if (myList.size() > 1){
				
				String f="src/theFridge/file/foodcheck.txt";
				try{
					PrintWriter writer = new PrintWriter(f);
					writer.print("");
					for(int i = 0; i<myList.size(); i++){
					String ice = myList.get(i);
					ice = ice.replaceAll("\\s+", "");
					writer.println(ice);
					}
					writer.close();
				}catch (IOException e){
					e.printStackTrace();
				}
				
				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page3.fxml"));
				stage.setScene(new Scene(root));
		 	    stage.show();
			}
					
	    	
	    }
	    
}
