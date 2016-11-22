package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class AlbumController {
	
	@FXML Button deletePhoto, addPhoto, editCaption, tag, movePhoto, displayPhoto, logout;
	@FXML ListView<Image> listView = new ListView<Image>();
	ObservableList<Image> photoList = FXCollections.observableArrayList();
	Stage stage;
	int albumIndex;
	
	public void start(Stage primaryStage, Album primaryAlbum) {
		// TODO Auto-generated method stub
		stage = primaryStage;
		albumIndex = LoginController.currentUser.albums.indexOf(primaryAlbum);
		
		listView.setCellFactory(param -> new ListCell<Image>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Image photo, boolean empty) {
                super.updateItem(photo, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    imageView.setImage(photo);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    setGraphic(imageView);
                    
                }
                
                
            }
        });
	}
	
	public void updateList(){
		photoList.clear();
		for(Photo photo: LoginController.currentUser.albums.get(albumIndex).photos) photoList.add(photo.thumbnail);
		
		listView.setItems(photoList);
	}
	
	public void pressButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		 
		 if(b==logout)
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
		 
		 else if(b==addPhoto)
		 {
			FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");

		    FileChooser fileChooser = new FileChooser();
		    fileChooser.getExtensionFilters().add(imageFilter);
		    File file = null;
		    file = fileChooser.showOpenDialog(stage);
		    String imgURL = null;
		    try {
				imgURL = file.toURI().toURL().toExternalForm();
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		   
		    
		    	LoginController.currentUser.albums.get(albumIndex).photos.add(new Photo(new Image(imgURL)));
		    	updateList();
		    }
	}
	
	public void pressphotoButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		 
		 if(b==deletePhoto)
		 {
			 int removedIndex = listView.getSelectionModel().getSelectedIndex();
			 
			 if(removedIndex == -1){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Option failure");
				alert.setHeaderText("Nothing is selected for you to delete!");
				alert.showAndWait();
			 }
			 else{
				 Alert alert = new Alert(AlertType.INFORMATION);
				 alert.setTitle("Delete Album");
				 alert.setHeaderText("Are you sure you want to delete this photo?");
				 Optional<ButtonType> result = alert.showAndWait();
	 		   
		 		  if (result.isPresent() && result.get() == ButtonType.OK) {
			 			 LoginController.currentUser.albums.get(albumIndex).photos.remove(LoginController.currentUser.albums.get(albumIndex).photos.get(removedIndex));
			 			 updateList();
	 		  }
	 		 
	 		   

	 		 }
		 }
		 
		 else if(b == editCaption)
		 {
			 /*TextInputDialog photoeditdialog = new TextInputDialog(photo.caption);
			 //dialog.initOwner(mainStage); 
			 photoeditdialog.setTitle("Edit Photo Caption");
			 photoeditdialog.setHeaderText("Edit Photo Caption");
			 photoeditdialog.setContentText("Photo Caption: ");  
			 
			 Optional<String> result = photoeditdialog.showAndWait();
			 if (result.isPresent())
			 { 
				//change caption
			 } */
		 }
		 
		 else if(b == tag)
		 {
			 //Implement
		 }
		 
		 else if(b == movePhoto)
		 {
			 //Implement
		 }
		 
		 else if(b == displayPhoto)
		 {
			 //Implement
		 }
	}
		 
	
}
