package com.springboot.librarysystem.repository;

import com.springboot.librarysystem.constants.RoleNames;
import com.springboot.librarysystem.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	boolean existsRoleByRole(RoleNames role);

}
