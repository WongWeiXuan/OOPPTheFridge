package theFridge.model;

public class QuizQuestionsModel {
	private String questions;
	private QuizChoicesModel choices;
	private QuizExplanationsModel explanations;
	
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
