package services;

import models.FootballClub;
import utils.ApplicationUtil;

import java.util.HashMap;
import java.util.Map;

public class FootballClubService {

	private Map<Integer, FootballClub> clubList = new HashMap<>();
	private static FootballClubService instance;

	public static FootballClubService getInstance() {
		if (instance == null) {
			instance = new FootballClubService();
		}
		return instance;
	}

	public void addFootballClub(FootballClub footballClub){
		int id = clubList.size() + 1;
		footballClub.setClubId(id);
		clubList.put(id, footballClub);
	}

	public void removeFootballClub(FootballClub footballClub){
		int id = footballClub.getClubId();

		if (!clubList.containsKey(id)){
			System.out.println(footballClub.getName() + " is not in league");
		}
		clubList.remove(id);
	}

	public void updateFootballClub(FootballClub footballClub){
		int id = footballClub.getClubId();
		if (clubList.containsKey(id)){
			clubList.put(id, footballClub);
		}else
			System.out.println("Club not in list");
	}

	public Map<Integer, FootballClub> getClubList() {
		return clubList;
	}

	public void setClubList(Map<Integer, FootballClub> clubList) {
		this.clubList = clubList;
	}

	public FootballClub getClub(int id) {
		return clubList.get(id);
	}
}
