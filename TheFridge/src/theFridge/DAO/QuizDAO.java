package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import p7.model.Friend;
import theFridge.model.QuizChoicesModel;
import theFridge.model.QuizExplanationsModel;
import theFridge.model.QuizQuestionsModel;

public class QuizDAO {
	private static final String QUIZ_FILE="Quiz Questions.txt";
	private File dataFile; 
	
	public QuizDAO() {
		Path dPath = FileSystems.getDefault().getPath("theFridge.file", QUIZ_FILE);
		dataFile=new File(dPath.toString());
	}
	
	public ArrayList<QuizQuestionsModel> getAllQuestions() {
		Scanner in;
		String record=null;
		String[] fields;
		ArrayList<QuizQuestionsModel> questions = new ArrayList<QuizQuestionsModel>>();
		try {
			in=new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				String question = fields[0];
				QuizChoicesModel choices = fields[1];
				QuizExplanationsModel explanation = fields[2];
				QuizQuestionsModel Q=new QuizQuestionsModel(question, choices, explanation);
				questions.add(Q);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return questions;
	}

	public static void main(String[] args) {
		
	}

}
