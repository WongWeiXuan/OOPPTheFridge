package theFridge.model;

public class StockModel {
	private String name;
	private int amount;
	private int serving;

	public StockModel(String name, int amount, int serving) {
		super();
		this.name = name;
		this.amount = amount;
		this.serving = serving;
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
	public int getServing() {
		return serving;
	}

	public void setServing(int serving) {
		this.serving = serving;
	}
}