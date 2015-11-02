package fr.unice.polytech.soa1.cookbook.flows.utils;


import fr.unice.polytech.soa1.cookbook.flows.business.OrderLine;
import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;

public class LetterWriter {

	public String write(OrderLine o, BillForm form, String method) {
		StringBuilder b = new StringBuilder();

		b.append("Product " + o.getName() + ", \n");
		b.append("\n");
		b.append("  Quantity: " + o.getQuantity() +"\n");
		b.append("  Price: " + o.getPrice() +"\n");
		b.append("  Color: " + o.getColor() +"\n");
		b.append("  Amount to pay: " + form.getAmount() + "\n");
		b.append("  Bill  using the " + method + " method on " + form.getDate() + "tentative 5" + "\n");

		/*b.append("  ID: " + p.getUid() + "\n");
		b.append("\n\n");
		b.append("Taxes computed using the " + method + " method on " + form.getDate() + "\n");
		b.append("\n\n");
		b.append("Amount to pay: " + form.getAmount() + "\n");*/
		b.append("\n");

		return b.toString();
	}

	public String write2(String listCatalog) {
		StringBuilder b = new StringBuilder();

		b.append("---\n---");
		b.append(listCatalog);
		b.append("\n");

		return b.toString();
	}
}
