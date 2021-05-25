package utils;

import models.FootballClub;
import models.FootballMatch;

import java.util.ArrayList;

public interface LeagueManager{

	void addToPremiereLeague();
	void deleteFromPremiereLeague();
	void displaySelectedTeamStats();
	void displayPremiereLeagueTable();
	void addMatchToLeagueTable();
	void saveData(ArrayList<FootballClub> footballClubList, ArrayList<FootballMatch> matchResults);
	void retrieveData(ArrayList<FootballClub> footballClubList, ArrayList<FootballMatch> matchResults);
	void openGUI();

}