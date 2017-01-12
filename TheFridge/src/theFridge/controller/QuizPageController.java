package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.QuizQuestionsModel;

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
	private Label question;
	@FXML
	private Label timeLeft;
	@FXML
	private Label timer;
	@FXML
	private Label pointsEarned;
	@FXML
	private Label questionNo;
	
	private ArrayList<QuizQuestionsModel> questionsList;
	private int currIndex = -1;
	
	public void setQuestionList(ArrayList<QuizQuestionsModel> questionsList) {
		if (questionsList != null && questionsList.size() > 0) {
			this.questionsList = questionsList;
			currIndex = 0;
			showQuestion(questionsList.get(0));
		}
	}
	
	public void showQuestion(QuizQuestionsModel questions) {
		question.setText(questions.getQuestions());
	}
	
	@FXML
	public void initialize() throws FileNotFoundException{
		int points = 0;
		timer.setText("10");
		boolean isCorrect = false;
		
		if (isCorrect = true) {
			points += 10;
			pointsEarned.setText("Points earned: " + points);
			//Show popup
		}
		
		else {
			if (isCorrect = false) {
				//Show popup
			}
			
			else if (timer.equals("0") || timer.equals(null)) {
				//Show popup
			}
		}
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

	@FXML public void showUserDropdown(MouseEvent event) {}
}
