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
@DiscriminatorValue("courbe")
@XmlType(name="courbe")
public class CurveDoc extends Doc {

	public CurveDoc() {
		super();
	}

	public CurveDoc(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat,
			Component component, CategoryDoc docCategory) {
		super(docId, docTitle, docContent, docLabel, docFormat, component, docCategory);
	}

	public CurveDoc(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat) {
		super(docId, docTitle, docContent, docLabel, docFormat);
	}
}
