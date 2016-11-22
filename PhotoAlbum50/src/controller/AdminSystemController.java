package controller;

import java.io.IOException;
import java.util.List;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

public class AdminSystemController{
	
	@FXML TextField username = new TextField();
	@FXML Button deleteUser, addUser, addUserOk, addUserCancel, deleteUserOk, deleteUserCancel;
	@FXML ListView<String> listView = new ListView<String>();
		  ObservableList<String> userList = FXCollections.observableArrayList();
		  Stage stage;

	public void start(Stage primaryStage) {
		stage = primaryStage;
		updateList();
	}
	
	public void updateList(){
		
		userList.clear();
		for(User user: LoginController.users) userList.add(user.name);
		listView.setItems(userList);
		
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
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Login.fxml"));
			AnchorPane root = null;
			try {
				root = (AnchorPane)loader.load();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			LoginController controller = loader.getController();
			controller.start(stage);
			Scene scene = new Scene(root);
			stage.setScene(scene);		 
		 }
		 
	}
	
	public void extraPress(ActionEvent e){
		
		Button b = (Button)e.getSource();
		
		if(b == deleteUserOk){
			int removedIndex = listView.getSelectionModel().getSelectedIndex();
			if(removedIndex != -1){
				LoginController.users.remove(removedIndex);
				updateList();
			}
			else{
				
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Login Failure");
				alert.setHeaderText("Nothing is selected for you to delete!");
				alert.showAndWait();
			}
			
			deleteUserOk.setDisable(true);
			deleteUserCancel.setDisable(true);
			deleteUser.setDisable(false);
			addUser.setDisable(false);
		}
		else if(b == deleteUserCancel){
			
			deleteUserOk.setDisable(true);
			deleteUserCancel.setDisable(true);
			deleteUser.setDisable(false);
			addUser.setDisable(false);
		}
		else if(b == addUserOk){
			if(username.getText() != null && !LoginController.users.contains(new User(username.getText()))){
				LoginController.users.add(new User(username.getText()));
				updateList();
			}
			else
			{
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Login Failure");
				alert.setHeaderText("You must enter a unique and proper username");
				alert.showAndWait();
			}
			
			addUserOk.setDisable(true);
			addUserCancel.setDisable(true);
			username.setDisable(true);
			addUser.setDisable(false);
			deleteUser.setDisable(false);
		}
		else if(b == addUserCancel){
			addUserOk.setDisable(true);
			addUserCancel.setDisable(true);
			username.setDisable(true);
			addUser.setDisable(false);
			deleteUser.setDisable(false);
		}
		
	}
	
}
		 
	