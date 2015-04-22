package com.lumata.e4o.gui.common;

import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.NotificationForm.NotificationChannel;
import com.lumata.e4o.gui.common.NotificationForm.NotificationTongue;

public interface INotificationForm {

	public INotificationForm openDialogueNotification() throws FormException;
	public INotificationForm saveDialogueNotification() throws FormException;
	public INotificationForm cancelDialogueNotification() throws FormException;
	public INotificationForm editDialogueNotification(  NotificationTongue tongue, NotificationChannel channel  ) throws FormException;
	public INotificationForm editDialogueNotification(  String tongue, String channel  ) throws FormException;
	public INotificationForm deleteDialogueNotification(  NotificationTongue tongue, NotificationChannel channel  ) throws FormException;
	public INotificationForm deleteDialogueNotification(  String tongue, String channel  ) throws FormException;
	public INotificationForm importDialogueNotification(  NotificationTongue tongue, NotificationChannel channel  ) throws FormException;
	public INotificationForm importDialogueNotification(  String tongue, String channel  ) throws FormException;	
	public INotificationForm saveDialogueNotificationEditing() throws FormException;
	public INotificationForm saveTemplateDialogueNotificationEditing() throws FormException;
	public INotificationForm cancelDialogueNotificationEditing() throws FormException;
	public INotificationForm setDialogueNotificationMessage( String message ) throws FormException;
		
}
