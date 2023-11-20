package com.modis.acciaback.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "components")
public class Component implements Serializable {

	private static final long serialVersionUID = 6668932332806203815L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer componentId;

	private String componentLabel;

	@OneToOne
	private SerialNumber serialNumber;

	@OneToMany
	private List<Doc> docs;

	@OneToMany
	private List<Chart> charts;

	@OneToMany
	private List<InfoTypeValue> infoTypeValues;

	public Component() {
		super();
	}

	public Component(Integer componentId, String componentLabel) {
		super();
		this.componentId = componentId;
		this.componentLabel = componentLabel;
	}

	public Component(Integer componentId, String componentLabel, SerialNumber serialNumber) {
		super();
		this.componentId = componentId;
		this.componentLabel = componentLabel;
		this.serialNumber = serialNumber;
	}

	public Component(Integer componentId, String componentLabel, SerialNumber serialNumber, List<Doc> docs,
			List<Chart> charts, List<InfoTypeValue> infoTypeValues) {
		super();
		this.componentId = componentId;
		this.componentLabel = componentLabel;
		this.serialNumber = serialNumber;
		this.docs = docs;
		this.charts = charts;
		this.infoTypeValues = infoTypeValues;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public String getComponentLabel() {
		return componentLabel;
	}

	public void setComponentLabel(String componentLabel) {
		this.componentLabel = componentLabel;
	}

	public SerialNumber getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(SerialNumber serialNumber) {
		this.serialNumber = serialNumber;
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

	@JsonIgnore
	@XmlTransient
	public List<Chart> getCharts() {
		return charts;
	}

	@JsonSetter
	public void setCharts(List<Chart> charts) {
		this.charts = charts;
	}

	public List<InfoTypeValue> getInfoTypeValues() {
		return infoTypeValues;
	}

	public void setInfoTypeValues(List<InfoTypeValue> infoTypeValues) {
		this.infoTypeValues = infoTypeValues;
	}
}
