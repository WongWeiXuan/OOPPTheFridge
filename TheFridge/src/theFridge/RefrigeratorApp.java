package theFridge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

public class RefrigeratorApp extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("/theFridge/picture/fridge.png"));
			primaryStage.setTitle("TheFridge");
			//primaryStage.initStyle(StageStyle.UNDECORATED);
		    primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
