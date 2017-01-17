package theFridge.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import theFridge.DAO.ShoppingListDAO;

public class ShoppingListModel {
	public static int numOfPeople;
	JFXListView<HBox>StocklistView;
	JFXPopup Popup;
	JFXListView<HBox>ListlistView;
	JFXPopup Popup1;
	
	public ShoppingListModel(JFXListView<HBox> stocklistView, JFXPopup popup, JFXListView<HBox> listlistView,
			JFXPopup popup1) {
		StocklistView = stocklistView;
		Popup = popup;
		ListlistView = listlistView;
		Popup1 = popup1;
	}

	public void createTitle(){
		HBox HBoxTitle = new HBox();
		Label lbl1 = new Label("Current Stock");
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
		
		HBox HBoxTitle1 = new HBox();
		Label lbl11 = new Label("Shopping List");
		lbl11.setPrefWidth(400);
		lbl11.setAlignment(Pos.CENTER_LEFT);
		Label lbl12 = new Label("Amount");
		lbl12.setPrefWidth(100);
		lbl12.setAlignment(Pos.CENTER);
		HBoxTitle1.getChildren().addAll(lbl11, lbl12);
		HBoxTitle1.setPadding(new Insets(10, 10, 10, 10));
		ListlistView.getItems().add(HBoxTitle1);
	}
	
	public void displayStocks() throws FileNotFoundException{
		ShoppingListDAO s = new ShoppingListDAO();
		ArrayList<StockModel>stocklist = s.getAllStock();
		for(StockModel m:stocklist){
			HBox hbox = new HBox();
			Label nameLbl = new Label(m.getName());
				nameLbl.setMinWidth(500);
				nameLbl.setPrefWidth(500);
				nameLbl.setAlignment(Pos.CENTER_LEFT);
			Label amountLbl = new Label(String.valueOf(m.getAmount()));
				amountLbl.setMinWidth(100);
				amountLbl.setPrefWidth(100);
				amountLbl.setAlignment(Pos.CENTER);
			Label servingLbl = new Label(String.valueOf(m.getServing()));
				servingLbl.setMinWidth(100);
				servingLbl.setPrefWidth(100);
				servingLbl.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(nameLbl, amountLbl, servingLbl);
			hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.SECONDARY){
						Popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
					}
				}
	        });
			
			StocklistView.getItems().add(hbox);
		}
	}
	
	public void displayShopping() throws FileNotFoundException{
		ShoppingListDAO s = new ShoppingListDAO();
		ArrayList<ListModel>listlist = s.getAllList(numOfPeople);
		for(ListModel m:listlist){
			HBox hbox = new HBox();
			Label nameLbl = new Label(m.getName());
				nameLbl.setMinWidth(400);
				nameLbl.setPrefWidth(400);
				nameLbl.setAlignment(Pos.CENTER_LEFT);
			Label amountLbl = new Label(String.valueOf(m.getAmount()));
				amountLbl.setMinWidth(100);
				amountLbl.setPrefWidth(100);
				amountLbl.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(nameLbl, amountLbl);
			hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.SECONDARY){
						Popup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
					}
				}
	        });
			
			ListlistView.getItems().add(hbox);
		}
	}
	
	public void startPopup(){
		//Stock List...
		Label lbl1 = new Label("Edit");
		lbl1.setMinWidth(100);
		lbl1.setPrefWidth(100);
		lbl1.setPadding(new Insets(10));
		//Edit Items
		lbl1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				
			}
        });
		Label lbl2 = new Label("Delete");
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setPadding(new Insets(10));
		//Remove Items
		lbl2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				int selectedIdx = StocklistView.getSelectionModel().getSelectedIndex();
				if(selectedIdx != 0){
					StocklistView.getItems().remove(selectedIdx);
				}
				Popup.close();
			}
        });
		VBox vbox = new VBox(lbl1, lbl2);
		Popup.setContent(vbox);
		Popup.setSource(StocklistView);
		
		//Shopping List...
		Label lbl11 = new Label("Edit");
		lbl11.setMinWidth(100);
		lbl11.setPrefWidth(100);
		lbl11.setPadding(new Insets(10));
		//Edit Items
		lbl11.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				
			}
        });
		Label lbl12 = new Label("Delete");
		lbl12.setMinWidth(100);
		lbl12.setPrefWidth(100);
		lbl12.setPadding(new Insets(10));
		//Remove Items
		lbl12.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				int selectedIdx = ListlistView.getSelectionModel().getSelectedIndex();
				if(selectedIdx != 0){
					ListlistView.getItems().remove(selectedIdx);
				}
				Popup1.close();
			}
        });
		VBox vbox1 = new VBox(lbl11, lbl12);
		Popup1.setContent(vbox1);
		Popup1.setSource(ListlistView);
	}
	
	public void showStage() throws IOException{
		Stage stage = new Stage();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListAddPage.fxml"));
		stage.setScene(new Scene(root));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	
	public void addStocks(StockModel model) throws FileNotFoundException{
		HBox hbox = new HBox();
		Label lbl1 = new Label(model.getName());
		lbl1.setMinWidth(500);
		lbl1.setPrefWidth(500);
		lbl1.setAlignment(Pos.CENTER_LEFT);
		Label lbl2 = new Label(String.valueOf(model.getAmount()));
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setAlignment(Pos.CENTER);
		Label lbl3 = new Label(String.valueOf(model.getServing()));
		lbl3.setMinWidth(100);
		lbl3.setPrefWidth(100);
		lbl3.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(lbl1, lbl2, lbl3);
		StocklistView.getItems().add(hbox);
		hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY){
					Popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
				}
			}
        });
	}
	
	public void addShopping() throws FileNotFoundException{
		HBox hbox = new HBox();
		Label lbl1 = new Label("Corn");
		lbl1.setMinWidth(400);
		lbl1.setPrefWidth(400);
		lbl1.setAlignment(Pos.CENTER_LEFT);
		Label lbl2 = new Label("3");
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(lbl1, lbl2);
		ListlistView.getItems().add(hbox);
		hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY){
					Popup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
				}
			}
        });
	}
}
