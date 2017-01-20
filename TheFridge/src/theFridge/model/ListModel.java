package theFridge.model;

public class ListModel {
	private String name;
	private double amount;
	
	public ListModel(String name, double amount) {
		super();
		this.name = name;
		this.amount = amount;
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
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
