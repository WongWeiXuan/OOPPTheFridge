package theFridge.model;

public class QuizQuestionsModel {
	private String questions;
	private String explanations;
	
	public QuizQuestionsModel(String questions) {
		super();
		this.questions = questions;
	}
	
	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}
}
