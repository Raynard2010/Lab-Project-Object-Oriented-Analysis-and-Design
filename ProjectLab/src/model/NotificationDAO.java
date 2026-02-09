package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import util.Connect;

public class NotificationDAO {
	
		// access database to get a list of notifications with a specific recipient id
		public static ArrayList<Notification> getNotificationsByRecipientID(int recipientID) {
			// to store notifications
			ArrayList<Notification> res = new ArrayList<Notification>();
			
			try {
				PreparedStatement ps = Connect.getInstance().prepareStatement(
		                "SELECT * FROM notifications WHERE recipientID = ?"
						
				);
	            ps.setInt(1, recipientID);

				ResultSet rs = ps.executeQuery();

				// store notification data
				while (rs.next()) {
					Integer notificationID = rs.getInt("notificationid");
					Integer recipientId = rs.getInt("recipientid");
					String message = rs.getString("notificationmessage");
					// convert timestamp in SQL to LocalDateTime in Java
					LocalDateTime dateCreated = rs.getTimestamp("createdat").toLocalDateTime();
					Boolean isRead = rs.getBoolean("isread");
					
					Notification notification = new Notification(notificationID, recipientID, message, dateCreated,
							isRead);
					notification.setNotificationID(notificationID);
					notification.setRecipientID(recipientId);
					notification.setMessage(message);
					notification.setIsRead(isRead);
					notification.setDateCreated(dateCreated);
					
					// add notifications to list
					res.add(notification);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			// return list
			return res;
		}
		
		// access database to get a notification with a specific id
		public static Notification getNotificationByID(int notificationID) {
			// to store the notification
			Notification notification = null;
			
			try {
				PreparedStatement ps = Connect.getInstance().prepareStatement(
		                "SELECT * FROM notifications WHERE notificationID = ?"
						
				);
	            ps.setInt(1, notificationID);

				ResultSet rs = ps.executeQuery();

				// store notification data
				while (rs.next()) {
					Integer id = rs.getInt("notificationid");
					Integer recipientId = rs.getInt("recipientid");
					String message = rs.getString("notificationmessage");
					// convert timestamp in SQL to LocalDateTime in Java
					LocalDateTime dateCreated = rs.getTimestamp("createdat").toLocalDateTime();
					Boolean isRead = rs.getBoolean("isread");
					
					notification = new Notification(id, recipientId, message, dateCreated,
							isRead);
					notification.setNotificationID(notificationID);
					notification.setRecipientID(recipientId);
					notification.setMessage(message);
					notification.setIsRead(isRead);
					notification.setDateCreated(dateCreated);
				}
//				return res;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// return the notification
			return notification;
		}
		
		// to send notification to a specific recipient
		public static void sendNotification(int recipientID, String message) {
			try {
				
				// to add notification data to database
				PreparedStatement ps = Connect.getInstance().prepareStatement(
						"INSERT INTO notifications (recipientid, notificationmessage, isread) VALUES (?,?,?)"
				);
				ps.setInt(1, recipientID);
				ps.setString(2, message);
				// false because notification's isRead is false at creation
				ps.setBoolean(3, false);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// show success message
		    Alert success = new Alert(Alert.AlertType.INFORMATION);
		    success.setHeaderText("Send Notification Success");
		    success.setContentText("Notification sent");
		    success.show();
		}
		
		// to mark a notification as read
		public static void markAsRead(Integer notificationId) {
			try {
				// update a specific notification's isRead to true
				PreparedStatement ps = Connect.getInstance().prepareStatement(
						"UPDATE notifications SET isread = true WHERE notificationid = ?"
				);
				ps.setInt(1, notificationId);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// to delete a specific notification
		public static void deleteNotification(Integer notificationId) {
			try {
				PreparedStatement ps = Connect.getInstance().prepareStatement(
						"DELETE FROM notifications WHERE notificationid = ?"
				);
				ps.setInt(1, notificationId);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
