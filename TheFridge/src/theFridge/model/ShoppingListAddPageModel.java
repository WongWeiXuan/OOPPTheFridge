package theFridge.model;

import java.io.FileNotFoundException;

import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class ShoppingListAddPageModel {
	static ShoppingListModel model;
	static TextField nameField;
	static Spinner<Double> amountSpinner;
	static int index;
	static boolean edit = false;
	static String source = "";

	public ShoppingListAddPageModel() {
		super();
	}
	
	public ShoppingListAddPageModel(TextField nameFields, Spinner<Double> amountSpinners) {
		super();
		nameField = nameFields;
		amountSpinner = amountSpinners;
	}

	public void closeAndShow() throws FileNotFoundException{
		String name = nameField.getText();
    	double amount = amountSpinner.getValue();
    	StockModel s = new StockModel(name, amount, 1);
    	if(source == "Stock"){
	    	if(edit == true){
	    		ShoppingListModel.setStocklistViewNode(index, s);
	    		edit = false;
	    	}
	    	else{
				model.addStocks(s);
	    	}
    	}
    	else if(source == "List"){
    		if(edit == true){
	    		ShoppingListModel.setListlistViewNode(index, s);
    		}
    		else{
				model.addShopping(s);
				model.displayShoppingByStockModel(s);
	    	}
    	}
	}
	
	public static void showNameAndAmount(String name, double amount){
		nameField.setText(name);
		amountSpinner.getValueFactory().setValue(amount);
		edit = true;
	}
}
