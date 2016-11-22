package controller;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import model.Album;

public class NonAdminController {
	
	@FXML Button deleteAlbum, addAlbum, editAlbum, logout;
	@FXML TextArea displayusername = new TextArea(LoginController.currentUser.name + "Albums");
	
	public void logoutButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		 
		 if(b == logout)
		 {
			// logout();
		 }
		 
	}
	
	public void pressalbumButton(ActionEvent e, Album album) {
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
			 TextInputDialog editdialog = new TextInputDialog(album.name);
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
