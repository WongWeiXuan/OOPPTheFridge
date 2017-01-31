package theFridge.model;

public class StockModel {
	private String name;
	private double amount;
	private double serving;
	private int grams;
	private double maxAmount;
	private int index;

	public StockModel(String name, double amount, double serving) {
		super();
		this.name = name;
		this.amount = amount;
		this.serving = serving;
	}

	public StockModel(String name, double amount, double serving, int grams, double maxAmount, boolean forFun) {
		super();
		this.name = name;
		this.amount = amount;
		this.serving = serving;
		this.grams = grams;
		this.maxAmount = maxAmount;
	}

	public StockModel(String name, double amount, double serving, double maxAmount, int index) {
		super();
		this.name = name;
		this.amount = amount;
		this.serving = serving;
		this.maxAmount = maxAmount;
		this.index = index;
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
	
	public double getServing() {
		return serving;
	}

	public void setServing(double serving) {
		this.serving = serving;
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
