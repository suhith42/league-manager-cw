package services;

import models.FootballMatch;
import org.junit.Test;
import utils.ApplicationUtil;

import java.util.Collections;

import static org.junit.Assert.*;

public class FootballMatchServiceTest {

	@Test
	public void addFootballMatch() {
		FootballMatch footballMatch = new FootballMatch();
		FootballMatchService fms = new FootballMatchService();

		int previousSize = FootballMatchService.getMatchList().size();
		fms.addFootballMatch(footballMatch);
		assertEquals(previousSize+1,FootballMatchService.getMatchList().size());
	}

	@Test
	public void getRandomMatch() {
		FootballMatch randomMatch = new FootballMatch();
		ApplicationUtil.retrieveClubsFromFile();
		ApplicationUtil.retrieveMatchListFromFile();
		assertNotEquals(null, randomMatch);
	}
}