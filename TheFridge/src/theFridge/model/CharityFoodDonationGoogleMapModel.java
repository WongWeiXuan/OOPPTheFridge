package theFridge.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocoderGeometry;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import theFridge.DAO.MapPlacesDAO;
import theFridge.controller.CharityFoodDonationGoogleMapController;

public class CharityFoodDonationGoogleMapModel implements DirectionsServiceCallback {
	private GoogleMapView mapView;
    
    private GoogleMap map;
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    MapOptions mapOptions;
    private VBox vboxInScroll;
    private VBox donationVbox;
    private JFXButton donateBtn;
	private JFXButton moreInfoBtn;
	private String name;
	public static String OrganizationTxt;
	private LatLong currentLatLong;

	public CharityFoodDonationGoogleMapModel(GoogleMapView mapView, VBox vboxInScroll, VBox donationVbox,
			JFXButton donateBtn, JFXButton moreInfoBtn) {
		super();
		this.mapView = mapView;
		this.vboxInScroll = vboxInScroll;
		this.donationVbox = donationVbox;
		this.donateBtn = donateBtn;
		this.moreInfoBtn = moreInfoBtn;
	}

	public void initializeMap() throws FileNotFoundException, InterruptedException{
		MapOptions mapOptions = new MapOptions();
        User u = new User();
        u = u.getCurrentUser();
        //Need to convert address to LatLong in order to use profile's location
        mapOptions.mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .streetViewControl(false)
                .rotateControl(true)
                .scaleControl(true)
                .zoomControl(true)
                .panControl(true)
                .mapMarker(true)
                .zoom(12)
                .mapMarker(true);
        
        this.mapOptions = mapOptions;
        map = mapView.createMap(mapOptions);
        
        //System.out.println(CharityFoodDonationGoogleMapController.results.getRoutes().get(0).getLegs().get(0).getStartLocation() + " HEofeajf");
        
        //LatLong xuanZhengLocation = new LatLong(1.374867, 103.883622);
        LatLong foodFromTheHeartLocation = new LatLong(1.337480, 103.882905);
        LatLong willingHeartsLocation = new LatLong(1.317637, 103.900296);
        LatLong foodBin1Location = new LatLong(1.301408, 103.905295); //Giant Hypermarket - Parkway Parade, 03-27/28, Parkway Parade, 80 Marine Parade Rd, Din Tai Fung, Singapore 449269
        LatLong foodBin2Location = new LatLong(1.354205, 103.945022); //Tampines 1, 10 Tampines Central 1, 529536
        LatLong foodBin3Location = new LatLong(1.352739, 103.943836); //Century Square, 2 Tampines Central 5, Singapore 529509
        LatLong foodBin4Location = new LatLong(1.313859, 103.877551); //100 Sims Ave, 387426
        LatLong foodBin5Location = new LatLong(1.372469, 103.893780); //90 Hougang Avenue
        LatLong foodBin6Location = new LatLong(1.300259, 103.770791); //NUS Engineering, 9 Engineering Drive 1, Blk EA, #06-10, 117575
        LatLong foodBin7Location = new LatLong(1.294204, 103.771121); //NUS Arts & Social Sciences, 5 Arts Link, Block AS7, Level 5 The Shaw Foundation Building, 117570
        LatLong foodBin8Location = new LatLong(1.295048, 103.773841); //NUS Computing, 13 Computing Dr, Singapore 117417
        LatLong foodBin9Location = new LatLong(1.337892, 103.793338); //The Grandstand, 200 Turf Club Rd, Singapore 287994
        LatLong foodBin10Location = new LatLong(1.331880, 103.744881); //BIG BOX, 1 Venture Ave, Singapore 608521
        LatLong foodBin11Location = new LatLong(1.311387, 103.856609); //City Square Mall, 180 Kitchener Rd, Singapore 208539
        LatLong foodBin12Location = new LatLong(1.291532, 103.844609); //Armf II (Liang Court) Pte Ltd, 177 River Valley Rd, Singapore 179030
        LatLong foodBin13Location = new LatLong(1.247633, 103.842170); //Quayside Isle, 31 Ocean Way, Sentosa Cove, Singapore, 098375
        LatLong foodBin14Location = new LatLong(1.264699, 103.823083); //Giant, 1 Harbourfront Walk, 23, VivoCity, Singapore 098585
        LatLong foodBin15Location = new LatLong(1.264241, 103.822327); //VivoCity, #02-123/124, 1 Harbourfront Walk, Singapore 098585
        LatLong foodBin16Location = new LatLong(1.288735, 103.812196); //Chrysler Jeep Automotive of Singapore Pte Ltd, 1 Chang Charn Rd, Singapore 159630
        LatLong foodBankWarehouseLocation = new LatLong(1.271822, 103.837480); //The Food Bank Singapore Ltd, 39 Keppel Road #03-08, Tanjong Pagar Distripark, Singapore 089065
        
       // MarkerOptions markerOptions2 = new MarkerOptions()
        		//.position(xuanZhengLocation)
        		//.animation(Animation.DROP);
        
        MarkerOptions markerOptions3 = new MarkerOptions()
        		.position(foodFromTheHeartLocation)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions4 = new MarkerOptions()
        		.position(willingHeartsLocation)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions5 = new MarkerOptions()
        		.position(foodBin1Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions6 = new MarkerOptions()
        		.position(foodBin2Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions7 = new MarkerOptions()
        		.position(foodBin3Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions8 = new MarkerOptions()
        		.position(foodBin4Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions9 = new MarkerOptions()
        		.position(foodBin5Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions10 = new MarkerOptions()
        		.position(foodBin6Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions11 = new MarkerOptions()
        		.position(foodBin7Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions12 = new MarkerOptions()
        		.position(foodBin8Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions13 = new MarkerOptions()
        		.position(foodBin9Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions14 = new MarkerOptions()
        		.position(foodBin10Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions15 = new MarkerOptions()
        		.position(foodBin11Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions16 = new MarkerOptions()
        		.position(foodBin12Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions17 = new MarkerOptions()
        		.position(foodBin13Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions18 = new MarkerOptions()
        		.position(foodBin14Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions19 = new MarkerOptions()
        		.position(foodBin15Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions20 = new MarkerOptions()
        		.position(foodBin16Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions21 = new MarkerOptions()
        		.position(foodBankWarehouseLocation)
        		.animation(Animation.DROP);
        
        //Marker xuanZhengMarker = new Marker(markerOptions2);
        Marker foodFromTheHeartMarker = new Marker(markerOptions3);
        Marker willingHeartsMarker= new Marker(markerOptions4);
        Marker foodBin1Marker = new Marker(markerOptions5);
        Marker foodBin2Marker = new Marker(markerOptions6);
        Marker foodBin3Marker = new Marker(markerOptions7);
        Marker foodBin4Marker = new Marker(markerOptions8);
        Marker foodBin5Marker = new Marker(markerOptions9);
        Marker foodBin6Marker = new Marker(markerOptions10);
        Marker foodBin7Marker = new Marker(markerOptions11);
        Marker foodBin8Marker = new Marker(markerOptions12);
        Marker foodBin9Marker = new Marker(markerOptions13);
        Marker foodBin10Marker = new Marker(markerOptions14);
        Marker foodBin11Marker = new Marker(markerOptions15);
        Marker foodBin12Marker = new Marker(markerOptions16);
        Marker foodBin13Marker = new Marker(markerOptions17);
        Marker foodBin14Marker = new Marker(markerOptions18);
        Marker foodBin15Marker = new Marker(markerOptions19);
        Marker foodBin16Marker = new Marker(markerOptions20);
        Marker foodBankWarehouseMarker = new Marker(markerOptions21);
        
        //map.addMarker(xuanZhengMarker);
        map.addMarker(foodFromTheHeartMarker);
        map.addMarker(willingHeartsMarker);
        map.addMarker(foodBin1Marker);
        map.addMarker(foodBin2Marker);
        map.addMarker(foodBin3Marker);
        map.addMarker(foodBin4Marker);
        map.addMarker(foodBin5Marker);
        map.addMarker(foodBin6Marker);
        map.addMarker(foodBin7Marker);
        map.addMarker(foodBin8Marker);
        map.addMarker(foodBin9Marker);
        map.addMarker(foodBin10Marker);
        map.addMarker(foodBin11Marker);
        map.addMarker(foodBin12Marker);
        map.addMarker(foodBin13Marker);
        map.addMarker(foodBin14Marker);
        map.addMarker(foodBin15Marker);
        map.addMarker(foodBin16Marker);
        map.addMarker(foodBankWarehouseMarker);
        
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
        
        setTextLabels(directionsService);
        donationVbox.setVisible(false);
        
        DirectionsRequest request = new DirectionsRequest(u.getCountry(), u.getCountry(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
    }
	
	private void addMarkerAfterCreating(GoogleMap map, String name){
		//LatLong currentLocation = new LatLong(1.379256, 103.849670);
        //LatLong xuanZhengLocation = new LatLong(1.374867, 103.883622);
		LatLong foodFromTheHeartLocation = new LatLong(1.337480, 103.882905);
        LatLong willingHeartsLocation = new LatLong(1.317637, 103.900296);
        LatLong foodBin1Location = new LatLong(1.301408, 103.905295); //Giant Hypermarket - Parkway Parade, 03-27/28, Parkway Parade, 80 Marine Parade Rd, Din Tai Fung, Singapore 449269
        LatLong foodBin2Location = new LatLong(1.354205, 103.945022); //Tampines 1, 10 Tampines Central 1, 529536
        LatLong foodBin3Location = new LatLong(1.352739, 103.943836); //Century Square, 2 Tampines Central 5, Singapore 529509
        LatLong foodBin4Location = new LatLong(1.313859, 103.877551); //100 Sims Ave, 387426
        LatLong foodBin5Location = new LatLong(1.372469, 103.893780); //90 Hougang Avenue
        LatLong foodBin6Location = new LatLong(1.300259, 103.770791); //NUS Engineering, 9 Engineering Drive 1, Blk EA, #06-10, 117575
        LatLong foodBin7Location = new LatLong(1.294204, 103.771121); //NUS Arts & Social Sciences, 5 Arts Link, Block AS7, Level 5 The Shaw Foundation Building, 117570
        LatLong foodBin8Location = new LatLong(1.295048, 103.773841); //NUS Computing, 13 Computing Dr, Singapore 117417
        LatLong foodBin9Location = new LatLong(1.337892, 103.793338); //The Grandstand, 200 Turf Club Rd, Singapore 287994
        LatLong foodBin10Location = new LatLong(1.331880, 103.744881); //BIG BOX, 1 Venture Ave, Singapore 608521
        LatLong foodBin11Location = new LatLong(1.311387, 103.856609); //City Square Mall, 180 Kitchener Rd, Singapore 208539
        LatLong foodBin12Location = new LatLong(1.291532, 103.844609); //Armf II (Liang Court) Pte Ltd, 177 River Valley Rd, Singapore 179030
        LatLong foodBin13Location = new LatLong(1.247633, 103.842170); //Quayside Isle, 31 Ocean Way, Sentosa Cove, Singapore, 098375
        LatLong foodBin14Location = new LatLong(1.264699, 103.823083); //Giant, 1 Harbourfront Walk, 23, VivoCity, Singapore 098585
        LatLong foodBin15Location = new LatLong(1.264241, 103.822327); //VivoCity, #02-123/124, 1 Harbourfront Walk, Singapore 098585
        LatLong foodBin16Location = new LatLong(1.288735, 103.812196); //Chrysler Jeep Automotive of Singapore Pte Ltd, 1 Chang Charn Rd, Singapore 159630
        LatLong foodBankWarehouseLocation = new LatLong(1.271822, 103.837480); //The Food Bank Singapore Ltd, 39 Keppel Road #03-08, Tanjong Pagar Distripark, Singapore 089065
        
        
       // MarkerOptions markerOptions1 = new MarkerOptions()
        		//.position(currentLocation)
        		//.animation(Animation.BOUNCE);
        
       // MarkerOptions markerOptions2 = new MarkerOptions()
        		//.position(xuanZhengLocation)
        		//.animation(Animation.DROP);
        
        MarkerOptions markerOptions3 = new MarkerOptions()
        		.position(foodFromTheHeartLocation)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions4 = new MarkerOptions()
        		.position(willingHeartsLocation)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions5 = new MarkerOptions()
        		.position(foodBin1Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions6 = new MarkerOptions()
        		.position(foodBin2Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions7 = new MarkerOptions()
        		.position(foodBin3Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions8 = new MarkerOptions()
        		.position(foodBin4Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions9 = new MarkerOptions()
        		.position(foodBin5Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions10 = new MarkerOptions()
        		.position(foodBin6Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions11 = new MarkerOptions()
        		.position(foodBin7Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions12 = new MarkerOptions()
        		.position(foodBin8Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions13 = new MarkerOptions()
        		.position(foodBin9Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions14 = new MarkerOptions()
        		.position(foodBin10Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions15 = new MarkerOptions()
        		.position(foodBin11Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions16 = new MarkerOptions()
        		.position(foodBin12Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions17 = new MarkerOptions()
        		.position(foodBin13Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions18 = new MarkerOptions()
        		.position(foodBin14Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions19 = new MarkerOptions()
        		.position(foodBin15Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions20 = new MarkerOptions()
        		.position(foodBin16Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions21 = new MarkerOptions()
        		.position(foodBankWarehouseLocation)
        		.animation(Animation.DROP);
        
        //Marker currentMarker = new Marker(markerOptions1);
        //Marker xuanZhengMarker = new Marker(markerOptions2);
        Marker foodFromTheHeartMarker = new Marker(markerOptions3);
        Marker willingHeartsMarker= new Marker(markerOptions4);
        Marker foodBin1Marker = new Marker(markerOptions5);
        Marker foodBin2Marker = new Marker(markerOptions6);
        Marker foodBin3Marker = new Marker(markerOptions7);
        Marker foodBin4Marker = new Marker(markerOptions8);
        Marker foodBin5Marker = new Marker(markerOptions9);
        Marker foodBin6Marker = new Marker(markerOptions10);
        Marker foodBin7Marker = new Marker(markerOptions11);
        Marker foodBin8Marker = new Marker(markerOptions12);
        Marker foodBin9Marker = new Marker(markerOptions13);
        Marker foodBin10Marker = new Marker(markerOptions14);
        Marker foodBin11Marker = new Marker(markerOptions15);
        Marker foodBin12Marker = new Marker(markerOptions16);
        Marker foodBin13Marker = new Marker(markerOptions17);
        Marker foodBin14Marker = new Marker(markerOptions18);
        Marker foodBin15Marker = new Marker(markerOptions19);
        Marker foodBin16Marker = new Marker(markerOptions20);
        Marker foodBankWarehouseMarker = new Marker(markerOptions21);
        
        map.addMarker(foodFromTheHeartMarker);
        map.addMarker(willingHeartsMarker);
        map.addMarker(foodBin1Marker);
        map.addMarker(foodBin2Marker);
        map.addMarker(foodBin3Marker);
        map.addMarker(foodBin4Marker);
        map.addMarker(foodBin5Marker);
        map.addMarker(foodBin6Marker);
        map.addMarker(foodBin7Marker);
        map.addMarker(foodBin8Marker);
        map.addMarker(foodBin9Marker);
        map.addMarker(foodBin10Marker);
        map.addMarker(foodBin11Marker);
        map.addMarker(foodBin12Marker);
        map.addMarker(foodBin13Marker);
        map.addMarker(foodBin14Marker);
        map.addMarker(foodBin15Marker);
        map.addMarker(foodBin16Marker);
        map.addMarker(foodBankWarehouseMarker);
        
        donationVbox.setVisible(true);
        if(name.equalsIgnoreCase("Hougang Mall")){
        	map.removeMarker(foodBin5Marker);
        	donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("Food From The Heart")){
			map.removeMarker(foodFromTheHeartMarker);
			donateBtn.setVisible(true);
		}
		else if(name.equalsIgnoreCase("Willing Hearts")){
			map.removeMarker(willingHeartsMarker);
			donateBtn.setVisible(true);
		}
		else if(name.equalsIgnoreCase("Parkway Parade")){
			map.removeMarker(foodBin1Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("Tampines 1")){
			map.removeMarker(foodBin2Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("Century Square")){
			map.removeMarker(foodBin3Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("100 Sims Ave")){
			map.removeMarker(foodBin4Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("NUS Engineering")){
			map.removeMarker(foodBin6Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("NUS Arts & Social Sciences")){
			map.removeMarker(foodBin7Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("NUS Computing")){
			map.removeMarker(foodBin8Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("The Grandstand")){
			map.removeMarker(foodBin9Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("BIG BOX")){
			map.removeMarker(foodBin10Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("City Square Mall")){
			map.removeMarker(foodBin11Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("Armf II (Liang Court) Pte Ltd")){
			map.removeMarker(foodBin12Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("Quayside Isle")){
			map.removeMarker(foodBin13Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("Giant, VivoCity")){
			map.removeMarker(foodBin14Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("VivoCity")){
			map.removeMarker(foodBin15Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("Chrysler Jeep Automotive of Singapore Pte Ltd")){
			map.removeMarker(foodBin16Marker);
			donateBtn.setVisible(false);
		}
		else if(name.equalsIgnoreCase("Food Bank Singapore")){
			map.removeMarker(foodBankWarehouseMarker);
			donateBtn.setVisible(true);
		}
        
	}
	
	public ArrayList<PlaceModel> PlaceModelArrayListBasedOnTimeAndDistance(ArrayList<PlaceModel>placeList){
		ArrayList<PlaceModel> pL = new ArrayList<PlaceModel>();
		
		PlaceModel highestModel = null;
		while(pL.size() <= 18){
			double num = 0;
			for(int i =0; i < placeList.size(); i++){
				if(placeList.get(i).getDuration() > num){
					num = placeList.get(i).getDuration();
					highestModel = placeList.get(i);
				}
			}
			placeList.remove(highestModel);
			pL.add(highestModel);
		}
		
		Collections.reverse(pL);
		pL.remove(0);
		return pL;
	}
	
	public void setTextLabels(DirectionsService directionsService) throws FileNotFoundException{
		MapPlacesDAO dao = new MapPlacesDAO();
		ArrayList<PlaceModel>placeList = PlaceModelArrayListBasedOnTimeAndDistance(dao.getAllPlace());
		for(int i = 0; i < placeList.size(); i++){
			String name = placeList.get(i).getName();
			ImageView imageView = new ImageView();
			imageView.setImage(new Image(placeList.get(i).getPicture()));
			imageView.setFitWidth(200);
			imageView.setFitHeight(150);
			Label PlaceNameLbl = new Label("Destination: " + placeList.get(i).getName());
			Label DistanceLbl = new Label(String.valueOf("Distance: " + placeList.get(i).getDistance()) + "km");
			Label EtaLbl = new Label(String.valueOf("ETA: " + placeList.get(i).getDuration()) + "mins");
			PlaceNameLbl.setTextFill(Color.BLACK);
			DistanceLbl.setTextFill(Color.BLACK);
			EtaLbl.setTextFill(Color.BLACK);
			VBox vbox = new VBox(imageView, PlaceNameLbl, DistanceLbl, EtaLbl);
			vbox.setStyle("-fx-padding: 20 0 0 0; -fx-border-insets: 20 0 0 0; -fx-background-insets: 20 0 0 0;");
			vbox.setAlignment(Pos.TOP_CENTER);
			StackPane stackPane = new StackPane(vbox);
			stackPane.setPrefHeight(200);
			stackPane.setStyle(
					"-fx-padding: 20 30 0 30; -fx-border-insets: 20 30 0 30; -fx-background-insets: 20 30 0 30; -fx-background-color: white; -fx-background-radius: 20;"
			);
			stackPane.setOnMouseClicked((MouseEvent event) -> {
				User u = new User();
				try {
					u = u.getCurrentUser();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String destination = getDestination(name);
		        DirectionsRequest request = new DirectionsRequest(u.getCountry(), destination, TravelModes.DRIVING);
		        DirectionsRenderer directionsDisplay = new DirectionsRenderer(true, mapView.getMap(), directionsPane);
		        GoogleMap map2 = mapView.createMap(mapOptions);
		        addMarkerAfterCreating(map2, name);
		        directionsDisplay.setMap(map2);
		        directionsService.getRoute(request, this, directionsDisplay);
		        this.name = name;
			});
			
			vboxInScroll.getChildren().add(stackPane);
		}
	}

	public String getDestination(String name){
		if(name.equalsIgnoreCase("Hougang Mall")){
			return "Hougang Mall, 90 Hougang Ave 10, Singapore 538766";
		}
		else if(name.equalsIgnoreCase("Food From The Heart")){
			return "Food From The Heart, 130 Joo Seng Road #03-01, 368357";
		}
		else if(name.equalsIgnoreCase("Willing Hearts")){
			return "Willing Hearts, 11 Jalan Ubi Blk 6, #01-51 Chai Chee Community Hub, 409074";
		}
		else if(name.equalsIgnoreCase("Parkway Parade")){
			return "Giant Hypermarket - Parkway Parade, 03-27/28, Parkway Parade, 80 Marine Parade Rd, Din Tai Fung, Singapore 449269";
		}
		else if(name.equalsIgnoreCase("Tampines 1")){
			return "Tampines 1, 10 Tampines Central 1, 529536";
		}
		else if(name.equalsIgnoreCase("Century Square")){
			return "Century Square, 2 Tampines Central 5, Singapore 529509";
		}
		else if(name.equalsIgnoreCase("100 Sims Ave")){
			return "100 Sims Ave, 387426";
		}
		else if(name.equalsIgnoreCase("NUS Engineering")){
			return "NUS Engineering, 9 Engineering Drive 1, Blk EA, #06-10, 117575";
		}
		else if(name.equalsIgnoreCase("NUS Arts & Social Sciences")){
			return "NUS Arts & Social Sciences, 5 Arts Link, Block AS7, Level 5 The Shaw Foundation Building, 117570";
		}
		else if(name.equalsIgnoreCase("NUS Computing")){
			return "NUS Computing, 13 Computing Dr, Singapore 117417";
		}
		else if(name.equalsIgnoreCase("The Grandstand")){
			return "The Grandstand, 200 Turf Club Rd, Singapore 287994";
		}
		else if(name.equalsIgnoreCase("BIG BOX")){
			return "BIG BOX, 1 Venture Ave, Singapore 608521";
		}
		else if(name.equalsIgnoreCase("City Square Mall")){
			return "City Square Mall, 180 Kitchener Rd, Singapore 208539";
		}
		else if(name.equalsIgnoreCase("Armf II (Liang Court) Pte Ltd")){
			return "Armf II (Liang Court) Pte Ltd, 177 River Valley Rd, Singapore 179030";
		}
		else if(name.equalsIgnoreCase("Quayside Isle")){
			return "Quayside Isle, 31 Ocean Way, Sentosa Cove, Singapore, 098375";
		}
		else if(name.equalsIgnoreCase("Giant, VivoCity")){
			return "Giant, 1 Harbourfront Walk, 23, VivoCity, Singapore 098585";
		}
		else if(name.equalsIgnoreCase("VivoCity")){
			return "VivoCity, #02-123/124, 1 Harbourfront Walk, Singapore 098585";
		}
		else if(name.equalsIgnoreCase("Chrysler Jeep Automotive of Singapore Pte Ltd")){
			return "Chrysler Jeep Automotive of Singapore Pte Ltd, 1 Chang Charn Rd, Singapore 159630";
		}
		else if(name.equalsIgnoreCase("Food Bank Singapore")){
			return "The Food Bank Singapore Ltd, 39 Keppel Road #03-08, Tanjong Pagar Distripark, Singapore 089065";
		}
		else{
			return null;
		}
	}

	public void displayMoreInfo(){
		
		if(name.equalsIgnoreCase("Hougang Mall")){
		}
		else if(name.equalsIgnoreCase("Food From The Heart")){
		}
		else if(name.equalsIgnoreCase("Willing Hearts")){
		}
		else if(name.equalsIgnoreCase("Parkway Parade")){
		}
		else if(name.equalsIgnoreCase("Tampines 1")){
		}
		else if(name.equalsIgnoreCase("Century Square")){
		}
		else if(name.equalsIgnoreCase("100 Sims Ave")){
		}
		else if(name.equalsIgnoreCase("NUS Engineering")){
		}
		else if(name.equalsIgnoreCase("NUS Arts & Social Sciences")){
		}
		else if(name.equalsIgnoreCase("NUS Computing")){
		}
		else if(name.equalsIgnoreCase("The Grandstand")){
		}
		else if(name.equalsIgnoreCase("BIG BOX")){
		}
		else if(name.equalsIgnoreCase("City Square Mall")){
		}
		else if(name.equalsIgnoreCase("Armf II (Liang Court) Pte Ltd")){
		}
		else if(name.equalsIgnoreCase("Quayside Isle")){
		}
		else if(name.equalsIgnoreCase("Giant, VivoCity")){
		}
		else if(name.equalsIgnoreCase("VivoCity")){
		}
		else if(name.equalsIgnoreCase("Chrysler Jeep Automotive of Singapore Pte Ltd")){
		}
		else if(name.equalsIgnoreCase("Food Bank Singapore")){
		}
	}

	
	@Override
	public void directionsReceived(DirectionsResult arg0, DirectionStatus arg1) {
		CharityFoodDonationGoogleMapController.results = arg0;
		this.currentLatLong = arg0.getRoutes().get(0).getOverviewPath().get(0);
	}
}
