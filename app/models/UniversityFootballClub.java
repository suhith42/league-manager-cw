package models;

public class UniversityFootballClub extends FootballClub {
    private String universityName;
    private String universityDivision;
    private String conference;

    public UniversityFootballClub(){}

    public UniversityFootballClub(String name, String address, int wins, int draws, int defeats, int scoredGoals,
                                  int concededGoals, int goalDifference, int points, int matches, String headCoach,
                                  String captain, String universityName, String universityDivision, String conference) {
        super(name, address, wins, draws, defeats, scoredGoals, concededGoals, headCoach, captain);
        this.universityName = universityName;
        this.universityDivision = universityDivision;
        this.conference = conference;
    }

    //Getters

    public String getUniversityName(){
        return universityName;
    }

    public String getUniversityDivision(){
        return universityDivision;
    }

    public String getConference(){
        return conference;
    }


    //Setters

    public void setUniversityName(String universityName){
        this.universityName = universityName;
    }
    public void setUniversityDivision(String universityDivision){
        this.universityDivision = universityDivision;
    }

    public void setConference(String conference){
        this.conference = conference;
    }

    
}