package com.example.footballManager.service;

import java.util.List;

import com.example.footballManager.model.dto.PlayerDto;


public interface PlayerService {
	
	List<String> addNewPlayer(PlayerDto playerDto);
	List<PlayerDto> findAllPlayers();
	PlayerDto findPlayerById(String playerId);
	List<PlayerDto> findPlayersByTeamId(String teamId);
	void updatePlayerInfo(String id, String lastName, String firstName, 
			String careerStartDate, String age);
	void deletePlayerById(String playerId);
	List<String> transferPlayer(String playerId, String newTeamId);
}
