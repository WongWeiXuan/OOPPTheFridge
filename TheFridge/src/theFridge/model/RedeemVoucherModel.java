package theFridge.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.Label;
import theFridge.DAO.ProfileDAO;

public class RedeemVoucherModel {
	private User totalPoints;
	private User promoCode;
	private String codeOutput;
	private long in24Hours = TimeUnit.DAYS.toMillis(1);
	private long currentTime = System.currentTimeMillis();
	private long endTime = currentTime + in24Hours;
	
	public RedeemVoucherModel() {
		super();
	}

	public RedeemVoucherModel(User totalPoints) {
		super();
		this.totalPoints = totalPoints;
	}

	public User getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(User totalPoints) {
		this.totalPoints = totalPoints;
	}

	public User getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(User promoCode) {
		this.promoCode = promoCode;
	}
	
	public String getCodeOutput() {
		return codeOutput;
	}

	public void setCodeOutput(String codeOutput) {
		this.codeOutput = codeOutput;
	}

	public long getIn24Hours() {
		return in24Hours;
	}

	public void setIn24Hours(long in24Hours) {
		this.in24Hours = in24Hours;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public void generatePromoCode() throws FileNotFoundException {
		//Generate promo code
		int codeSize = 12;
		char[] chars = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < codeSize; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		setCodeOutput(output);
	}
	
	public void generateBarcode() {
		//Generate barcode
		RedeemVoucherBarcode gBC = new RedeemVoucherBarcode();
		gBC.generateBarcode(getCodeOutput());
	}
	
	public void sendEmail() throws FileNotFoundException {
		File file = new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		
		ProfileDAO profileDAO = new ProfileDAO();
		
		User user = new User();
		user = profileDAO.getUser(n);
		
		RedeemVoucherSendEmail rDVSE = new RedeemVoucherSendEmail();
		rDVSE.sendEmail(user.getEmail(), codeOutput);
		sc.close();
	}
	
	public void redeemIn24Hours(Label label) throws FileNotFoundException {
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User user = new User();
		user = profileDAO.getUser(n);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
		Date currentDate = new Date(currentTime);
		Date endDate = new Date(endTime);
		
		label.setText(sdf.format(endDate));
		
		user.setCurrentDate(sdf.format(currentDate));
		user.setEndDate(sdf.format(endDate));
		user.updateUser();
	}
	
	public void clearRedeemAgainDate() throws FileNotFoundException {
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User user = new User();
		user = profileDAO.getUser(n);
		
		user.setCurrentDate(null);
		user.setEndDate(null);
		user.updateUser();
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		RedeemVoucherModel rDV = new RedeemVoucherModel();
		rDV.generatePromoCode();
		System.out.println(rDV.getCodeOutput());
		
		rDV.generateBarcode();
		System.out.println("Barcode generated.");
		
		System.out.println(rDV.getCurrentTime());
		System.out.println(rDV.getEndTime());
	}
}
