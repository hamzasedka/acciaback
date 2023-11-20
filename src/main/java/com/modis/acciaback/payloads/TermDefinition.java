package com.modis.acciaback.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author nadege.tchuindem
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TermDefinition {
	private String sigle;
	private String domaine;
	private String definition;
	public String getSigle() {
		return sigle;
	}
	public void setSigle(String sigle) {
		this.sigle = sigle;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	

}
