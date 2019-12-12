package com.matrimony.cassini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserIntrest;

@Repository
public interface UserIntrestRepository extends JpaRepository<UserIntrest, Integer> {
	
	List<UserIntrest> findByFromUserAndStatus(User fromUser, String status);

}
