package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
	
	@FXML Button login;
	@FXML TextField username = new TextField();
	
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
				/*Find the user and then login
				 * load display for new admin view if user is an admin
				 * load display for nonadmin view otherwise
				 * error if user is not found
				 */
			}
		 
		 }
	 }
		
}
