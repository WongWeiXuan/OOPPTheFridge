package theFridge.controller;

import javafx.fxml.FXML;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
//import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;

import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Page2Controller implements Initializable {
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
	private Label chosenFood;
	@FXML
	private MediaView video;
	@FXML
	private MediaPlayer mp;
	@FXML
	private Media me;
	@FXML
	private TextField enterGrams;
	@FXML
	private Button btnGo;
	@FXML
	private Button btnMain;
	@FXML
	private Label steps;

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
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Page2.fxml"));
		
		
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
		mp.stop();
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	// Event Listener on Button[#btnGo].onAction
	@FXML
	public void go(ActionEvent event) {
		// TODO Autogenerated
	}
	public void goMain(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Page2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
 	    mp.stop();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String path = new File("src/theFridge/sound/PPAP.mp4").getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);
		video.setMediaPlayer(mp);
		//DoubleProperty width = video.fitWidthProperty();
		//DoubleProperty height = video.fitHeightProperty();
	}
	public void play(ActionEvent event){
		mp.play();
	}
	public void pause(ActionEvent event){
		mp.pause();
	}
	public void fast(ActionEvent event){
		mp.setRate(2);
	}
	public void slow(ActionEvent event){
		mp.setRate(.5);
	}
	public void restart(ActionEvent event){
		mp.seek(mp.getStartTime());
		mp.play();
	}
	public void stop(ActionEvent event){
		mp.stop();
	}

	
	
	
	
}
