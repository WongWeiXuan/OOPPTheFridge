package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import theFridge.model.QuizChoicesModel;
import theFridge.model.QuizLineChartDetails;
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
	private int pageNo = 1;
	private Timeline timeline;
	private Integer timeSeconds = 10;
	
	public void showQuestion(QuizQuestionsModel questions) {
		questionLabel.setText(questions.getQuestions());
		QuizChoicesModel quizC = questions.getChoices();

		choiceBtn1.setText(quizC.getChoice1());
		choiceBtn2.setText(quizC.getChoice2());
		choiceBtn3.setText(quizC.getChoice3());
		choiceBtn4.setText(quizC.getChoice4());
		
		QuizExplanationPopupController.explanationText = questions.getExplanations();
	}
	
	@FXML
	public void handleNext(ActionEvent event) throws IOException {	
		QuizQuestionsModel quizQ = new QuizQuestionsModel();
		pageNo++;
		
		if (currIndex >= 0 && currIndex < questionsList.size() - 1){
			currIndex++;
			showQuestion(questionsList.get(currIndex));
			timeLeft.setText("Time left:");
			timerOutput.setVisible(true);
			correctImg.setVisible(false);
			incorrectImg.setVisible(false);
			vBoxInfoImg.setVisible(false);
			vBoxInfoImg.setDisable(true);
			continueBtn.setVisible(false);
			continueBtn.setDisable(true);
			choiceBtn1.setStyle("-fx-background-color: #5CF1F1");
			choiceBtn2.setStyle("-fx-background-color: #5CF1F1");
			choiceBtn3.setStyle("-fx-background-color: #5CF1F1");
			choiceBtn4.setStyle("-fx-background-color: #5CF1F1");
			quizQ.unDisableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
			
			questionNo.setText(String.valueOf(pageNo) + "/10");
			pointsEarned.setText("Points earned: " + QuizQuestionsModel.getPointsAttained());
			
			//For Quiz Timer
			timeSeconds = 10;
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
		    			vBoxInfoImg.setVisible(true);
						vBoxInfoImg.setDisable(false);
						continueBtn.setVisible(true);
						continueBtn.setDisable(false);
		    			System.out.println("Timer has stopped.");
		    		}
		        }				
		    }));
		    timeline.playFromStart();
		}
		else {
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizEndPage.fxml"));
			stage.setScene(new Scene(root));
	 	    stage.show();
		}
	}

	@FXML
	public void checkChoice1Btn(ActionEvent event) {
		QuizQuestionsModel quizQ = questionsList.get(currIndex);
		int ans = quizQ.getChoices().getAnswerOption();
		
		QuizLineChartDetails qLCD = new QuizLineChartDetails();
		
		if (ans == 1){
			timeline.stop();
			QuizQuestionsModel.setPointsAttained(QuizQuestionsModel.getPointsAttained() + 10);
			pointsEarned.setText("Points earned: " + String.valueOf(QuizQuestionsModel.getPointsAttained()));
			timeLeft.setText("Correct answer!");
			timerOutput.setVisible(false);
			correctImg.setVisible(true);
			vBoxInfoImg.setVisible(true);
			vBoxInfoImg.setDisable(false);
			continueBtn.setVisible(true);
			continueBtn.setDisable(false);
			choiceBtn1.setStyle("-fx-background-color: #4CAF50");
			quizQ.disableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
			qLCD.addPoints(pageNo);
			
			if (QuizQuestionsModel.isDontShowAgain() == true) {
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
		}
		else {
			timeline.stop();
			timeLeft.setText("Incorrect answer!");
			timerOutput.setVisible(false);
			incorrectImg.setVisible(true);
			vBoxInfoImg.setVisible(true);
			vBoxInfoImg.setDisable(false);
			continueBtn.setVisible(true);
			continueBtn.setDisable(false);
			choiceBtn1.setStyle("-fx-background-color: #E91E63");
			quizQ.disableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
			
			if (QuizQuestionsModel.isDontShowAgain() == true) {
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
		}
	}
	
	@FXML
	public void checkChoice2Btn(ActionEvent event) {
		QuizQuestionsModel quizQ = questionsList.get(currIndex);
		int ans = quizQ.getChoices().getAnswerOption();
		
		QuizLineChartDetails qLCD = new QuizLineChartDetails();
		
		if (ans == 2){
			timeline.stop();
			QuizQuestionsModel.setPointsAttained(QuizQuestionsModel.getPointsAttained() + 10);
			pointsEarned.setText("Points earned: " + String.valueOf(QuizQuestionsModel.getPointsAttained()));
			timeLeft.setText("Correct answer!");
			timerOutput.setVisible(false);
			correctImg.setVisible(true);
			vBoxInfoImg.setVisible(true);
			vBoxInfoImg.setDisable(false);
			continueBtn.setVisible(true);
			continueBtn.setDisable(false);
			choiceBtn2.setStyle("-fx-background-color: #4CAF50");
			quizQ.disableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
			qLCD.addPoints(pageNo);
			
			if (QuizQuestionsModel.isDontShowAgain() == true) {
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
		}
		else {
			timeline.stop();
			timeLeft.setText("Incorrect answer!");
			timerOutput.setVisible(false);
			incorrectImg.setVisible(true);
			vBoxInfoImg.setVisible(true);
			vBoxInfoImg.setDisable(false);
			continueBtn.setVisible(true);
			continueBtn.setDisable(false);
			choiceBtn2.setStyle("-fx-background-color: #E91E63");
			quizQ.disableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
			
			if (QuizQuestionsModel.isDontShowAgain() == true) {
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
		}
	}
	
	@FXML
	public void checkChoice3Btn(ActionEvent event) {
		QuizQuestionsModel quizQ = questionsList.get(currIndex);
		int ans = quizQ.getChoices().getAnswerOption();
		
		QuizLineChartDetails qLCD = new QuizLineChartDetails();
		
		if (ans == 3){
			timeline.stop();
			QuizQuestionsModel.setPointsAttained(QuizQuestionsModel.getPointsAttained() + 10);
			pointsEarned.setText("Points earned: " + String.valueOf(QuizQuestionsModel.getPointsAttained()));
			timeLeft.setText("Correct answer!");
			timerOutput.setVisible(false);
			correctImg.setVisible(true);
			vBoxInfoImg.setVisible(true);
			vBoxInfoImg.setDisable(false);
			continueBtn.setVisible(true);
			continueBtn.setDisable(false);
			choiceBtn3.setStyle("-fx-background-color: #4CAF50");
			quizQ.disableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
			qLCD.addPoints(pageNo);
			
			if (QuizQuestionsModel.isDontShowAgain() == true) {
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
		}
		else {
			timeline.stop();
			timeLeft.setText("Incorrect answer!");
			timerOutput.setVisible(false);
			incorrectImg.setVisible(true);
			vBoxInfoImg.setVisible(true);
			vBoxInfoImg.setDisable(false);
			continueBtn.setVisible(true);
			continueBtn.setDisable(false);
			choiceBtn3.setStyle("-fx-background-color: #E91E63");
			quizQ.disableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
			
			if (QuizQuestionsModel.isDontShowAgain() == true) {
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
		}
	}
	
	@FXML
	public void checkChoice4Btn(ActionEvent event) {
		QuizQuestionsModel quizQ = questionsList.get(currIndex);
		int ans = quizQ.getChoices().getAnswerOption();
		
		QuizLineChartDetails qLCD = new QuizLineChartDetails();
		
		if (ans == 4){
			timeline.stop();
			QuizQuestionsModel.setPointsAttained(QuizQuestionsModel.getPointsAttained() + 10);
			pointsEarned.setText("Points earned: " + String.valueOf(QuizQuestionsModel.getPointsAttained()));
			timeLeft.setText("Correct answer!");
			timerOutput.setVisible(false);
			correctImg.setVisible(true);
			vBoxInfoImg.setVisible(true);
			vBoxInfoImg.setDisable(false);
			continueBtn.setVisible(true);
			continueBtn.setDisable(false);
			choiceBtn4.setStyle("-fx-background-color: #4CAF50");
			quizQ.disableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
			qLCD.addPoints(pageNo);
			
			if (QuizQuestionsModel.isDontShowAgain() == true) {
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
		}
		else {
			timeline.stop();
			timeLeft.setText("Incorrect answer!");
			timerOutput.setVisible(false);
			incorrectImg.setVisible(true);
			vBoxInfoImg.setVisible(true);
			vBoxInfoImg.setDisable(false);
			continueBtn.setVisible(true);
			continueBtn.setDisable(false);
			choiceBtn4.setStyle("-fx-background-color: #E91E63");
			quizQ.disableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
			
			if (QuizQuestionsModel.isDontShowAgain() == true) {
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
		}
	}
	
	@FXML
	public void initialize() throws FileNotFoundException {
		QuizQuestionsModel quizQ = new QuizQuestionsModel();
		questionsList = quizQ.getAllQuestions();
		
		QuizQuestionsModel.setPointsAttained(0);
		
		if (questionsList != null && questionsList.size() > 0) {
			currIndex = 0;
			showQuestion(questionsList.get(0));
		}
		
		questionNo.setText(String.valueOf(pageNo) + "/10");
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
					quizQ.disableButtons(choiceBtn1, choiceBtn2, choiceBtn3, choiceBtn4);
					
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
