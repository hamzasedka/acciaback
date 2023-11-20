package com.modis.acciaback.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author diane.dransart
 *
 */

@Entity
@Table(name = "serial_numbers")
public class SerialNumber implements Serializable {

	private static final long serialVersionUID = 4873999169877287766L;

	@Id
	private int serialNumberId;
	
	private String serialNumberLabel;
	
	@OneToOne
	private Component component;

	public SerialNumber() {
		super();
	}

	public SerialNumber(int serialNumberId, String serialNumberLabel) {
		super();
		this.serialNumberId = serialNumberId;
		this.serialNumberLabel = serialNumberLabel;
	}
	
	public SerialNumber(int serialNumberId, String serialNumberLabel, Component component) {
		super();
		this.serialNumberId = serialNumberId;
		this.serialNumberLabel = serialNumberLabel;
		this.component = component;
	}

	public int getSerialNumberId() {
		return serialNumberId;
	}

	public void setSerialNumberId(int serialNumberId) {
		this.serialNumberId = serialNumberId;
	}

	public String getSerialNumberLabel() {
		return serialNumberLabel;
	}

	public void setSerialNumberLabel(String serialNumberLabel) {
		this.serialNumberLabel = serialNumberLabel;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}
}
