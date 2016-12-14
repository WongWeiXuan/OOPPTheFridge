package theFridge.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;

public class ProfileController {

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
	private Button btnProfile1;
    
	@FXML
	private Button btnProfile2;
	
	@FXML
	private Label NLabel;
    
    @FXML
	private Label UNLabel;
    
    @FXML
	private Label PassLabel;
    
    @FXML
	private Label EMLabel;
    
    @FXML
    private Label LLabel;
    
    @FXML
    private VBox hideUN;
    
    @FXML
    private VBox hidePass1;
    
    @FXML
    private VBox hidePass2;
    
    @FXML
    private VBox hidePass3;
    
    @FXML
    private VBox hideEM;
    
	public void changeScene(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		
		
		if(event.getSource().equals(homeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		}
		else if(event.getSource().equals(recipeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RecipePage.fxml"));
		}
		else if(event.getSource().equals(compostScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
		}
		else if(event.getSource().equals(foodScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListPage.fxml"));
		}
		else if(event.getSource().equals(quizScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}

 		stage.setScene(new Scene(root));
 	    stage.show();
	}
    @FXML
	public void hideNavigation(MouseEvent event){
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), -150);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 1);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}

    @FXML
	public void showNavigation(MouseEvent event){
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	@FXML public void changeImage(ActionEvent event) {}
	@FXML public void showUN(ActionEvent event) {}
	
	//public void save1(ActionEvent event){
	//	String username=UNText.getText();
	//	UNLabel.setText(username);
	//	UNText.setText("");
	//}
	//public void save2(ActionEvent event){
	//	String password=PassText.getText();
	//	PassLabel.setText(password);
	//	PassText.setText("");
		
	//}
	//public void save3(ActionEvent event){
	//	String email=EMText.getText();
	//	EMLabel.setText(email);
	//	EMText.setText("");
		
	//}
	public void goToSettings(ActionEvent event)throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
}
	public void goToMain(ActionEvent event)throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
}
	


}
