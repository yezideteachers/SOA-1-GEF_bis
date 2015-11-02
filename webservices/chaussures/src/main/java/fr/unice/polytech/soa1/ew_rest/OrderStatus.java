package fr.unice.polytech.soa1.ew_rest;

public enum OrderStatus {
	
	VALIDATION("VALIDÉE"), ENVOI("ENVOYÉE"), RECEPTION("RECETION");
	
	private String orderStatus;
	
	private OrderStatus(String s){
		orderStatus = s;
	}

	public String getStatus(){
		return orderStatus;
	}
	
	public void setStatus(OrderStatus s){
		this.orderStatus = s.getStatus();
	}
}
