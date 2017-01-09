package theFridge.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import theFridge.controller.ProfileController;
import theFridge.model.First;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.TextField;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.PasswordField;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
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
	private JFXButton btnSave4;
	@FXML
	private JFXButton btnCancel4;
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
	public void save0(ActionEvent event) {
		String name= NText.getText();
		NText.setText("");
	}
	// Event Listener on JFXButton[#btnCancel0].onAction
	@FXML
	public void cancel0(ActionEvent event) {
		hbox.setVisible(false);
		NText.setText("");
		Ename.setVisible(true);
		hBox0.setVisible(true);
	}
	// Event Listener on JFXButton[#btnSave1].onAction
	@FXML
	public void save1(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton[#btnCancel1].onAction
	@FXML
	public void cancel1(ActionEvent event) {
		hbox2.setVisible(false);
		UNText.setText("");
		Eusername.setVisible(true);
		hBox1.setVisible(true);
	}
	// Event Listener on JFXButton[#btnSave2].onAction
	@FXML
	public void save2(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton[#btnCancel2].onAction
	@FXML
	public void cancel2(ActionEvent event) {
		hbox3.setVisible(false);
		hbox4.setVisible(false);
		hbox5.setVisible(false);
		PassText.setText("");
		Epassword.setVisible(true);
		hBox2.setVisible(true);
	}
	// Event Listener on JFXButton[#btnSave3].onAction
	@FXML
	public void save3(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton[#btnCancel3].onAction
	@FXML
	public void cancel3(ActionEvent event) {
		hbox6.setVisible(false);
		EMText.setText("");
		Eemail.setVisible(true);
		hBox3.setVisible(true);
		
	}
	// Event Listener on JFXButton[#btnSave4].onAction
	@FXML
	public void save4(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton[#btnCancel4].onAction
	@FXML
	public void cancel4(ActionEvent event) {
		hbox7.setVisible(false);
		LText.setText("");
		Elocation.setVisible(true);
		hBox4.setVisible(true);
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
		Ename.setVisible(false);
		hBox0.setVisible(false);
	}
	
	public void edituserName(ActionEvent event){
		hbox2.setVisible(true);
		Eusername.setVisible(false);
		hBox1.setVisible(false);
	}
	
	public void editpassword(ActionEvent event){
		hbox3.setVisible(true);
		hbox4.setVisible(true);
		hbox5.setVisible(true);
		Epassword.setVisible(false);
		hBox2.setVisible(false);
	}
	
	public void editemail(ActionEvent event){
		hbox6.setVisible(true);
		Eemail.setVisible(false);
		hBox3.setVisible(false);
	}
	
	public void editlocation(ActionEvent event){
		hbox7.setVisible(true);
		Elocation.setVisible(false);
		hBox4.setVisible(false);
	}
	
}
