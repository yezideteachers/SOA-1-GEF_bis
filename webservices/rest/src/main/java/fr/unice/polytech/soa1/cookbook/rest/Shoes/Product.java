package fr.unice.polytech.soa1.cookbook.rest.Shoes;

public class Product {
	
	private static int cpt=0;
	private String name;
	private int size;
	private String color;
	private double price;
	private String id;
	
	public Product(String n, int s, String c, double p) {
		// 
		this.name = n;
		this.size = s;
		this.color = c;
		this.price = p;
		
	}
	
	
	String run(){
		cpt++;
		id = name+cpt;
		return id;
	}
	
	public String getId(){
		return this.id;
	}
	
	public boolean equals(Product p){
		return(this.id.equals(p.id));
	}
	
	public String getName(){
		return name;
	}

	public double getPrice(){
		return this.price;
	}
	
	public int getSize(){
		return size;
	}
	public String getColor(){
		return this.color;
	}


	@Override
	public String toString() {
		return "{name=" + name + ", size=" + size + ", color=" + color + ", price=" + price + ", id=" + id
				+ "}";
	}
	
	

}
