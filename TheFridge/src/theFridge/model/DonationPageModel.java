package theFridge.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import theFridge.DAO.DonationPageDAO;

public class DonationPageModel {
	String organizationName;
	int openingHours;
	int closingHours;
	int openingDay;
	int closingDay;
	int secondaryOpeningHours;
	int secondaryClosingHours;
	int secondaryOpeningDay;
	
	public DonationPageModel() {
		super();
	}

	public DonationPageModel(String organizationName, int openingHours, int closingHours, int openingDay,
			int closingDay) {
		super();
		this.organizationName = organizationName;
		this.openingHours = openingHours;
		this.closingHours = closingHours;
		this.openingDay = openingDay;
		this.closingDay = closingDay;
	}

	public DonationPageModel(String organizationName, int openingHours, int closingHours, int openingDay,
			int closingDay, int secondaryOpeningHours, int secondaryClosingHours, int secondaryOpeningDay) {
		super();
		this.organizationName = organizationName;
		this.openingHours = openingHours;
		this.closingHours = closingHours;
		this.openingDay = openingDay;
		this.closingDay = closingDay;
		this.secondaryOpeningHours = secondaryOpeningHours;
		this.secondaryClosingHours = secondaryClosingHours;
		this.secondaryOpeningDay = secondaryOpeningDay;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(int openingHours) {
		this.openingHours = openingHours;
	}

	public int getClosingHours() {
		return closingHours;
	}

	public void setClosingHours(int closingHours) {
		this.closingHours = closingHours;
	}

	public int getOpeningDay() {
		return openingDay;
	}

	public void setOpeningDay(int openingDay) {
		this.openingDay = openingDay;
	}

	public int getClosingDay() {
		return closingDay;
	}

	public void setClosingDay(int closingDay) {
		this.closingDay = closingDay;
	}

	public int getSecondaryOpeningHours() {
		return secondaryOpeningHours;
	}

	public void setSecondaryOpeningHours(int secondaryOpeningHours) {
		this.secondaryOpeningHours = secondaryOpeningHours;
	}

	public int getSecondaryClosingHours() {
		return secondaryClosingHours;
	}

	public void setSecondaryClosingHours(int secondaryClosingHours) {
		this.secondaryClosingHours = secondaryClosingHours;
	}

	public int getSecondaryOpeningDay() {
		return secondaryOpeningDay;
	}

	public void setSecondaryOpeningDay(int secondaryOpeningDay) {
		this.secondaryOpeningDay = secondaryOpeningDay;
	}
	
	public static DonationPageModel getThisModel(String name) throws FileNotFoundException{
		DonationPageDAO dao = new DonationPageDAO();
		ArrayList<DonationPageModel>aldpm = dao.getAllOperationTimes();
		for(DonationPageModel a:aldpm){
			if(a.getOrganizationName().equalsIgnoreCase(name)){
				return a;
			}
		}
		return null;
	}
	
	public static void submitForm(){
		
	}
}
