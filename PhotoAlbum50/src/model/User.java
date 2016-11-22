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
	
	@Override
	public boolean equals(Object s) {
		if(!(s instanceof User)) 
        return false;
    
        //Overrides equals method for contains search in the LoginController
		User s2  = (User)s;
		if(this.name.equals(s2.name))
    		return true;
		
		return false;
		
	}
	
	@Override
	public int hashCode(){
		int code;
		
		code = 27 + name.hashCode();
		return code;
	}

}

