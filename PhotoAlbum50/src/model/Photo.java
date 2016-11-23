/*
 * 
 * @author Mohamed Seliman and Mohammad Hadi Uppal
 * @version 1.0
 * @since 11-11-2016
 */

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
	
	/*
	 * The constructor uses the image, calendar, and imageURl to construct a Photo object.
	 * @param image, calendar, imageURl. The image is self explanatory, whereas the calendar is the last modified date and the imageURL is the URL of the image.
	 * @return nothing as it's a constructor.
	 */
	
	public Photo(Image image, Calendar calendar, String imageURL){
		this.thumbnail = image;
		this.imageURL = imageURL;
		this.calendar = calendar;
		this.calendar.set(Calendar.MILLISECOND, 0);
		tags = new ArrayList<Tag>();
	}

}
