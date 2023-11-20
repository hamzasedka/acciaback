package com.modis.acciaback.service;

import com.modis.acciaback.model.Chart;
import com.modis.acciaback.payloads.ChartRequest;

/**
 * 
 * @author diane.dransart
 *
 */

public interface ChartService {

	public Chart searchChart(ChartRequest chartInfos) ;

	}
