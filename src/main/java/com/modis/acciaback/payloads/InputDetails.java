package com.modis.acciaback.payloads;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 

@JsonIgnoreProperties(ignoreUnknown = true)
public class InputDetails implements Serializable{
    private String intention;
    private LinkedHashMap<String,String>  entities;   
    private String criticity_level;

    public InputDetails() {
    	 this.entities = new LinkedHashMap<>();
    }

    public String getIntention() {
        return intention;
    }

 

    public void setIntention(String intention) {
        this.intention = intention;
    }

 

    public LinkedHashMap<String,String> getEntities() {
        return entities;
    }

 

    public void setEntities(LinkedHashMap<String,String> entities) {
        this.entities = entities;
    }

 

    public String getCriticity_level() {
        return criticity_level;
    }

 

    public void setCriticity_level(String criticity_level) {
        this.criticity_level = criticity_level;
    }
}

 