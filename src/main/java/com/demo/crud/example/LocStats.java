package com.demo.crud.example;

public class LocStats {

	private String state;

	private String country;

	private int totalCases;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}

	@Override
	public String toString() {
		return "LocStats [state=" + state + ", country=" + country + ", totalCases=" + totalCases + "]";
	}

}
