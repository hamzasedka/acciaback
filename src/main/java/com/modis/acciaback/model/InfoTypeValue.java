package com.modis.acciaback.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author diane.dransart
 *
 */

@Entity
@Table(name = "info_type_values")
public class InfoTypeValue implements Serializable {

	private static final long serialVersionUID = 5402946048704350713L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double pressure; // pression

	private double volume;

	private double temperature;

	private double vibration;

	private double humidity;

	private double intensity;

	private double fanFlow; // debit

	private double powerInput; // puissance

	private double airVelocity; // vitesse d'air

	private double serviceValue; // efficacité energétique

	private double lifetime; // durée de vie

	@ManyToOne
	@JoinColumn(name = "ID_COMP")
	private Component component;
	
	@ManyToOne
	@JoinColumn(name = "ID_HIST_DOC")
	private HistoricalDoc historicalDoc;

	public InfoTypeValue() {
		super();
	}

	public InfoTypeValue(Integer id, double pressure, double volume, double temperature, double vibration,
			double humidity, double intensity, double fanFlow, double powerInput, double airVelocity,
			double serviceValue, double lifetime, Component component, HistoricalDoc historicalDoc) {
		super();
		this.id = id;
		this.pressure = pressure;
		this.volume = volume;
		this.temperature = temperature;
		this.vibration = vibration;
		this.humidity = humidity;
		this.intensity = intensity;
		this.fanFlow = fanFlow;
		this.powerInput = powerInput;
		this.airVelocity = airVelocity;
		this.serviceValue = serviceValue;
		this.lifetime = lifetime;
		this.component = component;
		this.historicalDoc = historicalDoc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getVibration() {
		return vibration;
	}

	public void setVibration(double vibration) {
		this.vibration = vibration;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getIntensity() {
		return intensity;
	}

	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}

	public double getFanFlow() {
		return fanFlow;
	}

	public void setFanFlow(double fanFlow) {
		this.fanFlow = fanFlow;
	}

	public double getPowerInput() {
		return powerInput;
	}

	public void setPowerInput(double powerInput) {
		this.powerInput = powerInput;
	}

	public double getAirVelocity() {
		return airVelocity;
	}

	public void setAirVelocity(double airVelocity) {
		this.airVelocity = airVelocity;
	}

	public double getServiceValue() {
		return serviceValue;
	}

	public void setServiceValue(double serviceValue) {
		this.serviceValue = serviceValue;
	}

	public double getLifetime() {
		return lifetime;
	}

	public void setLifetime(double lifetime) {
		this.lifetime = lifetime;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public HistoricalDoc getHistoricalDoc() {
		return historicalDoc;
	}

	public void setHistoricalDoc(HistoricalDoc historicalDoc) {
		this.historicalDoc = historicalDoc;
	}
	
}
