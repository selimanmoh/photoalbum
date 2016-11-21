package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2891770293930830115L;
	public String name;
	public ArrayList<Album> albums;
	public User(String name){
		this.name = name;
	}
	
	

}
