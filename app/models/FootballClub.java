package models;

public class FootballClub extends SportsClub implements Comparable<FootballClub> {

	private int wins;
	private int draws;
	private int defeats;
	private int scoredGoals;
	private int concededGoals;
	private int goalDifference;
	private int points;
	private int matches;
	private String headCoach;
	private String captain;



	public FootballClub(){}

	public FootballClub(String name, String address, String headCoach, String captain) {
		super(name, address);
		this.headCoach = headCoach;
		this.captain = captain;
	}

	public FootballClub(String name, String address, int wins, int draws, int defeats, int scoredGoals,
						int concededGoals,  String headCoach,
						String captain) {
		super(name, address);
		this.wins = wins;
		this.draws = draws;
		this.defeats = defeats;
		this.scoredGoals = scoredGoals;
		this.concededGoals = concededGoals;
		this.goalDifference = getGoalDifference();
		this.points = getPoints();
		this.matches = getMatches();
		this.headCoach = headCoach;
		this.captain = captain;
	}

	//Getters
	public String getHeadCoach(){
	return headCoach;
	}
	public String getCaptain(){
	return captain;
	}

	public int getWins(){
		return wins;
	}
	public int getDraws(){
		return draws;
	}

	public int getDefeats(){
		return defeats;
	}

	public int getScoredGoals(){
		return scoredGoals;
	}

	public int getConcededGoals(){
		return concededGoals;
	}

	public int getGoalDifference(){
		setGoalDifference();
		return goalDifference;
	}

	public int getPoints(){
		setPoints();
		return points;
	}

	public int getMatches(){
		setMatches();
		return matches;
	}



	//Setters
	public void setHeadCoach(String headCoach){
		this.headCoach = headCoach;
	}
	public void setCaptain(String captain){
		this.captain = captain;
	}
	public void setWins(int wins){
		this.wins = wins;
	}

	public void setDraws(int draws){
		this.draws = draws;
	}
	public void setDefeats(int defeats){
		this.defeats = defeats;
	}

	public void setScoredGoals(int scoredGoals){
		this.scoredGoals = scoredGoals;
	}

	public void setConcededGoals(int concededGoals){
		this.concededGoals = concededGoals;
	}

	public void setGoalDifference(){
		goalDifference = scoredGoals - concededGoals;
	}

	public void setPoints(){
		points =  wins * 3 + draws;	//Awards 3 point for a win and 1 point for a draw
	}
	public void setMatches(){
		matches = wins + draws + defeats;;
	}


	@Override
	public int compareTo(FootballClub fc){
		if (this.points != fc.points){
			return this.points > fc.points ? -1 : 1;
		}else{
			if (this.goalDifference != fc.goalDifference){
				return this.goalDifference > fc.goalDifference ? -1 : 1;
			}else {
				if(this.concededGoals != fc.concededGoals){
					return this.concededGoals > fc.concededGoals ? -1 : 1;
				}else
					return 0;
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		FootballClub fc = (FootballClub)obj;
		if(this.points == fc.points && this.goalDifference == fc.goalDifference && this.scoredGoals == fc.scoredGoals){
			return true;
		}else return false;
	}

	@Override
	public String toString() {
		return 	"Name: "+ super.getName() + '\n' +
				"wins: " + wins + '\n' +
				"draws: " + draws + '\n' +
				"defeats: " + defeats + '\n' +
				"scoredGoals: " + scoredGoals + '\n' +
				"concededGoals: " + concededGoals + '\n' +
				"goalDifference: " + goalDifference + '\n' +
				"points: " + points + '\n' +
				"matches: " + matches + '\n' +
				"headCoach: '" + headCoach + '\n' +
				"captain: '" + captain ;

	}
}