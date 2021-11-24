package com.example.footballManager.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.footballManager.component.mapper.Mapper;
import com.example.footballManager.model.dto.PlayerDto;
import com.example.footballManager.model.dto.TeamDto;
import com.example.footballManager.model.entity.Player;
import com.example.footballManager.model.entity.Team;
import com.example.footballManager.repository.PlayerRepository;
import com.example.footballManager.service.PlayerService;
import com.example.footballManager.service.TeamService;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	private static final int TRANSFER_CONST = 100000;
	private static final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);
	
	private final PlayerRepository playerRepository;
	private final Mapper<PlayerDto, Player> playerMapper;
	private final TeamService teamService;
	private final Mapper<TeamDto, Team> teamMapper;
	
	@Autowired
	public PlayerServiceImpl(PlayerRepository playerRepository, Mapper<PlayerDto, Player> playerMapper,
			TeamService teamService, Mapper<TeamDto, Team> teamMapper) {
		this.playerRepository = playerRepository;
		this.playerMapper = playerMapper;
		this.teamService = teamService;
		this.teamMapper = teamMapper;
	}
	
	@Override
	@Transactional
	public List<String> addNewPlayer(PlayerDto playerDto) {
		List<String> errorList = new ArrayList<>();
		boolean isValid = checkPlayerParams(playerDto, errorList);
		if (isValid) {
			log.info("Starts to add a player (id " + playerDto.getId() + ") to db.");
			Player playerToAdd = playerMapper.toEntity(playerDto);
			playerRepository.save(playerToAdd);
			log.info("Player (id " + playerDto.getId() + ") added to db successfully.");
		}
		return errorList;
	}

	@Override
	public List<PlayerDto> findAllPlayers() {
		log.info("Starts to find all players from db.");
		List<Player> playerList = playerRepository.findAll();
		log.info("All players from db are in a list.");
		return playerList.stream().map(e -> playerMapper.toDto(e)).collect(Collectors.toList());
	}

	@Override
	public PlayerDto findPlayerById(String playerId) {
		log.info("Starts to find a player (id " + playerId + ").");
		Optional<Player> playerFromDb = playerRepository.findById(Integer.parseInt(playerId));
		if (playerFromDb.isEmpty()) {
			log.info("Not find a player (id " + playerId + ")");
		}
		return playerMapper.toDto(playerFromDb.get());
	}

	@Override
	public List<PlayerDto> findPlayersByTeamId(String teamId) {
		log.info("Starts to find players from team (id " + teamId + ").");
		List<Player> playerList = playerRepository.findAllByTeamId(Integer.parseInt(teamId));
		return playerList.stream().map(e -> playerMapper.toDto(e)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void updatePlayerInfo(String id, String lastName, String firstName, 
			String careerStartDate, String age) {
		log.info("Starts to update a player (id " + id + ").");
		Optional<Player> playerFromDb = playerRepository.findById(Integer.parseInt(id));
		if (playerFromDb.isEmpty()) {
			log.info("Not find a player (id " + id + ") to update.");
		}
		Player playerToUpdate = playerFromDb.get();
		playerToUpdate.setLastName(lastName);
		playerToUpdate.setFirstName(firstName);
		playerToUpdate.setCareerStartDate(LocalDate.parse(careerStartDate));
		playerToUpdate.setAge(Integer.parseInt(age));
		playerRepository.save(playerToUpdate);
		log.info("Player (id " + id + ") updated successfully.");
	}

	@Override
	@Transactional
	public void deletePlayerById(String playerId) {
		log.info("Starts to delete a player (id " + playerId + ").");
		playerRepository.deleteById(Integer.parseInt(playerId));
		log.info("Player (id " + playerId + ") deleted successfully.");
	}

	@Override
	@Transactional
	public List<String> transferPlayer(String playerId, String newTeamId) {
		log.info("Starts to transfer a player (id " + playerId + ") to team (id " + newTeamId + ").");
		List<String> errorList = new ArrayList<>();
		Optional<Player> playerFromDb = playerRepository.findById(Integer.parseInt(playerId));
		if (playerFromDb.isEmpty()) {
			log.info("Not find a player (id " + playerId + ") to transfer");
		}
		Player playerToTransfer = playerFromDb.get();
		TeamDto newTeam = teamService.findTeamById(newTeamId);
		if (playerToTransfer.getTeam().getId() == Integer.parseInt(newTeamId)) {
			log.info("Can not transfer a player (id " + playerId + ") to the same team (id " + newTeamId + ").");
			errorList.add("Choose another team to transfer");
			return errorList;
		}
		double transferCost = calculateTransferCost(playerToTransfer);
		boolean transferIsValid = checkTransferParams(newTeam, transferCost, errorList);
		if (transferIsValid) {
			doTransfer(playerToTransfer, newTeam, transferCost);
			log.info("Player (id " + playerId + ") transferred successfully.");
		}
		return errorList;
	}
	
	private boolean checkPlayerParams(PlayerDto playerDto, List<String> errorList) {
		if (playerDto.getLastName() == null || playerDto.getLastName().isBlank()) {
			errorList.add("empty last name");
		} else if (playerDto.getLastName().length() > 64) {
			errorList.add("Max length: 64 symbols");
		}
		
		if (playerDto.getFirstName() == null || playerDto.getFirstName().isBlank()) {
			errorList.add("empty first name");
		} else if (playerDto.getFirstName().length() > 64) {
			errorList.add("Max length: 64 symbols");
		}
		
		if (playerDto.getCareerStartDate() == null || playerDto.getCareerStartDate().toString().isBlank()) {
			errorList.add("empty date");
		} else if (!GenericValidator.isDate(playerDto.getCareerStartDate(), "yyyy-MM-dd", true)) {
			errorList.add("wrong date format");
		} else if (LocalDate.parse(playerDto.getCareerStartDate()).isAfter(LocalDate.now())) {
			errorList.add("wrong date");
		}
		
		if (playerDto.getAge() < 18 || playerDto.getAge() > 80) {
			errorList.add("Min age: 18; max age: 60");
		}
		
		if (errorList.size() == 0) {
			log.info("Data is valid");
			return true;
		}else {
			log.info("Data is not valid");
		}
		return false;
	}
	
	private double calculateTransferCost(Player playerToTransfer) {
		long monthsBetween = ChronoUnit.MONTHS.between(
			     YearMonth.from(playerToTransfer.getCareerStartDate()), 
			     YearMonth.from(LocalDate.now())
			);
		long rawTransferCost = monthsBetween * TRANSFER_CONST / (playerToTransfer.getAge());
		return rawTransferCost + (rawTransferCost/100.0*(playerToTransfer.getTeam().getCommission()));
	}
	
	private boolean checkTransferParams(TeamDto newTeam, double transferCost, List<String> errorList) {
		if (newTeam.getMoney() < transferCost) {
			errorList.add("Too expensive for " + newTeam.getName());
			return false;
		}
		return true;
	}
	
	private void doTransfer(Player playerToTransfer, TeamDto newTeam, double transferCost) {
		Team currentTeam = playerToTransfer.getTeam();
		teamService.updateTeamInfo(currentTeam.getId().toString(), ((Integer)currentTeam.getCommission()).toString(),
				((Double)(currentTeam.getMoney()+transferCost)).toString());
		Team teamBuyer = teamMapper.toEntity(newTeam);
		teamBuyer.setId(newTeam.getId());
		playerToTransfer.setTeam(teamBuyer);
		playerRepository.save(playerToTransfer);
		teamService.updateTeamInfo(newTeam.getId().toString(), ((Integer)newTeam.getCommission()).toString(),
				((Double)(newTeam.getMoney()-transferCost)).toString());
	}

}
