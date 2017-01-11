package theFridge.model;

public class FoodCompost {
	private String foodName;
	private String videoURL;
	private String instruction;
	public FoodCompost(String foodName, String videoURL, String instruction) {
		super();
		this.foodName = foodName;
		this.videoURL = videoURL;
		this.instruction = instruction;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public String getInstrustion() {
		return instruction;
	}
	public void setInstrustion(String instrustion) {
		this.instruction = instrustion;
	}
	
}
