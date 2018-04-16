package com.lxx.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lxx.model.User;
import com.lxx.repository.UserRepository;
import com.lxx.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUserList() {

		return userRepository.findAll();
	}

	@Override
	public User findUserById(long id) {

		return userRepository.findById(id);
	}

	@Override
	public void save(User user) {

		userRepository.save(user);
	}

	@Override
	public void edit(User user) {

		userRepository.save(user);
	}

	@Override
	public void delete(long id) {

		userRepository.delete(id);
	}

	@Override
	public List<User> check(String userName, String password) {

		return userRepository.findByUserNameAndPassword(userName, password);
	}

	@Override
	public User register(String userName) {

		return userRepository.findByUserName(userName);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		
		return userRepository.findAll(pageable);
	}
	
	@Override
	public User findByUserName(String userName) {
		
		
		return userRepository.findByUserName(userName);
	}

//	@Override
//	public Page<User> findByKeyName(String keyName, Pageable pageable) {
//		
//		return userRepository.findByKeyName(keyName, pageable);
//	}

	@Override
	public Page<User> findAllByUserNameLike(String uString, Pageable pageable) {
		
		return userRepository.findAllByUserNameLike("%"+uString+"%", pageable);
	}

}
