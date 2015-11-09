package fr.unice.polytech.soa1.cookbook.rest.furniture.Service;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.Response;

import fr.unice.polytech.soa1.cookbook.rest.furniture.Cart;
import org.json.JSONObject;

public class CartImpl implements CartService {

	
	public Response getAllCommande() {
	
		Collection<Cart> listcarts = Cart.mapCart.values();
		
		ArrayList<Cart> list = new ArrayList<Cart>();
		for(Cart el: listcarts) {
			list.add(el);
		}
		JSONObject result = new JSONObject();
		result.put("carts", list);
		return Response.ok().entity(result.toString(2)).build();
	}

	public Response getCart(int idC) {
		
		JSONObject result = new JSONObject();
		for (Cart el : Cart.mapCart.values()) {
			if(el.getIdCart()==idC){
				result.put("Cart ", el);
				break;
			}
			
		}
		
		return Response.ok().entity(result.toString(2)).build();
	}

	public Response addToCart(int id) {
		Cart.create(id);
		return Response.ok().build();
	}

	public Response deleteToCart(int id) {
		Cart.mapCart.remove(id);
		return Response.ok().build();
	}

}
