package theFridge.model;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FoodCompostDAO {
	private File dataFile;
	 
	
	public FoodCompostDAO() {
		Path dPath = FileSystems.getDefault().getPath("/theFridge/model/foodCompostData.txt");
     	dataFile=new File(dPath.toString());     
	}
}
