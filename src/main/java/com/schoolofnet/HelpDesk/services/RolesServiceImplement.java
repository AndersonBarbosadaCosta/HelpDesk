package com.schoolofnet.HelpDesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolofnet.HelpDesk.Repositories.RolesRepository;
import com.schoolofnet.HelpDesk.model.Role;

@Service
public class RolesServiceImplement implements RolesService {

	@Autowired
	private RolesRepository repository;

	public RolesServiceImplement(RolesRepository repository) {

		this.repository = repository;
	}

	@Override
	public Role create(Role role) {
		//transforma o texto para caixa alta antes de salvar
		role.setName(role.getName().toUpperCase());
		Role roleCreatead = this.repository.save(role);
		return roleCreatead;
	}

	@Override
	public List<Role> listAll() {

		return this.repository.findAll();
	}
	@Override
	public Boolean delete(Long id){
		Role role = findById(id);
		if(role != null){
			this.repository.delete(role);
			return true;
		}
		return false;
	}
	
	private Role findById(Long id){
		return this.repository.findOne(id);
	}

}
