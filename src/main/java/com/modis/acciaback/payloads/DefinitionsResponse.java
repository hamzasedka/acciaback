/**
 * 
 */
package com.modis.acciaback.payloads;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author nadege.tchuindem
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefinitionsResponse {
	private List<TermDefinition>  definitions; 
	
	  public DefinitionsResponse() {
		  this.definitions = new ArrayList<>();
	    }

	public List<TermDefinition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<TermDefinition> definitions) {
		this.definitions = definitions;
	}
 
		   
}
