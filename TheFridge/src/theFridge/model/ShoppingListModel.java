package theFridge.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import theFridge.DAO.ServingDAO;
import theFridge.DAO.ShoppingListDAO;

public class ShoppingListModel {
	public static int numOfPeople;
	static JFXListView<HBox> StocklistView;
	static JFXPopup Popup;
	static JFXListView<HBox> ListlistView;
	static JFXPopup Popup1;

	public ShoppingListModel(JFXListView<HBox> stocklistView, JFXPopup popup, JFXListView<HBox> listlistView,
			JFXPopup popup1) {
		StocklistView = stocklistView;
		Popup = popup;
		ListlistView = listlistView;
		Popup1 = popup1;
	}

	public void createTitle() {
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
		Label lbl4 = new Label("Grams");
		lbl4.setMinWidth(100);
		lbl4.setPrefWidth(100);
		lbl4.setAlignment(Pos.CENTER);
		Label lbl5 = new Label("Max Amount");
		lbl5.setMinWidth(100);
		lbl5.setPrefWidth(100);
		lbl5.setAlignment(Pos.CENTER);
		HBoxTitle.getChildren().addAll(lbl1, lbl2, lbl3, lbl4, lbl5);
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

	public void displayStocks() throws FileNotFoundException, IOException {
		ServingDAO servingdao = new ServingDAO();
		ServingToGrams s2g = new ServingToGrams();
		User user = new User();
		user = user.getCurrentUser();
		ShoppingListDAO s = new ShoppingListDAO();
		ArrayList<StockModel> stocklist = s.getAllStock(user.getUsername());
		for (StockModel m : stocklist) {
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
			Label gramslbl = new Label(String.valueOf(s2g.ServingToGramsReturn(m.getServing(), servingdao.checkType(m.getName()))));
			gramslbl.setMinWidth(100);
			gramslbl.setPrefWidth(100);
			gramslbl.setAlignment(Pos.CENTER);
			Label maxAmountlbl = new Label(String.valueOf(calculateMaxAmount(m)));
			maxAmountlbl.setMinWidth(100);
			maxAmountlbl.setPrefWidth(100);
			maxAmountlbl.setAlignment(Pos.CENTER);
			m.setMaxAmount(calculateMaxAmount(m));
			hbox.getChildren().addAll(nameLbl, amountLbl, servingLbl, gramslbl, maxAmountlbl);
			hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.SECONDARY) {
						Popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
					}
				}
			});

			StocklistView.getItems().add(hbox);
		}
	}

	public void displayShopping() throws FileNotFoundException {
		ShoppingListDAO s = new ShoppingListDAO();
		try{
			ArrayList<ListModel> listlist = s.getAllList(numOfPeople);
			for (ListModel m : listlist) {
				HBox hbox = new HBox();
				Label nameLbl = new Label(m.getName());
				nameLbl.setMinWidth(400);
				nameLbl.setPrefWidth(400);
				nameLbl.setAlignment(Pos.CENTER_LEFT);
				Label amountLbl = new Label(String.valueOf(m.getAmount()));
				amountLbl.setMinWidth(100);
				amountLbl.setPrefWidth(100);
				amountLbl.setAlignment(Pos.CENTER);
				try{
					StockModel sm = getStockModelByNameFromStockListArray(m.getName());
					if (m.getAmount() > sm.getMaxAmount() - sm.getAmount()) {
						amountLbl.setStyle("-fx-text-fill: red");
					} else {
						amountLbl.setStyle("-fx-text-fill: black");
					}
				}catch(Exception e){}
				hbox.getChildren().addAll(nameLbl, amountLbl);
				hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if(event.getButton().equals(MouseButton.PRIMARY)){
				            if(event.getClickCount() == 2){
				            	try{
				            		doubleClick();
				            		ShoppingListDAO a = new ShoppingListDAO();
									try {
										a.writeToStockFile(ShoppingListModel.getStocklistArray());
										a.writeToListFile(ShoppingListModel.getListlistArray());
									} catch (IOException e) {
										e.printStackTrace();
									}
				            	}catch(StackOverflowError e){}
				            }
				        }
						else if(event.getButton() == MouseButton.SECONDARY) {
							Popup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
						}
					}
				});
	
				ListlistView.getItems().add(hbox);
				try {
					ShoppingListDAO a = new ShoppingListDAO();
					a.writeToListFile(ShoppingListModel.getListlistArray());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}catch(NullPointerException e){
				
		}
	}

	public void displayShoppingByStockModel(StockModel stock) throws FileNotFoundException {
		ShoppingListDAO a = new ShoppingListDAO();
		ListModel list = a.getListWithStock(stock);
		HBox hbox = new HBox();
		Label nameLbl = new Label(list.getName());
		nameLbl.setMinWidth(400);
		nameLbl.setPrefWidth(400);
		nameLbl.setAlignment(Pos.CENTER_LEFT);
		Label amountLbl = new Label(String.valueOf(list.getAmount()));
		amountLbl.setMinWidth(100);
		amountLbl.setPrefWidth(100);
		amountLbl.setAlignment(Pos.CENTER);
		if (list.getAmount() > stock.getMaxAmount()) {
			amountLbl.setStyle("-fx-text-fill: red");
		} else {
			amountLbl.setStyle("-fx-text-fill: black");
		}
		list.setMaxAmount(stock.getMaxAmount());
		hbox.getChildren().addAll(nameLbl, amountLbl);
		hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.PRIMARY)
					if(event.getClickCount() == 2){
		            	try{
		            		doubleClick();
		            	}catch(StackOverflowError e){}
		            }
				else if (event.getButton() == MouseButton.SECONDARY) {
					Popup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
				}
			}
		});

		ListlistView.getItems().add(hbox);
	}

	public void startPopup() {
		// Stock List...
		Label lbl1 = new Label("Edit");
		lbl1.setMinWidth(100);
		lbl1.setPrefWidth(100);
		lbl1.setPadding(new Insets(10));
		// Edit Items
		lbl1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				ShoppingListAddPageModel.source = "Stock";
				HBox selectedItem = StocklistView.getSelectionModel().getSelectedItem();
				int selectedIdx = StocklistView.getSelectionModel().getSelectedIndex();
				ObservableList<Node> Label = selectedItem.getChildren();
				String name = ((Label)(Label.get(0))).getText();
				String amount = ((Label)(Label.get(1))).getText();
				String serving = nodeToString(Label.get(2));
				String grams = nodeToString(Label.get(3));
				String maxAmount = nodeToString(Label.get(4));

				try {
					showStage(selectedIdx);
					ShoppingListDAO a = new ShoppingListDAO();
					StockModel sm = a.getStockModelByName(name);
					sm.setAmount(Double.parseDouble(amount));
					sm.setServing(Double.parseDouble(serving));
					sm.setGrams(Integer.parseInt(grams));
					sm.setMaxAmount(Double.parseDouble(maxAmount));
					ShoppingListAddPageModel.showNameAndAmount(sm);
				} catch (IOException e) {
					e.printStackTrace();
				}
				Popup.close();
			}
		});
		Label lbl2 = new Label("Delete");
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setPadding(new Insets(10));
		// Remove Items
		lbl2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				int selectedIdx = StocklistView.getSelectionModel().getSelectedIndex();
				if (selectedIdx != 0) {
					StocklistView.getItems().remove(selectedIdx);
				}
				try {
					ShoppingListDAO a = new ShoppingListDAO();
					a.writeToStockFile(ShoppingListModel.getStocklistArray());
					a.writeToListFile(ShoppingListModel.getListlistArray());
				} catch (IOException e) {
					e.printStackTrace();
				}
				Popup.close();
			}
		});
		VBox vbox = new VBox(lbl1, lbl2);
		Popup.setContent(vbox);
		Popup.setSource(StocklistView);

		// Shopping List...
		Label lbl11 = new Label("Edit");
		lbl11.setMinWidth(100);
		lbl11.setPrefWidth(100);
		lbl11.setPadding(new Insets(10));
		// Edit Items
		lbl11.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				ShoppingListAddPageModel.source = "List";
				HBox selectedItem = ListlistView.getSelectionModel().getSelectedItem();
				int selectedIdx = ListlistView.getSelectionModel().getSelectedIndex();
				ObservableList<Node> Label = selectedItem.getChildren();
				String name = ((Label)(Label.get(0))).getText();
				String amount = ((Label)(Label.get(1))).getText();

				try {
					showStage(selectedIdx);
				} catch (IOException e) {
					e.printStackTrace();
				}
				ShoppingListDAO a = new ShoppingListDAO();
				ListModel lm = null;
				try {
					StockModel sl = getStockModelByNameFromStockListArray(name);
					lm = a.getListModelByName(name);
					lm.setAmount(Double.parseDouble(amount));
					lm.setMaxAmount(sl.getMaxAmount());
					ShoppingListAddPageModel.stockModel = sl;
				}catch (Exception e) {
					lm = new ListModel(name, Double.parseDouble(amount));
				}
				ShoppingListAddPageModel.showNameAndAmount(lm);
				Popup1.close();
			}
		});
		Label lbl12 = new Label("Delete");
		lbl12.setMinWidth(100);
		lbl12.setPrefWidth(100);
		lbl12.setPadding(new Insets(10));
		// Remove Items
		lbl12.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				int selectedIdx = ListlistView.getSelectionModel().getSelectedIndex();
				if (selectedIdx != 0) {
					ListlistView.getItems().remove(selectedIdx);
				}
				try {
					ShoppingListDAO a = new ShoppingListDAO();
					a.writeToStockFile(ShoppingListModel.getStocklistArray());
					a.writeToListFile(ShoppingListModel.getListlistArray());
				} catch (IOException e) {
					e.printStackTrace();
				}
				Popup1.close();
			}
		});
		VBox vbox1 = new VBox(lbl11, lbl12);
		Popup1.setContent(vbox1);
		Popup1.setSource(ListlistView);
	}

	public void showStage(int index) throws IOException {
		@SuppressWarnings("rawtypes")
		Dialog dialog = new Dialog();
		Stage stage = (Stage)dialog.getDialogPane().getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListAddPage.fxml"));
		stage.setScene(new Scene(root));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		ShoppingListAddPageModel.index = index;
	}

	public void showStage(ShoppingListModel model) throws IOException {
		@SuppressWarnings("rawtypes")
		Dialog dialog = new Dialog();
		Stage stage = (Stage)dialog.getDialogPane().getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListAddPage.fxml"));
		stage.setScene(new Scene(root));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		ShoppingListAddPageModel.model = model;
	}

	public void showStage(ShoppingListModel model, String source) throws IOException {
		@SuppressWarnings("rawtypes")
		Dialog dialog = new Dialog();
		Stage stage = (Stage)dialog.getDialogPane().getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListAddPage.fxml"));
		stage.setScene(new Scene(root));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		ShoppingListAddPageModel.model = model;
		ShoppingListAddPageModel.source = source;
	}

	public void addStocks(StockModel s) throws FileNotFoundException {
		HBox hbox = new HBox();
		Label lbl1 = new Label(s.getName());
		lbl1.setMinWidth(500);
		lbl1.setPrefWidth(500);
		lbl1.setAlignment(Pos.CENTER_LEFT);
		Label lbl2 = new Label(String.valueOf(s.getAmount()));
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setAlignment(Pos.CENTER);
		Label lbl3 = new Label(String.valueOf(s.getServing()));
		lbl3.setMinWidth(100);
		lbl3.setPrefWidth(100);
		lbl3.setAlignment(Pos.CENTER);
		Label lbl4 = new Label(String.valueOf(s.getGrams()));
		lbl4.setMinWidth(100);
		lbl4.setPrefWidth(100);
		lbl4.setAlignment(Pos.CENTER);
		Label lbl5 = new Label(String.valueOf(s.getMaxAmount()));
		lbl5.setMinWidth(100);
		lbl5.setPrefWidth(100);
		lbl5.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(lbl1, lbl2, lbl3, lbl4, lbl5);
		StocklistView.getItems().add(hbox);
		hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY) {
					Popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
				}
			}
		});
	}

	public void addShopping(ListModel s) throws FileNotFoundException {
		HBox hbox = new HBox();
		Label lbl1 = new Label(s.getName());
		lbl1.setMinWidth(400);
		lbl1.setPrefWidth(400);
		lbl1.setAlignment(Pos.CENTER_LEFT);
		Label lbl2 = new Label(String.valueOf(s.getAmount()));
		lbl2.setMinWidth(100);
		lbl2.setPrefWidth(100);
		lbl2.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(lbl1, lbl2);
		ListlistView.getItems().add(hbox);
		hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)){
					if(event.getClickCount() == 2){
		            	try{
		            		doubleClick();
		            	}catch(StackOverflowError e){}
		            }
		        }
				else if(event.getButton() == MouseButton.SECONDARY) {
					Popup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
				}
			}
		});
	}

	public static String nodeToString(Node node) {
		String s = node.toString();
		s = s.substring(s.indexOf("'") + 1);
		s = s.substring(0, s.indexOf("'"));

		return s;
	}

	public static void setListlistViewNode(int index, ListModel model) {
		HBox hbox = new HBox();
		Label nameLbl = new Label(model.getName());
		nameLbl.setMinWidth(400);
		nameLbl.setPrefWidth(400);
		nameLbl.setAlignment(Pos.CENTER_LEFT);
		Label amountLbl = new Label(String.valueOf(model.getAmount()));
		amountLbl.setMinWidth(100);
		amountLbl.setPrefWidth(100);
		amountLbl.setAlignment(Pos.CENTER);
		amountLbl.setStyle("-fx-text-fill: black");
		hbox.getChildren().addAll(nameLbl, amountLbl);
		hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)){
					if(event.getClickCount() == 2){
		            	try{
		            		doubleClick();
		            	}catch(StackOverflowError e){}
		            }
		        }
				else if(event.getButton() == MouseButton.SECONDARY) {
					Popup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
				}
			}
		});
		ListlistView.getItems().set(index, hbox);
	}

	public static void setListlistViewNode(int index, ListModel model, boolean red) {
		HBox hbox = new HBox();
		Label nameLbl = new Label(model.getName());
		nameLbl.setMinWidth(400);
		nameLbl.setPrefWidth(400);
		nameLbl.setAlignment(Pos.CENTER_LEFT);
		Label amountLbl = new Label(String.valueOf(model.getAmount()));
		amountLbl.setMinWidth(100);
		amountLbl.setPrefWidth(100);
		amountLbl.setAlignment(Pos.CENTER);
		amountLbl.setStyle("-fx-text-fill: red");
		hbox.getChildren().addAll(nameLbl, amountLbl);
		hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)){
		            if(event.getClickCount() == 2){
		            	try{
		            		doubleClick();
		            	}catch(StackOverflowError e){}
		            }
		        }
				else if(event.getButton() == MouseButton.SECONDARY) {
					Popup1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
				}
			}
		});
		ListlistView.getItems().set(index, hbox);
	}

	public static void setStocklistViewNode(int index, StockModel model) {
		HBox hbox = new HBox();
		Label nameLbl = new Label(model.getName());
		nameLbl.setMinWidth(500);
		nameLbl.setPrefWidth(500);
		nameLbl.setAlignment(Pos.CENTER_LEFT);
		Label amountLbl = new Label(String.valueOf(model.getAmount()));
		amountLbl.setMinWidth(100);
		amountLbl.setPrefWidth(100);
		amountLbl.setAlignment(Pos.CENTER);
		Label servingLbl = new Label(String.valueOf(model.getServing()));
		servingLbl.setMinWidth(100);
		servingLbl.setPrefWidth(100);
		servingLbl.setAlignment(Pos.CENTER);
		Label gramslbl = new Label(String.valueOf(model.getGrams()));
		gramslbl.setMinWidth(100);
		gramslbl.setPrefWidth(100);
		gramslbl.setAlignment(Pos.CENTER);
		Label maxAmountlbl = new Label(String.valueOf(calculateMaxAmount(model)));
		maxAmountlbl.setMinWidth(100);
		maxAmountlbl.setPrefWidth(100);
		maxAmountlbl.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(nameLbl, amountLbl, servingLbl, gramslbl, maxAmountlbl);
		hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY) {
					Popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
				}
			}
		});
		StocklistView.getItems().set(index, hbox);
	}

	public static ArrayList<StockModel> getStocklistArray() {
		ArrayList<StockModel> stockArray = new ArrayList<StockModel>();
		ObservableList<HBox> HboxList = StocklistView.getItems();
		boolean numfirst = true;
		for (HBox a : HboxList) {
			if (numfirst == true) {
				numfirst = false;
				continue;
			} else {
				ObservableList<Node> NodeList = a.getChildren();
				String name = ((Label)NodeList.get(0)).getText();
				double amount = Double.parseDouble(nodeToString(NodeList.get(1)));
				double serving = Double.parseDouble(nodeToString(NodeList.get(2)));
				int grams = Integer.parseInt(nodeToString(NodeList.get(3)));
				double maxAmount = Double.parseDouble(nodeToString(NodeList.get(4)));
				StockModel stockModel = new StockModel(name, amount, serving, grams, maxAmount, true);
				stockArray.add(stockModel);
			}
		}
		return stockArray;
	}

	public static StockModel getStockModelByNameFromStockListArray(String name) {
		StockModel model = null;
		int i = 1;
		for (StockModel sm : getStocklistArray()) {
			if (sm.getName().equalsIgnoreCase(name)) {
				model = sm;
				model.setIndex(i);
			}
			i++;
		}
		return model;
	}

	public static ListModel getListModelByName(String name) {
		ListModel model = null;
		for (ListModel lm : getListlistArray()) {
			if (lm.getName().equalsIgnoreCase(name)) {
				model = lm;
			}
		}
		return model;
	}

	public static int getListModelIndexByName(String name) {
		int i = 1;
		for (ListModel lm : getListlistArray()) {
			if (lm.getName().equalsIgnoreCase(name)) {
				return i;
			}
			i++;
		}
		return 0;
	}
	
	public static ArrayList<ListModel> getListlistArray() {
		ArrayList<ListModel> listArray = new ArrayList<ListModel>();
		ObservableList<HBox> HboxList = ListlistView.getItems();
		boolean numfirst = true;
		for (HBox a : HboxList) {
			if (numfirst == true) {
				numfirst = false;
				continue;
			} else {
				ObservableList<Node> NodeList = a.getChildren();
				String name = ((Label)NodeList.get(0)).getText();
				double amount = Double.parseDouble(nodeToString(NodeList.get(1)));
				ListModel listModel;
				try {
					double maxAmount = getStockModelByNameFromStockListArray(name).getMaxAmount();
					listModel = new ListModel(name, amount, maxAmount);
				} catch (Exception e) {
					listModel = new ListModel(name, amount);
				}
				listArray.add(listModel);
			}
		}
		return listArray;
	}

	public static double calculateMaxAmount(StockModel stock) {
		double maxAmount = stock.getServing() * numOfPeople;
		stock.setMaxAmount(maxAmount);
		return maxAmount;
	}
	
	public static void doubleClick(){
		HBox selectedItem = ListlistView.getSelectionModel().getSelectedItem();
		int selectedIdx = ListlistView.getSelectionModel().getSelectedIndex();
		ObservableList<Node> Label = selectedItem.getChildren();
		String name = ((Label)Label.get(0)).getText();
		String amount = ((Label)(Label.get(1))).getText();
		StockModel model = getStockModelByNameFromStockListArray(name);
		model.setAmount(model.getAmount() + Double.parseDouble(amount));
		model.setMaxAmount(ShoppingListModel.calculateMaxAmount(model));
		setStocklistViewNode(model.getIndex(), model);
		setListlistViewNode(selectedIdx, new ListModel(name, 0, model.getMaxAmount()));
	}
	
	public static boolean checkExisting(String name){
		ArrayList<StockModel> sm = getStocklistArray();
		ArrayList<ListModel> lm = getListlistArray();
		for(StockModel s:sm){
			String stockName = s.getName();
			if(stockName.equalsIgnoreCase(name)){
				return true;
			}
		}
		
		for(ListModel l:lm){
			String listName = l.getName();
			if(listName.equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkUser(String name) throws FileNotFoundException{
		if(name == null || name == ""){
			User u = new User();
			u = u.getCurrentUser();
			name = u.getUsername();
		}
		ShoppingListDAO dao = new ShoppingListDAO();
		return dao.checkUser(name);
	}
	
}
