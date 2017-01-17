package theFridge.model;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;

public class RedeemVoucherModel {
	private User totalPoints;
	private User promoCode;
	
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
	
	public void generateCode(ActionEvent event) {
		//Generate promo code
	}
	
	public void generateBarcode() {
		
	}
}
