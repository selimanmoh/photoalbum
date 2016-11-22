package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4143935150417416554L;
	public ArrayList<Photo> photos;
	public String name;
	
	public Album(String name){
		this.name = name;
		photos = new ArrayList<Photo>();
	}
	
	@Override
	public boolean equals(Object s) {
		if(!(s instanceof Album)) 
        return false;
    
        //Overrides equals method for contains search in the LoginController
		Album s2  = (Album)s;
		if(this.name.equals(s2.name))
    		return true;
		
		return false;
		
	}
	
	@Override
	public int hashCode(){
		int code;
		
		code = 17 + name.hashCode();
		return code;
	}

}

