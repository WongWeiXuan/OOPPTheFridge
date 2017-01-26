package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.PlaceModel;

public class MapPlacesDAO {
	private File placeFile;
	
	public MapPlacesDAO(){
		placeFile = new File("src/theFridge/file/places.txt");
	}
	
	public ArrayList<PlaceModel> getAllPlace() throws FileNotFoundException{
		Scanner sc = new Scanner(placeFile);
		String line=null;
		String[] fields;
		ArrayList<PlaceModel> places=new ArrayList<PlaceModel>();
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split("-");
			String name = fields[0];
			double distance = Double.parseDouble(fields[1]);
			double duration = Double.parseDouble(fields[2]);
			PlaceModel a = new PlaceModel(name, distance, duration);
			places.add(a);
		}
		sc.close();

		return places;
	}
}
