package theFridge.model;

import java.util.ArrayList;

import theFridge.DAO.FoodCompostDAO;

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
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instrustion) {
		this.instruction = instrustion;
	}
	
	public String toString() {
		return foodName + ";"+ videoURL+";"+ instruction;
	}
	
	public static ArrayList<FoodCompost> getAllFood() {
		FoodCompostDAO foodDao=new FoodCompostDAO();
		return foodDao.getAllFood();
	}
	public void getFriend(){
		FoodCompostDAO foodDao=new FoodCompostDAO();
		FoodCompost f=foodDao.getFoodCompost(foodName);
		setVideoURL(f.getVideoURL());
		setInstruction(f.getInstruction());
	}
	
	public boolean createFood() {
		FoodCompostDAO foodDao=new FoodCompostDAO();
		return foodDao.createFood(this);
	}
	
	public void updateFriend() {
		FoodCompostDAO foodDao=new FoodCompostDAO();
		foodDao.updateFood(this);
	}
	
	public boolean equals(Object obj){
		if (!(obj instanceof FoodCompost)){
			return false;
		}
		FoodCompost f=(FoodCompost)obj;
		if (f.getFoodName().equals(this.foodName))
			return true;
		else
			return false;
	}
	
	public static void main(String[] args){
		
		
	}
	
}
