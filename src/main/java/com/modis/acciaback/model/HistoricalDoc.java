package com.modis.acciaback.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlType;

import com.modis.acciaback.enumerations.FormatDoc;

/**
 * 
 * @author diane.dransart
 *
 */

@Entity
@DiscriminatorValue("historique")
@XmlType(name = "historique")
public class HistoricalDoc extends Doc {

	//@OneToMany
	//private List<InfoTypeValue> infoTypeValues;

	public HistoricalDoc() {
		super();
	}

	public HistoricalDoc(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat,
			Component component, CategoryDoc docCategory) {
		super(docId, docTitle, docContent, docLabel, docFormat, component, docCategory);
	}

	public HistoricalDoc(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat) {
		super(docId, docTitle, docContent, docLabel, docFormat);
	}

	/*
	 * public HistoricalDoc(Integer docId, String docTitle, String docContent,
	 * String docLabel, FormatDoc docFormat, Component component, CategoryDoc
	 * docCategory, List<InfoTypeValue> infoTypeValues) { super(docId, docTitle,
	 * docContent, docLabel, docFormat, component, docCategory); this.infoTypeValues
	 * = infoTypeValues; }
	 * 
	 * @JsonIgnore
	 * 
	 * @XmlTransient public List<InfoTypeValue> getInfoTypeValues() { return
	 * infoTypeValues; }
	 * 
	 * @JsonSetter public void setInfoTypeValues(List<InfoTypeValue> infoTypeValues)
	 * { this.infoTypeValues = infoTypeValues; }
	 */
}
