package com.modis.acciaback.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author diane.dransart
 *
 */

@Entity
@DiscriminatorValue("textuel")
@XmlType(name = "textuel")
public class TextualContent extends TermDefinition {
	
	private String textualContent;

	public TextualContent() {
		super();
	}

	public TextualContent(int termDefId, TermAcronym termAcronym) {
		super(termDefId, termAcronym);
	}

	public TextualContent(int termDefId, TermAcronym termAcronym, String textualContent) {
		super(termDefId, termAcronym);
		this.textualContent = textualContent;
	}

	public String getTextualContent() {
		return textualContent;
	}

	public void setTextualContent(String textualContent) {
		this.textualContent = textualContent;
	}
}
