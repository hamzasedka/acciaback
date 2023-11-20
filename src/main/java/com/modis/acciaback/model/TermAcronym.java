package com.modis.acciaback.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author diane.dransart
 *
 */

@Entity
@Table(name = "term_acronyms")
public class TermAcronym implements Serializable {

	private static final long serialVersionUID = 165295029896750898L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int termAcronymId;
	
	private String termAcronymLabel;
	
	@ManyToOne
	@JoinColumn(name = "ID_TERM_DOM")
	private TermDomain termDom;
	
	@OneToOne
	@JoinColumn(name = "ID_TERM_DEF")
	private TermDefinition termDef;

	public TermAcronym() {
		super();
	}

	public TermAcronym(int termAcronymId, String termAcronymLabel, TermDomain termDom) {
		super();
		this.termAcronymId = termAcronymId;
		this.termAcronymLabel = termAcronymLabel;
		this.termDom = termDom;
	}

	public int getTermAcronymId() {
		return termAcronymId;
	}

	public void setTermAcronymId(int termAcronymId) {
		this.termAcronymId = termAcronymId;
	}

	public String getTermAcronymLabel() {
		return termAcronymLabel;
	}

	public void setTermAcronymLabel(String termAcronymLabel) {
		this.termAcronymLabel = termAcronymLabel;
	}

	public TermDomain getTermDom() {
		return termDom;
	}

	public void setTermDom(TermDomain termDom) {
		this.termDom = termDom;
	}

	public TermDefinition getTermDef() {
		return termDef;
	}

	public void setTermDef(TermDefinition termDef) {
		this.termDef = termDef;
	}
}
