package com.modis.acciaback.service.serviceImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.modis.acciaback.model.InfoTypeValue;
import com.modis.acciaback.payloads.ComponentRequest;
import com.modis.acciaback.repository.InfoTypeValueRepository;
import com.modis.acciaback.service.ComponentService;

@Service
public class ComponentServiceImpl implements ComponentService {

	Logger log = LogManager.getLogger(ComponentServiceImpl.class);
	private InfoTypeValueRepository infoTypeValueRepository;

	
	public ComponentServiceImpl(InfoTypeValueRepository infoTypeValueRepository) {
		
		this.infoTypeValueRepository = infoTypeValueRepository;
		
	}
	@Override
	public List<InfoTypeValue> informationsDetails(ComponentRequest componentInfos) {
		log.info("-------------------- Je suis Component informations Service ------------------------------");

		List<InfoTypeValue> paramsFound = null;

		paramsFound = infoTypeValueRepository.componentInformations(componentInfos.getComponentLabel(),
				componentInfos.getSerialNumber());

		if (paramsFound == null) {
			System.out.println("--------------------------params not found-----------------------:" + paramsFound);
		} else {
			System.out.println("---------------Params found ------------ :" + paramsFound);
		}
		return paramsFound;
	}

}
