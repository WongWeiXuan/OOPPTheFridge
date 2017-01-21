package theFridge.model;

import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import theFridge.DAO.ShoppingListDAO;

public class ShoppingListAddPageModel {
	public static ShoppingListModel model;
	static TextField nameField;
	static Spinner<Double> amountSpinner;
	static int index;
	static boolean edit = false;
	static String source;
	static int serving;
	static double maxAmount;
	static ListModel listModel;
	static StockModel stockModel;

	public ShoppingListAddPageModel() {
		super();
	}
	
	public ShoppingListAddPageModel(TextField nameFields, Spinner<Double> amountSpinners) {
		super();
		nameField = nameFields;
		amountSpinner = amountSpinners;
	}

	public boolean closeAndShow() throws IOException{
		String name = nameField.getText();
    	double amount = amountSpinner.getValue();
    	
    	if(source == "Stock"){
		    StockModel s = stockModel;
		    s.setName(name);
		    s.setAmount(amount);
	    	if(edit == true){
	    		ShoppingListModel.setStocklistViewNode(index, s);
	    		edit = false;
	    	}
	    	else{
				model.addStocks(s);
				model.addShopping(new ListModel(s.getName(), s.getAmount(), s.getMaxAmount()));
	    	}
	    	ShoppingListDAO a = new ShoppingListDAO();
    		a.writeToStockFile(ShoppingListModel.getStocklistArray());
    		a.writeToListFile(ShoppingListModel.getListlistArray());
    		return true;
    	}
    	else if(source == "List"){
    		ListModel l = listModel;
    		l.setName(name);
    		l.setAmount(amount);
    		if(edit == true){
    			if(amount > maxAmount - stockModel.getAmount()){
    				//Alert Box
    	    		Alert alert = new Alert(AlertType.CONFIRMATION);
    	    		alert.setTitle("Confirmation Dialog");
    	    		alert.setHeaderText("You have entered an amount higher than the maximum");
    	    		alert.setContentText("Are you sure you want to continue?");
    	
    	    		Optional<ButtonType> result = alert.showAndWait();
    	    		if (result.get() == ButtonType.OK){
			    		ShoppingListModel.setListlistViewNode(index, l, true);
			    		edit = false;
    	    		}
    	    		else{
        				return false;
        			}
    	    		//--------
    			}
    			else{
    				ShoppingListModel.setListlistViewNode(index, l);
		    		edit = false;
    				return true;
    			}
    		}
    		else{
    			//Get servings and calculate maxAmount
				model.addStocks(new StockModel(l.getName(), 0, 1, 0));
				model.addShopping(l);
	    	}
    		ShoppingListDAO a = new ShoppingListDAO();
    		a.writeToStockFile(ShoppingListModel.getStocklistArray());
    		a.writeToListFile(ShoppingListModel.getListlistArray());
    		return true;
    	}
    	else{
    		return true;
    	}
	}
	
	public static void showNameAndAmount(ListModel lm){
		listModel = lm;
		nameField.setText(lm.getName());
		amountSpinner.getValueFactory().setValue(lm.getAmount());
		edit = true;
	}
	
	public static void showNameAndAmount(StockModel sm){
		stockModel = sm;
		nameField.setText(sm.getName());
		amountSpinner.getValueFactory().setValue(sm.getAmount());
		serving = sm.getServing();
		maxAmount = sm.getMaxAmount();
		edit = true;
	}
}
