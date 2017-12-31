package com.schoolofnet.HelpDesk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolofnet.HelpDesk.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
