package com.modis.acciaback.payloads;

public class DocumentRequest {

	
	private String documentType;
	private String componentLabel;
	private String serialNumber;
		
	
	
	/**
	 * 
	 */
	public DocumentRequest() {
		this.documentType="";
		this.componentLabel="";
		this.serialNumber="";
		
	}
	/*
	 * Document Type
	 */
	public String getDocumentType() {
		return documentType;
	}
	
	public void setDocumentType(String docLabel) {
		this.documentType = docLabel;
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
	public void setSerialNumberLabel(String serialNumberLabel) {
		this.serialNumber = serialNumberLabel;
	}
	
}
