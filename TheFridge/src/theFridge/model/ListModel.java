package theFridge.model;

public class ListModel {
	private String name;
	private double amount;
	private int grams;
	private double maxAmount;
	
	public ListModel(String name, double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	
	public ListModel(String name, int grams) {
		super();
		this.name = name;
		this.grams = grams;
	}
	
	public ListModel(String name, double amount, double maxAmount) {
		super();
		this.name = name;
		this.amount = amount;
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

	public int getGrams() {
		return grams;
	}

	public void setGrams(int grams) {
		this.grams = grams;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}
}
