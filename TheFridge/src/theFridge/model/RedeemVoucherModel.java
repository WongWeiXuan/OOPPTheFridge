package theFridge.model;

public class RedeemVoucherModel {
	private int totalPoints;

	public RedeemVoucherModel(int totalPoints) {
		super();
		this.totalPoints = totalPoints;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
}
