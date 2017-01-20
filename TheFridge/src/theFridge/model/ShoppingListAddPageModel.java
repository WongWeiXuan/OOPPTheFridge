package theFridge.model;

import java.io.IOException;

import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import theFridge.DAO.ShoppingListDAO;

public class ShoppingListAddPageModel {
	static ShoppingListModel model;
	static TextField nameField;
	static Spinner<Double> amountSpinner;
	static int index;
	static boolean edit = false;
	static String source;
	static int serving;
	static double maxAmount;

	public ShoppingListAddPageModel() {
		super();
	}
	
	public ShoppingListAddPageModel(TextField nameFields, Spinner<Double> amountSpinners) {
		super();
		nameField = nameFields;
		amountSpinner = amountSpinners;
	}

	public void closeAndShow() throws IOException{
		String name = nameField.getText();
    	double amount = amountSpinner.getValue();
    	if(source == "Stock"){
        	StockModel s = new StockModel(name, amount, serving, maxAmount);
	    	if(edit == true){
	    		ShoppingListModel.setStocklistViewNode(index, s);
	    		edit = false;
	    	}
	    	else{
				model.addStocks(s);
	    	}
	    	ShoppingListDAO a = new ShoppingListDAO();
    		a.writeToStockFile(ShoppingListModel.getStocklistArray());
    	}
    	else if(source == "List"){
    		ListModel l = new ListModel(name, amount);
    		if(edit == true){
	    		ShoppingListModel.setListlistViewNode(index, l);
	    		edit = false;
    		}
    		else{
				model.addShopping(l);
	    	}
    		ShoppingListDAO a = new ShoppingListDAO();
    		a.writeToListFile(ShoppingListModel.getListlistArray());
    	}
	}
	
	public static void showNameAndAmount(String name, double amount){
		nameField.setText(name);
		amountSpinner.getValueFactory().setValue(amount);
		edit = true;
	}
	
	public static void showNameAndAmount(String name, double amount, int servings, double maxAmounts){
		nameField.setText(name);
		amountSpinner.getValueFactory().setValue(amount);
		serving = servings;
		maxAmount = maxAmounts;
		edit = true;
	}
}
