package com.modis.acciaback.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * 
 * @author diane.dransart
 *
 */

@Entity
@Table(name = "categories")
public class CategoryDoc implements Serializable {

	private static final long serialVersionUID = -5748353973014586991L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	private String categoryLabel;
	
	@ManyToOne
	@JoinColumn(name = "ID_DEPART")
	private Department department;
	
	@OneToMany
	private List<Chart> charts;
	
	@OneToMany
	private List<Doc> docs;

	public CategoryDoc() {
		super();
	}

	public CategoryDoc(Integer categoryId, String categoryLabel) {
		super();
		this.categoryId = categoryId;
		this.categoryLabel = categoryLabel;
	}

	public CategoryDoc(Integer categoryId, String categoryLabel, Department department) {
		super();
		this.categoryId = categoryId;
		this.categoryLabel = categoryLabel;
		this.department = department;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryLabel() {
		return categoryLabel;
	}

	public void setCategoryLabel(String categoryLabel) {
		this.categoryLabel = categoryLabel;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@JsonIgnore
	@XmlTransient
	public List<Chart> getCharts() {
		return charts;
	}

	@JsonSetter
	public void setCharts(List<Chart> charts) {
		this.charts = charts;
	}

	@JsonIgnore
	@XmlTransient
	public List<Doc> getDocs() {
		return docs;
	}

	@JsonSetter
	public void setDocs(List<Doc> docs) {
		this.docs = docs;
	}
}
