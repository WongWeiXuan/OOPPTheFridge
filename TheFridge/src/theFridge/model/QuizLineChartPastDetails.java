package theFridge.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuizLineChartPastDetails {
	private int endPointsAttained;
	private String todayDate;
	
	public QuizLineChartPastDetails() {
		super();
	}

	public QuizLineChartPastDetails(int endPointsAttained, String todayDate) {
		super();
		this.endPointsAttained = endPointsAttained;
		this.todayDate = todayDate;
	}

	public int getEndPointsAttained() {
		return endPointsAttained;
	}

	public void setEndPointsAttained(int endPointsAttained) {
		this.endPointsAttained = endPointsAttained;
	}

	public String getTodayDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String todayDate = sdf.format(new Date());
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public static void main(String[] args) {
		
	}

}
