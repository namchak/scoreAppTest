package screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseTeamsScreen {
	
	private String goBackArrow_xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";
	private String starIcon_id = "com.fivemobile.thescore:id/icon";
	private String chooseTeamsTitle_id = "com.fivemobile.thescore:id/title_onboarding";
	private String chooseTeamsSubTitle_id = "com.fivemobile.thescore:id/subtitle_onboarding";
	private String searchField_id = "com.fivemobile.thescore:id/search_bar_placeholder";
	private String teamsList_xpath = "//androidx.recyclerview.widget.RecyclerView[contains(@resource-id, \"com.fivemobile.thescore:id/recyclerView\")]/android.view.ViewGroup";
	private String continueButton_id = "com.fivemobile.thescore:id/btn_primary";
	private List<String> tabsList = new ArrayList<String>();
	
	public void waitForChooseTeamsScreen(AndroidDriver<MobileElement> d) {
		WebDriverWait wait = new WebDriverWait(d, 3000);
		wait.until(ExpectedConditions.visibilityOf(d.findElementById(chooseTeamsTitle_id)));
	}
	
	public void clickContinue(AndroidDriver<MobileElement> d) {
		d.findElementById(continueButton_id).click();
	}
	
	public void setTabsList() {
		List<String> tabs = Arrays.asList("Recommendend", "NHL", "NFL", "MLB", "NBA", "NCAAF", "CFL");
		
		for(String tab:tabs ) {
			tabsList.add("//android.widget.LinearLayout[@content-desc=\"" + tab + "\"]");
		}
	}
	
}
