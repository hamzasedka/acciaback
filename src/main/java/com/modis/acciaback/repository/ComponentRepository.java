package com.modis.acciaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modis.acciaback.model.Component;

/**
 * 
 * @author diane.dransart
 *
 */

@Repository
public interface ComponentRepository extends JpaRepository<Component, Integer> {

}
