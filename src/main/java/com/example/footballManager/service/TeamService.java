package com.example.footballManager.service;

import java.util.List;

import com.example.footballManager.model.dto.TeamDto;

public interface TeamService {
	
	List<TeamDto> findAllTeams();
	TeamDto findTeamById(String teamId);
	List<String> addNewTeam(TeamDto teamDto);
	void updateTeamInfo(String teamId, String commission, String money);
	void deleteTeamById(String teamId);
	
}
