package com.schoolofnet.HelpDesk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolofnet.HelpDesk.model.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {

}
