package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AdminSystemController{
	
	TextField username = new TextField();
	@FXML Button deleteUser, addUser, addUserOk, addUserCancel, deleteUserOk, deleteUserCancel;
	
	public void pressButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		
		 
		 if (b == deleteUser)
		 {
			deleteUserOk.setDisable(false);
			deleteUserCancel.setDisable(false);
		  }
		
		 else if(b == addUser)
		 {
			addUserOk.setDisable(false);
			addUserCancel.setDisable(false);
			username.setDisable(false);
			
		 }
		 
	}
	
}
		 
	