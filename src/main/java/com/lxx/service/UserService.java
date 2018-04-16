package com.lxx.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lxx.model.User;

@Service
public interface UserService {

	public List<User> getUserList();

	public User findUserById(long id);

	public void save(User user);

	public void edit(User user);

	public void delete(long id);

	public List<User> check(String userName, String password);

	public User register(String userName);

	public Page<User> findAll(Pageable pageable);

	User findByUserName(String userName);

	Page<User> findAllByUserNameLike(String uString, Pageable pageable);

	//Page<User> findByKeyName(String keyName, Pageable pageable);
}
