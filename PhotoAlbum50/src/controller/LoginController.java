package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class LoginController {
	
	@FXML Button login;
	@FXML TextField username = new TextField();
	public static ArrayList<User> users = new ArrayList<User>();
	public static User currentUser;
	public Stage stage;
	FileOutputStream fos;
	
	
	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) {
	
		if(users.isEmpty()){
			FileInputStream fis;
			try {
				
			fis = new FileInputStream("serializedFile.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList<User>) ois.readObject();
            ois.close();
            fis.close();
            } 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				try {
					FileOutputStream fos= new FileOutputStream("serializedFile.ser");
					fos.close();
				} catch (FileNotFoundException e1) {
				} catch (IOException e1) {
				}
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		else{
			
        	 FileOutputStream fos;
			try {
				fos = new FileOutputStream("serializedFile.ser");
				ObjectOutputStream oos= new ObjectOutputStream(fos);
	            oos.writeObject(users);
	            oos.close();
	            fos.close();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
             
        }
   
		
		//Do shut down stuff here
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

	        public void run() {
	            try{
	            	FileOutputStream fos= new FileOutputStream("serializedFile.ser");
	                ObjectOutputStream oos= new ObjectOutputStream(fos);
	                oos.writeObject(users);
	                oos.close();
	                fos.close();
	              }catch(IOException ioe){
	                   ioe.printStackTrace();
	               }
	        }
	    }));
		stage = primaryStage;
		
		
	}
	
	public void loginButton(ActionEvent e) {
		 Button b = (Button)e.getSource();
		 
		 if(b == login)
		 {
			if(username.getText()==null || username.getText().isEmpty())
			{
				Alert alert = new Alert (AlertType.INFORMATION);
	    		alert.setTitle("Login Failure");
	    		alert.setHeaderText("You must enter a username");
	    		alert.showAndWait();
			}
			
			else {
				
				if(username.getText().equals("Admin")){				     

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/view/adminhome.fxml"));
					AnchorPane root = null;
					try {
						root = (AnchorPane)loader.load();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					AdminSystemController controller = loader.getController();
					controller.start(stage);
					Scene scene = new Scene(root);
					stage.setScene(scene);
				}
				else if(users.contains(new User(username.getText()))){
					
					currentUser = users.get(users.indexOf(new User(username.getText())));
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
				else{
					
					currentUser = new User(username.getText());
					users.add(currentUser);
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
				/*Find the user and then login
				 * load display for new admin view if user is an admin
				 * load display for nonadmin view otherwise
				 * error if user is not found
				 */
			}
		 
		 }
	 }

		
}
