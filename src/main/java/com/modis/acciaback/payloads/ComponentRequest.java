package com.modis.acciaback.payloads;





public class ComponentRequest {
	private String componentLabel;	
	private String serialNumber;
	
	public ComponentRequest() {
		// TODO Auto-generated constructor stub
		componentLabel="";
		serialNumber="";
	}

	public String getComponentLabel() {
		return componentLabel;
	}

	public void setComponentLabel(String componentLabel) {
		this.componentLabel = componentLabel;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
