package scoreAppTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import dataobjects.Stat;
import dataobjects.Team;



public class TeamStatsTest extends init{
	
	@DataProvider( name = "teamsData")
	public Team[] getTeamsDataJsonProvider() throws StreamReadException, DatabindException, IOException {
		String jsonFilePath = System.getProperty("user.dir")+"/src/test/resources/testdata/data.json";		
		ObjectMapper obj = new ObjectMapper();
		obj.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		Team[] teams = obj.readValue(new File(jsonFilePath), Team[].class);
		return teams;
		
	}
	
	private Stat[] getTeamStatsData(String teamName) throws StreamReadException, DatabindException, IOException {
		String jsonFilePath = System.getProperty("user.dir")+"/src/test/resources/testdata/" + teamName + ".json";		
		ObjectMapper obj = new ObjectMapper();
		obj.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		Stat[] statsArr = obj.readValue(new File(jsonFilePath), Stat[].class);
		return statsArr;
		
	}

	@Test (dataProvider = "teamsData")
	public void ValidateTeamStats(Team team)  {
		System.out.println("TEST STARTED: Validate team stats for - "+ team.getTeamName());
		
		try {
		  if(driver.findElement(By.id(favorites.getFavoritesMenu_id())).isSelected()){
			favorites.clickAddIcon(driver);
			addFav.waitForAddFavoritesScreen(driver);
			if(addFav.addYourFavoriteTeam(driver, team.getTeamName())) {
				// STEP1: Opens a team page
				Assert.assertTrue(favorites.navigateToTeam(driver, team.getIconId()));
				teamNews.waitForTeamScreen(driver);
				
				// STEP2: Verify the team page is opened correctly
				Assert.assertTrue(teamNews.verifyTeamTitle(driver, team.getTeamName()));
				
				// STEP3: Tap on TEAM STATS subtab
				teamNews.clickTeamsSubTab(driver);
				teamStats.waitForTeamScreen(driver);
				
				// STEP4: Verify that correct tab is open by matching sub header (RANK)
				Assert.assertTrue(teamStats.verifyRankSubHeader(driver));
				
				// STEP5: Verify first five stats for the team w.r.t reference data in <team name>.json
				Stat[] stats = getTeamStatsData(team.getTeamName());
				Assert.assertTrue(teamStats.verifyFirstFiveStats(driver, stats));
				
				// STEP6: Verify the back navigation returns to previous page
				teamStats.clickBack(driver);
				Assert.assertTrue(favorites.verifyFavoritesMenuIspresent(driver));
				
				System.out.println("TEST PASSED");
			
			}else {
				System.out.println("ERROR: Team name not found in app");
				Assert.assertFalse(true);
			}
		  }
			
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}
