package com.matrimony.cassini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, Integer> {
	
	List<UserInterest> findByFromUserAndStatus(User fromUser, String status);

}
