package utils;

import models.FootballClub;
import models.FootballMatch;
import models.SportsClub;
import org.springframework.remoting.support.UrlBasedRemoteAccessor;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.time.DateTimeException;
import java.time.LocalDate;


public class PremierLeagueManager implements LeagueManager  {

	private ArrayList<FootballClub> footballClubList = new ArrayList<>();
	private ArrayList<FootballMatch> matchResults = new ArrayList<>();


	private static final Scanner input = new Scanner(System.in);

	public static void main(String[] args){

		System.out.println("======================================================================");
		System.out.println("|                    PREMIERE LEAGUE MANAGER                          |");
		System.out.println("======================================================================");

		(new PremierLeagueManager()).displayMenu();
	}
	public void displayMenu(){

		final int maxClubsCount = 20;

		retrieveData(footballClubList,matchResults); //Adds all previously saved data
		menu:
		while (true){
			System.out.println("1 - Add a new football team to the premiere league");
			System.out.println("2 - Remove a club from the premiere league");
			System.out.println("3 - Display statistics of a club");
			System.out.println("4 - Display league table");
			System.out.println("5 - Add a played match");
			System.out.println("6 - Open GUI");
			System.out.println("0 - Quit League Manager");

			int option = getIntFromUser("Enter your option: ");

			switch(option){
				case 1:
						//Only 20 clubs are played in a football premier league
						if (footballClubList.size() <= maxClubsCount)
							addToPremiereLeague();
						else
							System.out.println("Only " + maxClubsCount + " clubs can be stored in maximum in premier league manager");
						break ;
				case 2:
					deleteFromPremiereLeague();
					break;
				case 3:
					displaySelectedTeamStats();
					break;
				case 4:
					displayPremiereLeagueTable();
					break;
				case 5:
					addMatchToLeagueTable();
					break;
				case 6:
					openGUI();
					break;
				case 0:
					endProgram(); //Saves all data on exit
					break menu;
				default:
					System.out.println("Invalid input, please re-enter");
			}
		}
	}

	@Override
	public void addToPremiereLeague(){

		System.out.println("Are you adding an existing team in the league or a new team?");
		System.out.println("1 - Existing team");    //Should add club statistics if its an existing team in the league
		System.out.println("2 - New team");
		int selection = getIntFromUser("Selection");

		System.out.println("Enter the following details");
		String name = null;
		do {
			if (name != null) System.out.println("Club you entered is already in the Premier League");
			System.out.print("Football club name: ");
			name = input.nextLine();
		}while (isTeamInLeague(name));




		System.out.print("Address of the club: ");
		String address = input.nextLine();

		System.out.print("Head coach of the club: ");
		String headCoach = input.nextLine();

		System.out.print("Name of the team captain: ");
		String captain = input.nextLine();

		FootballClub footballClub;

		if(selection == 1){
			int  wins = getIntFromUser("Number of wins: ");

			int  draws = getIntFromUser("Number of draws: ");

			int  defeats = getIntFromUser("Number of defeats: ");

			int  scoredGoals = getIntFromUser("Number of scoredGoals: ");

			int  concededGoals = getIntFromUser("Number of concededGoals: ");

			footballClub = new FootballClub(name,address,wins,draws,defeats,scoredGoals,concededGoals,headCoach,captain);

		}else
			footballClub = new FootballClub(name,address,headCoach,captain);

		footballClubList.add(footballClub);
		System.out.println(footballClub.getName()+ " has been successfully added to the league manager!");
	}

	@Override
	public void deleteFromPremiereLeague(){
		String inputName=null;
		do {
			if(inputName != null){
				System.out.println(inputName + " is not found in the League Manager");
			}
			System.out.print("Enter the name of the football club required to be deleted: ");
			inputName = input.nextLine();
		}while (!isTeamInLeague(inputName));

		for(FootballClub fc: footballClubList){
			if(fc.getName().toLowerCase().equals(inputName.toLowerCase())){
				System.out.println(fc.getName()+" has benn successfully removed from the League Manager!");
				footballClubList.remove(fc);
				break;
			}
		}
	}

	@Override
	public void displaySelectedTeamStats(){
		String inputName = null;
		do {
			if (inputName != null) System.out.println(inputName+" is not in the club list. please chack and re-enter");
			System.out.print("Enter the name of the club: ");
			inputName = input.nextLine();
		}while (!isTeamInLeague(inputName));


		for (FootballClub fc : footballClubList){
			if(fc.getName().toLowerCase().equals(inputName.toLowerCase())){
				System.out.println(fc.toString());
				break;
			}
		}
		if(!isTeamInLeague(inputName)){
			System.out.println(inputName+" is not added to the League Manager");
		}
	}



	@Override
	public void addMatchToLeagueTable(){
		//Get match played date
		LocalDate matchDate = null;
		do{
			System.out.println("Enter the date of the match ");
			int day = getIntFromUser("Day: ");
			int month = getIntFromUser("Month: ");
			int year = getIntFromUser("Year: ");
			try {
				matchDate = LocalDate.of(year,month,day);
			}catch(DateTimeException e){
				System.out.println(e.getMessage());
				System.out.println(day+" "+month+" "+year);
			}
		}while(matchDate==null); //Validates the entered date


		//Get team and score
		String team1Name = null;
		System.out.println("Team 1");
		do{
			if(team1Name!=null) System.out.println("Team you entered is not in the League Manager");
			System.out.print("Enter team name ");
			team1Name = input.nextLine();
		}while(!isTeamInLeague(team1Name)); //Validates the existence of the entered team

		int team1Score = getIntFromUser("Score: ");;


		//Get other team and score
		String team2Name = null;
		System.out.println("Team 2");
		do{
			if(team2Name!=null) System.out.println("Team you entered is not in the League Manager");
			System.out.print("Enter team name ");
			team2Name = input.nextLine();
		}while(!isTeamInLeague(team2Name));

		int team2Score = getIntFromUser("Score: ");

		FootballMatch footballMatch = new FootballMatch(matchDate.toString(),team1Score,team2Score);
		FootballClub team1 = new FootballClub();
		FootballClub team2 = new FootballClub();

		for (FootballClub fc : footballClubList) {
			if (team1Name.toLowerCase().equals(fc.getName().toLowerCase())) {
				team1 = fc;
			}
			if(team2Name.toLowerCase().equals(fc.getName().toLowerCase())){
				team2 = fc;

			}
		}

		if(!team1.getName().equals(team2.getName())){ //Checks whether the same team is entered as the opponent
			updateTeamStats(team1,team2,footballMatch);
			footballMatch.setTeam1(team1.getName());
			footballMatch.setTeam2(team2.getName());
			matchResults.add(footballMatch);
			System.out.println(footballMatch.getTeam1() + " vs " + footballMatch.getTeam2() +" match has been successfully added to the records");
		}else {
			System.out.println("You added the same team as opponent");
		}

	}

	@Override
	public void saveData(ArrayList<FootballClub> footballClubList, ArrayList<FootballMatch> matchResults){
		//addTestData();
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream=null;

		try {
			//Write club list on file
			fileOutputStream = new FileOutputStream("project/datafiles/ClubData.txt");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(footballClubList);
			System.out.println("All football clubs in league are saved successfully");

			//Writes match results on file
			fileOutputStream = new FileOutputStream("project/datafiles/MatchData.txt");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(matchResults);
			System.out.println("Match records are saved successfully!");

		}catch (FileNotFoundException e) {
			System.out.println("File not found");
		}catch(IOException e){
			System.out.println("Failed to save in file");
		}finally {
			if(objectOutputStream != null) {
				try {
					objectOutputStream.close();
				}catch(IOException e){
					System.out.println("Failed to close output stream");
				}
			}
		}

	}

	@Override
	public void retrieveData(ArrayList<FootballClub> footballClubList, ArrayList<FootballMatch> matchResults){
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


	}
	@Override
	public void displayPremiereLeagueTable(){

		Collections.sort(footballClubList);
		System.out.println("\n---------------------------------------------------------------------------------------------------");
		System.out.format("%3s%13s%10s%10s%10s%10s%10s%10s%10s%11s","Place","Team","Matches", "Won","Drawn","Lost","GF","GA","GD","Points\n");
		System.out.println("---------------------------------------------------------------------------------------------------");
		int place = 1;
		for(int i = 0; i<footballClubList.size();i++){

			FootballClub fc = footballClubList.get(i);
			System.out.format("%3s%15s%10s%10s%10s%10s%10s%10s%10s%10s%10s",place,fc.getName(),fc.getMatches(),fc.getWins(),fc.getDraws(),fc.getDefeats(),fc.getScoredGoals(),fc.getConcededGoals(),fc.getGoalDifference(),fc.getPoints(),"\n");

			if(i<footballClubList.size()-1){
				if(!fc.equals(footballClubList.get(i+1))){
					place++;
				}
			}
		}
		System.out.println("---------------------------------------------------------------------------------------------------");
	}

	@Override
	public void openGUI() {
//		addTestData();
		saveData(footballClubList,matchResults);
		try {
			System.out.println("Opening GUI...");
			Desktop.getDesktop().browse(new URL("http://localhost:4200").toURI());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean isTeamInLeague(String teamName){
		boolean isTeamInList = false;
		for (FootballClub fc : footballClubList){
			if(fc.getName().toLowerCase().equals(teamName.toLowerCase())){
				isTeamInList = true;
				break;
			}
		}
		return isTeamInList;

	}

	public static int getIntFromUser(String message){
		Scanner input = new Scanner(System.in);
		int inputNumber = 0;

		//Checks whether the entered input is an integer
		while(true){
			try {
				System.out.print(message);
				inputNumber = Integer.parseInt(input.nextLine());
				return inputNumber;
			}catch (NumberFormatException e){
				System.out.println("You entered an invalid input");
			}
		}

	}

	//Update score
	public void updateTeamStats(FootballClub team1, FootballClub team2, FootballMatch footballMatch){
		if(footballMatch.getMatchResult() == FootballMatch.Result.TEAM1WON){    //Updates stats accordingly if team 1 wins
			team1.setWins(team1.getWins()+1);
			team2.setDefeats(team2.getDefeats()+1);
		}else if(footballMatch.getMatchResult() == FootballMatch.Result.TEAM2WON){  //Updates stats accordingly if team 2 wins
			team2.setWins(team2.getWins()+1);
			team1.setDefeats(team1.getDefeats()+1);
		}else{
			team1.setDraws(team1.getDraws()+1); //Updates stats accordingly if match is drawn
			team2.setDraws(team2.getDraws()+1);
		}

		//Updates the goals scored by both teams
		team1.setScoredGoals(team1.getScoredGoals() + footballMatch.getTeam1Score());
		team2.setScoredGoals(team2.getScoredGoals() + footballMatch.getTeam2Score());

		//Updates the goals conceded by both teams
		team1.setConcededGoals(team1.getConcededGoals() + footballMatch.getTeam2Score());
		team2.setConcededGoals(team2.getConcededGoals() + footballMatch.getTeam1Score());

		//Updates number of matches played
		team1.setMatches();
		team2.setMatches();

		//Updates the points
		team1.setPoints();
		team2.setPoints();

		//Updates the goal difference
		team1.setGoalDifference();
		team2.setGoalDifference();
	}

	public void addTestData(){

		FootballClub team1 = new FootballClub("Spurs", "7882 Amerige Dr.Billerica, MA 01821", 6, 2, 1, 27, 9, "Dania Patton", "Aryan Monroe");
		FootballClub team2 = new FootballClub("Chelsea", " 7505 Lancaster Lane Seattle, WA 98144", 5, 3, 1, 12, 10, "John cena", "Randy Ortan");
		FootballClub team3 = new FootballClub("Leicaster", "680 Piper Street Minot, ND 58701", 6, 0, 2, 9, 12, "Ryder Leonard", "Ella-Mai Timms");
		FootballClub team4 = new FootballClub("Liverpool", "70 Grove Lane Yuma, AZ 85365", 5, 2, 1, 18, 16, "Kason Neville", "Prince Robles");
		FootballClub team5 = new FootballClub("Southampton", "752 N. Thompson St.North Ridgeville, OH 44039", 5, 1, 3, 16, 12, "", "");
		FootballClub team6 = new FootballClub("Everton", "752 N. Thompson St.North Ridgeville, OH 44039", 5, 1, 3, 16, 12, "", "");
		FootballClub team7 = new FootballClub("Aston Villa", "66 Amherst St.Corona, NY 11368", 5, 0, 3, 19, 11, "Amiyah Downes", "Pharrell Kumar");
		FootballClub team8 = new FootballClub("West Ham", "720 Randall Mill St.Oak Ridge, TN 37830 ", 4, 2, 3, 15, 10,  "Isis Johnson", "Rickie Hart");
		FootballClub team9 = new FootballClub("C Palace", "8019 West Bay Street Suite D Alpharetta, GA 3000", 4, 1, 3, 13, 14, "Kaila Trujillo", "Kiran Shelton");

		footballClubList.add(team7);
		footballClubList.add(team4);
		footballClubList.add(team9);
		footballClubList.add(team6);
		footballClubList.add(team2);
		footballClubList.add(team8);
		footballClubList.add(team1);
		footballClubList.add(team5);
		footballClubList.add(team3);
	}

	public void endProgram(){

		addTestData();
		String option = null;
		//Validates the entered input
		do {
			if(option!=null) System.out.println("Enter 'y' or 'n' ");
			System.out.println("Do you want to save new changes? (y/n)");
			option = input.nextLine();
		}while (!(option.toLowerCase().equals("y") || option.toLowerCase().equals("n")));

		//Saves all data
		if (option.equals("y")){
			saveData(footballClubList,matchResults);
			System.out.println("All changes have been successfully saved");
		}else System.out.println("Changes not saved");
		System.out.println("Program ended");
	}

	public ArrayList<FootballClub> getFootballClubList() {
		return footballClubList;
	}

	public ArrayList<FootballMatch> getMatchResults() {
		return matchResults;
	}
}