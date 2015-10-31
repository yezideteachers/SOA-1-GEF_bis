package fr.unice.polytech.soa1.cookbook.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class SimpleTaxRequest {

	private String identifier;
	private float income;
	private int quant;

	@XmlElement(name = "id", required = true)
	public String getIdentifier() { return identifier; }
	public void setIdentifier(String identifier) { this.identifier = identifier; }

	@XmlElement(required=true)
	public float getIncome() { return income; }
	public void setIncome(float income) { this.income = income; }

	@XmlElement(required=true)
	public int getQuant(){return quant;}
	public void setQuant(int quant){this.quant = quant;}

	@Override
	public String toString() {
		return "TaxRequest:\n  identifier: " + identifier + "\n  income: " + income;
	}

}
