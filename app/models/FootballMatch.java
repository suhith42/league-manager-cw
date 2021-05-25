package models;

import java.io.Serializable;
import java.time.LocalDate;

public class FootballMatch implements Serializable {

	private int matchId;
	private String date;

	private String team1;
	private int team1Id;
	private int team1Score;

	private String team2;
	private int team2Id;
	private int team2Score;

	private Result matchResult;


	public FootballMatch(String date,int team1Score,int team2Score){
		this.date = date;
		this.team1Score = team1Score;
		this.team2Score = team2Score;
		setMatchResult();
	}

	@Override
	public boolean equals(Object obj) {
		FootballMatch footballMatch = (FootballMatch)obj;
		if((this.getTeam1().equals(footballMatch.getTeam1()) || this.getTeam1().equals(footballMatch.getTeam2()))
			&& (this.getTeam2().equals(footballMatch.getTeam1()) || this.getTeam2().equals(footballMatch.getTeam2())) ){
			return true;
		}else{
			return false;
		}
	}

	public FootballMatch() {
	}


	//Setters
	public void setTeam1(String team1){
		this.team1 = team1;
	}

	public void setTeam2(String team2){
		this.team2 = team2;
	}

	public void setDate(String date){
		this.date = date;
	}

	public void setTeam1Score(int team1Score){
		this.team1Score = team1Score;
	}

	public void setTeam2Score(int team2Score){
		this.team2Score = team2Score;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public void setTeam1Id(int team1Id) {
		this.team1Id = team1Id;
	}

	public void setTeam2Id(int team2Id) {
		this.team2Id = team2Id;
	}
//Getters

	public String getTeam1(){
		return team1;
	}

	public String getTeam2(){
		return team2;
	}

	public String getDate(){
		return date;
	}


	public int getTeam1Score(){
		return team1Score;
	}

	public Result getMatchResult(){
		return matchResult;
	}

	public int getTeam2Score(){
		return team2Score;
	}

	public int getMatchId() {
		return matchId;
	}

	public int getTeam1Id() {
		return team1Id;
	}

	public int getTeam2Id() {
		return team2Id;
	}



	public enum Result{
		TEAM1WON,
		TEAM2WON,
		DRAW
	}

	public void setMatchResult(){
		if (team1Score > team2Score){
			this.matchResult = Result.TEAM1WON;
		}else if(team1Score < team2Score){
			this.matchResult = Result.TEAM2WON;
		}else{
			this.matchResult = Result.DRAW;
		}
	}

	@Override
	public String toString() {
		return  date + " => " + team1 + " " + team1Score + " | " + team2Score + " " + team2 + " Winning team: "+ matchResult +'\n';
	}
}
