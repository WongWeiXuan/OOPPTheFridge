package theFridge.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import theFridge.model.PlaceModel;

public class MapPlacesDAO {
	private File placeFile;
	private File placeFile1;
	
	public MapPlacesDAO(){
		placeFile = new File("src/theFridge/file/places.txt");
		placeFile1 = new File("src/theFridge/file/places1.txt");
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
			String picture = fields[3];
			PlaceModel a = new PlaceModel(name, distance, duration, picture);
			places.add(a);
		}
		sc.close();

		return places;
	}
	
	public ArrayList<PlaceModel> getAllPlace1() throws FileNotFoundException{
		Scanner sc = new Scanner(placeFile1);
		String line=null;
		String[] fields;
		ArrayList<PlaceModel> places=new ArrayList<PlaceModel>();
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			fields = line.split("-");
			String name = fields[0];
			double distance = Double.parseDouble(fields[1]);
			double duration = Double.parseDouble(fields[2]);
			String picture = fields[3];
			PlaceModel a = new PlaceModel(name, distance, duration, picture);
			places.add(a);
		}
		sc.close();

		return places;
	}
}
