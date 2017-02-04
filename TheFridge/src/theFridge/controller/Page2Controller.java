package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.DAO.FoodCompostDAO;
import theFridge.DAO.ProfileDAO;
import theFridge.model.FoodCompost;
import theFridge.model.FoodCompostDatas;
import theFridge.model.User;

public class Page2Controller {
	@FXML
	private AnchorPane Anchor;
	@FXML
	private VBox naviPreview;
	@FXML
	private VBox navi;
	@FXML
	private VBox homeScene;
	@FXML
	private VBox recipeScene;
	@FXML
	private VBox compostScene;
	@FXML
	private VBox foodScene;
	@FXML
	private VBox quizScene;
	@FXML
	private VBox prizeScene;
	@FXML
	private Label chosenFood;
	@FXML
	private MediaView video;
	@FXML
	private MediaPlayer mp;
	@FXML
	private Media me;
	@FXML
	private TextField grams;
	@FXML
	private Button btnGo;
	@FXML
	private Button btnMain;
	@FXML
	private Label steps;
	@FXML
	private ImageView imagePlay;
	@FXML
	private ImageView imageFast;
	@FXML
	private ImageView imageStop;
	@FXML
	private ImageView imageFull;
	@FXML
	private JFXSlider slider;
	@FXML
	private JFXButton saveBtn;
	@FXML
	private TextFlow stacking;
	@FXML
	private JFXTextField textF;
	@FXML
	private StackPane dropdownMenu;
	@FXML
	private VBox dropdownBackground;
	@FXML
	private VBox dropdownWord;
	@FXML
	private VBox ProfileMenu;
	@FXML
	private VBox SettingMenu;
	@FXML
	private VBox LogoutMenu;
	@FXML
	private Circle profileCircle;
	
	private boolean open = false;
	@FXML
	private Label lbl;
	
	@FXML
	private StackPane sp;
	
	@FXML
	private AnchorPane apane;
	@FXML
	private VBox vboxing;
	@FXML
	private MediaView mediaa;
	@FXML
	private MediaPlayer mediap;
	@FXML
	private JFXButton buttonback;
	@FXML
	private HBox hboxingg;
	@FXML
	private JFXButton playingBtn;
	@FXML
	private ImageView ignn;
	@FXML
	private JFXSlider sliderr;
	@FXML
	private ImageView firstone;
	
	String checking = "play";
	
	public void fullVideo(MouseEvent event){
		mp.stop();
		//final Timeline slideIn = new Timeline();
		//final Timeline slideOut = new Timeline();
		/*hboxingg.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent mouseEvent){
				slideIn.play();
			}
		});
		hboxingg.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent mouseEvent){
				slideOut.play();
			}
			
		});*/
		/*HBox pannel = new HBox();
		JFXButton btn = new JFXButton("Close");
		btn.setStyle("-fx-background-color: rgba(211, 211, 206,1);");
		pannel.setStyle("-fx-background-color: rgba(211, 211, 206,1);");
		double wid = vboxing.getWidth();
		double hei = vboxing.getHeight();
		video.setFitHeight(hei);
		video.setFitWidth(wid);
		pannel.getChildren().add(btn);
		vboxing.getChildren().add(video);
		vboxing.getChildren().add(pannel);
		
		*/
		vboxing.setStyle("-fx-background-color: black;");
		double wid = vboxing.getWidth();
		double hei = vboxing.getHeight();
		mediaa.setFitWidth(wid);
		mediaa.setFitHeight(hei);
		mediap = new MediaPlayer(me);
		mediaa.setMediaPlayer(mediap);
		mediaa.setOnMouseClicked(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent event) {
					if(checking.equalsIgnoreCase("play")){
					mediap.play();
					checking = "playing";
					}
					else if(checking.equalsIgnoreCase("playing")){
						mediap.pause();
						checking = "play";
					}
					
				}
				});
		
		sliderr.setValue(mediap.getVolume() * 100);
		sliderr.valueProperty().addListener(new InvalidationListener(){
			@Override
			public void invalidated(Observable observable) {
				mediap.setVolume(sliderr.getValue() / 100);
			}
		});
		vboxing.toFront();
		
		/*mediap.setOnReady(new Runnable(){
			public void run(){
				int w = mediap.getMedia().getWidth();
				int h = mediap.getMedia().getHeight();
			
				
				slideOut.getKeyFrames().addAll(
						new KeyFrame(new Duration(0),
								new KeyValue(hboxingg.translateYProperty(), h-100),
								new KeyValue(hboxingg.opacityProperty(), 0.9)
								),
						new KeyFrame(new Duration(300),
								new KeyValue(hboxingg.translateYProperty(), h),
								new KeyValue(hboxingg.opacityProperty(), 0.0)
								)
						);
				
				
			
			slideIn.getKeyFrames().addAll(
					new KeyFrame(new Duration(0),
							new KeyValue(hboxingg.translateYProperty(), h),
							new KeyValue(hboxingg.opacityProperty(), 0.0)
							),
					new KeyFrame(new Duration(300),
							new KeyValue(hboxingg.translateYProperty(), h-100),
							new KeyValue(hboxingg.opacityProperty(), 0.9)
							)
					);
				
			
			
			}
		});	
		*/
	}
	public void showignn(MouseEvent event){
		sliderr.setVisible(true);
	}
	public void hideignn(MouseEvent event){
		sliderr.setVisible(false);
	}
	String checks = "h";
	public void playplay(ActionEvent event){
		if(checks.equals("h")){
		mediap.play();
		checks = "n";
		}
		else{
			mediap.pause();
			checks = "h";
		}
	}
	public void darker(MouseEvent event){
		hboxingg.setOpacity(1);
	}
	
	public void lighter(MouseEvent event){
		hboxingg.setOpacity(0.1);
	}
	
	public void hiding(ActionEvent event){
		mediap.stop();
		vboxing.toBack();
	}

	// Event Listener on VBox[#naviPreview].onMouseEntered
	@FXML
	public void showNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	// Event Listener on VBox[#navi].onMouseExited
	@FXML
	public void hideNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), -150);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 1);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	// Event Listener on VBox[#homeScene].onMouseClicked
	@FXML
	public void changeScene(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Page2.fxml"));
		
		
		if(event.getSource().equals(homeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/GetStartedPage.fxml"));
		}
		else if(event.getSource().equals(recipeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/FindingDish.fxml"));
		}
		else if(event.getSource().equals(compostScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
		}
		else if(event.getSource().equals(foodScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/FoodCalculatorNavigation.fxml"));
		}
		else if(event.getSource().equals(quizScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizBeginPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
			
		}
		mp.stop();
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	// Event Listener on Button[#btnGo].onAction
	@FXML
	public void go(ActionEvent event) {
		// TODO Autogenerated
	}
	public void goMain(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Page2.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
 	    mp.stop();
	}
	public void initialize() throws FileNotFoundException {
		String ded = null;
		File file=new File("src/theFridge/file/foodcheck.txt");
		Scanner sc=new Scanner(file) ;
		while(sc.hasNextLine()){
			ded = sc.nextLine();
			System.out.println(ded);
		}
		if(ded.equals("savedone")){
			@SuppressWarnings("unused")
			File file2=new File("src/theFridge/file/foodcheck.txt");
			Scanner sc2=new Scanner(file) ;
			String load = sc2.nextLine();
			FoodCompostDAO f = new FoodCompostDAO();
			FoodCompost c = new FoodCompost();
			c = f.getFoodCompost(load);
			chosenFood.setText(c.getFoodName());
			String path = new File(c.getVideoURL()).getAbsolutePath();
			me = new Media(new File(path).toURI().toString());
			mp = new MediaPlayer(me);
			video.setMediaPlayer(mp);
			slider.setValue(mp.getVolume() * 100);
			slider.valueProperty().addListener(new InvalidationListener(){
				@Override
				public void invalidated(Observable observable) {
					mp.setVolume(slider.getValue() / 100);
				}
			});
			textF.setVisible(false);
			saveBtn.setVisible(false);
			sc.close();
			sc2.close();
		}
		else{
		FoodCompostDAO f = new FoodCompostDAO();
		FoodCompost c = new FoodCompost();
		c = f.getFoodCompost(ded);
		chosenFood.setText(c.getFoodName());
		String path = new File(c.getVideoURL()).getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);
		video.setMediaPlayer(mp);
		slider.setValue(mp.getVolume() * 100);
		slider.valueProperty().addListener(new InvalidationListener(){
			@Override
			public void invalidated(Observable observable) {
				mp.setVolume(slider.getValue() / 100);
			}
		});
		}
		
	}
	
	public void saving(ActionEvent event) throws FileNotFoundException{	
		String save0 = textF.getText();
		String or = null;
		String gh = null;
		if(save0.equals("") || save0.equals("null")){
			lbl.setVisible(true);
		}
		else{
		File file1=new File("src/theFridge/file/confirm.txt");
		Scanner sc1=new Scanner(file1) ;
		String n = sc1.nextLine();
		sc1.close();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		String ff = uu.getChosenFC();
		if(ff.equals("[]")){
			or = "false";
			String newly =( "["+ save0 + "]");
			uu.setChosenFC(newly);
			profileDAO.updateUser(uu);
		}
		else{
		String replace = ff.replace("[","");
		String replace1 = replace.replace("]","");
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
		for(int i=0;i<myList.size();i++){
			String io = myList.get(i);
			io = io.replaceAll("\\s+", "");
			if(io.equals(save0)){
				System.out.println("repeat");
				or = "true";
				break;
			}
			else{
				 gh = save0;
				 or = "false";
			}
		}
			System.out.println(gh);
			myList.add(gh);
			String last = myList.toString();
			uu.setChosenFC(last);
			profileDAO.updateUser(uu);
			gh = null;
		
		
		}
		
		if(or.equals("false")){
		ArrayList<String> saving = new ArrayList<String>();
		File file9=new File("src/theFridge/file/foodcheck.txt");
		Scanner sc9=new Scanner(file9) ;
		while(sc9.hasNextLine()){
			String nn = sc9.nextLine();
			saving.add(nn);
		}
		sc9.close();
		String saved = saving.toString();
		FoodCompostDatas fc = new FoodCompostDatas();
		fc.setTitle(save0);
		fc.setFoodType(saved);
		fc.createFoodCompostDatas();
		}
		
		lbl.setVisible(true);
		lbl.setText("Successfully saved !");
		textF.setText("");
		}
		/*String save = chosenFood.getText();
		ArrayList<String> haha = new ArrayList<String>();
		haha.add(save0);
		haha.add(save);
		File file=new File("src/theFridge/file/confirm.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		uu.setChosenFC(haha);
		profileDAO.updateUser(uu);
		*/
	}
	/*public void restart(ActionEvent event){
		mp.seek(mp.getStartTime());
		mp.setRate(1);
		mp.play();
	}
	*/

	public void getGrams(ActionEvent event) throws FileNotFoundException{
		String weight= grams.getText();
		if(weight.equals("") || !(weight.matches("[0-9]+"))){
			steps.setText("Please enter a integer");
		}
		else{
		File file=new File("src/theFridge/file/foodcheck.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		FoodCompostDAO f = new FoodCompostDAO();
		FoodCompost c = new FoodCompost();
		c = f.getFoodCompost(n);
		
		String haha = c.getInstruction();
		String replace = haha.replace("[","");
		String replace1 = replace.replace("]","");
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(replace1.split("@")));
		StringBuilder msg = new StringBuilder();
			//Label label = new Label();
			for (int i = 0; i <myList.size(); i++) {
				msg.append(Integer.toString(i + 1) );
				msg.append(" ");
			    msg.append(myList.get(i));
			    msg.append("\n");
			    msg.append("\n");  //this is the new line you need
			}
			//System.out.println(msg.toString());
		String comeon = msg.toString();
		steps.setText(comeon);
		steps.setFont(Font.font ("Verdana", 24));
		sc.close();
		}
		
		
		
	}
	
	public void showVideo(MouseEvent event){
		if(imagePlay.getId() == "hello"){
			mp.pause();
			Image g = new Image("theFridge/picture/playButton.jpg");
			imagePlay.setImage(g);
			imagePlay.setId("imagePlay");
			imageFast.setVisible(false);
			imageStop.setVisible(false);
		}
		else{
		mp.play();
		mp.setRate(1);
		Image g = new Image("theFridge/picture/pauseButton.jpg");
		imagePlay.setImage(g);
		imagePlay.setId("hello");
		imageFast.setVisible(true);
		imageStop.setVisible(true);
		}
	}
	public void fastVideo(MouseEvent event){
		if(imageFast.getId() == "second"){
			mp.setRate(1);
			imageFast.setOpacity(1);
			imageFast.setId("imageFast");
		}
		else{
		mp.setRate(2);
		imageFast.setOpacity(0.5);
		imageFast.setId("second");
		}	
	}
	public void hover(MouseEvent event){
		imagePlay.setCursor(Cursor.CLOSED_HAND);
	}
	
	public void stopVideo(MouseEvent event){
		mp.stop();
		mp.setRate(1);
		Image g = new Image("theFridge/picture/playButton.jpg");
		imagePlay.setImage(g);
		imagePlay.setId("imagePlay");
		
		
	}
	/*public void fullVideo(MouseEvent event){
		DoubleProperty width = video.fitWidthProperty();
		DoubleProperty height = video.fitHeightProperty();
		width.bind(Bindings.selectDouble(video.sceneProperty(), "width"));
	    height.bind(Bindings.selectDouble(video.sceneProperty(), "height"));
	    video.setPreserveRatio(true);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		StackPane root = new StackPane();
		TextFlow tf = new TextFlow();
		JFXButton backButton = new JFXButton("Close");
		backButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent event) {
					Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page2.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stage.setScene(new Scene(root));
			 	    stage.show();
			 	    mp.stop();
					}
				});
		//tf.setPadding(new Insets(0,0,20,0));
		tf.getChildren().add(backButton);
		
		root.getChildren().add(video);
		root.getChildren().add(tf);
		Scene scene = new Scene(root, 1280, 800);
	    scene.setFill(Color.BLACK);
	    stage.setScene(scene);
	    stage.setFullScreen(true);
	    stage.show();
	    scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent event) {
					if(checking.equalsIgnoreCase("play")){
					mp.play();
					checking = "playing";
					}
					else if(checking.equalsIgnoreCase("playing")){
						mp.pause();
						checking = "play";
					}
					
				}
				});
	    
	    scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
	    () {
	          public void handle(KeyEvent t) {
	            if(t.getCode()==KeyCode.ESCAPE)
	            {
	            	mp.stop();
	            	
					}
	            }
	          
	      });
	    
	}
	*/
	
	
	public void menuChangeScene(MouseEvent event) throws IOException {
		User user = new User();
		user = user.getCurrentUser();
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = null; //(Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		
		
		if(event.getSource().equals(ProfileMenu)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
		}
		else if(event.getSource().equals(SettingMenu)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		}
		else if(event.getSource().equals(LogoutMenu)){
			user.setRememberMe(false);
			user.updateUser();
			
			stage.setX(450);
			stage.setY(128);
			stage.setWidth(1020);
			stage.setHeight(650);
			
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
			stage.setMaximized(false);
		}
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	// Event Listener on Circle[#profileCircle].onMouseClicked
	@FXML
	public void showUserDropdown(MouseEvent event) {
		RotateTransition rt = new RotateTransition(Duration.millis(400), profileCircle);
		if(open == false){
			Timeline timeline = new Timeline();
			rt.setByAngle(-360);
			rt.play();
			KeyValue CircleLeft = new KeyValue(profileCircle.translateXProperty(), -46);
			KeyValue MenuHalf = new KeyValue(dropdownMenu.translateXProperty(), -150); //Move dropdownMenu(dropdownWord & dropdownBackground) translateX to -100
			KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200), MenuHalf, CircleLeft); //1st KeyFrame with duration of 300ms
			//Wait
			KeyFrame WaitingFrame1 = new KeyFrame(
					Duration.millis(200), 
					first -> {
								Timeline timeline3 = new Timeline();
								KeyValue MenuDown = new KeyValue(dropdownMenu.translateYProperty(), 255); //Move dropdownMenu(dropdownWord & dropdownBackground) translateY to 135
								KeyValue ShowWord = new KeyValue(dropdownWord.opacityProperty(), 1);
								KeyFrame keyFrame3 = new KeyFrame(Duration.millis(200), MenuDown, ShowWord); //1st KeyFrame with duration of 300ms
								
								timeline3.getKeyFrames().addAll(keyFrame3);
								timeline3.play();
					});
						
			open = true;
			timeline.getKeyFrames().addAll(keyFrame1, WaitingFrame1);
			timeline.play();
		}
		else if(open == true){
			Timeline timeline = new Timeline();
			rt.setByAngle(360);
			rt.play();
			KeyValue MenuDown = new KeyValue(dropdownMenu.translateYProperty(), 0); //Move dropdownMenu(dropdownWord & dropdownBackground) translateY to 135
			KeyValue HideWord = new KeyValue(dropdownWord.opacityProperty(), 0);
			KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200), MenuDown, HideWord); //1st KeyFrame with duration of 300ms
			//Wait
			KeyFrame WaitingFrame1 = new KeyFrame(
					Duration.millis(200), 
					first -> {
								Timeline timeline3 = new Timeline();
								KeyValue CircleRight = new KeyValue(profileCircle.translateXProperty(), 0);
								KeyValue MenuHalf = new KeyValue(dropdownMenu.translateXProperty(), 0); //Move dropdownMenu(dropdownWord & dropdownBackground) translateX to -100
								KeyFrame keyFrame3 = new KeyFrame(Duration.millis(200), MenuHalf, CircleRight); //1st KeyFrame with duration of 300ms
								
								timeline3.getKeyFrames().addAll(keyFrame3);
								timeline3.play();
					});
						
			open = false;
			timeline.getKeyFrames().addAll(keyFrame1, WaitingFrame1);
			timeline.play();
		}
	}
	
}
