package com.example.footballManager.model.dto;

public class PlayerDto {
	
	private Integer id;
	private String lastName;
	private String firstName;
    private String careerStartDate;
	private int age;
	private Integer teamId;
	private String teamName;
	
	public PlayerDto(Integer id, String lastName, String firstName, 
			String careerStartDate, int age, int teamId, String teamName) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.careerStartDate = careerStartDate;
		this.age = age;
		this.teamId = teamId;
		this.teamName = teamName;
	}
	
	public static PlayerDtoBuilder builder() {
		return new PlayerDtoBuilder();
	}
	
	public static class PlayerDtoBuilder {
		private Integer id;
		private String lastName;
		private String firstName;
	    private String careerStartDate;
		private int age;
		private Integer teamId;
		private String teamName;
		
		public PlayerDtoBuilder setId(Integer id) {
			this.id = id;
			return this;
		}
		public PlayerDtoBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		public PlayerDtoBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		public PlayerDtoBuilder setCareerStartDate(String careerStartDate) {
			this.careerStartDate = careerStartDate;
			return this;
		}
		public PlayerDtoBuilder setAge(int age) {
			this.age = age;
			return this;
		}
		public PlayerDtoBuilder setTeamId(Integer teamId) {
			this.teamId = teamId;
			return this;
		}
		public PlayerDtoBuilder setTeamName(String teamName) {
			this.teamName = teamName;
			return this;
		}
		
		public PlayerDto build() {
			return new PlayerDto(id, lastName, firstName, careerStartDate, age, teamId, teamName);
		}
	}

	public Integer getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getCareerStartDate() {
		return careerStartDate;
	}

	public int getAge() {
		return age;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}
	

}
