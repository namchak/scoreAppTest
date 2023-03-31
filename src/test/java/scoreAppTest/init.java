package scoreAppTest;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.net.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import screens.AddFavoritesScreen;
import screens.ChooseLeagueScreen;
import screens.ChooseTeamsScreen;
import screens.DownloadNowPopup;
import screens.FavoritesScreen;
import screens.NeverMissAGameScreen;
import screens.TailoredContentPopUp;
import screens.WelcomeScreen;
import screens.TeamNewsScreen;
import screens.TeamStatsScreen;

public class init {
	protected static AndroidDriver driver;
	private static WelcomeScreen welcome = new WelcomeScreen();
	private static ChooseLeagueScreen chooseLeagues = new ChooseLeagueScreen();
	private static TailoredContentPopUp tailoredContent = new TailoredContentPopUp();
	private static ChooseTeamsScreen chooseTeams = new ChooseTeamsScreen();
	private static NeverMissAGameScreen neverMissAGame = new NeverMissAGameScreen();
	private static DownloadNowPopup downloadPop = new DownloadNowPopup();
	protected static FavoritesScreen favorites = new FavoritesScreen();
	protected static AddFavoritesScreen addFav = new AddFavoritesScreen();
	protected static TeamNewsScreen teamNews = new TeamNewsScreen();
	protected static TeamStatsScreen teamStats = new TeamStatsScreen();
	
	private static WebDriverWait wait; 
	
	private static void startScoreApp(String platformName,  String platformVersion, String deviceName, String appiumPort, String appiumRemoteHost, String appiumRemotePath) {
		try {
		    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		    desiredCapabilities.setCapability("platformName", platformName);
		    desiredCapabilities.setCapability("appium:platformVersion", platformVersion);
		    desiredCapabilities.setCapability("appium:deviceName", deviceName);
		    desiredCapabilities.setCapability("appium:automationName", "UIAutomator2");		    
		    desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
		    desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
		    desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
		    desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
		    desiredCapabilities.setCapability("appium:appPackage", "com.fivemobile.thescore");
		    desiredCapabilities.setCapability("appium:appActivity", "com.fivemobile.thescore.ui.MainActivity");
	    	
		    URL remoteUrl = new URL(appiumRemoteHost + ":" + appiumPort + appiumRemotePath);
		    
		    driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		    
		    driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		    wait = new WebDriverWait(driver, 3000);
		    			  
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
	
	public static void navigateToMainScreen() {
		
		try {			
			welcome.clickGetStarted(driver);
			chooseLeagues.verifyInChooseLeaguesScreen(driver);
			chooseLeagues.clickFirstLeague(driver);
			chooseLeagues.clickContinue(driver);
			tailoredContent.verifyInTailoredPopup(driver);
			tailoredContent.clickMaybelater(driver);
			chooseTeams.waitForChooseTeamsScreen(driver);
			chooseTeams.clickContinue(driver);
			neverMissAGame.waitForNeverMissaGame(driver);
			neverMissAGame.clickDone(driver);
			downloadPop.waitForDownloadPopup(driver);
			downloadPop.dismissModal(driver);
						
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	

	@Parameters ({"platformName", "platformVersion","deviceName", "appiumPort", "appiumRemoteHost", "appiumRemotePath" })
	@BeforeMethod
	public static void setup(String platformName,  String platformVersion, String deviceName, String appiumPort, String appiumRemoteHost, String appiumRemotePath) throws InterruptedException {
		
		// Prerequisite: Appium server should be up and running & theScore app should be installed in the android device
		// Start theScore App on device/emulator
		startScoreApp(platformName, platformVersion, deviceName, appiumPort, appiumRemoteHost, appiumRemotePath);  
		
		// If welcome screen is displayed, Ignore login and popups and navigate to the main screen
		if(driver.findElement(By.id(welcome.getWelcomeTitle_id())).getText().contentEquals("WELCOME")) {
			System.out.println("INFO: App started. Lets navigate to main screen");
			navigateToMainScreen();
		}
		if(!driver.findElement(By.id(favorites.getFavoritesMenu_id())).isSelected()) {
			System.out.println("INFO: Click Favorites menu");
			driver.findElement(By.id(favorites.getFavoritesMenu_id())).click();
		}
	}
	
	@AfterMethod
	public static void tearDown() {
		driver.quit();
	}

}
