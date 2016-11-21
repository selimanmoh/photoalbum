package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4143935150417416554L;
	public ArrayList<Photo> photos;
	String name;
	
	public Album(String name){
		this.name = name;
	}

}
