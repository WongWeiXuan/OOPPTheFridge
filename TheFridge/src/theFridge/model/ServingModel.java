package theFridge.model;

public class ServingModel {
	private String name;
	private int numOfServing;
	
	public ServingModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfServing() {
		return numOfServing;
	}

	public void setNumOfServing(int numOfServing) {
		this.numOfServing = numOfServing;
	}
	
}
