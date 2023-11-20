package com.modis.acciaback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.modis.acciaback.model.InfoTypeValue;

/**
 * 
 * @author diane.dransart
 *
 */

@Repository
public interface InfoTypeValueRepository extends JpaRepository<InfoTypeValue, Integer> {

	@Query(nativeQuery = true, value = "SELECT itv.*, c.component_label, s.serial_number_label "
			+ "FROM (serial_numbers s INNER JOIN components c ON s.serial_number_id = c.serial_number_serial_number_id) "
			+ "INNER JOIN info_type_values itv ON c.component_id = itv.id_comp "
			+ "WHERE c.component_label = :componentLabel AND s.serial_number_label = :snLabel")
	public List<InfoTypeValue> componentInformations(String componentLabel, String snLabel);
}
