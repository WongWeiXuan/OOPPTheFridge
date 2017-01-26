package theFridge.model;

public class PlaceModel {
	String name;
	double distance;
	double duration;
	
	public PlaceModel(String name, double distance, double duration) {
		super();
		this.name = name;
		this.distance = distance;
		this.duration = duration;
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

}
