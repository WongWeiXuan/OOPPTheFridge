package theFridge.model;

import java.util.ArrayList;

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
	
	public static void main(String args[]) {
		
	}
}
