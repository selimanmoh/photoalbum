package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import model.Photo;
import javafx.scene.control.Alert.AlertType;

public class AlbumController {
	
	@FXML Button deletePhoto, addPhoto, editCaption, tag, movePhoto, displayPhoto, logout;
	
	public void pressButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		 
		 if(b==logout)
		 {
			 //logout
		 }
		 
		 else if(b==addPhoto)
		 {
			 JFileChooser chooser = new JFileChooser();
			 FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", ".jpg", ".JPG", ".PNG", "png");
			 chooser.setFileFilter(filter);
			 
			 //needs completion
		 }
		 
	}
	
	public void pressphotoButton(ActionEvent e, Photo photo) {
		 Button b = (Button)e.getSource();
		 
		 if(b==deletePhoto)
		 {
			 Alert alert = new Alert(AlertType.INFORMATION);
	 		   alert.setTitle("Delete Photo");
	 		   alert.setHeaderText("Are you sure you want to delete this photo?");
	 		   alert.setContentText("Photo Name: ");

	 		   Optional<ButtonType> result = alert.showAndWait();
	 		   
	 		  if (result.isPresent() && result.get() == ButtonType.OK) {
	 		     //deletePhoto();
	 		 }
		 }
		 
		 else if(b == editCaption)
		 {
			 TextInputDialog photoeditdialog = new TextInputDialog(photo.caption);
			 //dialog.initOwner(mainStage); 
			 photoeditdialog.setTitle("Edit Photo Caption");
			 photoeditdialog.setHeaderText("Edit Photo Caption");
			 photoeditdialog.setContentText("Photo Caption: ");  
			 
			 Optional<String> result = photoeditdialog.showAndWait();
			 if (result.isPresent())
			 { 
				//change caption
			 } 
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
