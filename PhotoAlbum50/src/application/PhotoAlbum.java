package application;
import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class PhotoAlbum extends Application {
		
	public void start(Stage primaryStage)  throws Exception {                 
			FXMLLoader loader = new FXMLLoader();    
			loader.setLocation(getClass().getResource("/view/Login.fxml")); 
		    AnchorPane root = (AnchorPane)loader.load(); 
		    LoginController loginController =  loader.getController();
		    Scene scene = new Scene(root, 630, 451); 
		    primaryStage.setScene(scene);
		    loginController.start(primaryStage);
		    primaryStage.show();
		    
		} 

	public static void main(String[] args) {
		launch(args);
	}

}
