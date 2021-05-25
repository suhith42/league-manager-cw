package services;

import controllers.FootballMatchController;
import models.FootballClub;
import models.FootballMatch;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MatchGenerateService {

	private FootballMatch randomMatch;


	public MatchGenerateService() {
		this.randomMatch = new FootballMatch();
	}

	public FootballMatch getRandomMatch() {
		setRandomTeams(randomMatch);
		return randomMatch;
	}

	private void setRandomResult(FootballMatch footballMatch){
		Random random = new Random();

		Random random1 = new Random();
		int randomScore1 = random.nextInt(5);
		int randomScore2 = random1.nextInt(5);

		footballMatch.setTeam1Score(randomScore1);
		footballMatch.setTeam2Score(randomScore2);



	}

	private void setRandomTeams(FootballMatch footballMatch){
		Random random =  new Random();

		FootballClub team1;
		FootballClub team2;



		int randomTeam1Index;
		int randomTeam2Index;

		//To prevent the same team being chose as team 1 and team 2
		do {
			randomTeam1Index = getRandNumber(1,FootballClubService.getInstance().getClubList().size());
			randomTeam2Index = getRandNumber(1,FootballClubService.getInstance().getClubList().size());
		}while (randomTeam1Index == randomTeam2Index);


		footballMatch.setDate(randomDateGenerator());
		//Set team 1 info
		team1 = FootballClubService.getInstance().getClubList().get(randomTeam1Index);
		footballMatch.setTeam1Id(randomTeam1Index);
		footballMatch.setTeam1(team1.getName());
		//Set team 2 info
		team2 = FootballClubService.getInstance().getClubList().get(randomTeam2Index);
		footballMatch.setTeam2Id(randomTeam2Index);
		footballMatch.setTeam2(team2.getName());
		footballMatch.setMatchId(FootballMatchService.getMatchList().size()+1);
		footballMatch.setMatchResult();


		setRandomResult(footballMatch);
		footballMatch.setMatchResult();
		updateTeamStats(team1,team2,footballMatch);


	}

	public void updateTeamStats(FootballClub team1, FootballClub team2, FootballMatch footballMatch){
		if(footballMatch.getMatchResult() == FootballMatch.Result.TEAM1WON){
			team1.setWins(team1.getWins()+1);
			team2.setDefeats(team2.getDefeats()+1);
		}else if(footballMatch.getMatchResult() == FootballMatch.Result.TEAM2WON){
			team2.setWins(team2.getWins()+1);
			team1.setDefeats(team1.getDefeats()+1);
		}else{
			team1.setDraws(team1.getDraws()+1);
			team2.setDraws(team2.getDraws()+1);
		}

		team1.setScoredGoals(team1.getScoredGoals() + footballMatch.getTeam1Score());
		team2.setScoredGoals(team2.getScoredGoals() + footballMatch.getTeam2Score());

		team1.setConcededGoals(team1.getConcededGoals() + footballMatch.getTeam2Score());
		team2.setConcededGoals(team2.getConcededGoals() + footballMatch.getTeam1Score());

		team1.setMatches();
		team2.setMatches();
		team1.setPoints();
		team2.setPoints();
		team1.setGoalDifference();
		team2.setGoalDifference();

		FootballClubService.getInstance().updateFootballClub(team1);
		FootballClubService.getInstance().updateFootballClub(team2);
	}

	private String randomDateGenerator(){
		long minDay = LocalDate.of(2020, 1, 1).toEpochDay();
		long maxDay = LocalDate.of(2020, 12, 31).toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		return LocalDate.ofEpochDay(randomDay).toString();

		//Referenced: https://stackoverflow.com/questions/34051291/generate-a-random-localdate-with-java-time
	}



	private int getRandNumber(int min, int max){
		return (int) (Math.random() * (max - min + 1) + min);
	}
}
