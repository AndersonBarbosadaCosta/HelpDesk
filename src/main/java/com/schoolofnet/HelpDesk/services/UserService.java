package com.schoolofnet.HelpDesk.services;

import java.util.List;

import com.schoolofnet.HelpDesk.model.User;

public interface UserService {
	
	public List<User>findAll();
	
	public User create(User user);
	
	public Boolean delete(Long id);
	
	public Boolean update(Long id,User user);
	
	public User show(Long id);
	

}
