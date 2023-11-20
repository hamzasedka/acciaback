package com.modis.acciaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modis.acciaback.model.SerialNumber;

/**
 * 
 * @author diane.dransart
 *
 */

@Repository
public interface SerialNumberRepository extends JpaRepository<SerialNumber, Integer>{

}
