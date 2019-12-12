package com.matrimony.cassini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.cassini.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByGenderNot(String gender);

	public User findByUserNameAndPassword(String userName, String password);

}
