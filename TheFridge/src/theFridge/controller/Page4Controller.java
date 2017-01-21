package theFridge.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
	
	public void backToMain(ActionEvent event) throws IOException{
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page3.fxml"));
		stage.setScene(new Scene(root));
 	    stage.show();
	}
}
