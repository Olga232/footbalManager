package com.example.footballManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.footballManager.model.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
	
	List<Player> findAllByTeamId(int teamId);

}
