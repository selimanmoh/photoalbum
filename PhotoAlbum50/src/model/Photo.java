package model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;
import sun.util.calendar.BaseCalendar.Date;

public class Photo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6955723612371190680L;
	public Image thumbnail;
	public String caption;
	public Date datetime;
	public ArrayList<Tag> tags;
	
	public Photo(Image thumbnail){
		this.thumbnail = thumbnail;
	}
}
