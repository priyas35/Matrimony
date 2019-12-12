package com.matrimony.cassini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.cassini.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
