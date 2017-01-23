package theFridge.DAO;

import java.io.File;

public class ServingDAO {
	final String FILE_PATH = "src/theFridge/file/FruitTitle.txt";
	File servingFile;
	
	public ServingDAO(){
		servingFile = new File(FILE_PATH);
	}
	
	
}
