package theFridge.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import theFridge.DAO.ProfileDAO;

public class RedeemVoucherModel {
	private User totalPoints;
	private User promoCode;
	private String codeOutput;
	private long in24Hours = TimeUnit.DAYS.toMillis(1);
	private long currentTime = System.currentTimeMillis();
	private static final int VOUCHER_POINTS = 10000;
	private static String voucherType = "";
	
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
		long endTime = currentTime + in24Hours;
		return endTime;
	}

	public int getVoucherPoints() {
		return VOUCHER_POINTS;
	}

	public static String getVoucherType() {
		return voucherType;
	}

	public static void setVoucherType(String voucherType) {
		RedeemVoucherModel.voucherType = voucherType;
	}

	public void generatePromoCode() throws FileNotFoundException {
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
		RedeemVoucherBarcode gBC = new RedeemVoucherBarcode();
		gBC.generateBarcode(getCodeOutput());
	}
	
	public void sendEmail() throws FileNotFoundException {
		File file = new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		
		ProfileDAO profileDAO = new ProfileDAO();
		
		User user = new User();
		user = profileDAO.getUser(n);
		
		RedeemVoucherSendEmail rDVSE = new RedeemVoucherSendEmail();
		Image image = new Image(new FileInputStream("src/theFridge/picture/Barcode.png"));
		rDVSE.sendEmail(user.getEmail(), image);
	}
	
	public void setRedeemAgainDate() throws FileNotFoundException {
		File file = new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		
		ProfileDAO profileDAO = new ProfileDAO();
		User user = new User();
		user = profileDAO.getUser(n);

		user.setEndTime(getEndTime());
		user.updateUser();
	}
	
	public String changeToDateFormat(long endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
		Date endDate = new Date(endTime);
		
		String outputEndDate = sdf.format(endDate);
		
		return outputEndDate;
	}
	
	public void clearRedeemAgainDate() throws FileNotFoundException {
		File file = new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		
		ProfileDAO profileDAO = new ProfileDAO();
		User user = new User();
		user = profileDAO.getUser(n);
		
		user.setEndTime(0);
		user.updateUser();
	}
	
	public void clearPromoCode() throws FileNotFoundException {
		File file = new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		
		ProfileDAO profileDAO = new ProfileDAO();
		User user = new User();
		user = profileDAO.getUser(n);
		
		user.setPromoCode(null);
		user.addPromoCode();
	}
	
	public void disableVoucher(VBox vBox) {
		vBox.setDisable(true);
	}
	
	public void unDisableVoucher(VBox vBox) {
		vBox.setDisable(false);
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		//RedeemVoucherModel rDV = new RedeemVoucherModel();
		//rDV.generatePromoCode();
		//System.out.println(rDV.getCodeOutput());
		
		//rDV.generateBarcode();
		//System.out.println("Barcode generated.");
		
		
	}
}
