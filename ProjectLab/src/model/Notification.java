package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Notification {
	private Integer notificationID;
	private Integer recipientID;
	private String message;
	private LocalDateTime dateCreated;
	private Boolean isRead;
	
	public Notification(Integer notificationID, Integer recipientID, String message, LocalDateTime dateCreated,
			Boolean isRead) {
		super();
		this.notificationID = notificationID;
		this.recipientID = recipientID;
		this.message = message;
		this.dateCreated = dateCreated;
		this.isRead = isRead;
	}
	
	
	public Integer getNotificationID() {
		return notificationID;
	}


	public void setNotificationID(Integer notificationID) {
		this.notificationID = notificationID;
	}


	public Integer getRecipientID() {
		return recipientID;
	}


	public void setRecipientID(Integer recipientID) {
		this.recipientID = recipientID;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public LocalDateTime getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Boolean getIsRead() {
		return isRead;
	}


	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	//  call NotificationDAO to get all notifications with a specific recipientId
	public static ArrayList<Notification> getNotificationsByRecipientID(int recipientID) { 
		return NotificationDAO.getNotificationsByRecipientID(recipientID); 
	}
	
	// call NotificationDAO to send notification to a specific recipient
	public static void sendNotification(int recipientID, String message) {
		NotificationDAO.sendNotification(recipientID, message);
	}
	
	// call NotificationDAO to get a notification with a specific id
	public static Notification getNotificationByID(int notificationID) {
		return NotificationDAO.getNotificationByID(notificationID);
	}
	
	// call NotificationDAO to mark a specific notification as read
	public static void markAsRead(int notificationID) {
		NotificationDAO.markAsRead(notificationID);
	}
	
	// call NotificationDAO to delete a notification with a specific id
	public static void deleteNotification(int notificationID) { NotificationDAO.deleteNotification(notificationID); }
}
