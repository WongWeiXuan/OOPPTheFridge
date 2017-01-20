package theFridge.model;

import java.io.FileNotFoundException;

import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class ShoppingListAddPageModel {
	static ShoppingListModel model;
	TextField nameField;
	Spinner<Double> amountSpinner;

	public ShoppingListAddPageModel() {
		super();
	}
	
	public ShoppingListAddPageModel(TextField nameField, Spinner<Double> amountSpinner) {
		super();
		this.nameField = nameField;
		this.amountSpinner = amountSpinner;
	}

	public void closeAndShow() throws FileNotFoundException{
		String name = nameField.getText();
    	double amount = amountSpinner.getValue();
    	StockModel s = new StockModel(name, amount, 1);
    	
		model.addStocks(s);
		model.displayShoppingByStockModel(s);
	}
	
	public void showNameAndAmount(String name, double amount){
		nameField.setText(name);
		amountSpinner.getValueFactory().setValue(amount);
	}
}
