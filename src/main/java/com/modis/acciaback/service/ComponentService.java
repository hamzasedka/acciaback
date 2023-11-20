package com.modis.acciaback.service;

import java.util.List;

import com.modis.acciaback.model.InfoTypeValue;
import com.modis.acciaback.payloads.ComponentRequest;

public interface ComponentService {

	List<InfoTypeValue> informationsDetails(ComponentRequest componentInfos);

}