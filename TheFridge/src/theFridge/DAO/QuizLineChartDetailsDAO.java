package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

import theFridge.model.QuizLineChartDetails;

public class QuizLineChartDetailsDAO {
	private static File dataFile;
	
	public QuizLineChartDetailsDAO() {
		Path dPath = FileSystems.getDefault().getPath("src/theFridge/file/QuizLineChartDetails.txt");
     	dataFile = new File(dPath.toString()); 
	}
	
	public QuizLineChartDetails getAllDetails() {
        Scanner in;
        String record = null;
        String[] fields;
        QuizLineChartDetails lineChartDetailsList = new QuizLineChartDetails();
        try {
            in = new Scanner(dataFile);
            record = in.nextLine();
            fields = record.split(";");
            int q0P0 = Integer.parseInt(fields[0]);
            int q1P1 = Integer.parseInt(fields[1]);
            int q2P2 = Integer.parseInt(fields[2]);
            int q3P3 = Integer.parseInt(fields[3]);
            int q4P4 = Integer.parseInt(fields[4]);
            int q5P5 = Integer.parseInt(fields[5]);
            int q6P6 = Integer.parseInt(fields[6]);
            int q7P7 = Integer.parseInt(fields[7]);
            int q8P8 = Integer.parseInt(fields[8]);
            int q9P9 = Integer.parseInt(fields[9]);
            int q10P10 = Integer.parseInt(fields[10]);
            QuizLineChartDetails qLND = new QuizLineChartDetails(q0P0, q1P1, q2P2, q3P3, q4P4, q5P5, q6P6, q7P7, q8P8, q9P9, q10P10);
            lineChartDetailsList = qLND;
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("No record found!");
            //e.printStackTrace();
        }
        return lineChartDetailsList;
    }
	
	public QuizLineChartDetails getDetails(int q0P0) {
        QuizLineChartDetails lineChartDetailsList = getAllDetails();
        return lineChartDetailsList;
    }
	
	public void updateDetails(QuizLineChartDetails lineChartDetails) {
        synToFile(lineChartDetails);
    }
	
	public boolean createDetails() {
        boolean existing = false;
        QuizLineChartDetails lineChartDetailsList = getAllDetails();
        lineChartDetailsList.setQ0P0(0);
        lineChartDetailsList.setQ1P1(0);
        lineChartDetailsList.setQ2P2(0);
        lineChartDetailsList.setQ3P3(0);
        lineChartDetailsList.setQ4P4(0);
        lineChartDetailsList.setQ5P5(0);
        lineChartDetailsList.setQ6P6(0);
        lineChartDetailsList.setQ7P7(0);
        lineChartDetailsList.setQ8P8(0);
        lineChartDetailsList.setQ9P9(0);
        lineChartDetailsList.setQ10P10(0);
        return !existing;
    }
	
	private static void synToFile(QuizLineChartDetails lineChartDetailsList) {
        if (lineChartDetailsList == null)
            return;
        try {
            FileWriter out = new FileWriter(dataFile);
            String line = lineChartDetailsList.getQ0P0() + ";" + lineChartDetailsList.getQ1P1() + ";" + lineChartDetailsList.getQ2P2() + ";" + lineChartDetailsList.getQ3P3() + ";" + lineChartDetailsList.getQ4P4() + ";" + lineChartDetailsList.getQ5P5() + ";" + lineChartDetailsList.getQ6P6() + ";" + lineChartDetailsList.getQ7P7() + ";" + lineChartDetailsList.getQ8P8() + ";" + lineChartDetailsList.getQ9P9() + ";" + lineChartDetailsList.getQ10P10();
            out.write(line);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
	}

}
