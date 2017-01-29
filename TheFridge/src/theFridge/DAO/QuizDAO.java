package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.QuizChoicesModel;
import theFridge.model.QuizQuestionsModel;

public class QuizDAO {
	private static File dataFile; 
	
	public QuizDAO() {
		Path dPath = FileSystems.getDefault().getPath("src/theFridge/file/QuizQuestions.txt");
		dataFile = new File(dPath.toString()); 
	}
	
	public ArrayList<QuizQuestionsModel> getAllQuestions() {
		Scanner in;
		String record = null;
		String[] fields;
		ArrayList<QuizQuestionsModel> questionsList = new ArrayList<QuizQuestionsModel>();
		try {
			in = new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				String question = fields[0];
				String choice1 = fields[1]; 
				String choice2 = fields[2];
				String choice3 = fields[3];
				String choice4 = fields[4];
				int answerOption = Integer.parseInt(fields[5]);
				QuizChoicesModel choices = new QuizChoicesModel(choice1, choice2, choice3, choice4, answerOption);
				String explanation = fields[6];
				QuizQuestionsModel q = new QuizQuestionsModel(question, choices, explanation);
				questionsList.add(q);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return questionsList;
	}
	
	public QuizQuestionsModel getQuestions(String questions) {
		ArrayList<QuizQuestionsModel> questionsList = getAllQuestions();
		QuizQuestionsModel question = null;
		for (QuizQuestionsModel q : questionsList) {
			if (q.getQuestions().equals(question)) {
				question = q;
				break;
			}
		}
		return question;
	}
	
	public void updateQuestions(QuizQuestionsModel question) {
		ArrayList<QuizQuestionsModel> questionsList = getAllQuestions();
		for (int i = 0; i < questionsList.size(); i++) {
			QuizQuestionsModel q = questionsList.get(i);
			if (q.getQuestions().equals(question.getQuestions())) {
				questionsList.set(i, question);
			}
		}
		synToFile(questionsList);
	}
	
	private void synToFile(ArrayList<QuizQuestionsModel> questionsList) {
		if (questionsList == null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (QuizQuestionsModel q : questionsList) {
				out.append(q.toString() + "\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		QuizDAO quizDAO = new QuizDAO();
		ArrayList<QuizQuestionsModel> questionsList = quizDAO.getAllQuestions();
		for (QuizQuestionsModel q : questionsList) {
			System.out.println("Question: " + q.getQuestions());
			System.out.println("Choices: " + q.getChoices());
			System.out.println("Explanation: " + q.getExplanations());
		}
	}

}
