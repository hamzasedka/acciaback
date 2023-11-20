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
@DiscriminatorValue("graphique")
@XmlType(name = "graphique")
public class ChartContent extends TermDefinition {

	private String chartContentUrl;

	public ChartContent() {
		super();
	}

	public ChartContent(int termDefId, TermAcronym termAcronym) {
		super(termDefId, termAcronym);
	}

	public ChartContent(int termDefId, TermAcronym termAcronym, String chartContentUrl) {
		super(termDefId, termAcronym);
		this.chartContentUrl = chartContentUrl;
	}

	public String getChartContentUrl() {
		return chartContentUrl;
	}

	public void setChartContentUrl(String chartContentUrl) {
		this.chartContentUrl = chartContentUrl;
	}
}
