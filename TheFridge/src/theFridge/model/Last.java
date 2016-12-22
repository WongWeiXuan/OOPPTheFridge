package theFridge.model;
import javafx.beans.property.SimpleStringProperty;
public class Last {
	private final SimpleStringProperty foodCombi;

	public Last(String foodCombi) {
		super();
		this.foodCombi = new SimpleStringProperty(foodCombi);
	}
	public String getfoodCombi() {
		return foodCombi.get();
	}
}
