package theFridge.model;

public class QuizModel {
	private String questions;
	private String choices;
	private String explanations;
	
	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
	}

	public String getExplanations() {
		return explanations;
	}

	public void setExplanations(String explanations) {
		this.explanations = explanations;
	}

	public QuizModel(String questions, String choices, String explanations) {
		super();
		this.questions = questions;
		this.choices = choices;
		this.explanations = explanations;
	}
	
	

}
