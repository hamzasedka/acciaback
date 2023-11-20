package com.modis.acciaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.modis.acciaback.model.Doc;

/**
 * 
 * @author diane.dransart
 *
 */

@Repository
public interface DocRepository extends JpaRepository<Doc, Integer> {
	
	  @Query(nativeQuery = true, value = "SELECT d.* "
			  + "FROM (serial_numbers s INNER JOIN components c ON s.serial_number_id = c.serial_number_serial_number_id) "
			  + "INNER JOIN docs d ON c.component_id = d.id_comp " 
			  + "WHERE d.doc_type = :docLabel AND c.component_label = :componentLabel AND s.serial_number_label = :snLabel")
	public Doc findDocByTerms(String docLabel, String componentLabel, String snLabel);	 
}
