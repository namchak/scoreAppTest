package screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class WelcomeScreen {
	
	private String scoreLogo_id = "com.fivemobile.thescore:id/icon_welcome";
	private String welcomeTitle_id ="com.fivemobile.thescore:id/txt_welcome";
	private String welcomeSubTitle_id = "com.fivemobile.thescore:id/txt_app_exp";
	private String haveAccountLoginText_id = "com.fivemobile.thescore:id/txt_sign_in";
	private String termsConditionsText_id = "com.fivemobile.thescore:id/txt_terms";
	private String startedButton_id = "com.fivemobile.thescore:id/action_button_text";
	
	
	public void clickGetStarted(AndroidDriver<MobileElement> d) {
		d.findElement(By.id(startedButton_id)).click();
	}
}
