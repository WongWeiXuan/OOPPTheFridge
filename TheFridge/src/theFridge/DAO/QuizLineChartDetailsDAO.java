package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.QuizLineChartDetails;
import theFridge.model.User;

public class QuizLineChartDetailsDAO {
	private static File dataFile;
	
	public QuizLineChartDetailsDAO() {
		Path dPath = FileSystems.getDefault().getPath("src/theFridge/file/QuizLineChartDetails.txt");
     	dataFile = new File(dPath.toString()); 
	}
	
	public ArrayList<QuizLineChartDetails> getAllDetails() {
		Scanner in;
		String record = null;
		String[] fields;
		ArrayList<QuizLineChartDetails> lineChartDetailsList = new ArrayList<QuizLineChartDetails>();
		try {
			in = new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				int q1P1 = Integer.parseInt(fields[0]);
				int q2P2 = Integer.parseInt(fields[1]);
				int q3P3 = Integer.parseInt(fields[2]);
				int q4P4 = Integer.parseInt(fields[3]);
				int q5P5 = Integer.parseInt(fields[4]);
				int q6P6 = Integer.parseInt(fields[5]);
				int q7P7 = Integer.parseInt(fields[6]);
				int q8P8 = Integer.parseInt(fields[7]);
				int q9P9 = Integer.parseInt(fields[8]);
				int q10P10 = Integer.parseInt(fields[9]);
				QuizLineChartDetails qLND = new QuizLineChartDetails(q1P1, q2P2, q3P3, q4P4, q5P5, q6P6, q7P7, q8P8, q9P9, q10P10);
				lineChartDetailsList.add(qLND);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return lineChartDetailsList;
	}
	
	public QuizLineChartDetails getDetails(int q1P1) {
		ArrayList<QuizLineChartDetails> lineChartDetailsList = getAllDetails();
		QuizLineChartDetails lineChartDetail = null;
		for (QuizLineChartDetails d : lineChartDetailsList) {
			if (d.getQ1P1() == (q1P1)){
				lineChartDetail = d;
				break;
			}
		}
		return lineChartDetail;
	}
	
	public void updateDetails(QuizLineChartDetails lineChartDetails) {
		ArrayList<QuizLineChartDetails> lineChartDetailsList = getAllDetails();
		for (int i = 0; i < lineChartDetailsList.size(); i++) {
			QuizLineChartDetails d = lineChartDetailsList.get(i);
			if (d.getQ1P1() == lineChartDetails.getQ1P1()) {
				lineChartDetailsList.set(i, lineChartDetails);
			}
		}
		synToFile(lineChartDetailsList);
	}
	
	public boolean createDetails(QuizLineChartDetails lineChartDetails) {
		boolean existing = false;
		ArrayList<QuizLineChartDetails> lineChartDetailsList = getAllDetails();
		for (QuizLineChartDetails d : lineChartDetailsList) {
			if (d.getQ1P1() == (lineChartDetails.getQ1P1())){
				existing = true;
				break;
			}
		}
		if (!existing) {
			lineChartDetailsList.add(lineChartDetails);
			synToFile(lineChartDetailsList);
		}
		return !existing;
	}
	
	public void synToFile(ArrayList<QuizLineChartDetails> lineChartDetailsList) {
		if (lineChartDetailsList == null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (QuizLineChartDetails d : lineChartDetailsList) {
				out.append(d.toString() + "\n" );
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}

}
