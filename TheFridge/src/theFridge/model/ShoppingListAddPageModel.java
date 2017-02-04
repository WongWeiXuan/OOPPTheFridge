package theFridge.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import com.jfoenix.controls.JFXComboBox;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import theFridge.DAO.ServingDAO;
import theFridge.DAO.ShoppingListDAO;

public class ShoppingListAddPageModel {
	public static ShoppingListModel model;
	static JFXComboBox<String> nameField = null;
	static Spinner<Double> amountSpinner;
	static int index;
	public static boolean edit = false;
	static String source;
	static double serving;
	static double maxAmount;
	static ListModel listModel;
	static StockModel stockModel;

	public ShoppingListAddPageModel() {
		super();
	}
	
	public ShoppingListAddPageModel(JFXComboBox<String> nameFields, Spinner<Double> amountSpinners) {
		super();
		nameField = nameFields;
		amountSpinner = amountSpinners;
	}

	public boolean closeAndShow() throws IOException{
		String name = nameField.getValue();
    	double amount = amountSpinner.getValue();
    	
    	if(nameField.getValue() != null && nameField.getValue() != ""){
    		//if(!ShoppingListModel.checkExisting(name))
    		ServingDAO servingdao = new ServingDAO();
    		ServingToGrams s2g = new ServingToGrams();
		    	if(source == "Stock"){
		    		//Edit Button
			    	if(edit == true){
			    		try{
				    		StockModel s = stockModel;
			    			listModel = ShoppingListModel.getListModelByName(s.getName());
			    			s.setAmount(amount);
			    			listModel.setName(name);
			    			if(listModel.getAmount() > s.getMaxAmount()- s.getAmount()){
				    			ShoppingListModel.setListlistViewNode(ShoppingListModel.getListModelIndexByName(s.getName()), listModel, true);
				    		}
				    		else{
				    			ShoppingListModel.setListlistViewNode(ShoppingListModel.getListModelIndexByName(s.getName()), listModel);
				    		}
						    s.setName(name);
				    		ShoppingListModel.setStocklistViewNode(index, s);
			    		}catch(NullPointerException e){
			    			StockModel s = stockModel;
			    			s.setName(name);
						    s.setAmount(amount);
				    		ShoppingListModel.setStocklistViewNode(index, s);
			    		}
			    	}
			    	//Add Button
			    	else{
			    		try{
		    				ShoppingListModel.getStockModelByNameFromStockListArray(name).equals(null);
		    				return false;
		    			}catch(NullPointerException e){
		    				StockModel s = new StockModel(name, amount, servingdao.getServingWithName(name));
						    s.setGrams(s2g.ServingToGramsReturn(servingdao.getServingWithName(name), servingdao.checkType(name)));
						    s.setMaxAmount(ShoppingListModel.calculateMaxAmount(s));
							model.addStocks(s);
		    			}
			    	}
			    	ShoppingListDAO a = new ShoppingListDAO();
		    		a.writeToStockFile(ShoppingListModel.getStocklistArray());
		    		a.writeToListFile(ShoppingListModel.getListlistArray());
		    		edit = false;
		    		return true;
		    	}
		    	//List
		    	else if(source == "List"){
		    		ListModel l = listModel;
		    		try{
		    			l.setName(name);
		        		l.setAmount(amount);
		    		}catch(NullPointerException e){
		    			l = new ListModel(name, amount);
		    		}
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
					    		ShoppingListDAO a = new ShoppingListDAO();
					    		a.writeToListFile(ShoppingListModel.getListlistArray());
					    		
		    	    		}
		    	    		else{
		        				return false;
		        			}
		    	    		//--------
		    			}
		    			else{
		    				ShoppingListDAO a = new ShoppingListDAO();
		    				ShoppingListModel.setListlistViewNode(index, l);
		    				a.writeToListFile(ShoppingListModel.getListlistArray());
		    				return true;
		    			}
		    		}
		    		//Add button
		    		else{
		    			try{
		    				ShoppingListModel.getListModelByName(name).equals(null);
		    				return false;
		    			}catch(NullPointerException e){
		    				try{
			    				ShoppingListModel.getStockModelByNameFromStockListArray(name).equals(null);
			    				model.addShopping(l);
			    			}catch(NullPointerException r){
			    				StockModel stm = new StockModel(l.getName(), 0, servingdao.getServingWithName(l.getName()), 1, 0, true);
				    			stm.setGrams(s2g.ServingToGramsReturn(servingdao.getServingWithName(l.getName()), servingdao.checkType(l.getName())));
				    			stm.setMaxAmount(ShoppingListModel.calculateMaxAmount(stm));
								model.addStocks(stm);
								model.addShopping(l);
			    			}
		    			}
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
		nameField.setValue(lm.getName());
		amountSpinner.getValueFactory().setValue(lm.getAmount());
		nameField.setDisable(true);
		nameField.setOpacity(1);
		edit = true;
	}
	
	public static void showNameAndAmount(StockModel sm){
		stockModel = sm;
		nameField.setValue(sm.getName());
		amountSpinner.getValueFactory().setValue(sm.getAmount());
		serving = sm.getServing();
		maxAmount = sm.getMaxAmount();
		nameField.setDisable(false);
		edit = true;
	}
	
	public void initializeComboBox() throws FileNotFoundException{
		ServingDAO dao = new ServingDAO();
		ArrayList<String> als = dao.getAllFoodName();
		Collections.sort(als);
		nameField.getItems().addAll(als);
	}
	
	public static void sortBySearch(String enter) throws FileNotFoundException{
		ServingDAO dao = new ServingDAO();
		ArrayList<String> als = dao.getAllFoodName();
		ArrayList<String> updated = new ArrayList<String>();
		for(String a:als){
			if(a.toLowerCase().contains(enter.toLowerCase())){
				updated.add(a);
			}
		}
		nameField.getItems().clear();
		if(nameField.getValue().equals(null) || nameField.getValue().equals("")){
			Collections.sort(als);
			nameField.getItems().addAll(als);
			nameField.show();
		}else{
			Collections.sort(updated);
			nameField.getItems().addAll(updated);
			nameField.show();
		}
	}
}
