package models;

public class SchoolFootballClub extends FootballClub{
    private String schoolName;
    private String schoolDivision;
    private String teacherInCharge;

    public SchoolFootballClub(){

    }

    public SchoolFootballClub(String name, String address, int wins, int draws, int defeats, int scoredGoals,
                              int concededGoals, int goalDifference, int points, int matches, String headCoach,
                              String captain, String schoolName, String schoolDivision, String teacherInCharge) {
        super(name, address, wins, draws, defeats, scoredGoals, concededGoals, headCoach, captain);
        this.schoolName = schoolName;
        this.schoolDivision = schoolDivision;
        this.teacherInCharge = teacherInCharge;
    }

    //Setters


    public void setSchoolName(String schoolName){
        this.schoolName = schoolName;
    }

    public void setSchoolDivision(String schoolDivision){
        this.schoolDivision = schoolDivision;
    }

    public void setTeacherInCharge(String teacherInCharge){
        this.teacherInCharge = teacherInCharge;
    }

    //Getters
    public String getSchoolName(){
        return schoolName;
    }
    public String getSchoolDivision(){
        return schoolDivision;
    }
    public String getTeacherInCharge(){
        return teacherInCharge;
    }




    
}