package theFridge.model;

public class PlaceModel {
	String name;
	double distance;
	double duration;
	String picture;
	
	public PlaceModel(String name, double distance, double duration, String picture) {
		super();
		this.name = name;
		this.distance = distance;
		this.duration = duration;
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
