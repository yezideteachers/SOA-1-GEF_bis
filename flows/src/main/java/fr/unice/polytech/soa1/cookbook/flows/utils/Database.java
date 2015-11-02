package fr.unice.polytech.soa1.cookbook.flows.utils;

import fr.unice.polytech.soa1.cookbook.flows.business.BillForm;

import java.util.HashMap;
import java.util.Map;


public final class Database {

	// Local mock for a database
	private static Map<String,BillForm> contents = new HashMap<String, BillForm>();

	public void setData(String uid, BillForm f) {
		contents.put(uid, f);
	}

	public BillForm getData(String uuid) {
		if (contents.containsKey(uuid))
			return contents.get(uuid);
		else
			throw new IllegalArgumentException("Unknown uuid: [" + uuid + "]");
	}

}
