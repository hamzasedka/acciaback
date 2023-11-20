package com.modis.acciaback.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "departments")
public class Department implements Serializable {

	private static final long serialVersionUID = -1779869071385808022L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer departmentId;

	private String departmentLabel;
	
	@OneToMany
	private List<CategoryDoc> docCategories;

	public Department() {
		super();
	}

	public Department(Integer departmentId, String departmentLabel) {
		super();
		this.departmentId = departmentId;
		this.departmentLabel = departmentLabel;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentLabel() {
		return departmentLabel;
	}

	public void setDepartmentLabel(String departmentLabel) {
		this.departmentLabel = departmentLabel;
	}

	@JsonIgnore
	@XmlTransient
	public List<CategoryDoc> getDocCategories() {
		return docCategories;
	}

	@JsonSetter
	public void setDocCategories(List<CategoryDoc> docCategories) {
		this.docCategories = docCategories;
	}
}
