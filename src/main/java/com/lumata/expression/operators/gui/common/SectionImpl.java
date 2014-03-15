package com.lumata.expression.operators.gui.common;

import org.apache.commons.lang.WordUtils;

public class SectionImpl <S extends Enum<S>, section_id_prefix, section_type> implements ISection {

	public enum SectionNameType { FIXED, DYNAMIC }
	
	private S section;
	private String section_id_prefix;
	private SectionNameType section_type;
	
    public SectionImpl(S section, String section_id_prefix, SectionNameType section_type ) {
        this.section = section;
        this.section_id_prefix = section_id_prefix;
        this.section_type = section_type;
    }

    public int ordinal() {
        return section.ordinal();
    }
    
    public String getSectionName() {
    	
    	String section_name = ( WordUtils.capitalizeFully( this.section.name().replaceAll( "_", " ") ) ).replaceAll( " ", ""); 
    	
    	switch( this.section_type ) {
    	
    		case DYNAMIC: {
    			
    			section_name = section_name.substring(0, 1).toLowerCase() + section_name.substring(1);
    			
    			break;
    		}
    	
    	}
    	
    	return	section_name;    	  	
    
    }
    
    public String getSectionID() { 
    	return	( this.section_id_prefix + this.getSectionName() );    	  	
    }

}
