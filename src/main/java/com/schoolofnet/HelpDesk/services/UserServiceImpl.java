package com.schoolofnet.HelpDesk.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.schoolofnet.HelpDesk.Repositories.RolesRepository;
import com.schoolofnet.HelpDesk.Repositories.UserRepository;
import com.schoolofnet.HelpDesk.model.Role;
import com.schoolofnet.HelpDesk.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repositorio;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RolesRepository roleRepositorio;
	
	public UserServiceImpl(UserRepository repositorio,BCryptPasswordEncoder passwordEncoder,RolesRepository roleRepositorio) {
		
		this.repositorio = repositorio;
		this.passwordEncoder = passwordEncoder;
		this.roleRepositorio= roleRepositorio;
	}

	@Override
	public List<User> findAll() {
	
		return this.repositorio.findAll();
	}

	@Override
	public User create(User user) {
		Role roleUser = this.roleRepositorio.findByName("USER");
		
     user.setPassword(this.passwordEncoder.encode(user.getPassword()));
     user.setRoles(new HashSet<Role>(Arrays.asList(roleUser)));
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
			userAlterar.setPassword(this.passwordEncoder.encode(user.getPassword()));
			userAlterar.setActive(user.getActive());
			
			Role userRole=this.roleRepositorio.findByName(user.getRoles().iterator().next().getName());
			userAlterar.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			
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
