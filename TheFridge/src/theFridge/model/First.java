package theFridge.model;

public class First {
		String foodCanCompost;
	 	String foodCannotCompost;
		
		public First(String foodCanCompost, String foodCannotCompost) {
			super();
			this.foodCanCompost = foodCanCompost;
			this.foodCannotCompost = foodCannotCompost;
		}
		public String getFoodCanCompost() {
			return foodCanCompost;
		}
		public void setFoodCanCompost(String foodCanCompost) {
			this.foodCanCompost = foodCanCompost;
		}
		public String getFoodCannotCompost() {
			return foodCannotCompost;
		}
		public void setFoodCannotCompost(String foodCannotCompost) {
			this.foodCannotCompost = foodCannotCompost;
		}
		
}