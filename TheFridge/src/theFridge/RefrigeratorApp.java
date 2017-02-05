package theFridge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import theFridge.model.User;
//import javafx.stage.StageStyle;

public class RefrigeratorApp extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		User user = new User();
		user = user.getCurrentUser();
		
		if(user == null){
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("/theFridge/picture/fridge.png"));
			primaryStage.setTitle("TheFridge");
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
		}
		else{
			try {
				Parent root;
				if (user.isRememberMe() == true) {
					if(user.getUsername().equals("Food From The Heart") || user.getUsername().equals("Willing Hearts") || user.getUsername().equals("Food Bank Singapore")){
						root = FXMLLoader.load(getClass().getResource("/theFridge/view/AdminPage.fxml"));
					}else{
						root = FXMLLoader.load(getClass().getResource("/theFridge/view/GetStartedPage.fxml"));
					}
						Screen screen = Screen.getPrimary();
						Rectangle2D bounds = screen.getVisualBounds();
						primaryStage.setX(bounds.getMinX());
						primaryStage.setY(bounds.getMinY());
						primaryStage.setWidth(bounds.getWidth());
						primaryStage.setHeight(bounds.getHeight());
						primaryStage.setMaximized(true);
				}
				else {
					root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
				}
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
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
