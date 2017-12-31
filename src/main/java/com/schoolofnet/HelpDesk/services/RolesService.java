package com.schoolofnet.HelpDesk.services;

import java.util.List;

import com.schoolofnet.HelpDesk.model.Role;

public interface RolesService {

	public Role create(Role name);

	public List<Role> listAll();

	public Boolean delete(Long id);
}
