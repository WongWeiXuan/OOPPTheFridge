package theFridge.controller;

import javafx.fxml.FXML;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;

import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
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
	private Label lbl;

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
		File file=new File("src/theFridge/file/foodcheck.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		FoodCompostDAO f = new FoodCompostDAO();
		FoodCompost c = new FoodCompost();
		c = f.getFoodCompost(n);
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
	public void saving(ActionEvent event) throws FileNotFoundException{	
		String save0 = textF.getText();
		String gh = null;
		if(save0.equals("")){
			lbl.setVisible(true);
		}
		else{
		File file1=new File("src/theFridge/file/confirm.txt");
		Scanner sc1=new Scanner(file1) ;
		String n = sc1.nextLine();
		ProfileDAO profileDAO = new ProfileDAO();
		User uu = new User();
		uu = profileDAO.getUser(n);
		String ff = uu.getChosenFC();
		if(ff.equals("[]")){
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
				//gh = null;
				break;
			}
			else{
				 gh = save0;
			}
		}
			System.out.println(gh);
			myList.add(gh);
			String last = myList.toString();
			uu.setChosenFC(last);
			profileDAO.updateUser(uu);
			gh = null;
		
		
		}
		
		
		ArrayList<String> saving = new ArrayList<String>();
		File file9=new File("src/theFridge/file/foodcheck.txt");
		Scanner sc9=new Scanner(file9) ;
		while(sc9.hasNextLine()){
			String nn = sc9.nextLine();
			saving.add(nn);
		}
		String saved = saving.toString();
		FoodCompostDatas fc = new FoodCompostDatas();
		fc.setTitle(save0);
		fc.setFoodType(saved);
		fc.createFoodCompostDatas();
		
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
		//steps.setText("You have entered " + weight + "grams of food\n" + "Please give me a A FOR MY HARDWORK");
		File file=new File("src/theFridge/file/foodcheck.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		FoodCompostDAO f = new FoodCompostDAO();
		FoodCompost c = new FoodCompost();
		c = f.getFoodCompost(n);
		steps.setText(c.getInstruction());
		
		
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
	String checking = "play";
	public void fullVideo(MouseEvent event){
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
	
	
	
}
