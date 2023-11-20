package com.modis.acciaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modis.acciaback.model.CategoryDoc;

/**
 * 
 * @author diane.dransart
 *
 */

@Repository
public interface CategoryDocRepository extends JpaRepository<CategoryDoc, Integer>{

}
