package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	private VBox vBoxInfoImg;
	@FXML
	private ImageView correctImg;
	@FXML
	private ImageView incorrectImg;
	@FXML
	private JFXButton continueBtn;
	
	private ArrayList<QuizQuestionsModel> questionsList;
	private int currIndex = -1;
	private Timeline timeline;
	private Integer timeSeconds = 10;
	
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
	public void initialize() throws FileNotFoundException {
		QuizQuestionsModel quizQ = new QuizQuestionsModel();
		QuizChoicesModel quizC = new QuizChoicesModel();
		questionLabel.setText(quizQ.getQuestions());
		System.out.println("Question: " + quizQ.getQuestions());
		choiceBtn1.setText(quizC.getChoice1());
		choiceBtn2.setText(quizC.getChoice2());
		choiceBtn3.setText(quizC.getChoice3());
		choiceBtn4.setText(quizC.getChoice4());
		
		questionNo.setText(String.valueOf(currIndex) + "/10");
		pointsEarned.setText("Points earned: " + QuizQuestionsModel.getPointsAttained());
		
		//For Quiz Timer
		timerOutput.setText("10");
		timeline = new Timeline();
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
	    	@Override
			public void handle(ActionEvent event) {
	    		timeSeconds--;

	    		if (timeSeconds >= 0) {
	    			timerOutput.setText(timeSeconds.toString());
	    			System.out.println(timeSeconds.toString());
	    		}
	    		else {
	    			timeline.stop();
	    			System.out.println("Timer has stopped.");
	    		}
	        }				
	    }));
	    timeline.playFromStart();

		timerOutput.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals("0")) {
					vBoxInfoImg.setVisible(true);
					vBoxInfoImg.setDisable(false);
					continueBtn.setVisible(true);
					continueBtn.setDisable(false);
					timeLeft.setText("Out of time!");
					
					if (QuizQuestionsModel.isDontShowAgain() == true) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								try {
									@SuppressWarnings("rawtypes")
									Dialog dialog = new Dialog();
									Parent root;
									root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizExplanationPopup.fxml"));
									Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
									stage.initStyle(StageStyle.TRANSPARENT);
									Scene scene = new Scene(root);
									stage.setX(650);
									stage.setY(400);
									stage.setScene(scene);
									stage.showAndWait();
								} catch (IOException e) {
									e.printStackTrace();
								}
						}});
					}
				}
			}});
	}
	
	@FXML
	public void showExplanations(MouseEvent event) {
		QuizQuestionsModel.setDontShowAgain(true);
		try {
			@SuppressWarnings("rawtypes")
			Dialog dialog = new Dialog();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizExplanationPopup.fxml"));
			Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root);
			stage.setX(650);
			stage.setY(400);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
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
		if (timeline.getStatus().equals(Status.RUNNING)) {
			timeline.stop();
		}
		
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
