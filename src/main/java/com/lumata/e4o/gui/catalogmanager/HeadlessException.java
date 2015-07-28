package com.lumata.e4o.gui.catalogmanager;

import java.awt.GraphicsEnvironment;

public class HeadlessException extends UnsupportedOperationException {
private static final long serialVersionUID = 167183644944358563L;
public HeadlessException() {}
public HeadlessException(String msg) {
super(msg);
}
public String  getMessage() {
	String superMessage = super.getMessage();
GraphicsEnvironment headlessMessage = GraphicsEnvironment.getLocalGraphicsEnvironment();
if (superMessage == null) {
//return headlessMessage;
} else if (headlessMessage == null) {
return superMessage;
} else {
return superMessage + headlessMessage;
        }
return superMessage;
    }
}