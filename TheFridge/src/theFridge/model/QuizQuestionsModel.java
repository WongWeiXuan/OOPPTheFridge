package theFridge.model;

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
}
