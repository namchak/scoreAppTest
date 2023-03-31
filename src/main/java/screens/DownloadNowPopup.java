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
public class DownloadNowPopup {
	
	private String dismissModal_id = "com.fivemobile.thescore:id/dismiss_modal";
	private String mapleLeafImage_id = "com.fivemobile.thescore:id/canada_maple_leaf";
	private String betLogo_id = "com.fivemobile.thescore:id/score_bet_logo";
	private String headerText_id = "com.fivemobile.thescore:id/header_text";
	private String subtitle_id = "com.fivemobile.thescore:id/subtitle_text";
	private String downloadButton_id = "com.fivemobile.thescore:id/button_positive";
	private String responsibleIcon_id = "com.fivemobile.thescore:id/icon_responsible_gaming";
	private String responsibleMessage_id = "com.fivemobile.thescore:id/responsible_gaming_message";

	public void waitForDownloadPopup(AndroidDriver<MobileElement> d) {
		WebDriverWait wait = new WebDriverWait(d, 3000);
		wait.until(ExpectedConditions.visibilityOf(d.findElementById(dismissModal_id)));
	}
	
	public void dismissModal(AndroidDriver<MobileElement> d) {
		d.findElementById(dismissModal_id).click();
	}
	
}
