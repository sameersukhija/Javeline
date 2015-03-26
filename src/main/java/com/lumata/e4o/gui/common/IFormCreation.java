package com.lumata.e4o.gui.common;

import com.lumata.e4o.exceptions.FormException;

public interface IFormCreation {
	Object addBtn() throws FormException;
    Object saveBtn() throws FormException;
    Object cancelBtn() throws FormException;
    Object refreshBtn() throws FormException;
}
