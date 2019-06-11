package TestCase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import POM.LandingScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class Onboarding {

	public static URL url;
	public static DesiredCapabilities capabilities;
	public static AndroidDriver<AndroidElement> driver;
	boolean ifHomeBtnIsEnabled;

	@BeforeTest
	public void setupAppium() throws MalformedURLException {
		final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
		url = new URL(URL_STRING);
		File appDir = new File("src");
		File app = new File(appDir, "Saavn Music Radio_v6.0.1_apkpure.com.apk");
		//Set the DesiredCapabilities to connect with appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		//Device ID device where the test will be executed
		capabilities.setCapability("deviceId", "9886f5414a524e1234");
		// Name of the device where the test will be executed
		capabilities.setCapability("deviceName", "GalaxyS8");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new AndroidDriver<AndroidElement>(url, capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterTest
	public void closeApp() throws InterruptedException {
		driver.closeApp();
	}

	@Test
	public void onboarding() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(LandingScreen.LanguageType(driver, "Hindi")));
 
		// Verify user has landed on page
		boolean ifFrameIsLoaded = LandingScreen.Frame(driver).isDisplayed();
		AssertJUnit.assertEquals(ifFrameIsLoaded, true);

		// Verify What Kinds of Music do you like question is displayed on welcome
		// screen
		boolean ifWelcomeQuestionDisplayed = LandingScreen.LandingQuestion(driver).isDisplayed();
		AssertJUnit.assertEquals(ifWelcomeQuestionDisplayed, true);

		// DeSelect Hindi
		LandingScreen.LanguageType(driver, "Hindi").click();

		// Scroll to Assamese and Select it
		LandingScreen.ScrollToElement(driver, "Assamese");
		LandingScreen.LanguageType(driver, "Assamese").click();

		// Verify How many language has been Selected.
		String langValue = LandingScreen.SelectedLangText(driver).getText().toString();
		AssertJUnit.assertEquals(langValue, "1 Selected");

		// Click on the home Button
		LandingScreen.DoneButton(driver).click();
		Thread.sleep(5000);
		try {
			ifHomeBtnIsEnabled = LandingScreen.HomeButton(driver).isEnabled();
		} catch (NoSuchElementException e) {
			if ((LandingScreen.AdCloseButton(driver).isDisplayed()) == true) {
				LandingScreen.AdCloseButton(driver).click();
			}
			ifHomeBtnIsEnabled = LandingScreen.HomeButton(driver).isEnabled();
		}

		// Verify that home button is enabled
		AssertJUnit.assertEquals(ifHomeBtnIsEnabled, true);

	}

}
