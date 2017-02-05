package theFridge.model;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import theFridge.DAO.QuizDAO;

public class QuizQuestionsModel {
	private String questions;
	private QuizChoicesModel choices;
	private String explanations;
	private static boolean dontShowAgain = true;
	private static int pointsAttained = 0;
	
	public QuizQuestionsModel() {
		super();
	}

	public QuizQuestionsModel(String questions, QuizChoicesModel choices, String explanations) {
		super();
		this.questions = questions;
		this.choices = choices;
		this.explanations = explanations;
	}
	
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	
	public String getQuestions() {
		return questions;
	}
	
	public void setChoices(QuizChoicesModel choices) {
		this.choices = choices;
	}
	
	public QuizChoicesModel getChoices() {
		return choices;
	}

	public void setExplanations(String explanations) {
		this.explanations = explanations;
	}

	public String getExplanations() {
		return explanations;
	}
	
	public static boolean isDontShowAgain() {
		return dontShowAgain;
	}

	public static void setDontShowAgain(boolean dontShowAgain) {
		QuizQuestionsModel.dontShowAgain = dontShowAgain;
	}

	public static int getPointsAttained() {
		return pointsAttained;
	}

	public static void setPointsAttained(int pointsAttained) {
		QuizQuestionsModel.pointsAttained = pointsAttained;
	}

	@Override
	public String toString() {
		return questions + ";" + choices + ";" + explanations;
	}
	
	public static ArrayList<QuizQuestionsModel> getAllQuestions() {
		QuizDAO quizDAO = new QuizDAO();
		return quizDAO.getAllQuestions();
	}
	
	public void getQuizQuestions() {
		QuizDAO quizDAO = new QuizDAO();
		QuizQuestionsModel q = quizDAO.getQuestions(questions);
		setChoices(q.getChoices());
		setExplanations(q.getExplanations());
	}
	
	public void updateQuestions() {
		QuizDAO quizDAO = new QuizDAO();
		quizDAO.updateQuestions(this);
	}
	
	public void disableButtons(JFXButton btn1, JFXButton btn2, JFXButton btn3, JFXButton btn4) {
		btn1.setDisable(true);
		btn2.setDisable(true);
		btn3.setDisable(true);
		btn4.setDisable(true);
	}
	
	public void unDisableButtons(JFXButton btn1, JFXButton btn2, JFXButton btn3, JFXButton btn4) {
		btn1.setDisable(false);
		btn2.setDisable(false);
		btn3.setDisable(false);
		btn4.setDisable(false);
	}
	
	public void hackTooltipStartTiming(Tooltip tooltip) {
	    try {
	        Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
	        fieldBehavior.setAccessible(true);
	        Object objBehavior = fieldBehavior.get(tooltip);

	        Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
	        fieldTimer.setAccessible(true);
	        Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

	        objTimer.getKeyFrames().clear();
	        objTimer.getKeyFrames().add(new KeyFrame(new Duration(150)));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String args[]) {
		
	}
}
