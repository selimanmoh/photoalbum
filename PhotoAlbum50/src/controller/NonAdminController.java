/*
 * 
 * @author Mohamed Seliman and Mohammad Hadi Uppal
 * @version 1.0
 * @since 11-11-2016
 * 
 * The User Controller contains functions like addAlbum, editAlbum, etc. all of which are the basic functions allotted to each user outside of the more
 * integral photo view.
 */


package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Album;
import model.Photo;

public class NonAdminController {
	
	@FXML Button deleteAlbum, addAlbum, editAlbum, logout, openAlbum;
	@FXML Label displayusername, oldPhoto, numPhotos, dateRange;
	@FXML ListView<String> listView = new ListView<String>();
	ObservableList<String> albumList = FXCollections.observableArrayList();
	Stage stage;

	public void start(Stage primaryStage) {
		displayusername.setText(LoginController.currentUser.name + " Albums");
		stage = primaryStage;
		
		updateList();

		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	if(newValue!=null){
		    		Album temp = LoginController.currentUser.albums.get(LoginController.currentUser.albums.indexOf(new Album(listView.getSelectionModel().getSelectedItem()))); //get item currently selected
		    		ArrayList<Calendar> dates = rangeDates(temp);
		    		numPhotos.setText(""+temp.photos.size());
		    		if(dates.size()>0){
		    			oldPhoto.setText(dates.get(0).getTime().toString());
		    			dateRange.setText(dates.get(0).getTime().toString() + " to " + dates.get(dates.size()-1).getTime().toString());
		    		}
		    		else{
		    			oldPhoto.setText("No Photos");
		    			dateRange.setText("No Photos");
		    		}
		    	}
		    }
		});
	}
	
	public void updateList(){
				
		albumList.clear();
		for(Album album: LoginController.currentUser.albums) albumList.add(album.name);
		
		listView.setItems(albumList);
	}
	
	public ArrayList<Calendar> rangeDates(Album album){
		
		ArrayList<Calendar> dates = new ArrayList<Calendar>();
		for(Photo photo: album.photos){ photo.calendar.set(Calendar.MILLISECOND, 0); dates.add(photo.calendar);}
		Collections.sort(dates, new Comparator<Calendar>() {
			  public int compare(Calendar x, Calendar y) {
				    Calendar xcal = (Calendar) x;
				    Calendar ycal = (Calendar) y;
				    if ( xcal.before(ycal) ) return -1;
				    if ( xcal.after(ycal) ) return 1;
				    return 0;
			  }});
		
		return dates;
		
	}
	
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
			 int removedIndex = listView.getSelectionModel().getSelectedIndex();
			 
			 if(removedIndex == -1){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Login Failure");
				alert.setHeaderText("Nothing is selected for you to delete!");
				alert.showAndWait();
			 }
			 else{
				 Alert alert = new Alert(AlertType.INFORMATION);
				 alert.setTitle("Delete Album");
				 alert.setHeaderText("Are you sure you want to delete this album?");
				 alert.setContentText("Album Name: " + LoginController.currentUser.albums.get(removedIndex).name);

				 Optional<ButtonType> result = alert.showAndWait();
	 		   
	 		  if (result.isPresent() && result.get() == ButtonType.OK) {
	 			  LoginController.currentUser.albums.remove(new Album(albumList.get(removedIndex)));
	 			  updateList();
	 		  }
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
				if(!result.get().isEmpty()&& !LoginController.currentUser.albums.contains(new Album(result.get()))){
					LoginController.currentUser.albums.add(new Album(result.get()));
					updateList();
				}
				else{
					Alert alert = new Alert (AlertType.INFORMATION);
					alert.setTitle("Album Add Failure");
					alert.setHeaderText("You must enter a unique and proper album name");
					alert.showAndWait();
				}
			 } 
		 }
		 
		 else if(b == editAlbum)
		 {
			 int renameIndex = listView.getSelectionModel().getSelectedIndex();
			 
			 if(renameIndex == -1){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Rename Failure");
				alert.setHeaderText("Nothing is selected for you to rename!");
				alert.showAndWait();
			 }
			 else{
				 			 
				 TextInputDialog editdialog = new TextInputDialog("Rename Album name");
				 editdialog.setTitle("Rename Album Name");
				 editdialog.setHeaderText("Rename Album Name");
				 editdialog.setContentText("Album name: " + LoginController.currentUser.albums.get(renameIndex).name);  
			 
				 Optional<String> result = editdialog.showAndWait();
				 if (result.isPresent())
				 { 
					 if((!result.get().isEmpty() && !LoginController.currentUser.albums.contains(new Album(result.get()))) || 
							 !result.get().isEmpty() && result.get().equals(LoginController.currentUser.albums.get(renameIndex).name)){
						 LoginController.currentUser.albums.get(renameIndex).name = result.get();
						 updateList();
					 }
					 else{
						 Alert alert = new Alert (AlertType.INFORMATION);
						 alert.setTitle("Rename Failure");
						 alert.setHeaderText("You must enter a unique and proper album name");
						 alert.showAndWait();	
					 }
				 } 
			 }
		 
		 }


	}
	
	public void openAlbum(ActionEvent e){
		
		int openIndex = listView.getSelectionModel().getSelectedIndex();
		 
		if(openIndex == -1){
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle("Open Album Failure");
			alert.setHeaderText("Nothing is selected for you to open!");
			alert.showAndWait();
		}
		else{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/albumview.fxml"));
			AnchorPane root = null;
			try {
				root = (AnchorPane)loader.load();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			AlbumController controller = loader.getController();
			controller.start(stage, LoginController.currentUser.albums.get(openIndex));
			Scene scene = new Scene(root);
			stage.setScene(scene);		
			 
		 }
		
	}
	
	public void search(ActionEvent e){
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/photodisplay.fxml"));
		AnchorPane root = null;
		try {
			root = (AnchorPane)loader.load();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		SearchController controller = loader.getController();
		controller.start(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);		 
	}
}
