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
	static TextField nameField = null;
	static Spinner<Double> amountSpinner;
	static int index;
	public static boolean edit = false;
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
    	
    	if(nameField.getText() != null && !nameField.getText().isEmpty()){
    		//if(!ShoppingListModel.checkExisting(name))
		    	if(source == "Stock"){
			    	if(edit == true){
			    		System.out.print("hi");
			    		StockModel s = stockModel;
			    		try{
			    			listModel = ShoppingListModel.getListModelByName(s.getName());
						    s.setName(name);
						    s.setAmount(amount);
				    		ShoppingListModel.setStocklistViewNode(index, s);
				    		listModel.setName(name);
				    		if(listModel.getAmount() > s.getMaxAmount()- s.getAmount()){
				    			ShoppingListModel.setListlistViewNode(index, listModel, true);
				    		}
				    		else{
				    			ShoppingListModel.setListlistViewNode(index, listModel);
				    		}
			    		}catch(Exception e){
			    			s.setName(name);
						    s.setAmount(amount);
				    		ShoppingListModel.setStocklistViewNode(index, s);
			    		}
			    	}
			    	else{
					    StockModel s = new StockModel(name, amount, 1);
					    s.setMaxAmount(ShoppingListModel.calculateMaxAmount(s));
						model.addStocks(s);
			    	}
			    	ShoppingListDAO a = new ShoppingListDAO();
		    		a.writeToStockFile(ShoppingListModel.getStocklistArray());
		    		a.writeToListFile(ShoppingListModel.getListlistArray());
		    		edit = false;
		    		return true;
		    	}
		    	else if(source == "List"){
		    		ListModel l = listModel;
		    		try{
		    			l.setName(name);
		        		l.setAmount(amount);
		    		}catch(NullPointerException e){
		    			l = new ListModel(name, amount);
		    		}
		    		
		    		if(edit == true){
		    			try{
			    			if(amount > stockModel.getMaxAmount() - stockModel.getAmount()){
			    				//Alert Box
			    	    		Alert alert = new Alert(AlertType.CONFIRMATION);
			    	    		alert.setTitle("Confirmation Dialog");
			    	    		alert.setHeaderText("You have entered an amount higher than the maximum");
			    	    		alert.setContentText("Are you sure you want to continue?");
			    	
			    	    		Optional<ButtonType> result = alert.showAndWait();
			    	    		if (result.get() == ButtonType.OK){
						    		ShoppingListModel.setListlistViewNode(index, l, true);
			    	    		}
			    	    		else{
			        				return false;
			        			}
			    	    		//--------
			    			}
			    			else{
			    				ShoppingListModel.setListlistViewNode(index, l);
			    				return true;
			    			}
		    			}catch(NullPointerException e){
		    				ShoppingListModel.setListlistViewNode(index, l);
		    				return true;
		    			}
		    		}
		    		else{
		    			//Get servings and calculate maxAmount
						model.addStocks(new StockModel(l.getName(), 0, 1, 1, 0, true));
						model.addShopping(l);
			    	}
		    		ShoppingListDAO a = new ShoppingListDAO();
		    		a.writeToStockFile(ShoppingListModel.getStocklistArray());
		    		a.writeToListFile(ShoppingListModel.getListlistArray());
		    		edit = false;
		    		return true;
		    	}
		    	else{
		    		edit = false;
		    		return true;
		    	}	
    		//else{
    			//return false;
    		//}
    	}
    	else{
    		return false;
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
