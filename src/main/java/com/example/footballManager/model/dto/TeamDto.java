package com.example.footballManager.model.dto;

public class TeamDto {
	
	private Integer id;
	private String name;
	private String city;
	private String country;
	private int commission;
	private double money;
	
	public TeamDto(Integer id, String name, String city, 
			String country, int commission, double money) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.country = country;
		this.commission = commission;
		this.money = money;
	}
	
	public static TeamDtoBuilder builder() {
		return new TeamDtoBuilder();
	}
	
	public static class TeamDtoBuilder {
		private Integer id;
		private String name;
		private String city;
		private String country;
		private int commission;
		private double money;
		
		public TeamDtoBuilder setId(Integer id) {
			this.id = id;
			return this;
		}
		public TeamDtoBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public TeamDtoBuilder setCity(String city) {
			this.city = city;
			return this;
		}
		public TeamDtoBuilder setCountry(String country) {
			this.country = country;
			return this;
		}
		public TeamDtoBuilder setCommission(int commission) {
			this.commission = commission;
			return this;
		}
		public TeamDtoBuilder setMoney(double money) {
			this.money = money;
			return this;
		}
		
		public TeamDto build() {
			return new TeamDto(id, name, city, country, commission, money);
		}
		
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public int getCommission() {
		return commission;
	}

	public double getMoney() {
		return money;
	}
	

}
