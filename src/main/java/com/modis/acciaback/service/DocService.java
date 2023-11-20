package com.modis.acciaback.service;

import com.modis.acciaback.model.Doc;
import com.modis.acciaback.payloads.CheckingResponse;
import com.modis.acciaback.payloads.DocumentRequest;


/**
 * 
 * @author diane.dransart
 *
 */

public interface DocService {
	
	public Doc searchDoc(DocumentRequest docInfos);
	public CheckingResponse checking(DocumentRequest docInfos);
	
}
