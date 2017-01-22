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
	    	if(edit == true){
	    		StockModel s = stockModel;
			    listModel = ShoppingListModel.getListModelByName(s.getName());
			    s.setName(name);
			    s.setAmount(amount);
	    		ShoppingListModel.setStocklistViewNode(index, s);
	    		listModel.setName(name);
	    		ShoppingListModel.setListlistViewNode(index, listModel);
	    		edit = false;
	    	}
	    	else{
			    StockModel s = new StockModel(name, amount, 1);
			    s.setMaxAmount(ShoppingListModel.calculateMaxAmount(s));
				model.addStocks(s);
				model.addShopping(new ListModel(name, 0, s.getMaxAmount()));
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
    			if(amount > stockModel.getMaxAmount() - stockModel.getAmount()){
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
		nameField.setDisable(true);
		nameField.setOpacity(1);
		edit = true;
	}
	
	public static void showNameAndAmount(StockModel sm){
		stockModel = sm;
		nameField.setText(sm.getName());
		amountSpinner.getValueFactory().setValue(sm.getAmount());
		serving = sm.getServing();
		maxAmount = sm.getMaxAmount();
		nameField.setDisable(false);
		edit = true;
	}
}
