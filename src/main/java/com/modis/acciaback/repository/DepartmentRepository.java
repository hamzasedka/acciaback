package com.modis.acciaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modis.acciaback.model.Department;

/**
 * 
 * @author diane.dransart
 *
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
