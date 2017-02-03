package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.QuizLineChartPastDetails;
import theFridge.model.User;

public class QuizLineChartPastDetailsDAO {
	private static File dataFile;
	
	public QuizLineChartPastDetailsDAO() {
		Path dPath = FileSystems.getDefault().getPath("src/theFridge/file/QuizLineChartPastDetails.txt");
     	dataFile = new File(dPath.toString()); 
	}
	
	public ArrayList<QuizLineChartPastDetails> getAllPastDetails() {
		Scanner in;
		String record = null;
		String[] fields;
		ArrayList<QuizLineChartPastDetails> pastDetailsList = new ArrayList<QuizLineChartPastDetails>();
		try {
			in = new Scanner(dataFile);
			while (in.hasNextLine()) {
				record = in.nextLine();
				fields = record.split(";");
				int endPointsAttained = Integer.parseInt(fields[0]);
				String todayDate = fields[1];
				QuizLineChartPastDetails pastDetails = new QuizLineChartPastDetails(endPointsAttained, todayDate);
				pastDetailsList.add(pastDetails);
			}
			in.close();
		} catch (FileNotFoundException e) {	
			System.out.println("No record found!");
			//e.printStackTrace();
		}
		return pastDetailsList;
	}
	
	public QuizLineChartPastDetails getPastDetails(int endPointsAttained) {
		ArrayList<QuizLineChartPastDetails> pastDetailsList = getAllPastDetails();
		QuizLineChartPastDetails pastDetails = null;
		for (QuizLineChartPastDetails pD : pastDetailsList) {
			if (pD.getEndPointsAttained() == endPointsAttained) {
				pastDetails = pD;
				break;
			}
		}
		return pastDetails;
	}
	
	private static void synToFile(ArrayList<QuizLineChartPastDetails> pastDetailsList) {
		if (pastDetailsList == null)
			return;
		
		try {
			FileWriter out = new FileWriter(dataFile);
			for (QuizLineChartPastDetails pD : pastDetailsList) {
				out.append(pD.toString() + "\n" );
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createPastDetails(QuizLineChartPastDetails pastDetails) {
		boolean existing = false;
		ArrayList<QuizLineChartPastDetails> pastDetailsList = getAllPastDetails();
		for (QuizLineChartPastDetails pD : pastDetailsList) {
			if (pD.getEndPointsAttained() == pastDetails.getEndPointsAttained() && pastDetailsList.size() == 3) {
				pastDetailsList.add(0, pastDetails);
				pastDetailsList.remove(3);
				existing = true;
				break;
			}
		}
		if (!existing) {
			pastDetailsList.add(0 , pastDetails);
			synToFile(pastDetailsList);
		}
		return !existing;
	}
	
	public void updatePastDetails(QuizLineChartPastDetails pastDetails) {
		ArrayList<QuizLineChartPastDetails> pastDetailsList = getAllPastDetails();
		for (int i = 0; i < pastDetailsList.size(); i++) {
			QuizLineChartPastDetails pD = pastDetailsList.get(i);
			if (pD.getEndPointsAttained() == pastDetails.getEndPointsAttained()) {
				pastDetailsList.set(i, pastDetails);
			}
		}
		synToFile(pastDetailsList);
	}

	public static void main(String[] args) {
		
	}

}
