package services;

import models.FootballClub;
import org.junit.Test;
import play.libs.F;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FootballClubServiceTest {

	@Test
	public void addFootballClub() {
		FootballClubService fcs = new FootballClubService();
		int previousSize = fcs.getClubList().size();
		FootballClub testTeam = new FootballClub("C Palace", "8019 West Bay Street Suite D Alpharetta, GA 3000", 4, 1, 3, 13, 14, "Kaila Trujillo", "Kiran Shelton");

		fcs.addFootballClub(testTeam);
		assertEquals(previousSize+1,fcs.getClubList().size());
	}

	@Test
	public void removeFootballClub() {
		FootballClubService fcs = new FootballClubService();

		FootballClub testTeam = new FootballClub("C Palace", "8019 West Bay Street Suite D Alpharetta, GA 3000", 4, 1, 3, 13, 14, "Kaila Trujillo", "Kiran Shelton");
		fcs.addFootballClub(testTeam);
		int previousSize = fcs.getClubList().size();
		fcs.removeFootballClub(testTeam);

		assertEquals(previousSize-1,fcs.getClubList().size());

	}

	@Test
	public void updateFootballClub() {
		FootballClub oldTeam = new FootballClub("C Palace", "8019 West Bay Street Suite D Alpharetta, GA 3000", 4, 1, 3, 13, 14, "Kaila Trujillo", "Kiran Shelton");
		FootballClubService fcs = new FootballClubService();
		fcs.addFootballClub(oldTeam);

		FootballClub updatedTeam = new FootballClub();
		updatedTeam.setName("C Palace");
		updatedTeam.setWins(5);
		updatedTeam.setClubId(1);
		fcs.updateFootballClub(updatedTeam);



		assertEquals(5,fcs.getClub(1).getWins());

	}
}