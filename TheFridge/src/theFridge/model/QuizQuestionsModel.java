package theFridge.model;

import java.util.ArrayList;

import theFridge.DAO.QuizDAO;

public class QuizQuestionsModel {
	private String questions;
	private QuizChoicesModel choices;
	private QuizExplanationsModel explanations;
	
	public QuizQuestionsModel(String questions, QuizChoicesModel choices, QuizExplanationsModel explanations) {
		super();
		this.questions = questions;
		this.choices = choices;
		this.explanations = explanations;
	}
	
	public QuizChoicesModel getChoices() {
		return choices;
	}

	public void setChoices(QuizChoicesModel choices) {
		this.choices = choices;
	}

	public QuizExplanationsModel getExplanations() {
		return explanations;
	}

	public void setExplanations(QuizExplanationsModel explanations) {
		this.explanations = explanations;
	}
	
	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
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
	
	//Create a method that checks whether user selects the correct choice?
}
