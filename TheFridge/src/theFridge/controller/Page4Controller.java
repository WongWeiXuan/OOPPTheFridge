package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import theFridge.DAO.FoodCompostDAO;
import theFridge.model.FoodCompost;

public class Page4Controller {
	@FXML
	private Label label0;
	@FXML
	private Button buttonClose;
	@FXML
	private JFXButton play;
	@FXML
	private JFXButton pause;
	@FXML
	private JFXButton stop;
	@FXML
	private JFXButton fast;
	@FXML
	private JFXButton slow;
	@FXML
	private MediaPlayer mp;
	@FXML
	private Media me;
	@FXML
	private MediaView video;
	@FXML
	private AnchorPane darren1;
	@FXML
	private JFXButton darren;
	@FXML
	private VBox nick;
	
	private String darrenwhy = "haha";
	
	public void darrenlights(ActionEvent event){
		if(darrenwhy.equals("haha")){
			nick.setStyle("-fx-background-color: black");
			label0.setStyle("-fx-text-fill: #FFFFFF");
			darrenwhy = "hahaha";
		}
		else{
			nick.setStyle("-fx-background-color: white");
			label0.setStyle("-fx-text-fill: #000000");
			darrenwhy = "haha";
		}
	}
	
	public void initialize() throws FileNotFoundException{
		File file=new File("src/theFridge/file/video.txt");
		Scanner sc=new Scanner(file) ;
		String n = sc.nextLine();
		FoodCompostDAO f = new FoodCompostDAO();
		FoodCompost c = new FoodCompost();
		c = f.getFoodCompost(n);
		label0.setText("You have Selected : " + c.getFoodName());
		String path = new File(c.getVideoURL()).getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);
		video.setMediaPlayer(mp);
		sc.close();
	}
	public void backToMain(ActionEvent event) throws IOException{
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page3.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
 	   mp.stop();
	}
	public void play1(ActionEvent event){
		mp.play();
		mp.setRate(1);
	}
	
	public void pause1(ActionEvent event){
		mp.pause();
	}
	public void fast1(ActionEvent event){
		mp.setRate(2);
	}
	public void slow1(ActionEvent event){
		mp.setRate(0.5);
	}
	public void stop1(ActionEvent event){
		mp.stop();
		mp.setRate(1);
	}
}
