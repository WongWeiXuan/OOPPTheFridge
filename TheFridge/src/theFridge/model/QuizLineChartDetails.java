package theFridge.model;

import java.util.ArrayList;

import theFridge.DAO.QuizLineChartDetailsDAO;

public class QuizLineChartDetails {
	private int q1P1;
	private int q2P2;
	private int q3P3;
	private int q4P4;
	private int q5P5;
	private int q6P6;
	private int q7P7;
	private int q8P8;
	private int q9P9;
	private int q10P10;
	
	public QuizLineChartDetails(int q1p1, int q2p2, int q3p3, int q4p4, int q5p5, int q6p6, int q7p7, int q8p8, int q9p9, int q10p10) {
		super();
		q1P1 = q1p1;
		q2P2 = q2p2;
		q3P3 = q3p3;
		q4P4 = q4p4;
		q5P5 = q5p5;
		q6P6 = q6p6;
		q7P7 = q7p7;
		q8P8 = q8p8;
		q9P9 = q9p9;
		q10P10 = q10p10;
	}

	public int getQ1P1() {
		return q1P1;
	}

	public void setQ1P1(int q1p1) {
		q1P1 = q1p1;
	}

	public int getQ2P2() {
		return q2P2;
	}

	public void setQ2P2(int q2p2) {
		q2P2 = q2p2;
	}

	public int getQ3P3() {
		return q3P3;
	}

	public void setQ3P3(int q3p3) {
		q3P3 = q3p3;
	}

	public int getQ4P4() {
		return q4P4;
	}

	public void setQ4P4(int q4p4) {
		q4P4 = q4p4;
	}

	public int getQ5P5() {
		return q5P5;
	}

	public void setQ5P5(int q5p5) {
		q5P5 = q5p5;
	}

	public int getQ6P6() {
		return q6P6;
	}

	public void setQ6P6(int q6p6) {
		q6P6 = q6p6;
	}

	public int getQ7P7() {
		return q7P7;
	}

	public void setQ7P7(int q7p7) {
		q7P7 = q7p7;
	}

	public int getQ8P8() {
		return q8P8;
	}

	public void setQ8P8(int q8p8) {
		q8P8 = q8p8;
	}

	public int getQ9P9() {
		return q9P9;
	}

	public void setQ9P9(int q9p9) {
		q9P9 = q9p9;
	}

	public int getQ10P10() {
		return q10P10;
	}

	public void setQ10P10(int q10p10) {
		q10P10 = q10p10;
	}
	
	public String toString() {
		return q1P1 + ";" + q2P2 + ";" + q3P3 + ";" + q4P4 + ";" + q5P5 + ";" + q6P6 + ";" + q7P7 + ";" + q8P8 + ";" + q9P9 + ";" + q10P10;
	}
	
	public static ArrayList<QuizLineChartDetails> getAllDetails() {
		QuizLineChartDetailsDAO quizLineChartDetailsDAO = new QuizLineChartDetailsDAO();
		return quizLineChartDetailsDAO.getAllDetails();
	}
	
	public void getDetails() {
		QuizLineChartDetailsDAO quizLineChartDetailsDAO = new QuizLineChartDetailsDAO();
		QuizLineChartDetails lineChartDetails = quizLineChartDetailsDAO.getDetails(q1P1);
		setQ1P1(lineChartDetails.getQ1P1());
		setQ2P2(lineChartDetails.getQ2P2());
		setQ3P3(lineChartDetails.getQ3P3());
		setQ4P4(lineChartDetails.getQ4P4());
		setQ5P5(lineChartDetails.getQ5P5());
		setQ6P6(lineChartDetails.getQ6P6());
		setQ7P7(lineChartDetails.getQ7P7());
		setQ8P8(lineChartDetails.getQ8P8());
		setQ9P9(lineChartDetails.getQ9P9());
		setQ10P10(lineChartDetails.getQ10P10());
	}
	
	public void updateDetails() {
		QuizLineChartDetailsDAO quizLineChartDetailsDAO = new QuizLineChartDetailsDAO();
		quizLineChartDetailsDAO.updateDetails(this);
	}

	public static void main(String[] args) {
		
	}

}
