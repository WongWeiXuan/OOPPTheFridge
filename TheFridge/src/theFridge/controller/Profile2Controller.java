package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.DAO.ProfileDAO;
import theFridge.DAO.SignupDAO;
import theFridge.model.Encryption;
import theFridge.model.SignupModel;
import theFridge.model.User;

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
	@FXML
	private PasswordField CurrPass;
	@FXML
	private PasswordField RePassText;
	@FXML
	private Label alerting;
	@FXML
	private ImageView face;
	@FXML
	private JFXButton btnProfile3;
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
	@FXML
	private Label label8;
	@FXML
	private JFXButton EGender;
	@FXML
	private ImageView image8;
	@FXML
	private TextField GText;
	@FXML
	private JFXButton btnSave8;
	@FXML
	private JFXButton btnCancel8;
	@FXML
	private HBox hbox11;
	@FXML
	private HBox hBox8;
	
	private boolean open = false;

	public void initialize() throws FileNotFoundException{
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		label0.setText(uu.getName());
		label2.setText(uu.getPassword());
		label3.setText(uu.getEmail());
		label4.setText(uu.getCountry());
		label5.setText(uu.getAge());
		label6.setText(uu.getHeight());
		label7.setText(uu.getWeight());
		label8.setText(uu.getGender());
		String myface = uu.getProfileImage();
		if(myface.equals("null")){
			
		}
		else{
		Image image22 = new Image(myface);
		face.setImage(image22);
		}
	
	
		
	}
	
	public void letsgo(ActionEvent event) throws IOException{
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile3.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
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
	// Event Listener on Button[#btnProfile2].onAction
	@FXML
	public void goToSettings(ActionEvent event)throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	@SuppressWarnings({ "unused", "deprecation" })
	public void changeFace(MouseEvent event) throws IOException{
		String filename = null;
		File f = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("C:/Users"));
		chooser.showOpenDialog(null);
		if(chooser.getSelectedFile() == null){
			System.out.println("nothing");
		}
		else{
			f = chooser.getSelectedFile();
			filename = f.getAbsolutePath();
			Image image = new Image(f.toURL().toString());
			face.setImage(image);
			File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc=new Scanner(file) ;
			String n = sc.nextLine();
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			uu.setProfileImage(f.toURL().toString());
			uu.updateUser();
			sc.close();
		}
		
	}
	@FXML
	public void save0(ActionEvent event) throws IOException {
		String name= NText.getText();
		if(name.equals("")){
			label0.setText("Please enter a value");
			label0.setOpacity(1);
		}
		else{
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setName(name);
		profileDAO.updateUser(uu);
		NText.setText("");
		sc.close();
		hbox.setVisible(false);
		Ename.setVisible(true);
		hBox0.setVisible(true);
		namE.setOpacity(1);
		label0.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
		}
	}
	// Event Listener on JFXButton[#btnCancel0].onAction
	@FXML
	public void cancel0(ActionEvent event) throws IOException {
		hbox.setVisible(false);
		NText.setText("");
		Ename.setVisible(true);
		hBox0.setVisible(true);
		namE.setOpacity(1);
		label0.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	public void save2(ActionEvent event) throws IOException {
		String oldpassword = CurrPass.getText();
		String password= PassText.getText();
		String repassword = RePassText.getText();
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		String oldalready = uu.getPassword();
		Encryption old1 = new Encryption(oldalready, false);
		old1.encryptLine();
		if(oldpassword.equals(old1.getString())){
			if(password.equals(repassword)){
				Encryption new1 = new Encryption(repassword);
				new1.getBinary();
				String newnew = new1.encryptLine();
				uu.setPassword(newnew);
				profileDAO.updateUser(uu);
				SignupDAO u = new SignupDAO();
				SignupModel sg = new SignupModel();
				sg = u.getPerson(n); //Changed from SignupDAO.getPerson(n) to u.getPerson(n)
				sg.setPassword(newnew);
				sg.updatePerson();
				vbOx.setVisible(false);
				
				Epassword.setVisible(true);
				hBox2.setVisible(true);
				passworD.setOpacity(1);
				label2.setOpacity(1);
				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
				root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
				stage.setScene(new Scene(root));
		 	    stage.show();
			}
			else{
				alerting.setText("Password does not match");
				alerting.setVisible(true);
			}
		}
		else{
			alerting.setText("Please check your old password");
			alerting.setVisible(true);
		}
		CurrPass.setText("");
		PassText.setText("");
		RePassText.setText("");
		
	}
	// Event Listener on JFXButton[#btnCancel2].onAction
	@FXML
	public void cancel2(ActionEvent event) throws IOException {
		vbOx.setVisible(false);
		CurrPass.setText("");
		PassText.setText("");
		RePassText.setText("");
		Epassword.setVisible(true);
		hBox2.setVisible(true);
		passworD.setOpacity(1);
		label2.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	// Event Listener on JFXButton[#btnSave3].onAction
	@FXML
	public void save3(ActionEvent event) throws IOException {
		String email= EMText.getText();
		if(email.equals("")){
			label3.setText("Please enter a value");
			label3.setOpacity(1);
		}
		else{
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setEmail(email);
		profileDAO.updateUser(uu);
		SignupDAO u = new SignupDAO();
		SignupModel sg = new SignupModel();
		sg = u.getPerson(n); //Changed from SignupDAO.getPerson(n) to u.getPerson(n)
		sg.setEmail(email);
		sg.updatePerson();
		EMText.setText("");
		hbox6.setVisible(false);
		Eemail.setVisible(true);
		hBox3.setVisible(true);
		emaiL.setOpacity(1);
		label3.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
		}
	}
	// Event Listener on JFXButton[#btnCancel3].onAction
	@FXML
	public void cancel3(ActionEvent event) throws IOException {
		hbox6.setVisible(false);
		EMText.setText("");
		Eemail.setVisible(true);
		hBox3.setVisible(true);
		emaiL.setOpacity(1);
		label3.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
		
	}
	// Event Listener on JFXButton[#btnSave4].onAction
	@FXML
	public void save4(ActionEvent event) throws IOException {
		String location= LText.getText();
		if(location.equals("")){
			label4.setText("Please enter a value");
			label4.setOpacity(1);
		}
		else{
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setCountry(location);
		profileDAO.updateUser(uu);
		LText.setText("");
		hbox7.setVisible(false);
		Elocation.setVisible(true);
		hBox4.setVisible(true);
		locatioN.setOpacity(1);
		label4.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
		}
	}
	// Event Listener on JFXButton[#btnCancel4].onAction
	@FXML
	public void cancel4(ActionEvent event) throws IOException {
		hbox7.setVisible(false);
		LText.setText("");
		Elocation.setVisible(true);
		hBox4.setVisible(true);
		locatioN.setOpacity(1);
		label4.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	public void save5(ActionEvent event) throws IOException {
		String Age= AText.getText();
		if(Age.equals("")){
			label5.setText("Please enter a value");
			label5.setOpacity(1);
		}
		else{
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setAge(Age);
		profileDAO.updateUser(uu);
		AText.setText("");
		hbox8.setVisible(false);
		EAge.setVisible(true);
		hBox5.setVisible(true);
		agE.setOpacity(1);
		label5.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
		}
	}
	@FXML
	public void cancel5(ActionEvent event) throws IOException {
		hbox8.setVisible(false);
		AText.setText("");
		EAge.setVisible(true);
		hBox5.setVisible(true);
		agE.setOpacity(1);
		label5.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	public void save6(ActionEvent event) throws IOException {
		String height= HText.getText();
		if(height.equals("")){
			label6.setText("Please enter a value");
			label6.setOpacity(1);
		}
		else{
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setHeight(height);
		profileDAO.updateUser(uu);
		HText.setText("");
		hbox9.setVisible(false);
		EHeight.setVisible(true);
		hBox6.setVisible(true);
		heighT.setOpacity(1);
		label6.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
		}
	}
	// Event Listener on JFXButton[#btnCancel4].onAction
	@FXML
	public void cancel6(ActionEvent event) throws IOException {
		hbox9.setVisible(false);
		HText.setText("");
		EHeight.setVisible(true);
		hBox6.setVisible(true);
		heighT.setOpacity(1);
		label6.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	public void save7(ActionEvent event) throws IOException {
		String weight= WText.getText();
		if(weight.equals("")){
			label7.setText("Please enter a value");
			label7.setOpacity(1);
		}
		else{
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setWeight(weight);
		profileDAO.updateUser(uu);
		WText.setText("");
		hbox10.setVisible(false);
		EWeight.setVisible(true);
		hBox7.setVisible(true);
		weighT.setOpacity(1);
		label7.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
		}
	}
	@FXML
	public void cancel7(ActionEvent event) throws IOException {
		hbox10.setVisible(false);
		WText.setText("");
		EWeight.setVisible(true);
		hBox7.setVisible(true);
		weighT.setOpacity(1);
		label7.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	public void save8(ActionEvent event) throws IOException {
		String gender= GText.getText();
		if(gender.equals("")){
			label8.setText("Please enter a value");
			label8.setOpacity(1);
		}
		else{
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setGender(gender);
		profileDAO.updateUser(uu);
		GText.setText("");
		hbox11.setVisible(false);
		EGender.setVisible(true);
		hBox8.setVisible(true);
		//weighT.setOpacity(1);
		label8.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
		}
	}
	@FXML
	public void cancel8(ActionEvent event) throws IOException {
		hbox11.setVisible(false);
		GText.setText("");
		EGender.setVisible(true);
		hBox8.setVisible(true);
		//weighT.setOpacity(1);
		label8.setOpacity(1);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
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
		hbox.setStyle("-fx-background-color: rgb(0,0,255)");
		//namE.setOpacity(0.5);
		label0.setOpacity(0.5);
		Ename.setVisible(false);
		
	}
	
	public void editpassword(ActionEvent event){
		vbOx.setVisible(true);
		vbOx.setStyle("-fx-background-color:  rgb(0,255,255)");
		//passworD.setOpacity(0.5);
		label2.setOpacity(0.5);
		Epassword.setVisible(false);
		//hBox2.setVisible(false);
	}
	
	public void editemail(ActionEvent event){
		hbox6.setVisible(true);
		hbox6.setStyle("-fx-background-color: rgb(255,0,0)");
		//emaiL.setOpacity(0.5);
		label3.setOpacity(0.5);
		Eemail.setVisible(false);
		//hBox3.setVisible(false);
	}
	
	public void editlocation(ActionEvent event){
		hbox7.setVisible(true);
		hbox7.setStyle("-fx-background-color:  rgb(255,255,0)");
		//locatioN.setOpacity(0.5);
		label4.setOpacity(0.5);
		Elocation.setVisible(false);
		//hBox4.setVisible(false);
	}
	
	public void editgender(ActionEvent event){
		hbox11.setVisible(true);
		hbox11.setStyle("-fx-background-color:  rgb(255,255,0)");
		label8.setOpacity(0.5);
		EGender.setVisible(false);
	}
	
	public void editage(ActionEvent event){
		hbox8.setVisible(true);
		hbox8.setStyle("-fx-background-color: rgb(128,0,128)");
		//agE.setOpacity(0.5);
		label5.setOpacity(0.5);
		EAge.setVisible(false);
	}
	
	public void editheight(ActionEvent event){
		hbox9.setVisible(true);
		hbox9.setStyle("-fx-background-color:  rgb(0, 255, 0)");
		//heighT.setOpacity(0.5);
		label6.setOpacity(0.5);
		EHeight.setVisible(false);
	}
	
	public void editweight(ActionEvent event){
		hbox10.setVisible(true);
		hbox10.setStyle("-fx-background-color:  rgba(255,165,0)");
		//weighT.setOpacity(0.5);
		label7.setOpacity(0.5);
		EWeight.setVisible(false);
		
	}
	
	public void showImage0 (MouseEvent event) {
		image0.setOpacity(1);
	}
	
	public void hideImage0 (MouseEvent event) {
		image0.setOpacity(0);
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
	
	public void showImage8 (MouseEvent event) {
		image8.setOpacity(1);
	}
	
	public void hideImage8 (MouseEvent event) {
		image8.setOpacity(0);
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
