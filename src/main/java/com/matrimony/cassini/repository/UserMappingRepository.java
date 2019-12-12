package com.matrimony.cassini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimony.cassini.entity.UserMapping;

@Repository
public interface UserMappingRepository extends JpaRepository<UserMapping, Integer> {

}
