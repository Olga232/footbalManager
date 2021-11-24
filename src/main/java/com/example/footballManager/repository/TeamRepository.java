package com.example.footballManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.footballManager.model.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{

}
