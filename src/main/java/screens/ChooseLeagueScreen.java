package screens;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseLeagueScreen {
	
	private String backArrow_xpath ="//android.widget.ImageButton[@content-desc=\"Navigate up\"]";
	private String starIcon_id = "com.fivemobile.thescore:id/icon";
	private String chooseLeaguesTitle_id = "com.fivemobile.thescore:id/title_onboarding";
	private String chooseLeaguesSubTitle_id = "com.fivemobile.thescore:id/subtitle_onboarding";
	private String continueButton_id = "com.fivemobile.thescore:id/btn_primary";
	private String firstLeague_xpath ="//androidx.viewpager.widget.ViewPager[@resource-id='com.fivemobile.thescore:id/viewPager']//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]";
	
	public boolean verifyInChooseLeaguesScreen(AndroidDriver<MobileElement> d) {
		WebDriverWait wait = new WebDriverWait(d, 3000);
		wait.until(ExpectedConditions.visibilityOf(d.findElementById(chooseLeaguesTitle_id)));
		return d.findElementById(chooseLeaguesTitle_id).getText().equals("Choose your favorite leagues");
	}
	
	public void clickFirstLeague(AndroidDriver<MobileElement> d) {
		d.findElementByXPath(firstLeague_xpath).click();
	}
	
	public void clickContinue(AndroidDriver<MobileElement> d) {
		d.findElementById(continueButton_id).click();
	}
	
}
