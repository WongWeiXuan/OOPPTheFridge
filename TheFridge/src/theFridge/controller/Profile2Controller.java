package theFridge.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import theFridge.DAO.ProfileDAO;
import theFridge.controller.ProfileController;
import theFridge.model.First;
import theFridge.model.User;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.PasswordField;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Profile2Controller {
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
	private JFXButton btnProfile1;
	@FXML
	private JFXButton btnProfile2;
	@FXML
	private TextField NText;
	@FXML
	private JFXButton btnSave0;
	@FXML
	private JFXButton btnCancel0;
	@FXML
	private TextField UNText;
	@FXML
	private JFXButton btnSave1;
	@FXML
	private JFXButton btnCancel1;
	@FXML
	private PasswordField PassText;
	@FXML
	private JFXButton btnSave2;
	@FXML
	private JFXButton btnCancel2;
	@FXML
	private TextField EMText;
	@FXML
	private JFXButton btnSave3;
	@FXML
	private JFXButton btnCancel3;
	@FXML
	private TextField LText;
	@FXML
	private TextField AText;
	@FXML
	private TextField HText;
	@FXML
	private TextField WText;
	@FXML
	private JFXButton btnSave4;
	@FXML
	private JFXButton btnSave5;
	@FXML
	private JFXButton btnSave6;
	@FXML
	private JFXButton btnSave7;
	@FXML
	private JFXButton btnCancel4;
	@FXML
	private JFXButton btnCancel5;
	@FXML
	private JFXButton btnCancel6;
	@FXML
	private JFXButton btnCancel7;
	@FXML
	private JFXButton Ename;
	@FXML
	private JFXButton Eusername;
	@FXML
	private JFXButton Epassword;
	@FXML
	private JFXButton Eemail;
	@FXML
	private JFXButton Elocation;
	@FXML
	private JFXButton EAge;
	@FXML
	private JFXButton EWeight;
	@FXML
	private JFXButton EHeight;
	@FXML
	private HBox hbox;
	@FXML
	private HBox hbox2;
	@FXML
	private HBox hbox3;
	@FXML
	private HBox hbox4;
	@FXML
	private HBox hbox5;
	@FXML
	private HBox hbox6;
	@FXML
	private HBox hbox7;
	@FXML
	private HBox hbox8;
	@FXML
	private HBox hbox9;
	@FXML
	private HBox hbox10;
	@FXML
	private VBox box0;
	@FXML
	private HBox hBox0;
	@FXML
	private HBox hBox1;
	@FXML
	private HBox hBox2;
	@FXML
	private HBox hBox3;
	@FXML
	private HBox hBox4;
	@FXML
	private HBox hBox5;
	@FXML
	private HBox hBox6;
	@FXML
	private HBox hBox7;
	@FXML
	private ImageView image0;
	@FXML
	private ImageView image1;
	@FXML
	private ImageView image2;
	@FXML
	private ImageView image3;
	@FXML
	private ImageView image4;
	@FXML
	private ImageView image5;
	@FXML
	private ImageView image6;
	@FXML
	private ImageView image7;
	@FXML
	private Text namE;
	@FXML
	private Text usernamE;
	@FXML
	private Text passworD;
	@FXML
	private Text emaiL;
	@FXML
	private Text locatioN;
	@FXML
	private Text agE;
	@FXML
	private Text weighT;
	@FXML
	private Text heighT;
	@FXML
	private Label label0;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;
	@FXML
	private Label label5;
	@FXML
	private Label label6;
	@FXML
	private Label label7;
	@FXML
	private VBox vbOx;

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
	// Event Listener on Button[#btnProfile2].onAction
	@FXML
	public void goToSettings(ActionEvent event)throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	// Event Listener on JFXButton[#btnSave0].onAction
	@FXML
	public void save0(ActionEvent event) throws FileNotFoundException {
		String name= NText.getText();
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setName(name);
		profileDAO.updateUser(uu);
		NText.setText("");
	}
	// Event Listener on JFXButton[#btnCancel0].onAction
	@FXML
	public void cancel0(ActionEvent event) {
		hbox.setVisible(false);
		NText.setText("");
		Ename.setVisible(true);
		hBox0.setVisible(true);
		namE.setOpacity(1);
		label0.setOpacity(1);
	}
	// Event Listener on JFXButton[#btnSave1].onAction
	@FXML
	public void save1(ActionEvent event) throws FileNotFoundException {
		String username= UNText.getText();
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setUsername(username);
		System.out.println(uu.getUsername());
		profileDAO.updateUser(uu);
		UNText.setText("");
	}
	// Event Listener on JFXButton[#btnCancel1].onAction
	@FXML
	public void cancel1(ActionEvent event) {
		hbox2.setVisible(false);
		UNText.setText("");
		Eusername.setVisible(true);
		hBox1.setVisible(true);
		usernamE.setOpacity(1);
		label1.setOpacity(1);
	}
	// Event Listener on JFXButton[#btnSave2].onAction
	@FXML
	public void save2(ActionEvent event) throws FileNotFoundException {
		String password= PassText.getText();
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setPassword(password);
		profileDAO.updateUser(uu);
		PassText.setText("");
	}
	// Event Listener on JFXButton[#btnCancel2].onAction
	@FXML
	public void cancel2(ActionEvent event) {
		vbOx.setVisible(false);
		PassText.setText("");
		Epassword.setVisible(true);
		hBox2.setVisible(true);
		passworD.setOpacity(1);
		label2.setOpacity(1);
	}
	// Event Listener on JFXButton[#btnSave3].onAction
	@FXML
	public void save3(ActionEvent event) throws FileNotFoundException {
		String email= EMText.getText();
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setEmail(email);
		profileDAO.updateUser(uu);
		EMText.setText("");
	}
	// Event Listener on JFXButton[#btnCancel3].onAction
	@FXML
	public void cancel3(ActionEvent event) {
		hbox6.setVisible(false);
		EMText.setText("");
		Eemail.setVisible(true);
		hBox3.setVisible(true);
		emaiL.setOpacity(1);
		label3.setOpacity(1);
		
	}
	// Event Listener on JFXButton[#btnSave4].onAction
	@FXML
	public void save4(ActionEvent event) throws FileNotFoundException {
		String location= LText.getText();
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setCountry(location);
		profileDAO.updateUser(uu);
		LText.setText("");
	}
	// Event Listener on JFXButton[#btnCancel4].onAction
	@FXML
	public void cancel4(ActionEvent event) {
		hbox7.setVisible(false);
		LText.setText("");
		Elocation.setVisible(true);
		hBox4.setVisible(true);
		locatioN.setOpacity(1);
		label4.setOpacity(1);
	}
	
	public void save5(ActionEvent event) throws FileNotFoundException {
		String Age= AText.getText();
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setAge(Age);
		profileDAO.updateUser(uu);
		AText.setText("");
	}
	@FXML
	public void cancel5(ActionEvent event) {
		hbox8.setVisible(false);
		AText.setText("");
		EAge.setVisible(true);
		hBox5.setVisible(true);
		agE.setOpacity(1);
		label5.setOpacity(1);
	}
	
	public void save6(ActionEvent event) throws FileNotFoundException {
		String height= HText.getText();
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setHeight(height);
		profileDAO.updateUser(uu);
		HText.setText("");
	}
	// Event Listener on JFXButton[#btnCancel4].onAction
	@FXML
	public void cancel6(ActionEvent event) {
		hbox9.setVisible(false);
		HText.setText("");
		EHeight.setVisible(true);
		hBox6.setVisible(true);
		heighT.setOpacity(1);
		label6.setOpacity(1);
	}
	
	public void save7(ActionEvent event) throws FileNotFoundException {
		String weight= WText.getText();
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setWeight(weight);
		profileDAO.updateUser(uu);
		WText.setText("");
	}
	// Event Listener on JFXButton[#btnCancel4].onAction
	@FXML
	public void cancel7(ActionEvent event) {
		hbox10.setVisible(false);
		WText.setText("");
		EWeight.setVisible(true);
		hBox7.setVisible(true);
		weighT.setOpacity(1);
		label7.setOpacity(1);
	}
	@FXML
	public void goToMain(ActionEvent event)throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
}
	public void editname(ActionEvent event){
		hbox.setVisible(true);
		hbox.setStyle("-fx-background-color:  #FCE4EC;");
		namE.setOpacity(0.5);
		label0.setOpacity(0.5);
		Ename.setVisible(false);
		
	}
	
	public void edituserName(ActionEvent event){
		hbox2.setVisible(true);
		hbox2.setStyle("-fx-background-color:  #FCE4EC;");
		usernamE.setOpacity(0.5);
		label1.setOpacity(0.5);
		Eusername.setVisible(false);
		//hBox1.setVisible(false);
	}
	
	public void editpassword(ActionEvent event){
		vbOx.setVisible(true);
		vbOx.setStyle("-fx-background-color:  #FCE4EC;");
		passworD.setOpacity(0.5);
		label2.setOpacity(0.5);
		Epassword.setVisible(false);
		//hBox2.setVisible(false);
	}
	
	public void editemail(ActionEvent event){
		hbox6.setVisible(true);
		hbox6.setStyle("-fx-background-color:  #FCE4EC;");
		emaiL.setOpacity(0.5);
		label3.setOpacity(0.5);
		Eemail.setVisible(false);
		//hBox3.setVisible(false);
	}
	
	public void editlocation(ActionEvent event){
		hbox7.setVisible(true);
		hbox7.setStyle("-fx-background-color:  #FCE4EC;");
		locatioN.setOpacity(0.5);
		label4.setOpacity(0.5);
		Elocation.setVisible(false);
		//hBox4.setVisible(false);
	}
	
	public void editage(ActionEvent event){
		hbox8.setVisible(true);
		hbox8.setStyle("-fx-background-color:  #FCE4EC;");
		agE.setOpacity(0.5);
		label5.setOpacity(0.5);
		EAge.setVisible(false);
	}
	
	public void editheight(ActionEvent event){
		hbox9.setVisible(true);
		hbox9.setStyle("-fx-background-color:  #FCE4EC;");
		heighT.setOpacity(0.5);
		label6.setOpacity(0.5);
		EHeight.setVisible(false);
	}
	
	public void editweight(ActionEvent event){
		hbox10.setVisible(true);
		hbox10.setStyle("-fx-background-color:  #FCE4EC;");
		weighT.setOpacity(0.5);
		EWeight.setOpacity(0.5);
		EWeight.setVisible(false);
		
	}
	
	public void showImage0 (MouseEvent event) {
		image0.setOpacity(1);
	}
	
	public void hideImage0 (MouseEvent event) {
		image0.setOpacity(0);
	}
	
	public void showImage1 (MouseEvent event) {
		image1.setOpacity(1);
	}
	public void hideImage1 (MouseEvent event) {
		image1.setOpacity(0);
	}
	
	public void showImage2 (MouseEvent event) {
		image2.setOpacity(1);
	}
	
	public void hideImage2 (MouseEvent event) {
		image2.setOpacity(0);
	}
	
	public void showImage3 (MouseEvent event) {
		image3.setOpacity(1);
	}
	
	public void hideImage3 (MouseEvent event) {
		image3.setOpacity(0);
	}
	
	public void showImage4 (MouseEvent event) {
		image4.setOpacity(1);
	}
	
	public void hideImage4 (MouseEvent event) {
		image4.setOpacity(0);
	}
	
	public void showImage5 (MouseEvent event) {
		image5.setOpacity(1);
	}
	
	public void hideImage5 (MouseEvent event) {
		image5.setOpacity(0);
	}

	public void showImage6 (MouseEvent event) {
		image6.setOpacity(1);
	}
	
	public void hideImage6 (MouseEvent event) {
		image6.setOpacity(0);
	}

	public void showImage7 (MouseEvent event) {
		image7.setOpacity(1);
	}
	
	public void hideImage7 (MouseEvent event) {
		image7.setOpacity(0);
	}
	
	
}
