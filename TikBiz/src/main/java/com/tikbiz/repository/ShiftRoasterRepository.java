package com.tikbiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tikbiz.model.ShiftRoaster;
@Transactional
public interface ShiftRoasterRepository extends JpaRepository<ShiftRoaster, Long> {
}
