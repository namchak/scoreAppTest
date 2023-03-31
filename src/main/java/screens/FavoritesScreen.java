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
public class FavoritesScreen {
	
	private String favoritesMenu_id = "com.fivemobile.thescore:id/navigation_favorites";
	private String addIcon_xpath = "//android.widget.LinearLayout[@resource-id='com.fivemobile.thescore:id/appbar_layout']//androidx.recyclerview.widget.RecyclerView[@resource-id='com.fivemobile.thescore:id/horizontal_recycler_view']/android.view.ViewGroup[1]/android.widget.TextView";
	private String teamsIconsList_xpath = "//android.widget.LinearLayout[@resource-id='com.fivemobile.thescore:id/appbar_layout']//androidx.recyclerview.widget.RecyclerView[@resource-id='com.fivemobile.thescore:id/horizontal_recycler_view']/android.view.ViewGroup";
	
	
	public void waitForFavoritesMenu(AndroidDriver<MobileElement> d) {
		WebDriverWait wait = new WebDriverWait(d, 3000);
		wait.until(ExpectedConditions.visibilityOf(d.findElementById(favoritesMenu_id)));
	}
	
	public boolean verifyFavoritesMenuIspresent(AndroidDriver<MobileElement> d) {
		return d.findElementById(favoritesMenu_id).isDisplayed();
	}
	
	public void clickAddIcon(AndroidDriver<MobileElement> d) {
		d.findElementByXPath(addIcon_xpath).click();
	}
	
	public boolean navigateToTeam(AndroidDriver<MobileElement> d, String teamIcon) {
		List<MobileElement> icons = d.findElementsByXPath(teamsIconsList_xpath);
		for(int i=1; i <= icons.size(); i++) {
			MobileElement icon = d.findElementByXPath(teamsIconsList_xpath + "[" + i + "]/android.widget.TextView");
			if(icon.getText().equalsIgnoreCase(teamIcon)) {
				icon.click();
				return true;
			}
		}
		System.out.println("ERROR: Team icon not found");
		return false;
	}
}
