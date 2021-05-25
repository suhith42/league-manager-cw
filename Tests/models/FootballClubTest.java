package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class FootballClubTest {

	FootballClub testClub = new FootballClub("Spurs", "7882 Amerige Dr.Billerica, MA 01821", 6, 2, 1, 27, 9, "Dania Patton", "Aryan Monroe");

	@Test
	public void getHeadCoach() {
		assertEquals("Dania Patton",testClub.getHeadCoach());
	}

	@Test
	public void getCaptain() {
		assertEquals("Aryan Monroe",testClub.getCaptain());
	}

	@Test
	public void getWins() {
		assertEquals(6,testClub.getWins());
	}

	@Test
	public void getDraws() {
		assertEquals(2,testClub.getDraws());
	}

	@Test
	public void getDefeats() {
		assertEquals(1,testClub.getDefeats());
	}

	@Test
	public void getScoredGoals() {
		assertEquals(27,testClub.getScoredGoals());
	}

	@Test
	public void getConcededGoals() {
		assertEquals(9,testClub.getConcededGoals());
	}

	@Test
	public void getGoalDifference() {
		assertEquals(18,testClub.getGoalDifference());
	}

	@Test
	public void getPoints() {
		assertEquals(20,testClub.getPoints());
	}

	@Test
	public void getMatches() {
		assertEquals(9,testClub.getMatches());
	}

	@Test
	public void setHeadCoach() {
		testClub.setHeadCoach("Biff Wellington");
		assertEquals("Biff Wellington", testClub.getHeadCoach());
	}

	@Test
	public void setCaptain() {
		testClub.setCaptain("Earl E. Bird");
		assertEquals("Earl E. Bird",testClub.getCaptain());
	}

	@Test
	public void setWins() {
		testClub.setWins(4);
		assertEquals(4,testClub.getWins());
	}

	@Test
	public void setDraws() {
		testClub.setDraws(3);
		assertEquals(3,testClub.getDraws());
	}

	@Test
	public void setDefeats() {
		testClub.setDefeats(5);
		assertEquals(5,testClub.getDefeats());
	}

	@Test
	public void setScoredGoals() {
		testClub.setScoredGoals(8);
		assertEquals(8,testClub.getScoredGoals());
	}

	@Test
	public void setConcededGoals() {
		testClub.setConcededGoals(3);
		assertEquals(3,testClub.getConcededGoals());
	}

	@Test
	public void setGoalDifference() {
		testClub.setGoalDifference();
		assertEquals(18,testClub.getGoalDifference());
	}

	@Test
	public void setPoints() {
		testClub.setPoints();
		assertEquals(20,testClub.getPoints());
	}

	@Test
	public void setMatches() {
		testClub.setMatches();
		assertEquals(9,testClub.getMatches());
	}

	@Test
	public void compareTo() {
		FootballClub team1 = new FootballClub("Chelsea", " 7505 Lancaster Lane Seattle, WA 98144", 5, 3, 1, 12, 10, "John cena", "Randy Ortan");
		FootballClub team2 = new FootballClub("Leicaster", "680 Piper Street Minot, ND 58701", 6, 0, 2, 9, 12, "Ryder Leonard", "Ella-Mai Timms");
		assertEquals(-1,team1.compareTo(team2));
	}

	@Test
	public void testEquals() {
		FootballClub team1 = new FootballClub("Chelsea", " 7505 Lancaster Lane Seattle, WA 98144", 5, 3, 1, 12, 10, "John cena", "Randy Ortan");
		FootballClub team2 = new FootballClub("Leicaster", "680 Piper Street Minot, ND 58701", 6, 0, 2, 9, 12, "Ryder Leonard", "Ella-Mai Timms");
		assertFalse(team1.equals(team2));
	}

}