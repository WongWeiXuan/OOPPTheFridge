package theFridge.model;

public class ShoppingListAddPageModel {
	public void closeAndShow(ShoppingListModel model,){
		String name = nameField.getText();
    	int amount = amountSpinner.getValue();
    	StockModel s = new StockModel(name, amount, 1);
		model.addStocks(model);
	}
}
