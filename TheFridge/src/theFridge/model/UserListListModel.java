package theFridge.model;

import java.util.ArrayList;

public class UserListListModel {
	private String name;
	private ArrayList<ListModel> listList;
	
	public UserListListModel(String name, ArrayList<ListModel> listList) {
		super();
		this.name = name;
		this.listList = listList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ListModel> getListList() {
		return listList;
	}

	public void setStockList(ArrayList<ListModel> listList) {
		this.listList = listList;
	}
}
