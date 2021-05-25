package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.FootballClub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;

import play.mvc.Result;
import services.FootballClubService;
import utils.ApplicationUtil;

import java.util.HashSet;
import java.util.Set;

public class FootballClubController extends Controller {

	private static final Logger logger = LoggerFactory.getLogger("controller");

	public Result listAllClubs(){
		ApplicationUtil.retrieveClubsFromFile();
		System.out.println("Size of the array: "+ FootballClubService.getInstance().getClubList().size());

		Set<FootballClub> result = new HashSet<>(FootballClubService.getInstance().getClubList().values());
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
		return ok(ApplicationUtil.createResponse(jsonData, true));
	}

	public Result retrieveClub(int id) {
		ApplicationUtil.retrieveClubsFromFile();
		if (!FootballClubService.getInstance().getClubList().containsKey(id)) {
			return notFound(ApplicationUtil.createResponse("Employee with id:" + id + " not found", false));
		}
		JsonNode jsonObjects = Json.toJson(FootballClubService.getInstance().getClub(id));
		System.out.println(jsonObjects);
		return ok(ApplicationUtil.createResponse(jsonObjects, true));
	}

}
