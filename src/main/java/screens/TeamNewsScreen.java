package screens;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamNewsScreen {
	
	private String teamTitle_id = "com.fivemobile.thescore:id/team_name";
	private String teamStatsTab_xpath = "//android.widget.LinearLayout[@content-desc=\"Team Stats\"]/android.widget.TextView";
	
	public void waitForTeamScreen(AndroidDriver<MobileElement> d ) {
		WebDriverWait wait = new WebDriverWait(d, 3000);
		wait.until(ExpectedConditions.visibilityOf(d.findElementById(teamTitle_id)));
	}
	
	public boolean verifyTeamTitle(AndroidDriver<MobileElement> d, String teamName) {
		return d.findElementById(teamTitle_id).getText().equals(teamName);
	}
	
	public void clickTeamsSubTab(AndroidDriver<MobileElement> d) {
		d.findElementByXPath(teamStatsTab_xpath).click();
	}

}
