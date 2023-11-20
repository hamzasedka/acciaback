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
@DiscriminatorValue("mixte")
@XmlType(name = "mixte")
public class MixContent extends TermDefinition {
	
	private String textualContent;
	private String chartContentUrl;
	
	public MixContent() {
		super();
	}
	
	public MixContent(int termDefId, TermAcronym termAcronym) {
		super(termDefId, termAcronym);
	}

	public MixContent(int termDefId, TermAcronym termAcronym, String textualContent, String chartContentUrl) {
		super(termDefId, termAcronym);
		this.textualContent = textualContent;
		this.chartContentUrl = chartContentUrl;
	}

	public String getTextualContent() {
		return textualContent;
	}

	public void setTextualContent(String textualContent) {
		this.textualContent = textualContent;
	}

	public String getChartContentUrl() {
		return chartContentUrl;
	}

	public void setChartContentUrl(String chartContentUrl) {
		this.chartContentUrl = chartContentUrl;
	}
}
