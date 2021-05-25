package services;

import models.FootballClub;
import models.FootballMatch;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FootballMatchService {

	private static Map<Integer, FootballMatch> matchList = new HashMap<>();
	private static FootballMatchService instance;

	public static FootballMatchService getInstance() {
		if (instance == null) {
			instance = new FootballMatchService();
		}
		return instance;
	}

	public void addFootballMatch(FootballMatch footballMatch){
		int id = matchList.size() + 1;
		footballMatch.setMatchId(id);
		matchList.put(id, footballMatch);
	}

	public FootballMatch getRandomMatch(){
		MatchGenerateService matchGenerate = new MatchGenerateService();
		return matchGenerate.getRandomMatch();
	}


	public static Map<Integer, FootballMatch> getMatchList() {
		return matchList;
	}

	public static void setMatchList(Map<Integer, FootballMatch> matchList) {
		FootballMatchService.matchList = matchList;
	}
}
