package theFridge.model;

public class IngredientModel {
	private String name;
	private String unit;
	
	public IngredientModel(String name, String unit) {
		super();
		this.name = name;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
