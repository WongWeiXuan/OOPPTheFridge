package theFridge.model;

import java.util.Random;

public class RedeemVoucherModel {
	private User totalPoints;
	private User promoCode;
	private String codeOutput;
	
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

	public void generatePromoCode() {
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
		gBC.generateBarcode(codeOutput);
	}
	
	public static void main(String args[]) {
		RedeemVoucherModel rDV = new RedeemVoucherModel();
		rDV.generatePromoCode();
		System.out.println(rDV.getCodeOutput());
		
		rDV.generateBarcode();
		System.out.println("Barcode generated.");
	}
}
