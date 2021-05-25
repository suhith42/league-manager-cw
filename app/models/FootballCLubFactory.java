package models;

public class FootballCLubFactory {

	public FootballClub getInstance(String fcClub){
		switch (fcClub) {
			case "CLUB":
				return new FootballClub();
			case "SCHOOL":
				return new SchoolFootballClub();
			case "UNIVERSITY":
				return new UniversityFootballClub();
			default:
				System.out.println("Invalid syntax!");
				return null;
		}


	}
}
