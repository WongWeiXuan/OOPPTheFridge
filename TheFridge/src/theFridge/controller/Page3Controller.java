package theFridge.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.DAO.FoodCompostDAO;
import theFridge.DAO.ProfileDAO;
import theFridge.model.FoodCompost;
import theFridge.model.FoodCompostDatas;
import theFridge.model.User;

public class Page3Controller {
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
	private JFXButton buttonmain;
	@FXML
	private Button Generate;
	@FXML
	private Label steps;
	@FXML
	private HBox boxes;
	@FXML
	private GridPane gp;
	
	private int i = 0;
	@FXML
	private JFXButton saveBtn;
	@FXML
	private JFXTextField textF0;
	@FXML
	private Label labelAlert;
	

	// Event Listener on VBox[#naviPreview].onMouseEntered
	@FXML
	public void showNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	// Event Listener on VBox[#navi].onMouseExited
	@FXML
	public void hideNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), -150);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 1);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	// Event Listener on VBox[#homeScene].onMouseClicked
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
	public void backToMain(ActionEvent event) throws IOException{
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Page3.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	private double total = 0;
	public void initialize() throws FileNotFoundException{
		File file=new File("src/theFridge/file/foodcheck.txt");
		Scanner sc=new Scanner(file) ;
		while(sc.hasNextLine()){
			String n = sc.nextLine();
			FoodCompostDAO f = new FoodCompostDAO();
			FoodCompost c = new FoodCompost();
			c = f.getFoodCompost(n);
			
			
			
			Label a = new Label(c.getFoodName());
			a.setFont(Font.font("Amble CN", FontWeight.BOLD, 18));
			a.setMinWidth(100);
			a.setOnMouseClicked(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent event) {
					String P="src/theFridge/file/video.txt";
					try{
						PrintWriter writer = new PrintWriter(P);
						writer.print("");
						writer.print(a.getText());
						writer.close();
					}
					catch (IOException e){
						e.printStackTrace();
						
					}
					Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page4.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stage.setScene(new Scene(root));
			 	    stage.show();
					System.out.println("Yes finally");
				}
			});
			TextField b = new TextField();
			b.setId(n);
			//b.setMinWidth(90);
			b.setPadding(new Insets(0,5,0,5));
			Label cc = new Label("No Content");
			cc.setFont(Font.font("Amble CN", FontWeight.BOLD, 18));
			cc.setMinWidth(100);
			b.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					String percent = b.getText();
					try{
					double score = Double.parseDouble(percent);
					
					total = total + score;
					double percentage = (score/calPercentage())*100;
					cc.setText(percentage + "%");
					}
					catch(Exception e){
						cc.setText("Please enter a value only");
					}
				}
				});
			gp.add(a, i, 0);
			gp.add(b, i, 1);
			gp.add(cc, i, 2);
			i++;
			
			//System.out.println(n);
		}
	}
	public void showSteps(ActionEvent event) throws IOException{
		
	}
	
	public double calPercentage(){
		return total;
	}
	
	public void saving0(ActionEvent event) throws FileNotFoundException{
		String ss = textF0.getText();
		if(ss.equals("")){
			labelAlert.setVisible(true);
		}
		else{
			
		File file1=new File("src/theFridge/file/confirm.txt");
		Scanner sc1=new Scanner(file1) ;
		String n = sc1.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		
		String ff = uu.getChosenFC();
		if(ff.equals("[]")){
			String newly =( "["+ ss + "]");
			uu.setChosenFC(newly);
			profileDAO.updateUser(uu);
		}
		String replace = ff.replace("[","");
		String replace1 = replace.replace("]","");
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
		myList.add(ss);
		//System.out.println(myList);
		String last = myList.toString();
		uu.setChosenFC(last);
		profileDAO.updateUser(uu);
		
		ArrayList<String> saving = new ArrayList<String>();
		File file9=new File("src/theFridge/file/foodcheck.txt");
		Scanner sc9=new Scanner(file9) ;
		while(sc9.hasNextLine()){
			String nn = sc9.nextLine();
			saving.add(nn);
		}
		String saved = saving.toString();
		FoodCompostDatas fc = new FoodCompostDatas();
		fc.setTitle(ss);
		fc.setFoodType(saved);
		fc.createFoodCompostDatas();
		//fc.setFoodType(foodType);
		
		labelAlert.setVisible(true);
		labelAlert.setText("Successfully saved !");
		textF0.setText("");
		}
		
		
	}
}
