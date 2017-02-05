package theFridge.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
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
    private JFXComboBox<String> showFiles;
    @FXML
    private JFXButton showing;
    @FXML
    private JFXButton buton;
    @FXML
	private StackPane dropdownMenu;
	@FXML
	private VBox dropdownBackground;
	@FXML
	private VBox dropdownWord;
	@FXML
	private VBox ProfileMenu;
	@FXML
	private VBox SettingMenu;
	@FXML
	private VBox LogoutMenu;
	@FXML
	private Circle profileCircle;
	
	private boolean open = false;
    

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
	
	
	ObservableList<String> data = FXCollections.observableArrayList("Seaweed","Spoiled-soy","Rabbit-Manure","Cooked-rice","Coffee-Grounds","Tofu","Mouldy-Cheese","Stale-pretzels","Crab-or-Lobster-Shell",
			"Pizza-crusts","Fish-bones","Peanut-shells","Citrus-Peel","Old-jam","Apple","Cupcake-or-Muffin","Old-Pasta","Egg-Shell");
	ObservableList<String> a = FXCollections.observableArrayList();
	ObservableList<String> b = FXCollections.observableArrayList();
	ObservableList<String> c = FXCollections.observableArrayList();
	
	    public void initialize() throws FileNotFoundException {
	    	User user = new User();
			user = user.getCurrentUser();
	        String myface = user.getProfileImage();
			String gf = "/theFridge/picture/head.png";
		        if (myface.equals("null")) {
		            Image dd = new Image(gf);
		            profileCircle.setFill(new ImagePattern(dd));
		        }
		        else {
		            Image image21 = new Image(myface);
		            profileCircle.setFill(new ImagePattern(image21));
		        }
		        
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
	       // tableView1.setMouseTransparent(true);
	        choice.setItems(data); 
	        listView.setItems(a);
	        //foodCom.setItems(a);
	        sc.close();
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
	   
	   public void deleting(ActionEvent event) throws IOException{
		   String de = showFiles.getValue().toString();
		   de = de.replaceAll("\\s+", "");
		   
		   File file1=new File("src/theFridge/file/confirm.txt");
		   Scanner sc1=new Scanner(file1) ;
		   String n = sc1.nextLine();
		   ProfileDAO profileDAO = new ProfileDAO();
		   User uu = new User();
		   uu = profileDAO.getUser(n);
		   String ff = uu.getChosenFC();
		   String replace = ff.replace("[","");
		   String replace1 = replace.replace("]","");
		   ArrayList<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
		   for(int i=0; i<myList.size(); i++){
			   String oo = myList.get(i);
			   oo = oo.replaceAll("\\s+", "");
			   if(oo.equals(de)){
				   myList.remove(i);
			   }
		   }
		   String newly = myList.toString();
		   System.out.println(newly);
		   uu.setChosenFC(newly);
		   profileDAO.updateUser(uu);
		   
		   FoodCompostDatas fg = new FoodCompostDatas();
		   FoodCompostDatasDAO lol = new FoodCompostDatasDAO();
		   fg = lol.getFoodCompostDatas(de);
		   fg.deleteFoodCompost();
		   fg.updateFoodCompostDatas();
		   
		   Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
			stage.setScene(new Scene(root));
	 	    stage.show();
		   
		   sc1.close();
		   
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
			//System.out.println(myList);
			if(myList.size() == 1){
				
				String f="src/theFridge/file/foodcheck.txt";
				try{
					PrintWriter writer = new PrintWriter(f);
					writer.print("");
					for(int i = 0; i<myList.size(); i++){
					writer.println(myList.get(i));
					writer.println("savedone");
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
					writer.println("savedone");
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
	    
	    public void menuChangeScene(MouseEvent event) throws IOException {
	    	User user = new User();
			user = user.getCurrentUser();
			
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = null; //(Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
			
			
			if(event.getSource().equals(ProfileMenu)){
				root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
			}
			else if(event.getSource().equals(SettingMenu)){
				root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
			}
			else if(event.getSource().equals(LogoutMenu)){
				user.setRememberMe(false);
				user.updateUser();
				
				stage.setX(450);
				stage.setY(128);
				stage.setWidth(1020);
				stage.setHeight(650);
				
				root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
				stage.setMaximized(false);
			}
			stage.setScene(new Scene(root));
	 	    stage.show();
		}
		// Event Listener on Circle[#profileCircle].onMouseClicked
		@FXML
		public void showUserDropdown(MouseEvent event) {
			RotateTransition rt = new RotateTransition(Duration.millis(400), profileCircle);
			if(open == false){
				Timeline timeline = new Timeline();
				rt.setByAngle(-360);
				rt.play();
				KeyValue CircleLeft = new KeyValue(profileCircle.translateXProperty(), -46);
				KeyValue MenuHalf = new KeyValue(dropdownMenu.translateXProperty(), -150); //Move dropdownMenu(dropdownWord & dropdownBackground) translateX to -100
				KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200), MenuHalf, CircleLeft); //1st KeyFrame with duration of 300ms
				//Wait
				KeyFrame WaitingFrame1 = new KeyFrame(
						Duration.millis(200), 
						first -> {
									Timeline timeline3 = new Timeline();
									KeyValue MenuDown = new KeyValue(dropdownMenu.translateYProperty(), 255); //Move dropdownMenu(dropdownWord & dropdownBackground) translateY to 135
									KeyValue ShowWord = new KeyValue(dropdownWord.opacityProperty(), 1);
									KeyFrame keyFrame3 = new KeyFrame(Duration.millis(200), MenuDown, ShowWord); //1st KeyFrame with duration of 300ms
									
									timeline3.getKeyFrames().addAll(keyFrame3);
									timeline3.play();
						});
							
				open = true;
				timeline.getKeyFrames().addAll(keyFrame1, WaitingFrame1);
				timeline.play();
			}
			else if(open == true){
				Timeline timeline = new Timeline();
				rt.setByAngle(360);
				rt.play();
				KeyValue MenuDown = new KeyValue(dropdownMenu.translateYProperty(), 0); //Move dropdownMenu(dropdownWord & dropdownBackground) translateY to 135
				KeyValue HideWord = new KeyValue(dropdownWord.opacityProperty(), 0);
				KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200), MenuDown, HideWord); //1st KeyFrame with duration of 300ms
				//Wait
				KeyFrame WaitingFrame1 = new KeyFrame(
						Duration.millis(200), 
						first -> {
									Timeline timeline3 = new Timeline();
									KeyValue CircleRight = new KeyValue(profileCircle.translateXProperty(), 0);
									KeyValue MenuHalf = new KeyValue(dropdownMenu.translateXProperty(), 0); //Move dropdownMenu(dropdownWord & dropdownBackground) translateX to -100
									KeyFrame keyFrame3 = new KeyFrame(Duration.millis(200), MenuHalf, CircleRight); //1st KeyFrame with duration of 300ms
									
									timeline3.getKeyFrames().addAll(keyFrame3);
									timeline3.play();
						});
							
				open = false;
				timeline.getKeyFrames().addAll(keyFrame1, WaitingFrame1);
				timeline.play();
			}
		}
	    
}
