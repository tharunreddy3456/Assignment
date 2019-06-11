package TestCase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import POM.LandingScreen;
import POM.HomeScreen;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import static java.time.Duration.ofMillis;

public class PlaySong {

	public static URL url;
	public static DesiredCapabilities capabilities;
	public static AndroidDriver<AndroidElement> driver;

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

	@SuppressWarnings("rawtypes")
	@Test
	public void playSong() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(LandingScreen.DoneButton(driver)));

		// Click on the Done Button
		LandingScreen.DoneButton(driver).click();

		// Determine the dimension of the device
		Dimension size = driver.manage().window().getSize();
		int startx = size.getWidth() / 2;
		int endx = startx;
		int starty = (int) (size.getHeight() * 0.85);
		int endy = (int) (size.getHeight() * 0.05);

		// Scroll To the end
		for (int i = 0; i < 4; i++) {

			new TouchAction(driver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(ofMillis(1000))).moveTo(PointOption.point(endx, endy)).release()
					.perform();
			Thread.sleep(2000);
		}

		// Scroll to Editorial Picks
		HomeScreen.ScrollToElement(driver, "Editorial Picks");
		Thread.sleep(1000);

		// Determine the Vertical Position of the Editorial Picks section
		int upperY = HomeScreen.EditorialPicks(driver).getLocation().getY() + 150;

		// Scroll to the end of the Editorial Picks section
		for (int i = 0; i < 4; i++) {
			new TouchAction(driver).press(PointOption.point(700, upperY))
					.waitAction(WaitOptions.waitOptions(ofMillis(1000))).moveTo(PointOption.point(100, upperY))
					.release().perform();
			Thread.sleep(2000);
		}

		// Click on the last item in the Editorial Picks section
		HomeScreen.EditorialPicksItem(driver).click();
		// Verify the play screen is displayed
		wait.until(ExpectedConditions.elementToBeClickable(HomeScreen.PlayButton(driver)));
		AssertJUnit.assertEquals((HomeScreen.PlayButton(driver).isEnabled()), true);
		// Click on play button
		HomeScreen.PlayButton(driver).click();
		// Ensure that song is playing
		HomeScreen.ExpandPlayingSong(driver).click();
		AssertJUnit.assertEquals((HomeScreen.SeekBar(driver).isEnabled()), true);
		// Navigate to home page
		HomeScreen.HomeScreenButton(driver).click();
		// verify that user is back on home screen by verifying that Editorial Picks is
		// displayed on screen again
		AssertJUnit.assertEquals((HomeScreen.EditorialPicks(driver).isDisplayed()), true);
		
	}

}
