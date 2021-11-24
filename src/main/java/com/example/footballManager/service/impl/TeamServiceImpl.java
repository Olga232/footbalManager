package com.example.footballManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.footballManager.component.mapper.Mapper;
import com.example.footballManager.model.dto.TeamDto;
import com.example.footballManager.model.entity.Team;
import com.example.footballManager.repository.TeamRepository;
import com.example.footballManager.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	
	private static final Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);
	private final TeamRepository teamRepository;
	private final Mapper<TeamDto, Team> teamMapper;
	
	@Autowired
	public TeamServiceImpl(TeamRepository teamRepository, Mapper<TeamDto, Team> teamMapper) {
		this.teamRepository = teamRepository;
		this.teamMapper = teamMapper;
	}

	@Override
	public List<TeamDto> findAllTeams() {
		log.info("Starts to find all teams from db.");
		List<Team> teamList = teamRepository.findAll();
		log.info("All teams from db are in a list.");
		return teamList.stream().map(e -> teamMapper.toDto(e)).collect(Collectors.toList());
	}

	@Override
	public TeamDto findTeamById(String teamId) {
		log.info("Starts to find a team (id " + teamId + ").");
		Optional<Team> teamFromDb = teamRepository.findById(Integer.parseInt(teamId));
		if (teamFromDb.isEmpty()) {
			log.info("Not find a team (id " + teamId + ")");
		}
		return teamMapper.toDto(teamFromDb.get());
	}

	@Override
	@Transactional
	public List<String> addNewTeam(TeamDto teamDto) {
		List<String> errorList = new ArrayList<>();
		boolean isValid = checkTeamParams(teamDto, errorList);
		if (isValid) {
			log.info("Starts to add a team (id " + teamDto.getId() + ") to db.");
			Team teamToAdd = teamMapper.toEntity(teamDto);
			teamRepository.save(teamToAdd);
			log.info("Team (id " + teamDto.getId() + ") added to db successfully.");
		}
		return errorList;
	}
	
	@Override
	@Transactional
	public void updateTeamInfo(String teamId, String commission, String money) {
		log.info("Starts to update a team (id " + teamId + ").");
		Optional<Team> teamFromDb = teamRepository.findById(Integer.parseInt(teamId));
		if (teamFromDb.isEmpty()) {
			log.info("Not find a team (id " + teamId + ") to update.");
		}
		Team teamToUpdate = teamFromDb.get();
		teamToUpdate.setCommission(Integer.parseInt(commission));
		teamToUpdate.setMoney(Double.parseDouble(money));
		teamRepository.save(teamToUpdate);
		log.info("Team (id " + teamId + ") updated successfully.");
	}
	
	@Override
	@Transactional
	public void deleteTeamById(String teamId) {
		log.info("Starts to delete a team (id " + teamId + ").");
		teamRepository.deleteById(Integer.parseInt(teamId));
		log.info("Team (id " + teamId + ") deleted successfully.");
	}
	
	private boolean checkTeamParams(TeamDto teamDto, List<String> errorList) {
		if (teamDto.getName() == null || teamDto.getName().isBlank()) {
			errorList.add("empty name");
		} else if (teamDto.getName().length() > 64) {
			errorList.add("Max length: 64");
		} 
		
		if (teamDto.getCity() == null || teamDto.getCity().isBlank()) {
			errorList.add("empty city");
		} else if (teamDto.getCity().length() > 64) {
			errorList.add("Max length: 64");
		} else if (!Pattern.matches("[a-zA-Z]+", teamDto.getCity())) {
			errorList.add("wrong character in field City");
		}
		
		if (teamDto.getCountry() == null || teamDto.getCountry().isBlank()) {
			errorList.add("empty country");
		} else if (teamDto.getCountry().length() > 64) {
			errorList.add("Max length: 64");
		} else if (!Pattern.matches("[a-zA-Z]+", teamDto.getCountry())) {
			errorList.add("wrong character in field Country");
		}
		
		if (teamDto.getCommission() < 0 || teamDto.getCommission() > 10) {
			errorList.add("Min commission: 0; max commission: 10");
		}
		
		if (teamDto.getMoney() < 0) {
			errorList.add("money < 0");
		}
		
		if (errorList.size() == 0) {
			log.info("Data is valid");
			return true;
		} else {
			log.info("Data is not valid");
		}
		return false;
	}


}
