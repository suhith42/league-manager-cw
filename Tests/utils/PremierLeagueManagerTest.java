package utils;

import models.FootballClub;
import models.FootballMatch;
import org.apache.bcel.generic.FMUL;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PremierLeagueManagerTest {

	@Test
	public void displayMenu() {
	}

	@Test
	public void addToPremiereLeague() {
		PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
		FootballClub testTeam = new FootballClub("Spurs", "7882 Amerige Dr.Billerica, MA 01821", 6, 2, 1, 27, 9, "Dania Patton", "Aryan Monroe");
		int previousSize = premierLeagueManager.getFootballClubList().size();
		premierLeagueManager.getFootballClubList().add(testTeam);
		assertEquals(previousSize+1,premierLeagueManager.getFootballClubList().size());
	}

	@Test
	public void deleteFromPremiereLeague() {
		PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
		FootballClub testTeam = new FootballClub("Spurs", "7882 Amerige Dr.Billerica, MA 01821", 6, 2, 1, 27, 9, "Dania Patton", "Aryan Monroe");
		premierLeagueManager.getFootballClubList().add(testTeam);

		int previousSize = premierLeagueManager.getFootballClubList().size();
		premierLeagueManager.getFootballClubList().remove(testTeam);

		assertEquals(previousSize-1, premierLeagueManager.getFootballClubList().size());
	}

	@Test
	public void addMatchToLeagueTable() {
		PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
		FootballClub testTeam1 = new FootballClub("Spurs", "7882 Amerige Dr.Billerica, MA 01821", 6, 2, 1, 27, 9, "Dania Patton", "Aryan Monroe");
		FootballClub testTeam2 = new FootballClub("Leicaster", "680 Piper Street Minot, ND 58701", 6, 0, 2, 9, 12, "Ryder Leonard", "Ella-Mai Timms");

		FootballMatch testMatch = new FootballMatch();
		testMatch.setTeam1(testTeam1.getName());
		testMatch.setTeam2(testTeam2.getName());

		int previousSize = premierLeagueManager.getMatchResults().size();
		premierLeagueManager.getMatchResults().add(testMatch);
		assertEquals(previousSize+1,premierLeagueManager.getMatchResults().size());
		;

	}

	@Test
	public void saveData() {

	}

	@Test
	public void retrieveData() {
		ArrayList<FootballClub> footballClubList = new ArrayList<>();
		ArrayList<FootballMatch> matchResults = new ArrayList<>();
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;

		try{
			//Retrieve club list from file
			fileInputStream = new FileInputStream("project/datafiles/ClubData.txt");
			objectInputStream = new ObjectInputStream(fileInputStream);
			ArrayList<FootballClub> tempClubArray = (ArrayList<FootballClub>)objectInputStream.readObject();
			footballClubList.addAll(tempClubArray);
			System.out.println("All teams added to club list from file");

			//Retrieve match results from file
			fileInputStream = new FileInputStream("project/datafiles/MatchData.txt");
			objectInputStream = new ObjectInputStream(fileInputStream);
			ArrayList<FootballMatch> tempResultsArray = (ArrayList<FootballMatch>)objectInputStream.readObject();
			matchResults.addAll(tempResultsArray);
			System.out.println("Match records have been successfully updated");

		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e){
			System.out.println("Failed to read from file");
		}
		assertNotEquals(0,footballClubList.size());
		assertNotEquals(0,matchResults.size());


	}
}