package theFridge.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXListView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import theFridge.DAO.ShoppingListDAO;

public class ShoppingListModel {
	public void createTitle(JFXListView<HBox> StocklistView){
		HBox HBoxTitle = new HBox();
		Label lbl1 = new Label("Stock");
		lbl1.setMinWidth(500);
		lbl1.setPrefWidth(500);
		lbl1.setAlignment(Pos.CENTER_LEFT);
		Label lbl2 = new Label("Amount");
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setAlignment(Pos.CENTER);
		Label lbl3 = new Label("Serving");
		lbl3.setMinWidth(100);
		lbl3.setPrefWidth(100);
		lbl3.setAlignment(Pos.CENTER);
		HBoxTitle.getChildren().addAll(lbl1, lbl2, lbl3);
		HBoxTitle.setPadding(new Insets(10, 10, 10, 10));
		StocklistView.getItems().add(HBoxTitle);
	}
	
	public void displayStocks() throws FileNotFoundException{
		ShoppingListDAO s = new ShoppingListDAO();
		ArrayList<StockModel>stocklist = s.getAllStock();
		for(StockModel m:stocklist){
			Label lbl = new Label();
			m.getName();
			m.getAmount();
		}
	}
}
