package fr.unice.polytech.soa1.cookbook;


import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import fr.unice.polytech.soa1.cookbook.data.*;

@WebService(name="TaxComputation")
public interface BillService {


	@WebResult(name="simple_result")
	TaxComputation simple(@WebParam(name="simpleTaxInfo") SimpleTaxRequest request);

	@WebResult(name="complex_result")
	TaxComputation complex(@WebParam(name="complexTaxInfo") AdvancedTaxRequest request);

	@WebResult(name="command_result")
	TaxComputation command(@WebParam(name="commandInfo") SimpleTaxRequest request);

/*	@WebResult(name="command_result")
	TaxComputation commandFurniture(@WebParam(name="commandInfo") SimpleTaxRequest request);*/
}

