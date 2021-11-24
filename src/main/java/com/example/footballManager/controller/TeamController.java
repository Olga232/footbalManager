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
@RequestMapping(value = "/team")
public class TeamController {
	
	private static final Logger log = LoggerFactory.getLogger(TeamController.class);
	private final TeamService teamService;
	private final PlayerService playerService;
	
	@Autowired
	public TeamController(TeamService teamService, PlayerService playerService) {
		this.teamService = teamService;
		this.playerService = playerService;
	}
	
	@PostMapping
	public String addNewTeam(TeamDto teamDto, ModelMap modelMap) {
		log.info("Request to add new team. " + teamDto.getName());
		String result = "";
		List<String> errorList = teamService.addNewTeam(teamDto);
		if (errorList.size() == 0) {
			result = "The team " + teamDto.getName() + " has been added successfully";
		}
		modelMap.addAttribute("result", result);
		modelMap.addAttribute("errorList", errorList);
		return "addNewTeamForm";
	}
	
	@GetMapping
	public String getAllTeams(ModelMap modelMap) {
		log.info("Request to find all teams.");
		List<TeamDto> listOfTeams = teamService.findAllTeams();
		modelMap.addAttribute("listOfTeams", listOfTeams);
		return "teams";
	}

	@GetMapping(params = {"id"})
	public String getTeamById(@RequestParam (name = "id") String teamId, ModelMap modelMap) {
		log.info("Request to find a team (id): " + teamId);
		TeamDto teamDto = teamService.findTeamById(teamId);
		List<PlayerDto> listOfPlayers = playerService.findPlayersByTeamId(teamId);
		modelMap.addAttribute("team", teamDto);
		modelMap.addAttribute("listOfPlayers", listOfPlayers);
		return "team";
	}
	
	@GetMapping(params = {"add"})
	public String addNewTeamForm(@RequestParam (name = "add") String add) {
		log.info("Request to team add form.");
		return "addNewTeamForm";
	}
	
	@GetMapping(params = {"update", "id"})
	public String updateTeamForm(@RequestParam (name = "update") String update,
			@RequestParam (name = "id") String teamId, ModelMap modelMap) {
		log.info("Request to team update form.");
		TeamDto teamDto = teamService.findTeamById(teamId);
		modelMap.addAttribute("team", teamDto);
		return "updateTeamForm";
	}
	
	@PutMapping(params = {"id", "com", "mon"})
	@ResponseBody
	public void updateTeam(@RequestParam (name = "id") String teamId,
			@RequestParam (name = "com") String commission, 
			@RequestParam (name = "mon") String money) {
		log.info("Request to update a team (id): " + teamId);
		teamService.updateTeamInfo(teamId, commission, money);
	}
	
	@DeleteMapping(params = {"id"})
	@ResponseBody
	public void deleteTeamById(@RequestParam (name = "id") String teamId) {
		log.info("Request to delete a team (id): " + teamId);
		teamService.deleteTeamById(teamId);
	}


}
