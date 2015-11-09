package fr.unice.polytech.soa1.cookbook.flows;


import fr.unice.polytech.soa1.cookbook.flows.utils.RequestBuilder;
import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import javax.xml.transform.Source;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static fr.unice.polytech.soa1.cookbook.flows.utils.Endpoints.*;


public class CallExternalPartners extends RouteBuilder {


	@Override
	public void configure() throws Exception {

		// Consuming a rest service with a Get
		from("direct:generator")
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setBody(constant(""))
				.to(GEN_SERVICE + "/cxf/demo/catalog/product/1")
				.process(readResponseStream)
						//.process(uuidCleaner)
				.log("==========================")
				.log(" name of product ${body}	")
				.log("==========================")
				.to("direct:generateLetter")
				;

		// Consuming a rest service with a Post
		from("direct:generator2")
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
			//	.setBody(constant(""))
				.log("############################################################################")
				.log(" BEFORE : name of product ${body}	")
				.log("############################################################################")
				.setProperty("test",body())
				.setBody(constant(""))
				.to(GEN_SERVICE + "/cxf/demo/carts/cart/6")
				.setBody(simple("${property.test}"))
				.log("==========================")
				.log(" name of product ${body}	")
				.log("==========================")

				;


		// SOAP: Using the simple method
		from("direct:commandMethod")
				.log("    Computing ${body.name} with simple computation [ref: ${property.p_uuid}]")
				.bean(RequestBuilder.class, "buildSimpleRequest(${body}, ${property.p_uuid})")
				.to(Bill_COMPUTATION_SERVICE)
				.process(result2bill)
				;

	}

	/**
	 * Static processors used as helpers to process the retrieved data
	 */

	// read an InputStream completely and store it in a String
	private static Processor readResponseStream = new Processor() {
		public void process(Exchange exchange) throws Exception {
			InputStream response = (InputStream) exchange.getIn().getBody();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response));
			StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) { out.append(line); }
			reader.close();
			exchange.getIn().setBody(out.toString());
		}
	};

	// transform "xxx" into xxx (removing starting and ending double quotes).
	private static Processor uuidCleaner = new Processor() {
		public void process(Exchange exchange) throws Exception {
			String data = (String) exchange.getIn().getBody();
			String cleaned ="";// data.substring(1,data.length()-1);
			for (int i = 0; i < data.length(); i++) {
				if(data.charAt(i)=='i'){cleaned+="\n";}
				else{cleaned+=data.charAt(i);}

			}
			exchange.getIn().setBody(cleaned);
		}
	};

	// Transform the response of the TaxComputation web service into a BillForm Business Object (using XPath)
	private static Processor result2bill = new Processor() {

		private XPath xpath = XPathFactory.newInstance().newXPath();    // feature:install camel-saxon

		public void process(Exchange exchange) throws Exception {
			Source response = (Source) exchange.getIn().getBody();
			BillForm result = new BillForm();
			result.setAmount(Double.parseDouble(xpath.evaluate("//amount/text()", response)));
			result.setDate(xpath.evaluate("//date/text()", response));
			exchange.getIn().setBody(result);
		}
	};

}
