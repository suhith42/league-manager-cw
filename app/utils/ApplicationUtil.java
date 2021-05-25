package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballClub;
import models.FootballMatch;
import play.libs.Json;
import services.FootballClubService;
import services.FootballMatchService;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class ApplicationUtil {

//	public static ObjectNode createResponse(Object response, boolean ok) {
//		ObjectNode result = Json.newObject();
//		result.put("status", ok);
//		if (response instanceof String)
//			result.put("response", (String) response);
//		else result.set("response", (JsonNode) response);
//
//		return result;
//	}

	public static ObjectNode createResponse(Object response, boolean ok) {
		ObjectNode result = Json.newObject();
		result.put("isSuccessful", ok);
		if (response instanceof String) {
			result.put("body", (String) response);
		} else {
			result.putPOJO("body", response);
		}
		return result;
	}

	public static void retrieveClubsFromFile(){
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;
		ArrayList<FootballClub> tempClubArray = new ArrayList<>();

		try{
			//Retrieve club list from file
			fileInputStream = new FileInputStream("project/datafiles/ClubData.txt");
			objectInputStream = new ObjectInputStream(fileInputStream);
			tempClubArray = (ArrayList<FootballClub>)objectInputStream.readObject();
			System.out.println("All teams added to club list from file");

		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e){
			System.out.println("Failed to read from file");
		}

		FootballClubService.getInstance().getClubList().clear();
		for (FootballClub fc: tempClubArray) {
			FootballClubService.getInstance().addFootballClub(fc);
		}

	}

	public static void retrieveMatchListFromFile(){
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;
		ArrayList<FootballMatch> tempResultsArray = new ArrayList<>();

		try{
						//Retrieve match results from file
			fileInputStream = new FileInputStream("project/datafiles/MatchData.txt");
			objectInputStream = new ObjectInputStream(fileInputStream);
			tempResultsArray = (ArrayList<FootballMatch>)objectInputStream.readObject();
			System.out.println("Match records have been successfully updated");

		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e){
			System.out.println("Failed to read from file");
		}

		FootballMatchService.getMatchList().clear();
		for (FootballMatch match: tempResultsArray) {
			FootballMatchService.getInstance().addFootballMatch(match);
		}
	}

	public static void saveMatchesToFile(){
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream=null;

		Collection<FootballMatch> value = FootballMatchService.getMatchList().values();
		ArrayList<FootballMatch> footballMatches = new ArrayList<>(value);

		System.out.println(FootballMatchService.getMatchList());

		try {

			//Writes match results on file
			fileOutputStream = new FileOutputStream("project/datafiles/MatchData.txt");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(footballMatches);
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

	public static void saveClubsToFile(){
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream=null;

		Collection<FootballClub> value = FootballClubService.getInstance().getClubList().values();
		ArrayList<FootballClub> footballClubs = new ArrayList<>(value);

//		System.out.println(FootballClubService.getInstance().getClubList());
		try {
			//Write club list on file
			fileOutputStream = new FileOutputStream("project/datafiles/ClubData.txt");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(footballClubs);
			System.out.println("All football clubs in league are saved successfully");

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

}
