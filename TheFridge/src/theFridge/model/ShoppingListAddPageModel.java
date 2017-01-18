package theFridge.model;

import java.io.FileNotFoundException;

import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class ShoppingListAddPageModel {
	static ShoppingListModel model;
	
	public ShoppingListAddPageModel() {
		super();
	}

	public void closeAndShow(TextField nameField, Spinner<Integer> amountSpinner) throws FileNotFoundException{
		String name = nameField.getText();
    	int amount = amountSpinner.getValue();
    	StockModel s = new StockModel(name, amount, 1);
    	
		model.addStocks(s);
		model.displayShoppingByStockModel(s);
	}
}
