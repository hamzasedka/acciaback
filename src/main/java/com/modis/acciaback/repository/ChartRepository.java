package com.modis.acciaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.modis.acciaback.model.Chart;

/**
 * 
 * @author diane.dransart
 *
 */
@Repository
public interface ChartRepository extends JpaRepository<Chart, Integer>{

	@Query(nativeQuery = true, value = "SELECT ch.*, c.component_label, s.serial_number_label "
			  + "FROM (serial_numbers s INNER JOIN components c ON s.serial_number_id = c.serial_number_serial_number_id) "
			  + "INNER JOIN charts ch ON c.component_id = ch.id_comp " 
			  + "WHERE ch.chart_title = :chartTitle AND c.component_label = :componentLabel AND s.serial_number_label = :snLabel")
	public Chart findChartByTerms(String chartTitle, String componentLabel, String snLabel);
}