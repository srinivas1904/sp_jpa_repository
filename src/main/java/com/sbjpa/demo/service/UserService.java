package com.sbjpa.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sbjpa.demo.model.User;
import com.sbjpa.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
	
	public List<User> getAllUsers(){
		return (List<User>) repo.findAll();
	}
	
	public User addUser(User u) {
		return repo.save(u);
	}
	
	public Optional<User> findUser(Long id){
		return repo.findById(id);
	}
	
	public User updateUser(User u) {
		return repo.save(u);
	}
	
	public void deleteUser(Long id) {
		repo.deleteById(id);
	}
	//---
	public User findUserByCity(String city) {
		return repo.findByCity(city);
	}
	public Page<User> findAll(Pageable paging) {
		return repo.findAll(paging);
	}
	public List<User> getAllUsers(Sort sorting){
		return repo.findAll(sorting);
	}
	public List<User> getAllUsersByUserIdGreaterThan(Long id){
//		return repo.findByUserIdGreaterThanOrderByCity(id);
//		return repo.findByUserIdGreaterThan(id);
		return repo.findByUserIdGreaterThanOrderByNameDesc(id);
	}
	public List<User> getAllUsersByState(String state){
		return repo.findByStateContaining(state);
	}
	
//	public Page<User> findByCityContaining(String city, Pageable paging){
//		return repo.findByStateContaining(city, paging);
//	}
	

}
