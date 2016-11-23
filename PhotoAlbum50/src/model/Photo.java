package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.Image;

public class Photo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6955723612371190680L;
	public transient Image thumbnail;
	public String imageURL;
	public String caption;
	public Calendar calendar;
	public ArrayList<Tag> tags;
	
	public Photo(Image image, Calendar calendar, String imageURL){
		this.thumbnail = image;
		this.imageURL = imageURL;
		this.calendar = calendar;
		this.calendar.set(Calendar.MILLISECOND, 0);
		tags = new ArrayList<Tag>();
	}

}
