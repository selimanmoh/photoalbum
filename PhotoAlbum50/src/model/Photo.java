package model;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

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
	
	public Photo(Image image, Calendar calendar){
		this.thumbnail = image;
		//this.imageURL = imgURL;
		this.calendar = calendar;
		this.calendar.set(Calendar.MILLISECOND, 0);
		tags = new ArrayList<Tag>();
	}
	
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        //File file = new File(imgURL);
        //thumbnail = ImageIO.read(input)
 
    }
}
