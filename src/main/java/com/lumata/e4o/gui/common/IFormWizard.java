package com.lumata.e4o.gui.common;

import com.lumata.e4o.exceptions.FormException;

public interface IFormWizard {
	Object addBtn() throws FormException;
    Object refreshAllBtn() throws FormException;
    Object previousBtn() throws FormException;
    Object nextBtn() throws FormException;
    Object cancelBtn() throws FormException;
    Object saveBtn() throws FormException;
    Object activateBtn() throws FormException;    
}
