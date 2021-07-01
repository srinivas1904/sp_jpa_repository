package com.sbjpa.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbjpa.demo.model.User;
import com.sbjpa.demo.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return service.getAllUsers();
	}
	@GetMapping("/{id}")
	public Optional<User> getUser(Long id) {
		return service.findUser(id);
	}
	
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
		return service.addUser(user);
	}
	
	@DeleteMapping("/")
	public void deleteUser(Long id) {
		service.deleteUser(id);
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User user){
		return service.addUser(user);
	}
	
	//---------
	@GetMapping("city/{city}")   //X- java.lang.IllegalStateException: Ambiguous handler methods mapped for '/user/Hyderabad'
	public User getUserByCity(String city){ //@PathVariable("city")
		return service.findUserByCity(city);
	}
	@GetMapping("/all/page/size")
	public ResponseEntity<Map<String,Object>> getAllUserByPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam (defaultValue = "3") int size){
		try {
			List<User> tutori=new ArrayList<>();
			Pageable paging=PageRequest.of(page, size);
			Page<User> pagetute;
			pagetute = service.findAll(paging);
			
			tutori = pagetute.getContent();
			
			Map<String,Object> response=new HashMap<>();
			response.put("users", tutori);
			response.put("currentpage", pagetute.getNumber());
			response.put("totalItems", pagetute.getTotalElements());
			response.put("totalPages", pagetute.getTotalPages());
			
			return new ResponseEntity(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/all/sort")
	public List<User> getAllUserBySort(){
		return service.getAllUsers(Sort.by("city"));
	}
	
	@GetMapping("/all/sort/desc")
	public List<User> getAllUsersBySortDesc(){
		return service.getAllUsers(Sort.by("city").descending());
	}
	@GetMapping("/all/id/greaterthan/{userId}")
	public List<User> getAllUsersByUserIdGreaterThanAndOrderByCity(@PathVariable Long userId){
		return service.getAllUsersByUserIdGreaterThan(userId);
	}
	@GetMapping("/all/state/{state}")
	public List<User> getAllUsersByState(@PathVariable String  state){
		return service.getAllUsersByState(state);
	}
}
