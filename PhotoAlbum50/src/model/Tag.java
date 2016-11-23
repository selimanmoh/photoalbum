/*
 * 
 * @author Mohamed Seliman and Mohammad Hadi Uppal
 * @version 1.0
 * @since 11-11-2016
 */

package model;

import java.io.Serializable;

public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8755149491437262067L;
	public String name;
	public String value;
	
	
	/*
	 * The constructor name and value parameters to create a tag.
	 * @param name and value Strings. The pair with which the tag is created.
	 * @return nothing as it's a constructor.
	 */
	public Tag(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public boolean equals(Object s) {
		if(!(s instanceof Tag)) 
        return false;
    
        //Overrides equals method for contains search in the LoginController
		Tag s2  = (Tag)s;
		if(this.name.equals(s2.name) && this.value.equals(s2.value))
    		return true;
		
		return false;
		
	}
	
	@Override
	public int hashCode(){
		int code;
		code = 27 + name.hashCode();
		code *= 27 + value.hashCode();
		return code;
	}
}
