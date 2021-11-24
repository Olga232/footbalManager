package com.example.footballManager.component.mapper.impl;

import org.springframework.stereotype.Component;

import com.example.footballManager.component.mapper.Mapper;
import com.example.footballManager.model.dto.TeamDto;
import com.example.footballManager.model.entity.Team;

@Component
public class TeamMapper implements Mapper<TeamDto, Team> {

	@Override
	public Team toEntity(TeamDto teamDto) {
		Team team = new Team();
		team.setName(teamDto.getName());
		team.setCity(teamDto.getCity());
		team.setCountry(teamDto.getCountry());
		team.setCommission(teamDto.getCommission());
		team.setMoney(teamDto.getMoney());
		return team;
	}

	@Override
	public TeamDto toDto(Team team) {
		return TeamDto.builder()
				.setId(team.getId())
				.setName(team.getName())
				.setCity(team.getCity())
				.setCountry(team.getCountry())
				.setCommission(team.getCommission())
				.setMoney(team.getMoney())
				.build();
	}

}
