package com.modis.acciaback.payloads;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckingResponse implements Serializable{
		
		 private List<String>  entities_missing;
		 
		 private List<String>   entities_present;
		 
		 private boolean is_complete;
		 
		
		 public CheckingResponse() {
			 this.entities_missing = new ArrayList<>();
			 this.entities_present = new ArrayList<>();
	    
	    				 
	    }


		public List<String> getEntities_missing() {
			return entities_missing;
		}


		public void setEntities_missing(List<String> entities_missing) {
			this.entities_missing = entities_missing;
		}


		public List<String> getEntities_present() {
			return entities_present;
		}


		public void setEntities_present(List<String> entities_present) {
			this.entities_present = entities_present;
		}


		public boolean isIs_complete() {
			return is_complete;
		}


		public void setIs_complete(boolean is_complete) {
			this.is_complete = is_complete;
		}
		 
}
