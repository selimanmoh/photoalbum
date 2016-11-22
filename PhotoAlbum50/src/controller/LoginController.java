package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class LoginController {
	
	@FXML Button login;
	@FXML TextField username = new TextField();
	public ArrayList<User> users;
	public static User currentUser;
	
	
	public void start(Stage primaryStage) {
		
		//Do serializable stuff here
		users = new ArrayList<User>();
		
		if(!users.contains(new User("Admin")))
			users.add(new User("Admin"));
		
		
	}
	
	
	
	public void loginButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		 
		 if(b == login)
		 {
			if(username.getText()==null)
			{
				Alert alert = new Alert (AlertType.INFORMATION);
	    		alert.setTitle("Login Failure");
	    		alert.setHeaderText("You must enter a username");
	    		alert.showAndWait();
			}
			
			else {
				
				if(username.getText().equals("Admin")){				     

					Scene scene = null;
					try {
						scene = new Scene(FXMLLoader.load(getClass().getResource("/view/adminhome.fxml")), 630, 451);
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
				    ((Stage)(b.getScene().getWindow())).setScene(scene);
				}
				else if(users.contains(new User(username.getText()))){
					
					currentUser = users.get(users.indexOf(new User(username.getText())));
					Scene scene = null;
					try {
						scene = new Scene(FXMLLoader.load(getClass().getResource("/view/nonadminhome.fxml")), 630, 451);
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
				    ((Stage)(b.getScene().getWindow())).setScene(scene);	
				}
				else{
					
					currentUser = new User(username.getText());
					users.add(currentUser);
					Scene scene = null;
					try {
						scene = new Scene(FXMLLoader.load(getClass().getResource("/view/nonadminhome.fxml")), 630, 451);
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
				    ((Stage)(b.getScene().getWindow())).setScene(scene);
				    AdminSystemController.start();
				}
				/*Find the user and then login
				 * load display for new admin view if user is an admin
				 * load display for nonadmin view otherwise
				 * error if user is not found
				 */
			}
		 
		 }
	 }

		
}
