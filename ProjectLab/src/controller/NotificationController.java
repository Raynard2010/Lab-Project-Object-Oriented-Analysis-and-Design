package controller;

import java.util.ArrayList;

import model.Notification;

public class NotificationController {
	
	// call Notification to send notification
	public static void sendNotification(int recipientID, String message) {
		Notification.sendNotification(recipientID, message);
	}
	
	// call Notification to get notifications based on the recipient id
	public static ArrayList<Notification> getNotificationsByRecipientID(int recipientID) {
		return Notification.getNotificationsByRecipientID(recipientID);
	}
	
	// call Notification to mark a notification as read
	public static void markAsRead(int notificationID) {
		Notification.markAsRead(notificationID);
	}
	
	public static Notification getNotificationByID(int notificationID) {
		// call notification to get a specific notification
		Notification notification = Notification.getNotificationByID(notificationID);
		
		// validate if a notification has been read or not
		boolean isRead = validateIsRead(notification);
		
		// if notification has not been read, mark notification as read
		if(!isRead) markAsRead(notification.getNotificationID());
		
		return notification;
	}
	
	// validate if a notification has been read or not
	public static boolean validateIsRead(Notification notification) {
		if(notification.getIsRead()) return true;
		else return false;
	}
	
	// call Notification to delete a specific notification
	public static void deleteNotification(int notificationID) {
		Notification.deleteNotification(notificationID);
	}
}
