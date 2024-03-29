package fr.unice.polytech.soa1.cookbook.flows.utils;

import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;
import fr.unice.polytech.soa1.cookbook.flows.business.Person;
import org.springframework.core.annotation.Order;


/**
 * This file is part of the system project
 *
 * @author mosser (19/10/2015, 12:28)
 **/
public class RequestBuilder {

	public String buildSimpleRequest(OrderLine o, String uuid) {
		StringBuilder builder = new StringBuilder();
		builder.append("<cook:command xmlns:cook=\"http://cookbook.soa1.polytech.unice.fr/\">\n");
		builder.append("  <commandInfo>\n");
		builder.append("    <id>"     + uuid          + "</id>\n");
		builder.append("    <quant>" + o.getQuantity() + "</quant>\n");
		builder.append("    <income>" + o.getPrice() + "</income>\n");
		builder.append("  </commandInfo>\n");
		builder.append("</cook:command>");
		return builder.toString();
	}

/*
	public String buildAdvancedRequest(OrderLine o, String uuid) {
		StringBuilder builder = new StringBuilder();
		builder.append("<cook:complex xmlns:cook=\"http://cookbook.soa1.polytech.unice.fr/\">\n");
		builder.append("  <complexTaxInfo>\n");
		builder.append("    <id>"     + uuid           + "</id>\n");
		builder.append("    <income>" + o.getQuantity()  + "</income>\n");
		/*builder.append("    <assets>" + p.getAssets()  + "</assets>\n");
		builder.append("    <zone>"   + p.getZipCode() + "</zone>\n");*/
	/*	builder.append("  </complexTaxInfo>\n");
		builder.append("</cook:complex>");
		return builder.toString();
	}*/
}
