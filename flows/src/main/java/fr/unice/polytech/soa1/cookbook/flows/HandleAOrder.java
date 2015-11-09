package fr.unice.polytech.soa1.cookbook.flows;

import static fr.unice.polytech.soa1.cookbook.flows.utils.Endpoints.*;

import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;
import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;
import fr.unice.polytech.soa1.cookbook.flows.utils.Database;
import fr.unice.polytech.soa1.cookbook.flows.utils.LetterWriter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.core.annotation.Order;

import java.util.Map;


/**
 * feature:install http
 */
public class HandleAOrder extends RouteBuilder {
	static Map<Integer, Object> input = null;
	static Map<Integer, Object> inputest = null;
	static int testval = 0;
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
				.setProperty("p_uuid", simple("${body.idClient}"))
                .setBody(simple("${property.orderline}"))
				/*.log("		BEFORE	${body}	")
				.log("##########################################")
						//.process(test2orderLines)
				.log("##########################################")
				.log("		PAST	${body}	")
				.log("##########################################")*/
                .choice()
                    .when(simple("${body.idClient} == 0"))
                        .log("	enter in choice and when	${body.ref}		")
                        .setProperty("bill_computation_method", constant("COMMAND"))
                        .log("___________  check for existence of product ___________")
                        .when(simple(String.valueOf(Database.contents.get(1).getAmount() > 0)))
                                .log("--- product exist ---- ")
                                .to("direct:generator2")
                                .to("direct:commandMethod")
                    .when(simple("${body.idClient} == 1"))
                        .log("	enter in choice and when	${body.ref}		")
                        .setProperty("bill_computation_method", constant("COMMAND"))
                        .log("___________  call services methods ___________ ")
                        .to("direct:generator2")
                        .to("direct:commandMethod")
                    .when(simple(String.valueOf(Database.contents.get(1).getAmount() > 0)))
                        .log("00000???????????????????????????????????????00000")
                        .to("direct:badOrder").stop() // stopping the route for bad citizens
                    .otherwise()
                            .to("direct:badOrder").stop() // stopping the route for bad citizens
                .end() // End of the content-based-router
                .setHeader("orderline_idClient", simple("${property.orderline.idClient}"))
                .multicast()
                .parallelProcessing()
                .to("direct:generateLetterBill")


        ;
		// collect and re-assemble the validated OrderItems into an order again
		/*from("seda:aggregate")
				.aggregate(new MyOrderAggregationStrategy()).header("orderId").completionTimeout(1000L)
				.to("seda:result");
*/
		// bad information about a given citizen
		from("direct:badOrder")
				.log("    Bad information for citizen ${body.name}, ending here.")
		;

		from("direct:generateLetterBill")
				.bean(LetterWriter.class, "write(${property.orderline}, ${body}, ${property.bill_computation_method})")
				.to(CSV_OUTPUT_DIRECTORY + "?fileName=${property.p_uuid}.txt")
		;
		from("direct:generateLetter")
				.bean(LetterWriter.class, "write2()")
				.to(CSV_OUTPUT_DIRECTORY + "?fileName=file0.txt")
		;


/*
		from("seda:result")
				.bean(LetterWriter.class, "write(${property.orderline}, ${body}, ${property.tax_computation_method})")
				.to(CSV_OUTPUT_DIRECTORY + "?fileName=${property.uuid}.txt")

		;*/
	}
		private static Processor test2orderLines = new Processor() {

			public void process(Exchange exchange) throws Exception {
				// retrieving the body of the exchanged message
				input.put(0, exchange.getIn().getBody());
				OrderLine o = (OrderLine) input.get(0);
				o.setName("Product23434");
				exchange.getIn().setBody(o);
			}

	};

		/*private static Processor verifyExistant = new Processor() {

				public void process(Exchange exchange) throws Exception {
					inputest.put(0,1);
					inputest.put(1,2);

					String s = "test deu process verifyEx";
					exchange.getIn().setBody(s);
				}

		};*/

}
