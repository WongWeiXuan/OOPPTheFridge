package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.CountdownTimer;
import theFridge.model.QuizChoicesModel;
import theFridge.model.QuizQuestionsModel;
import theFridge.model.QuizTimer;

public class QuizPageController {
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
	private JFXButton choiceBtn1;
	@FXML
	private JFXButton choiceBtn2;
	@FXML
	private JFXButton choiceBtn3;
	@FXML
	private JFXButton choiceBtn4;
	@FXML
	private Label questionLabel;
	@FXML
	private Label timeLeft;
	@FXML
	private Label timerOutput;
	@FXML
	private Label pointsEarned;
	@FXML
	private Label questionNo;
	@FXML
	private ImageView infoImg;
	@FXML
	private ImageView correctImg;
	@FXML
	private ImageView incorrectImg;
	@FXML
	private JFXButton continueBtn;
	
	private ArrayList<QuizQuestionsModel> questionsList;
	private int currIndex = -1;
	private int pointsAttained = 0;
	private static Integer timeSeconds = 10;
	
	public void setQuestionList(ArrayList<QuizQuestionsModel> questionsList) {
		if (questionsList != null && questionsList.size() > 0) {
			this.questionsList = questionsList;
			currIndex = 0;
			showQuestion(questionsList.get(0));
		}
	}
	
	public void showQuestion(QuizQuestionsModel questions) {
		questionLabel.setText(questions.getQuestions());
	}
	
	@FXML
	public void handleNext(ActionEvent event) {	
		if (currIndex >= 0 && currIndex < questionsList.size() - 1){
			currIndex++;
			showQuestion(questionsList.get(currIndex));
		}
	}
	
	@FXML
	public void initialize() throws FileNotFoundException{
		QuizTimer qTimer = new QuizTimer();
		qTimer.setTimer(timerOutput);
		
		QuizQuestionsModel quizQ = new QuizQuestionsModel();
		QuizChoicesModel quizC = new QuizChoicesModel();
		questionLabel.setText(quizQ.getQuestions());
		choiceBtn1.setText(quizC.getChoice1());
		choiceBtn2.setText(quizC.getChoice2());
		choiceBtn3.setText(quizC.getChoice3());
		choiceBtn4.setText(quizC.getChoice4());
		
		questionNo.setText(String.valueOf(currIndex) + "/10");
		pointsEarned.setText("Points earned: " + pointsAttained);
		
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

	@FXML public void showUserDropdown(MouseEvent event) {}
}
