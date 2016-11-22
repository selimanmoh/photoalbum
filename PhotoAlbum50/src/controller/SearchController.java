package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import javafx.scene.control.ListCell;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import javafx.scene.layout.AnchorPane;

public class SearchController {
	
	@FXML Button searchDate, searchTags, logout, tagGO, dateGO;
	@FXML ListView<Image> listView = new ListView<Image>();
	@FXML ImageView imageDisplay;
	@FXML TextField date1, date2, tagText;
	ObservableList<Image> photoList = FXCollections.observableArrayList();
	ArrayList<Photo> searchList = new ArrayList<Photo>();
	Stage stage;

	
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		stage = primaryStage;
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
		for(Album album: LoginController.currentUser.albums) searchList.addAll(album.photos);
		updateList();
		
	}
	
	public void updateList(){
		photoList.clear();
		for(Photo photo: searchList) photoList.add(photo.thumbnail);
		
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
	}
	
	public void pressSearch(ActionEvent e) {
		 Button b = (Button)e.getSource();
		 
		 if(b == searchDate)
		 {
			  date1.setDisable(false);
			  date2.setDisable(false);
			  dateGO.setDisable(false);
			  searchTags.setDisable(true);
			
		 }
	}
	
	public void extraPress(ActionEvent e){
		Button b = (Button)e.getSource();
		Boolean exc = false;
		
		if(b == dateGO){
			
			if(date1.getText().isEmpty() || date2.getText().isEmpty()){
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Option failure");
				alert.setHeaderText("Two dates must be specified for this search!");
				alert.showAndWait();
				date1.setDisable(true);
				date2.setDisable(true);
				dateGO.setDisable(true);
				searchTags.setDisable(false);
			}
			else{
				DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
				Date dte = null;
				Date dte2 = null;
				try {
					dte = parser.parse(date1.getText());
					dte2 = parser.parse(date2.getText());
 				} catch (ParseException e1) {
					Alert alert = new Alert (AlertType.INFORMATION);
					alert.setTitle("Option failure");
					alert.setHeaderText("Wrong date format!");
					alert.showAndWait();
					exc = true;
				}
				if(!exc)
				{
					Calendar cal = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal.setTime(dte);
					cal2.setTime(dte2);
					calendarParse(cal, cal2);
					
					date1.setDisable(true);
					date2.setDisable(true);
					dateGO.setDisable(true);
					searchTags.setDisable(false);
				}
					
			}
		}
	}
	
	public void calendarParse(Calendar cal1, Calendar cal2){
		ArrayList<Photo> temp = new ArrayList<Photo>();
		
		for(Photo photo: searchList) if(!cal1.after(photo.calendar) && !cal2.before(photo.calendar)) temp.add(photo);
		
		searchList.clear();
		searchList.addAll(temp);
		updateList();
	}
	
}
