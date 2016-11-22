package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.User;

public class AdminSystemController{
	
	TextField username = new TextField();
	@FXML Button deleteUser, addUser, addUserOk, addUserCancel, deleteUserOk, deleteUserCancel;
	@FXML ListView<String> listView = new ListView<String>();
	public static ObservableList<String> userList;

	public static void start() {
	
		userList = FXCollections.observableArrayList();
		
	}	
	
	public void pressButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		
		 
		 if (b == deleteUser)
		 {
			deleteUserOk.setDisable(false);
			deleteUserCancel.setDisable(false);
			deleteUser.setDisable(true);
			addUser.setDisable(true);
		  }
		
		 else if(b == addUser)
		 {
			addUserOk.setDisable(false);
			addUserCancel.setDisable(false);
			username.setDisable(false);
			addUser.setDisable(true);
			deleteUser.setDisable(true);
		 }
		 else{
			 //logout
			 LoginController.currentUser = null; //current user is reset
			 Scene scene = null;
			
			 try {
				 scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Login.fxml")), 630, 451);
				} catch (IOException e1) {
				 e1.printStackTrace();
				} 
			 ((Stage)(b.getScene().getWindow())).setScene(scene);			 
		 }
		 
	}
	
}
		 
	