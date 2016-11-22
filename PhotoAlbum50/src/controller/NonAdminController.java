package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Album;

public class NonAdminController {
	
	@FXML Button deleteAlbum, addAlbum, editAlbum, logout;
	@FXML Label displayusername;
	@FXML ListView<String> listView = new ListView<String>();
	ObservableList<String> albumList = FXCollections.observableArrayList();
	Stage stage;
	ArrayList<Album> albums;

	public void start(Stage primaryStage) {
		stage = primaryStage;
		albums = LoginController.currentUser.albums;
		displayusername.setText(LoginController.currentUser.name + " Albums");
		
		int i = 0;
		while(albums != null && i < albums.size()){
			albumList.add("Name: " + albums.get(i).name +"\n Number of photos: " + albums.get(i).photos.size() + "\n Oldest Photo Date: " + )
		}
	}
	
	public Date 
	
	public void logoutButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		 
		 if(b == logout)
		 {
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
	
	public void pressalbumButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		
		 
		 if (b == deleteAlbum)
		 {
			 Alert alert = new Alert(AlertType.INFORMATION);
	 		 alert.setTitle("Delete Album");
	 		 alert.setHeaderText("Are you sure you want to delete this album?");
	 		 alert.setContentText("Album Name: ");

	 		 Optional<ButtonType> result = alert.showAndWait();
	 		   
	 		  if (result.isPresent() && result.get() == ButtonType.OK) {
	 		     //deleteAlbum();
	 		 }
	 		  
		 }

	 	
		 else if(b == addAlbum)
		 {
			 TextInputDialog adddialog = new TextInputDialog("Album name");
			 //dialog.initOwner(mainStage); 
			 adddialog.setTitle("Create Album");
			 adddialog.setHeaderText("Enter the name of the album you want to create");
			 adddialog.setContentText("Enter name: "); 
			 
			 Optional<String> result = adddialog.showAndWait();
			 if (result.isPresent())
			 { 
				//obsList.set(index, result.get()); 
			 } 
		 }
		 
		 else if(b == editAlbum)
		 {
			 TextInputDialog editdialog = new TextInputDialog(LoginController.currentUser.albums.get(0).name);
			 //dialog.initOwner(mainStage); 
			 editdialog.setTitle("Edit Album Name");
			 editdialog.setHeaderText("Edit Album Name");
			 editdialog.setContentText("Album name: ");  
			 
			 Optional<String> result = editdialog.showAndWait();
			 if (result.isPresent())
			 { 
				//obsList.set(index, result.get()); 
			 } 
		 }
		 
	}


}
