package com.modis.acciaback.service;

import com.modis.acciaback.payloads.CheckingResponse;
import com.modis.acciaback.payloads.DefinitionsResponse;


/**
 * 
 * @author diane.dransart
 *
 */

public interface TermService {
	
	
	public CheckingResponse checking(String termInfos);
	public DefinitionsResponse searchDefinition(String sigle);
	public DefinitionsResponse searchDefinition(String sigle, String domaine);
	
	
}
