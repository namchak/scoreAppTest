package screens;

import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFavoritesScreen {
	
	private String addFavoritestTitle_id = "com.fivemobile.thescore:id/titleTextView";
	private String allTab_xpath = "//android.widget.LinearLayout[@content-desc='All']";
	private String teamsTab_xpath = "//android.widget.LinearLayout[@content-desc='Teams']";
	private String leaguesTab_xpath = "//android.widget.LinearLayout[@content-desc='Leagues']";
	private String playersTab_xpath = "//android.widget.LinearLayout[@content-desc='Players']";
	private String popularSubTitle_xpath = "//androidx.viewpager.widget.ViewPager[@resource-id='com.fivemobile.thescore:id/viewPager']//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView";
	private String back_xpath = "//android.widget.ImageButton[@content-desc='Navigate up']";
	
	//Element list of teams including subtitle "Popular in your area"
	private String teamsList_xpath = "//androidx.viewpager.widget.ViewPager[@resource-id='com.fivemobile.thescore:id/viewPager']//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup";

	public void waitForAddFavoritesScreen(AndroidDriver<MobileElement> d ) {
		WebDriverWait wait = new WebDriverWait(d, 3000);
		wait.until(ExpectedConditions.visibilityOf(d.findElementById(addFavoritestTitle_id)));
	}
	
	public boolean addYourFavoriteTeam(AndroidDriver<MobileElement> d, String teamName) {
		
		// Navigate to TEAMS tab
		d.findElementByXPath(teamsTab_xpath).click();
		if(d.findElementByXPath(popularSubTitle_xpath).getText().equalsIgnoreCase("POPULAR IN YOUR AREA")) {
			List<MobileElement> teams = d.findElementsByXPath(teamsList_xpath);
			
			// Select the team name
			for(int i=2; i<teams.size(); i++) {
				String nameXpath = teamsList_xpath + "[" + i + "]/android.widget.TextView";
				if(d.findElementByXPath(nameXpath).getText().equalsIgnoreCase(teamName)) {
					d.findElementByXPath(nameXpath).click();
					d.findElementByXPath(back_xpath).click();
					return true;
				}
			}
			return false;
		}	
		return false;
	}
	
}
