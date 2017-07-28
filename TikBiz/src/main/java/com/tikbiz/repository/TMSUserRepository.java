package com.tikbiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tikbiz.model.TMSUser;
@Transactional
public interface TMSUserRepository extends JpaRepository<TMSUser, Long> {
	TMSUser findByUserNameAndPassword(String userName, String password);

	TMSUser findByUserName(String userName);
	
	List<TMSUser> findByRole(String role);
}
