package model;

public class Tag {
	public String name;
	public String value;
	
	public Tag(String name, String value){
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
