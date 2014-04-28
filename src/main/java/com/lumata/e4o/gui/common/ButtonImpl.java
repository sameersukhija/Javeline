package com.lumata.e4o.gui.common;

import org.apache.commons.lang.WordUtils;

public class ButtonImpl <B extends Enum<B>> implements IButton {

	private B button;
	
    public ButtonImpl( B button ) {
        this.button = button;
    }

    public int ordinal() {
        return button.ordinal();
    }
    
    public String getButtonName() {
    	
    	String button_name = this.button.name().toLowerCase().replaceAll( "_", "-"); 
    	
    	return	button_name;    	  	
    
    }
    
    public String getButtonID() {
    	return	this.getButtonName();    	  	
    }

}
