package theFridge.model;

public class StockModel {
	private String name;
	private double amount;
	private int serving;
	private double maxAmount;

	public StockModel(String name, double amount, int serving) {
		super();
		this.name = name;
		this.amount = amount;
		this.serving = serving;
	}

	public StockModel(String name, double amount, int serving, double maxAmount) {
		super();
		this.name = name;
		this.amount = amount;
		this.serving = serving;
		this.maxAmount = maxAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public int getServing() {
		return serving;
	}

	public void setServing(int serving) {
		this.serving = serving;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}
}
