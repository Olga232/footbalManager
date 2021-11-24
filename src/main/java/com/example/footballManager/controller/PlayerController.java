package com.example.footballManager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.footballManager.model.dto.PlayerDto;
import com.example.footballManager.model.dto.TeamDto;
import com.example.footballManager.service.PlayerService;
import com.example.footballManager.service.TeamService;

@Controller
@RequestMapping(value = "/player")
public class PlayerController {
	
	private static final Logger log = LoggerFactory.getLogger(PlayerController.class);
	private final PlayerService playerService;
	private final TeamService teamService;
	
	@Autowired
	public PlayerController(PlayerService playerService, TeamService teamService) {
		this.playerService = playerService;
		this.teamService = teamService;
	}
	
	@PostMapping
	public String addNewPlayer(PlayerDto playerDto, ModelMap modelMap) {
		log.info("Request to add new player. " + playerDto.getLastName());
		String result = "";
		List<String> errorList = playerService.addNewPlayer(playerDto);
		if (errorList.size() == 0) {
			result = "The player " + playerDto.getLastName() + " " + playerDto.getFirstName() + " has been added successfully";
			modelMap.addAttribute("result", result);
			return "addNewPlayerForm";
		}
		List<TeamDto> listOfTeams = teamService.findAllTeams();
		modelMap.addAttribute("listOfTeams", listOfTeams);
		modelMap.addAttribute("result", result);
		modelMap.addAttribute("errorList", errorList);
		return "addNewPlayerForm";
	}
	
	@PostMapping(params = {"playerId", "newTeamId"})
	public String transferPlayer(@RequestParam(name = "playerId") String playerId,
			@RequestParam(name = "newTeamId") String newTeamId, ModelMap modelMap) {
		log.info("Request to transfer player (id " + playerId + ") to team (id " + newTeamId + ")");
		String result = "";
		List<String> errorList = playerService.transferPlayer(playerId, newTeamId);
		if (errorList.size() == 0) {
			result = "Transfer completed successfully";
			modelMap.addAttribute("result", result);
			return "transferPlayerForm";
		}
		List<TeamDto> listOfTeams = teamService.findAllTeams();
		PlayerDto playerDto = playerService.findPlayerById(playerId);
		modelMap.addAttribute("player", playerDto);
		modelMap.addAttribute("listOfTeams", listOfTeams);
		modelMap.addAttribute("result", result);
		modelMap.addAttribute("errorList", errorList);
		return "transferPlayerForm";
	}

	@GetMapping
	public String getAllPlayers(ModelMap modelMap) {
		log.info("Request to find all players.");
		List<PlayerDto> listOfPlayers = playerService.findAllPlayers();
		modelMap.addAttribute("listOfPlayers", listOfPlayers);
		return "players";
	}
	
	@GetMapping(params = {"id"})
	public String getPlayerById(@RequestParam (name = "id") String playerId, ModelMap modelMap) {
		log.info("Request to find a player (id): " + playerId);
		PlayerDto playerDto = playerService.findPlayerById(playerId);
		modelMap.addAttribute("player", playerDto);
		return "player";
	}
	
	@GetMapping(params = {"add"})
	public String addNewPlayerForm(@RequestParam (name = "add") String add, ModelMap modelMap) {
		log.info("Request to player add form.");
		List<TeamDto> listOfTeams = teamService.findAllTeams();
		modelMap.addAttribute("listOfTeams", listOfTeams);
		return "addNewPlayerForm";
	}
	
	@GetMapping(params = {"update", "id"})
	public String updatePlayerForm(@RequestParam (name = "update") String update,
			@RequestParam (name = "id") String playerId, ModelMap modelMap) {
		log.info("Request to player update form.");
		PlayerDto playerDto = playerService.findPlayerById(playerId);
		modelMap.addAttribute("player", playerDto);
		return "updatePlayerForm";
	}
	
	@GetMapping(params = {"transfer", "id"})
	public String transferPlayerForm(@RequestParam (name = "transfer") String transfer,
			@RequestParam (name = "id") String playerId, ModelMap modelMap) {
		log.info("Request to player transfer form.");
		PlayerDto playerDto = playerService.findPlayerById(playerId);
		modelMap.addAttribute("player", playerDto);
		List<TeamDto> listOfTeams = teamService.findAllTeams();
		modelMap.addAttribute("listOfTeams", listOfTeams);
		return "transferPlayerForm";
	}
	
	@PutMapping(params = {"id", "lastName", "firstName", "careerStartDate", "age"})
	@ResponseBody
	public void updatePlayer(@RequestParam (name = "id") String playerId,
			@RequestParam (name = "lastName") String lastName, 
			@RequestParam (name = "firstName") String firstName,
			@RequestParam (name = "careerStartDate") String careerStartDate,
			@RequestParam (name = "age") String age) {
		log.info("Request to update player info (id): " + playerId);
		playerService.updatePlayerInfo(playerId, lastName, firstName, careerStartDate, age);
	}
	
	@DeleteMapping(params = {"id"})
	@ResponseBody
	public void deletePlayerById(@RequestParam (name = "id") String playerId) {
		log.info("Request to delete a player (id): " + playerId);
		playerService.deletePlayerById(playerId);
	}

}
