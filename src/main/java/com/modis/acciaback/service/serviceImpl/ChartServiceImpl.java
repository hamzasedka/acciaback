package com.modis.acciaback.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.modis.acciaback.model.Chart;
import com.modis.acciaback.model.InfoTypeValue;
import com.modis.acciaback.payloads.ChartRequest;
import com.modis.acciaback.payloads.ChatResponse;
import com.modis.acciaback.payloads.ComponentRequest;
import com.modis.acciaback.payloads.EntityResponse;
import com.modis.acciaback.repository.ChartRepository;
import com.modis.acciaback.repository.InfoTypeValueRepository;
import com.modis.acciaback.service.ChartService;
import com.modis.acciaback.service.ChatService;

/**
 * 
 * @author diane.dransart
 *
 */

@Service
public class ChartServiceImpl implements ChartService {
	Logger log = LogManager.getLogger(ChartServiceImpl.class);

	
	private ChartRepository chartRepository;

	

	public ChartServiceImpl(ChartRepository chartRepository) {
		
		this.chartRepository = chartRepository;
		
	}

	@Override
	public Chart searchChart(ChartRequest chartInfos) {
		log.info("-------------------- Je suis SearchOneChart Service -------");
	
		
		Chart chartFound = null;
		chartInfos.setChartTitle("progression");
		
			/*chartFound = chartRepository.findChartByTerms(entities.get(0).getName(), entities.get(1).getName(),
					entities.get(2).getName());*/
		
			
			chartFound = chartRepository.findChartByTerms(chartInfos.getChartTitle(),chartInfos.getComponentLabel(),
					chartInfos.getSerialNumber());
		

		if (chartFound == null) {
			System.out.println("-----------------Chart not found--------------------");
		} else {
			System.out.println("--------------------Chart found--------------------------");
		}
		return chartFound;
	}

	
}
