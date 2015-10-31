package fr.unice.polytech.soa1.cookbook.flows.business;

import java.io.Serializable;

/**
 * Created by yazide on 28/10/2015.
 */
public class OrderLine implements Serializable{

    private String ref;
    private String name;
    private String color;
    private double price;
    private int quantity;

   /* public OrderLine(String ref, String name, String color, double price, int quantity) {
        this.ref = ref;
        this.name = name;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }
    public OrderLine(){}
*/
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "ref='" + ref + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
