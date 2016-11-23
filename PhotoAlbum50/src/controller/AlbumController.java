package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.Tag;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class AlbumController {
	
	@FXML Button deletePhoto, addPhoto, editCaption, tag, movePhoto, displayPhoto, logout, addTags, cancelTags, deleteTags, copyPhoto, nextSlide, previousSlide, backAlbums;
	@FXML ListView<Image> listView = new ListView<Image>();
	@FXML TextField tagType, tagValue;
	@FXML ImageView imageDisplay;
	@FXML Label displayalbumname, displayTags, displayDate, displayCaption;
	ObservableList<Image> photoList = FXCollections.observableArrayList();
	Stage stage;
	int albumIndex;
	int slideSequence = 0;
	
	public void start(Stage primaryStage, Album primaryAlbum) {
		// TODO Auto-generated method stub
		stage = primaryStage;
		albumIndex = LoginController.currentUser.albums.indexOf(primaryAlbum);
		displayalbumname.setText(LoginController.currentUser.albums.get(albumIndex).name+ " Album");

		listView.setCellFactory(param -> new ListCell<Image>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Image photo, boolean empty) {
                super.updateItem(photo, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    imageView.setImage(photo);
                    imageView.setPreserveRatio(true);
                    imageView.setFitHeight(200);
                    imageView.setFitWidth(200);
                    setGraphic(imageView);
                    
                }
            }
        });
		updateList();
		
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
		    if(file !=null){
		    try {
				imgURL = file.toURI().toURL().toExternalForm();
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
			}
		    Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(new Date(file.lastModified()));
	    	
		    if(!LoginController.currentUser.albums.get(albumIndex).photos.contains(new Photo(new Image(imgURL), calendar,imgURL))){
		    	LoginController.currentUser.albums.get(albumIndex).photos.add(new Photo(new Image(imgURL), calendar, imgURL));
		    	updateList();
		    }
		    else{
		    	Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Login Failure");
				alert.setHeaderText("You must enter a unique and proper album name");
				alert.showAndWait();
		    	}
		    }
		 }
		 else if(b == backAlbums){
			 
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(getClass().getResource("/view/nonadminhome.fxml"));
			 AnchorPane root = null;
				try {
					root = (AnchorPane)loader.load();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			 NonAdminController controller = loader.getController();
			 controller.start(stage);
			 Scene scene = new Scene(root);
			 stage.setScene(scene);		 
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
				 alert.setTitle("Delete Photo");
				 alert.setHeaderText("Are you sure you want to delete this photo?");
				 Optional<ButtonType> result = alert.showAndWait();
	 		   
		 		  if (result.isPresent() && result.get() == ButtonType.OK) {
			 			 LoginController.currentUser.albums.get(albumIndex).photos.remove(removedIndex);
			 			 updateList();
	 		  }
	 		
	 		 }
		 }
		 
		 else if(b == editCaption)
		 {
			 int captionIndex = listView.getSelectionModel().getSelectedIndex();
			 
			 if(captionIndex == -1){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Option failure");
				alert.setHeaderText("Nothing is selected for you to edit!");
				alert.showAndWait();
			 }
			 else{
				 TextInputDialog photoeditdialog = new TextInputDialog();
				 photoeditdialog.setTitle("Edit/Create Photo Caption");
				 photoeditdialog.setHeaderText("Edit/Create Photo Caption");
				 photoeditdialog.setContentText("Photo Caption: ");  
			 
				 Optional<String> result = photoeditdialog.showAndWait();
				 if (result.isPresent())
					 LoginController.currentUser.albums.get(albumIndex).photos.get(captionIndex).caption = result.get();
			 }
		}
		 
		 else if(b == tag)
		 {
			 int tagIndex = listView.getSelectionModel().getSelectedIndex();
			 
			 if(tagIndex == -1){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Option failure");
				alert.setHeaderText("Nothing is selected for you to tag!");
				alert.showAndWait();
			 }
			 else{
				 
				tagType.setDisable(false);
				tagValue.setDisable(false);
				addTags.setDisable(false);
				cancelTags.setDisable(false);
				deleteTags.setDisable(false);
				editCaption.setDisable(true);
				deletePhoto.setDisable(true);
				addPhoto.setDisable(true);
				displayPhoto.setDisable(true);
				tag.setDisable(true);
			 }
		 }
		 else if(b == movePhoto)
		 {
			 int moveIndex = listView.getSelectionModel().getSelectedIndex();
			 
			 if(moveIndex == -1){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Option failure");
				alert.setHeaderText("Nothing is selected for you to move!");
				alert.showAndWait();
			 }
			 else{
				 TextInputDialog photoeditdialog = new TextInputDialog();
				 photoeditdialog.setTitle("Move Selected Photo to Another Album");
				 photoeditdialog.setHeaderText("Move Photo");
				 photoeditdialog.setContentText("Move to Album: ");  
			 
				 Optional<String> result = photoeditdialog.showAndWait();
	 		   
		 		  if (result.isPresent()) {
		 			  if(LoginController.currentUser.albums.contains(new Album(result.get()))){
		 				 int newIndex = LoginController.currentUser.albums.indexOf(new Album(result.get()));
				 		 LoginController.currentUser.albums.get(newIndex).photos.add(LoginController.currentUser.albums.get(albumIndex).photos.get(moveIndex)); //Moves the photo
			 			 LoginController.currentUser.albums.get(albumIndex).photos.remove(moveIndex);
			 			 updateList();
		 			  }
		 			  else{
		 				 Alert alert = new Alert (AlertType.INFORMATION);
		 				 alert.setTitle("Option failure");
		 				 alert.setHeaderText("That album does not exist!");
		 				 alert.showAndWait();		 				  
		 			  }
		 		  }
		 		
	 		 }
		 }
		 else if(b == copyPhoto){
			 
			 int copyIndex = listView.getSelectionModel().getSelectedIndex();
			 
			 if(copyIndex == -1){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Option failure");
				alert.setHeaderText("Nothing is selected for you to copy!");
				alert.showAndWait();
			 }
			 else{
				 TextInputDialog photoeditdialog = new TextInputDialog();
				 photoeditdialog.setTitle("Copy Selected Photo to Another Album");
				 photoeditdialog.setHeaderText("Copy Photo");
				 photoeditdialog.setContentText("Copy to Album: ");  
			 
				 Optional<String> result = photoeditdialog.showAndWait();
	 		   
		 		  if (result.isPresent()) {
		 			  if(LoginController.currentUser.albums.contains(new Album(result.get()))){ //Album exists
		 				 int newIndex = LoginController.currentUser.albums.indexOf(new Album(result.get()));
				 		 LoginController.currentUser.albums.get(newIndex).photos.add(LoginController.currentUser.albums.get(albumIndex).photos.get(copyIndex)); //Moves the photo
		 			  }
		 			  else{
		 				 Alert alert = new Alert (AlertType.INFORMATION);
		 				 alert.setTitle("Option failure");
		 				 alert.setHeaderText("That album does not exist!");
		 				 alert.showAndWait();		 				  
		 			  }
		 		  }
		 		
	 		 }			 
		 }
		 else if(b == displayPhoto)
		 {
			 int displayIndex = listView.getSelectionModel().getSelectedIndex();
			 
			 if(displayIndex == -1){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Option failure");
				alert.setHeaderText("Nothing is selected for you to display!");
				alert.showAndWait();
			 }
			 else{
		 		Photo pho = LoginController.currentUser.albums.get(albumIndex).photos.get(displayIndex);
		 		if(pho.thumbnail != null)
		 			imageDisplay.setImage(pho.thumbnail);
		 		else
		 			imageDisplay.setImage(new Image(pho.imageURL));	
			 	//imageDisplay.setPreserveRatio(true);
			 	imageDisplay.setFitHeight(imageDisplay.getFitHeight());
			 	imageDisplay.setFitWidth(imageDisplay.getFitWidth());
			 	
			 	displayCaption.setText(pho.caption);
			 	displayDate.setText(pho.calendar.getTime().toString());
			 	displayTags.setText(tagFill(displayIndex));
	 		 }
		 }
	}
	
	public String tagFill(int index){
		
		ArrayList<Tag> tags = LoginController.currentUser.albums.get(albumIndex).photos.get(index).tags;
		String tagFill = "";
		for(Tag tag: tags) tagFill= tagFill + "\n" + tag.name + ", " + tag.value;
		return tagFill;
	}
	
	public void slideshow(ActionEvent e){
		Button b = (Button)e.getSource();
		
		if(b == nextSlide){
			if(slideSequence == LoginController.currentUser.albums.get(albumIndex).photos.size())
				slideSequence = 0;
			listView.getSelectionModel().select(slideSequence++);
			ActionEvent ae = new ActionEvent(displayPhoto,null);
			pressphotoButton(ae);
		}
		else if( b == previousSlide){
			
			if(slideSequence < 0)
				slideSequence = LoginController.currentUser.albums.get(albumIndex).photos.size() - 1;
			listView.getSelectionModel().select(slideSequence--);
			ActionEvent ae = new ActionEvent(displayPhoto,null);
			pressphotoButton(ae);
		}
	}
	
	public void extraPress(ActionEvent e){
		Button b = (Button)e.getSource();
		int tagIndex = listView.getSelectionModel().getSelectedIndex();
		
		if(b == addTags){
			
			if(tagType.getText().isEmpty() || tagValue.getText().isEmpty()){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Option failure");
				alert.setHeaderText("You must have a tag type and value!");
				alert.showAndWait();
			}
			else{
				
				if(!LoginController.currentUser.albums.get(albumIndex).photos.get(tagIndex).tags.contains(new Tag(tagType.getText(), tagValue.getText()))){
					LoginController.currentUser.albums.get(albumIndex).photos.get(tagIndex).tags.add(new Tag(tagType.getText(),tagValue.getText()));
					updateList();//unneeded
				}
				else{
					Alert alert = new Alert (AlertType.INFORMATION);
					alert.setTitle("Option failure");
					alert.setHeaderText("Duplicate tags, where tag type and value are the same, are not allowed!");
					alert.showAndWait();
				}
				tagType.setText("");
				tagValue.setText("");
				tagType.setDisable(true);
				tagValue.setDisable(true);
				addTags.setDisable(true);
				cancelTags.setDisable(true);
				deleteTags.setDisable(true);
				editCaption.setDisable(false);
				deletePhoto.setDisable(false);
				addPhoto.setDisable(false);
				displayPhoto.setDisable(false);
				tag.setDisable(false);
			}
		}
		else if(b == cancelTags){
			
			tagType.setText("");
			tagValue.setText("");
			tagType.setDisable(true);
			tagValue.setDisable(true);
			addTags.setDisable(true);
			cancelTags.setDisable(true);
			deleteTags.setDisable(true);
			editCaption.setDisable(false);
			deletePhoto.setDisable(false);
			addPhoto.setDisable(false);
			displayPhoto.setDisable(false);
			tag.setDisable(false);
		}
		else if(b == deleteTags){
			
			if(tagType.getText().isEmpty() || tagValue.getText().isEmpty()){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Option failure");
				alert.setHeaderText("Not enough information to delete!");
				alert.showAndWait();
			}
			else{
				
				if(LoginController.currentUser.albums.get(albumIndex).photos.get(tagIndex).tags.contains(new Tag(tagType.getText(), tagValue.getText()))){
					LoginController.currentUser.albums.get(albumIndex).photos.get(tagIndex).tags.remove(new Tag(tagType.getText(), tagValue.getText()));
					updateList();
					tagType.setText("");
					tagValue.setText("");
					tagType.setDisable(true);
					tagValue.setDisable(true);
					addTags.setDisable(true);
					cancelTags.setDisable(true);
					deleteTags.setDisable(true);
					editCaption.setDisable(false);
					deletePhoto.setDisable(false);
					addPhoto.setDisable(false);
					displayPhoto.setDisable(false);
					tag.setDisable(false);
				}
				else{
					Alert alert = new Alert (AlertType.INFORMATION);
					alert.setTitle("Option failure");
					alert.setHeaderText("No tag by that type and value!");
					alert.showAndWait();
					tagType.setText("");
					tagValue.setText("");
					tagType.setDisable(true);
					tagValue.setDisable(true);
					addTags.setDisable(true);
					cancelTags.setDisable(true);
					deleteTags.setDisable(true);
					editCaption.setDisable(false);
					deletePhoto.setDisable(false);
					addPhoto.setDisable(false);
					displayPhoto.setDisable(false);
					tag.setDisable(false);
				}
			}
		}
	}
		 
	
}
