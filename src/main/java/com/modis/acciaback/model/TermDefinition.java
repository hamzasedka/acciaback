package com.modis.acciaback.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

/**
 * 
 * @author diane.dransart
 *
 */

@Entity
@Table(name = "term_definitions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "content_type", discriminatorType = DiscriminatorType.STRING, length = 15)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
	@Type(name = "TextualContent", value = TextualContent.class), 
	@Type(name = "ChartContent", value = ChartContent.class),
	@Type(name = "MixContent", value = MixContent.class)
	})
@XmlSeeAlso({ TextualContent.class, ChartContent.class, MixContent.class })
public abstract class TermDefinition implements Serializable {

	private static final long serialVersionUID = 1532553631579394546L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int termDefId;
	
	@OneToOne
	@JoinColumn(name = "ID_TERM_ACRONYM")
	private TermAcronym termAcronym;

	public TermDefinition() {
		super();
	}

	public TermDefinition(int termDefId, TermAcronym termAcronym) {
		super();
		this.termDefId = termDefId;
		this.termAcronym = termAcronym;
	}

	public int getTermDefId() {
		return termDefId;
	}

	public void setTermDefId(int termDefId) {
		this.termDefId = termDefId;
	}

	public TermAcronym getTermAcronym() {
		return termAcronym;
	}

	public void setTermAcronym(TermAcronym termAcronym) {
		this.termAcronym = termAcronym;
	}
}
