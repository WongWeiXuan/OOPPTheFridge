package theFridge.model;

public class Ingredient {
	private String unit;
	private int amount;
	private String name;
	
	public Ingredient(int amount, String unit, String name) {
		super();
		this.amount = amount;
		this.unit = unit;
		this.name = name;
	}
	
	public void printFood(){
		System.out.println(amount + " " + unit + " " + name +" ");
	}
	
	public String getMeasurement(){
		String line = amount + " " + unit;
		return line;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
