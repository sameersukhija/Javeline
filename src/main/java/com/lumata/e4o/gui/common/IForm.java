package com.lumata.e4o.gui.common;

import com.lumata.e4o.exceptions.FormException;

public interface IForm {
	Object openForm() throws FormException;
    Object closeForm() throws FormException;
}
