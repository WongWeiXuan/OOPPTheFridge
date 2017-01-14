package theFridge.model;

public class ListModel {
	private String name;
	private int amount;
	
	public ListModel(String name, int amount) {
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
