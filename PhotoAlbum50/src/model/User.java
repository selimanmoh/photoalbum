/*
 * 
 * @author Mohamed Seliman and Mohammad Hadi Uppal
 * @version 1.0
 * @since 11-11-2016
 * 
 * Represents a User in the Photo Album system.
 */

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
	/*
	 * The constructor uses the parent abstract class to set the color field.
	 * @param name The name of the User created.
	 * @return nothing as it's a constructor.
	 */
	
	public User(String name){
		this.name = name;
		albums = new ArrayList<Album>();
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

