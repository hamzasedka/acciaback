package com.modis.acciaback.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * 
 * @author diane.dransart
 *
 */

@Entity
@Table(name = "term_domains")
public class TermDomain implements Serializable {

	private static final long serialVersionUID = 4326643243469837945L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int termDomainId;

	private String termDomainLabel;

	@OneToMany
	private List<TermAcronym> termAcronyms;

	public TermDomain() {
		super();
	}

	public TermDomain(int termDomainId, String termDomainLabel, List<TermAcronym> termAcronyms) {
		super();
		this.termDomainId = termDomainId;
		this.termDomainLabel = termDomainLabel;
		this.termAcronyms = termAcronyms;
	}

	public int getTermDomainId() {
		return termDomainId;
	}

	public void setTermDomainId(int termDomainId) {
		this.termDomainId = termDomainId;
	}

	public String getTermDomainLabel() {
		return termDomainLabel;
	}

	public void setTermDomainLabel(String termDomainLabel) {
		this.termDomainLabel = termDomainLabel;
	}

	@JsonIgnore
	@XmlTransient
	public List<TermAcronym> getTermAcronyms() {
		return termAcronyms;
	}

	@JsonSetter
	public void setTermAcronyms(List<TermAcronym> termAcronyms) {
		this.termAcronyms = termAcronyms;
	}
}
