package theFridge.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import theFridge.DAO.DonationPageDAO;
import theFridge.DAO.ShoppingListDAO;
import theFridge.controller.DonationPageController;

public class DonationPageModel {
	String organizationName;
	int openingHours;
	int closingHours;
	int openingDay;
	int closingDay;
	int secondaryOpeningHours;
	int secondaryClosingHours;
	int secondaryOpeningDay;
	
	public DonationPageModel() {
		super();
	}

	public DonationPageModel(String organizationName, int openingHours, int closingHours, int openingDay,
			int closingDay) {
		super();
		this.organizationName = organizationName;
		this.openingHours = openingHours;
		this.closingHours = closingHours;
		this.openingDay = openingDay;
		this.closingDay = closingDay;
	}

	public DonationPageModel(String organizationName, int openingHours, int closingHours, int openingDay,
			int closingDay, int secondaryOpeningHours, int secondaryClosingHours, int secondaryOpeningDay) {
		super();
		this.organizationName = organizationName;
		this.openingHours = openingHours;
		this.closingHours = closingHours;
		this.openingDay = openingDay;
		this.closingDay = closingDay;
		this.secondaryOpeningHours = secondaryOpeningHours;
		this.secondaryClosingHours = secondaryClosingHours;
		this.secondaryOpeningDay = secondaryOpeningDay;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(int openingHours) {
		this.openingHours = openingHours;
	}

	public int getClosingHours() {
		return closingHours;
	}

	public void setClosingHours(int closingHours) {
		this.closingHours = closingHours;
	}

	public int getOpeningDay() {
		return openingDay;
	}

	public void setOpeningDay(int openingDay) {
		this.openingDay = openingDay;
	}

	public int getClosingDay() {
		return closingDay;
	}

	public void setClosingDay(int closingDay) {
		this.closingDay = closingDay;
	}

	public int getSecondaryOpeningHours() {
		return secondaryOpeningHours;
	}

	public void setSecondaryOpeningHours(int secondaryOpeningHours) {
		this.secondaryOpeningHours = secondaryOpeningHours;
	}

	public int getSecondaryClosingHours() {
		return secondaryClosingHours;
	}

	public void setSecondaryClosingHours(int secondaryClosingHours) {
		this.secondaryClosingHours = secondaryClosingHours;
	}

	public int getSecondaryOpeningDay() {
		return secondaryOpeningDay;
	}

	public void setSecondaryOpeningDay(int secondaryOpeningDay) {
		this.secondaryOpeningDay = secondaryOpeningDay;
	}
	
	public static DonationPageModel getThisModel(String name) throws FileNotFoundException{
		DonationPageDAO dao = new DonationPageDAO();
		ArrayList<DonationPageModel>aldpm = dao.getAllOperationTimes();
		for(DonationPageModel a:aldpm){
			if(a.getOrganizationName().equalsIgnoreCase(name)){
				return a;
			}
		}
		return null;
	}
	
	public static void submitForm(){
		
	}
	
	public static ArrayList<ListModel> getAutomaticFood() throws FileNotFoundException{
		ArrayList<ListModel> allm = new ArrayList<ListModel>();
		User u = new User();
		u = u.getCurrentUser();
		ShoppingListDAO dao = new ShoppingListDAO();
		ArrayList<StockModel> alsm = dao.getAllStock(u.getUsername());
		for(StockModel a:alsm){
			if(a.getAmount() > a.getMaxAmount()){
				double amount = a.getAmount() - a.getMaxAmount();
				ListModel lm = new ListModel(a.getName(), amount);
				allm.add(lm);
			}
		}
		
		return allm;
	}
	
	public static VBox enterFoodVBox;
	
	public void addFood(ListModel lm){
		if(DonationPageController.first == true){
			Label q = new Label("Name");
			q.setPrefWidth(400);
			Label w = new Label("Amount");
			w.setPrefWidth(200);
			HBox title = new HBox(q, w);
			title.setAlignment(Pos.CENTER);
			enterFoodVBox.getChildren().add(0, title);
		}
		Label name = new Label(lm.getName());
		name.setPrefWidth(400);
		Label amount = new Label(String.valueOf((int)lm.getAmount()));
		amount.setPrefWidth(200);
		HBox hbox = new HBox(name, amount);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
		hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				enterFoodVBox.getChildren().remove(hbox);
			}
		});
		enterFoodVBox.getChildren().add(hbox);
		DonationPageController.first = false;
	}
	
	public static void updateStockList(ArrayList<ListModel> lm) throws IOException {
		User u = new User();
		u = u.getCurrentUser();
		
		for(ListModel l:lm){
			ShoppingListDAO dao = new ShoppingListDAO();
			try{
				StockModel sm = dao.getStockModelByName(l.getName());
				sm.equals(null);
				if(l.getAmount() < sm.getAmount()){
					sm.setAmount(sm.getAmount() - l.getAmount());
				}else{
					sm.setAmount(0);
				}
				ArrayList<StockModel> alsm = dao.getAllStock(u.getUsername());
				for(StockModel stock:alsm){
					if(stock.getName().equalsIgnoreCase(sm.getName())){
						stock.setAmount(sm.getAmount());
					}
				}
				dao.writeToStockFile(alsm);
			}catch(NullPointerException e){
				
			}
		}
	}
}
