package theFridge.model;

public class ServingModel {
	private String name;
	private double numOfServing;
	
	public ServingModel(String name, double numOfServing) {
		this.name = name;
		this.numOfServing = numOfServing;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getNumOfServing() {
		return numOfServing;
	}

	public void setNumOfServing(double numOfServing) {
		this.numOfServing = numOfServing;
	}
	
}
