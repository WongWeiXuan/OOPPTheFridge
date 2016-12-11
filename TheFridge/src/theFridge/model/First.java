package theFridge.model;

import javafx.beans.property.SimpleStringProperty;
public class First {
		private final SimpleStringProperty foodCanCompost;
	 	private final SimpleStringProperty foodCannotCompost;
		public First(String foodCanCompost, String foodCannotCompost) {
			super();
			this.foodCanCompost = new SimpleStringProperty(foodCanCompost);
			this.foodCannotCompost = new SimpleStringProperty(foodCannotCompost);
		}
		
		public String getFoodCanCompost() {
			return foodCanCompost.get();
		}

		public String getFoodCannotCompost() {
			return foodCannotCompost.get();
		}



		
}