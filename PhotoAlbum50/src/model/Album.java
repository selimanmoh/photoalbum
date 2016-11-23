/*
 * 
 * @author Mohamed Seliman and Mohammad Hadi Uppal
 * @version 1.0
 * @since 11-11-2016
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable{

	private static final long serialVersionUID = -4143935150417416554L;
	public ArrayList<Photo> photos;
	public String name;
	/*
	 * The constructor takes a String name as parameter in which it then assigns to its class variable name.
	 * @param name The name of the Album specified.
	 * @return nothing as it's a constructor.
	 */
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

