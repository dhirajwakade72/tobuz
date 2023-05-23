package com.tobuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobuz.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	

}
