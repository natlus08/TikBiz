package com.tikbiz.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tikbiz.model.TMSTicket;
@Transactional
public interface TMSTicketRepository extends JpaRepository<TMSTicket, Long> {
	List<TMSTicket> findByCreatedDate(Date date);
}
