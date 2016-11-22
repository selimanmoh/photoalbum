package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.Image;

public class Photo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6955723612371190680L;
	public BufferedImage thumbnail;
	public String caption;
	public Calendar calendar;
	public ArrayList<Tag> tags;
	
	public Photo(BufferedImage image){
		this.thumbnail = image;
	}
}
