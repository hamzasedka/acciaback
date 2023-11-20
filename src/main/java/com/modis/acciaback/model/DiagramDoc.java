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
@DiscriminatorValue("diagramme")
@XmlType(name="diagramme")
public class DiagramDoc extends Doc {

	public DiagramDoc() {
		super();
	}

	public DiagramDoc(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat,
			Component component, CategoryDoc docCategory) {
		super(docId, docTitle, docContent, docLabel, docFormat, component, docCategory);
	}

	public DiagramDoc(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat) {
		super(docId, docTitle, docContent, docLabel, docFormat);
	}
}
