package theFridge.DAO;

import java.io.File;

public class ServingDAO {
	File breadFile;
	File cerealFile;
	File fruitsFile;
	File pastaFile;
	File vegetableFile;
	
	public ServingDAO(){
		breadFile = new File("src/theFridge/file/servings/Bread.txt");
		cerealFile = new File("src/theFridge/file/servings/Cereal.txt");
		fruitsFile = new File("src/theFridge/file/servings/Fruits.txt");
		pastaFile = new File("src/theFridge/file/servings/Pasta.txt");
		vegetableFile = new File("src/theFridge/file/servings/Vegetable.txt");
	}
	
	
}
