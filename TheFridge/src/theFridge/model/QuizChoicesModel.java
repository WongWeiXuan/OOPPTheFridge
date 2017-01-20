package theFridge.model;

public class QuizChoicesModel {
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private int answerOption;
	
	public QuizChoicesModel(String choice1, String choice2, String choice3, String choice4, int answerOption) {
		super();
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.answerOption = answerOption;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public int getAnswerOption() {
		return answerOption;
	}

	public void setAnswerOption(int answerOption) {
		this.answerOption = answerOption;
	}
}
