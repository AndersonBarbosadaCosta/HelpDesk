package com.schoolofnet.HelpDesk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolofnet.HelpDesk.model.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction,Long>{

}
