package model;

import java.util.List;

public class Service {
	private int serviceID;
	private String serviceName;
	private String serviceDescription;
	private double servicePrice;
	private int serviceDuration;
	public Service(int serviceID, String serviceName, String serviceDescription, double servicePrice,
			int serviceDuration) {
		super();
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.serviceDescription = serviceDescription;
		this.servicePrice = servicePrice;
		this.serviceDuration = serviceDuration;
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public double getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}
	public int getServiceDuration() {
		return serviceDuration;
	}
	public void setServiceDuration(int serviceDuration) {
		this.serviceDuration = serviceDuration;
	}
	
	// call ServiceDAO to get all services
	public static List<Service> getAllServices(){
		return ServiceDAO.getAllServices();
	}
	
	// call ServiceDAO to add a new service
	public static void addService(String name, String description, double price, int duration) {		
		ServiceDAO.addService(name, description, price, duration);
	}
	
	// call ServiceDAO to edit a specific service
	public static void editService(int serviceID, String name, String description, double price, int duration) {		
		ServiceDAO.editService(serviceID, name, description, price, duration);
	}
	
	// call ServiceDAO to delete a specific service
	public static void deleteService(int serviceID) {
		ServiceDAO.deleteService(serviceID);
	}
	
}
