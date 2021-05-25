package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.internal.cglib.core.$LocalVariablesSorter;
import models.FootballClub;
import models.FootballMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Result;
import services.FootballClubService;
import services.FootballMatchService;
import services.MatchGenerateService;
import utils.ApplicationUtil;

import java.util.HashSet;
import java.util.Set;

import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;


public class FootballMatchController {

	private static final Logger logger = LoggerFactory.getLogger("controller");

	public Result listAllMatches(){
		ApplicationUtil.retrieveMatchListFromFile();
		Set<FootballMatch> result = new HashSet<>(FootballMatchService.getMatchList().values());

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonData = mapper.convertValue(result, JsonNode.class);

		System.out.println(jsonData);
		return ok(ApplicationUtil.createResponse(jsonData, true));
	}

	public Result getRandomMatch() {
		ApplicationUtil.retrieveClubsFromFile();
		ApplicationUtil.retrieveMatchListFromFile();
		if (FootballClubService.getInstance().getClubList().size() < 2){
			return ok(ApplicationUtil.createResponse(null, true));
		}

		MatchGenerateService mgs = new MatchGenerateService();
		FootballMatch randMatch = mgs.getRandomMatch();
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonData = mapper.convertValue(randMatch, JsonNode.class);

		FootballMatchService.getInstance().addFootballMatch(randMatch);

		ApplicationUtil.saveMatchesToFile();
		ApplicationUtil.saveClubsToFile();

		return ok(ApplicationUtil.createResponse(jsonData, true));
	}

}
