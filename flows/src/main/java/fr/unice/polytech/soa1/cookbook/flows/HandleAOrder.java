package fr.unice.polytech.soa1.cookbook.flows;

import static fr.unice.polytech.soa1.cookbook.flows.utils.Endpoints.*;

import fr.unice.polytech.soa1.cookbook.flows.utils.LetterWriter;
import org.apache.camel.builder.RouteBuilder;


/**
 * feature:install http
 */
public class HandleAOrder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// Dead letter channel as a logger
		errorHandler(deadLetterChannel("log:deadPool"));

		// Route to handle a given Order
		from(HANDLE_ORDER)
				.log("    Routing ${body.name} according to quantity ${body.quantity}")
				.log("      Storing the Person as an exchange property")
				.setProperty("orderline", body())
				.log("      Calling an existing generator")
				.to("direct:generator")
				.setProperty("catalog", body())
				.setBody(simple("${property.orderline}"))
				.choice()
					.when(simple("${body.price} >= 0 && ${body.quantity} > 0"))
						.setProperty("bill_computation_method", constant("COMMAND"))
						.to("direct:commandMethod")
					.otherwise()
						.to("direct:badOrder").stop() // stopping the route for bad citizens*/
					.end() // End of the content-based-router
				.setHeader("orderline_ref", simple("${property.orderline.ref}"))
				.multicast()
					.parallelProcessing()
					.to("direct:generateLetterBill")
					.to("direct:generateLetter")

					//.to(STORE_TAX_FORM)

		;
		// collect and re-assemble the validated OrderItems into an order again
		/*from("seda:aggregate")
				.aggregate(new MyOrderAggregationStrategy()).header("orderId").completionTimeout(1000L)
				.to("seda:result");
*/
		// bad information about a given citizen
	/*	from("direct:badOrder")
				.log("    Bad information for citizen ${body.name}, ending here.")
		;*/

		from("direct:generateLetterBill")
				.bean(LetterWriter.class, "write(${property.orderline}, ${body}, ${property.bill_computation_method})")
				.to(CSV_OUTPUT_DIRECTORY + "?fileName=file1.txt")
		;
		from("direct:generateLetter")
				.bean(LetterWriter.class, "write2(${property.catalog})")
				.to(CSV_OUTPUT_DIRECTORY + "?fileName=file0.json")
		;


/*
		from("seda:result")
				.bean(LetterWriter.class, "write(${property.orderline}, ${body}, ${property.tax_computation_method})")
				.to(CSV_OUTPUT_DIRECTORY + "?fileName=${property.uuid}.txt")

		;*/

	}


}
