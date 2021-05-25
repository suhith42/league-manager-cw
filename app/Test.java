import controllers.FootballMatchController;

import models.FootballMatch;
import services.FootballClubService;
import services.FootballMatchService;
import services.MatchGenerateService;
import utils.ApplicationUtil;

public class Test {
	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			System.out.println(getRandNumber(1,4));
		}


//		ApplicationUtil.retrieveClubsFromFile();
//		ApplicationUtil.retrieveMatchListFromFile();
//
//
////		System.out.println(FootballClubService.getInstance().getClubList().get(1).getMatches());
//		for (int i = 0; i < 10; i++) {
//			MatchGenerateService mgs = new MatchGenerateService();
//			System.out.println(mgs.getRandomMatch());
//		}
//
//		ApplicationUtil.saveMatchesToFile();

//		System.out.println(FootballClubService.getInstance().getClubList().get(1).getMatches());








	}
	private static int getRandNumber(int max, int min){
		return (int) (Math.random() * (max - min + 1) + min);
	}
}
