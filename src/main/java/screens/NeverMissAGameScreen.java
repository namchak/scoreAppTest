package screens;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NeverMissAGameScreen {

	private String neverMissImage_id = "com.fivemobile.thescore:id/image_onboarding";
	private String neverMissTitle_id = "com.fivemobile.thescore:id/title_onboarding";
	private String neverMissSubtitle_id = "com.fivemobile.thescore:id/subtitle_onboarding";
	
	private String toggleList_xpath = "//androidx.recyclerview.widget.RecyclerView[contains(@resource-id, \"com.fivemobile.thescore:id/recyclerView\")]/android.view.ViewGroup";
	private String doneButton_id = "com.fivemobile.thescore:id/btn_primary";
	
	public void waitForNeverMissaGame(AndroidDriver<MobileElement> d) {
		WebDriverWait wait = new WebDriverWait(d, 3000);
		wait.until(ExpectedConditions.visibilityOf(d.findElementById(neverMissTitle_id)));
	}
	
	public void clickDone(AndroidDriver<MobileElement> d) {
		d.findElementById(doneButton_id).click();
	}
	
}
