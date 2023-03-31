package screens;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TailoredContentPopUp {
	
	private String locationImage_id = "com.fivemobile.thescore:id/img_location";
	private String locationTitle_id = "com.fivemobile.thescore:id/location_title";
	private String locationSubText_id = "com.fivemobile.thescore:id/location_subtext";
	private String allowLocationButton_id = "com.fivemobile.thescore:id/btn_allow";
	private String maybeLaterButton_id = "com.fivemobile.thescore:id/btn_disallow";
	
	public boolean verifyInTailoredPopup(AndroidDriver<MobileElement> d) {
		WebDriverWait wait = new WebDriverWait(d, 3000);
		wait.until(ExpectedConditions.visibilityOf(d.findElementById(locationTitle_id)));
		return d.findElementById(locationTitle_id).getText().equals("Tailored Content");
	}
	
	public void clickMaybelater(AndroidDriver<MobileElement> d) {
		d.findElementById(maybeLaterButton_id).click();
	}
}
