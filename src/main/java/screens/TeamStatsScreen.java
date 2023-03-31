package screens;

import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataobjects.Stat;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamStatsScreen {
	private String back_xpath = "//android.widget.ImageButton[@content-desc=\'Navigate up\']";
	private String statsField_id = "com.fivemobile.thescore:id/header_text";
	private String rankField_id = "com.fivemobile.thescore:id/header_secondary_text";
	private String statsList_xpath = "//androidx.viewpager.widget.ViewPager[@resource-id='com.fivemobile.thescore:id/viewPager']//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup";

	public void waitForTeamScreen(AndroidDriver<MobileElement> d ) {
		WebDriverWait wait = new WebDriverWait(d, 3000);
		wait.until(ExpectedConditions.visibilityOf(d.findElementById(statsField_id)));
	}
	
	public boolean verifyRankSubHeader(AndroidDriver<MobileElement> d) {
		return d.findElementById(rankField_id).getText().equals("(RANK)");
	}
	
	public void clickBack(AndroidDriver<MobileElement> d) {
		d.findElementByXPath(back_xpath).click();
	}
	
	public boolean verifyFirstFiveStats(AndroidDriver<MobileElement> d, Stat[] stats) {
		List<MobileElement> teamStatsList = d.findElementsByXPath(statsList_xpath);
		
		// Select the team name
		for(int i=2; i<6; i++) {
			String statXpath = statsList_xpath + "[" + i + "]/android.widget.TextView[@resource-id = 'com.fivemobile.thescore:id/text_category_name']";
			String rankValXpath = statsList_xpath + "[" + i + "]/android.widget.TextView[@resource-id = 'com.fivemobile.thescore:id/text_value']";
			String rankFormXpath = statsList_xpath + "[" + i + "]/android.widget.TextView[@resource-id = 'com.fivemobile.thescore:id/text_formatted_rank']";
			
			if(d.findElementByXPath(statXpath).getText().equalsIgnoreCase(stats[i-2].getStats())
					&& d.findElementByXPath(rankValXpath).getText().equalsIgnoreCase(stats[i-2].getRank_value())
					&& d.findElementByXPath(rankFormXpath).getText().equalsIgnoreCase(stats[i-2].getFormatted_rank())
					) {
				return true;
			}else {
				System.out.println("ERROR: Team stats does not match with reference data. Esnure reference data is updated to latest");
				return false;
			}
		}
		return false;
	}
	

}
