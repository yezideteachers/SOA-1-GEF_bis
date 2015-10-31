package fr.unice.polytech.soa1.cookbook.restbis;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/catalog")
//Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public interface ProductService {
	
	
	@POST
	@Path("/products/{description}/{quantity}/{TYPE}/{COLOR}/{price}/{WOOD}")
	public Response createNewProduct(@PathParam("description") String description, @PathParam("quantity") int quantity, @PathParam("TYPE") TYPE type,
									 @PathParam("COLOR") COLOR color,
									 @PathParam("price") double price, @PathParam("WOOD") WOOD wood);

	@Path("/products")
	@GET
	public Response getAvailableProducts();
	
	
	@DELETE
	@Path("/product/{id}")
	public Response deleteProduct(int id);

	@PUT
	@Path("/product/{description}/{quantity}/{type}/{color}/{price}/{wood}")
	public Response updateProduct(@PathParam("description") String descr, @PathParam("quantity") int quant, @PathParam("type") TYPE type,
								  @PathParam("color") COLOR color, @PathParam("price") double price, @PathParam("wood") WOOD wood);
	
	@GET
	@Path("/product/{id}")
	public Response getProduct(@PathParam("id") int id);
	
	/*filtrer le produit par nom , prix ... en spécifiant le niveau de filter */
	@GET
	@Path("/product/{type}/{value}")
	public Response filterBy(@PathParam("type") String name, @PathParam("value") String v);
		

}
