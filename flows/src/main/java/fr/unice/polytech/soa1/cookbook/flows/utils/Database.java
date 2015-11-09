package fr.unice.polytech.soa1.cookbook.flows.utils;

import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;

import java.util.HashMap;
import java.util.Map;


public final class Database {

	// Local mock for a database
	public static Map<Integer, BillForm> contents = new HashMap<Integer, BillForm>();

	public void setData(int uid, BillForm f) {
		contents.put(uid, f);
	}

	public BillForm getData(int uuid) {
		if (contents.containsKey(uuid))
			return contents.get(uuid);
		else
			throw new IllegalArgumentException("Unknown uuid: [" + uuid + "]");
	}

	public double getAmounts(int id){
		return contents.get(id).getAmount();
	}

	static {
		Database.contents.put(1,new BillForm());
		Database.contents.put(2,new BillForm());
		Database.contents.put(3,new BillForm());
		Database.contents.get(1).setAmount(4555.5);
	}

}