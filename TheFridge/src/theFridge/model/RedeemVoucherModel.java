package theFridge.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.control.Label;
import theFridge.DAO.ProfileDAO;

public class RedeemVoucherModel {
	private User totalPoints;
	private User promoCode;
	private String codeOutput;
	//private long in24Hours = TimeUnit.DAYS.toMillis(1);
	//private long currentTime = System.currentTimeMillis();
	//private long endTime = currentTime + in24Hours;
	private User in24Hours;
	private User currentTime;
	private User endTime;
	private static final int VOUCHER_POINTS = 10000;
	
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

	public User getIn24Hours() {
		return in24Hours;
	}

	public void setIn24Hours(User in24Hours) {
		this.in24Hours = in24Hours;
	}

	public User getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(User currentTime) {
		this.currentTime = currentTime;
	}

	public User getEndTime() {
		return endTime;
	}

	public void setEndTime(User endTime) {
		this.endTime = endTime;
	}

	public int getVoucherPoints() {
		return VOUCHER_POINTS;
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
		sc.close();
		
		ProfileDAO profileDAO = new ProfileDAO();
		User user = new User();
		user = profileDAO.getUser(n);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
		Date endDate = new Date(user.getEndTime());
		
		label.setText(sdf.format(endDate));
		
		user.setEndTime(user.getEndTime());
		user.updateUser();
	}
	
	public void clearRedeemAgainDate() throws FileNotFoundException {
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		
		ProfileDAO profileDAO = new ProfileDAO();
		User user = new User();
		user = profileDAO.getUser(n);
		
		user.setEndTime(0);
		user.updateUser();
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		RedeemVoucherModel rDV = new RedeemVoucherModel();
		rDV.generatePromoCode();
		System.out.println(rDV.getCodeOutput());
		
		rDV.generateBarcode();
		System.out.println("Barcode generated.");
		
		User user = new User();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
		Date currentDate = new Date(user.getCurrentTime());
		Date endDate = new Date(user.getEndTime());
		
		System.out.println(sdf.format(currentDate));
		System.out.println(sdf.format(endDate));
	}
}
