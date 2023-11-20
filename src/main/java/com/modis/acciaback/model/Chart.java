package com.modis.acciaback.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.modis.acciaback.enumerations.TypeChart;

/**
 * 
 * @author diane.dransart
 *
 */

@Entity
@Table(name = "charts")
public class Chart implements Serializable {

	private static final long serialVersionUID = 6863975381583974725L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer chartId;
	
	private String chartTitle;

	private String chartSubTitle;

	private String chartLegendX;
	
	private String chartLegendY;

	private String chartTooltip;
	
	private TypeChart chartType;

	@ManyToOne
	@JoinColumn(name = "ID_COMP")
	private Component component;

	@ManyToOne
	@JoinColumn(name = "ID_CATEG")
	private CategoryDoc docCategory;

	public Chart() {
		super();
	}

	public Chart(Integer chartId, String chartTitle, String chartSubTitle, String chartLegendX, String chartLegendY,
			String chartTooltip, TypeChart chartType) {
		super();
		this.chartId = chartId;
		this.chartTitle = chartTitle;
		this.chartSubTitle = chartSubTitle;
		this.chartLegendX = chartLegendX;
		this.chartLegendY = chartLegendY;
		this.chartTooltip = chartTooltip;
		this.chartType = chartType;
	}

	public Chart(Integer chartId, String chartTitle, String chartSubTitle, String chartLegendX, String chartLegendY,
			String chartTooltip, TypeChart chartType, Component component, CategoryDoc docCategory) {
		super();
		this.chartId = chartId;
		this.chartTitle = chartTitle;
		this.chartSubTitle = chartSubTitle;
		this.chartLegendX = chartLegendX;
		this.chartLegendY = chartLegendY;
		this.chartTooltip = chartTooltip;
		this.chartType = chartType;
		this.component = component;
		this.docCategory = docCategory;
	}

	public Integer getChartId() {
		return chartId;
	}

	public void setChartId(Integer chartId) {
		this.chartId = chartId;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public String getChartSubTitle() {
		return chartSubTitle;
	}

	public void setChartSubTitle(String chartSubTitle) {
		this.chartSubTitle = chartSubTitle;
	}

	public String getChartLegendX() {
		return chartLegendX;
	}

	public void setChartLegendX(String chartLegendX) {
		this.chartLegendX = chartLegendX;
	}

	public String getChartLegendY() {
		return chartLegendY;
	}

	public void setChartLegendY(String chartLegendY) {
		this.chartLegendY = chartLegendY;
	}

	public String getChartTooltip() {
		return chartTooltip;
	}

	public void setChartTooltip(String chartTooltip) {
		this.chartTooltip = chartTooltip;
	}

	public TypeChart getChartType() {
		return chartType;
	}

	public void setChartType(TypeChart chartType) {
		this.chartType = chartType;
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
