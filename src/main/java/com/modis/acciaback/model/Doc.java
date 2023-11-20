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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.modis.acciaback.enumerations.FormatDoc;

@Entity
@Table(name = "docs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "doc_type", discriminatorType = DiscriminatorType.STRING, length = 10)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(name = "ManualDoc", value = ManualDoc.class), @Type(name = "NoticeDoc", value = NoticeDoc.class),
		@Type(name = "Repertory", value = Repertory.class), @Type(name = "DiagramDoc", value = DiagramDoc.class),
		@Type(name = "CurveDoc", value = CurveDoc.class), @Type(name = "HistoricalDoc", value = HistoricalDoc.class) })
@XmlSeeAlso({ ManualDoc.class, NoticeDoc.class, Repertory.class, DiagramDoc.class, CurveDoc.class,
		HistoricalDoc.class })
public abstract class Doc implements Serializable {

	private static final long serialVersionUID = -3206334167278715263L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer docId;

	private String docTitle;

	private String docContent;

	//private String docLabel;
	
	private FormatDoc docFormat;

	@ManyToOne
	@JoinColumn(name = "ID_COMP")
	private Component component;

	@ManyToOne
	@JoinColumn(name = "ID_CATEG")
	private CategoryDoc docCategory;

	public Doc() {
		super();
	}

	public Doc(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat) {
		super();
		this.docId = docId;
		this.docTitle = docTitle;
		this.docContent = docContent;
		//this.docLabel = docLabel;
		this.docFormat = docFormat;
	}

	public Doc(Integer docId, String docTitle, String docContent, String docLabel, FormatDoc docFormat,
			Component component, CategoryDoc docCategory) {
		super();
		this.docId = docId;
		this.docTitle = docTitle;
		this.docContent = docContent;
		//this.docLabel = docLabel;
		this.docFormat = docFormat;
		this.component = component;
		this.docCategory = docCategory;
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocContent() {
		return docContent;
	}

	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}

	/*
	 * public String getDocLabel() { return docLabel; }
	 * 
	 * public void setDocLabel(String docLabel) { this.docLabel = docLabel; }
	 */

	public FormatDoc getDocFormat() {
		return docFormat;
	}

	public void setDocFormat(FormatDoc docFormat) {
		this.docFormat = docFormat;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public CategoryDoc getDocCategory() {
		return docCategory;
	}

	public void setDocCategory(CategoryDoc docCategory) {
		this.docCategory = docCategory;
	}
}
