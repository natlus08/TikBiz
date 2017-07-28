package com.tikbiz.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tikbiz.model.ShiftRoaster;
@Transactional
public interface ShiftRoasterRepository extends JpaRepository<ShiftRoaster, Long> {
	
	List<ShiftRoaster> findByDateGreaterThanEqual(Date start, Pageable pageable);
	
}
