package com.schoolofnet.HelpDesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolofnet.HelpDesk.Repositories.UserRepository;
import com.schoolofnet.HelpDesk.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repositorio;
	
	
	public UserServiceImpl(UserRepository repositorio) {
		
		this.repositorio = repositorio;
	}

	@Override
	public List<User> findAll() {
	
		return this.repositorio.findAll();
	}

	@Override
	public User create(User user) {
 
     return this.repositorio.save(user);

	}

	@Override
	public Boolean delete(Long id) {
		User usuario = findById(id);
		if(usuario != null){
			this.repositorio.delete(id);
			return true;
		}
		return false;
	}
	
	private User findById(Long id){
		 return this.repositorio.findOne(id);
	
	}

	@Override
	public Boolean update(Long id, User user){
		User userAlterar = findById(id);
		if(userAlterar != null){
			userAlterar.setId(user.getId());
			userAlterar.setName(user.getName());
			userAlterar.setLastName(user.getLastName());
			userAlterar.setEmail(user.getEmail());
			userAlterar.setPassword(user.getPassword());
			userAlterar.setActive(user.getActive());
			
			this.repositorio.save(userAlterar);
			return true;
		}
		
		return false;
	}

	@Override
	public User show(Long id) {
		return findById(id);
	}
}
