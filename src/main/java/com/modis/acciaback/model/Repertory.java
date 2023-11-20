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
@DiscriminatorValue("dossier")
@XmlType(name="dossier")
public class Repertory extends Doc {

	public Repertory() {
		super();
	}

	public Repertory(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat,
			Component component, CategoryDoc docCategory) {
		super(docId, docTitle, docContent, docLabel, docFormat, component, docCategory);
	}

	public Repertory(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat) {
		super(docId, docTitle, docContent, docLabel, docFormat);
	}

}
