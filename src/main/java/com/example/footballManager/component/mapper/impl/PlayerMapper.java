package com.example.footballManager.component.mapper.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.footballManager.component.mapper.Mapper;
import com.example.footballManager.model.dto.PlayerDto;
import com.example.footballManager.model.entity.Player;
import com.example.footballManager.model.entity.Team;

@Component
public class PlayerMapper implements Mapper<PlayerDto, Player>{

	@Override
	public Player toEntity(PlayerDto playerDto) {
		Player player = new Player();
		player.setLastName(playerDto.getLastName());
		player.setFirstName(playerDto.getFirstName());
		player.setCareerStartDate(LocalDate.parse(playerDto.getCareerStartDate()));
		player.setAge(playerDto.getAge());
		Team team = new Team();
		team.setId(playerDto.getTeamId());
		player.setTeam(team); 											// check
		return player;
	}

	@Override
	public PlayerDto toDto(Player player) {
		return PlayerDto.builder()
				.setId(player.getId())
				.setLastName(player.getLastName())
				.setFirstName(player.getFirstName())
				.setCareerStartDate(player.getCareerStartDate().toString())
				.setAge(player.getAge())
				.setTeamId((player.getTeam() == null ? 0 : player.getTeam().getId()))
				.setTeamName((player.getTeam() == null ? "" : player.getTeam().getName()))
				.build();
	}

}
