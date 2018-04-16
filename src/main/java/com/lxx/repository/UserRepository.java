package com.lxx.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lxx.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	// 根据ID查找用户
	User findById(long id);

	// 根据ID删除用户
	Long deleteById(Long id);

	// 根据用户名及密码查找
	List<User> findByUserNameAndPassword(String userName, String password);

	// 根据用户名查找
	User findByUserName(String userName);

	// 分页查询
	Page<User> findAll(Pageable pageable);

	// 模糊查询
	Page<User> findAllByUserNameLike(String uString, Pageable pageable);

	// @Query(value = "SELECT * FROM user WHERE user_name LIKE
	// CONCAT('%',:keyName,'%')",nativeQuery=true)
	// Page<User> findByKeyName(@Param("keyName") String keyName, Pageable pageable);

}