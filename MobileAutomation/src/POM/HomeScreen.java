package POM;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class HomeScreen {
	private static MobileElement element = null;

	// Scroll to Element based on its Text on the HomePage
	public static void ScrollToElement(AndroidDriver<?> driver, String elementText) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"));");

	}

	// EditorialPicks Section Elements
	public static MobileElement EditorialPicks(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Editorial Picks']");
		return element;
	}

	// EditorialPicks last item Elements
	public static MobileElement EditorialPicksItem(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByXPath(
				"//android.support.v7.widget.RecyclerView//android.widget.LinearLayout[2]//android.support.v7.widget.RecyclerView//android.widget.LinearLayout[4]//android.widget.FrameLayout//android.widget.ImageView");
		return element;
	}

	// Play button
	public static MobileElement PlayButton(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByXPath(
				"//android.widget.LinearLayout[2]//android.widget.RelativeLayout[1]//android.widget.FrameLayout//android.widget.ImageView");
		return element;
	}

	// Pause button
	public static MobileElement PauseButton(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByXPath(
				"//android.widget.FrameLayout[2]//android.widget.RelativeLayout//android.widget.FrameLayout//android.widget.RelativeLayout//android.widget.LinearLayout//android.widget.FrameLayout//android.widget.FrameLayout//android.widget.ImageView");
		return element;
	}

	// Home Screen button
	public static MobileElement HomeScreenButton(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByXPath(
				"//android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ImageView");
		return element;
	}

	// Expand playing song
	public static MobileElement ExpandPlayingSong(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByXPath(
				"//android.support.v4.widget.DrawerLayout//android.widget.FrameLayout//android.widget.LinearLayout//android.widget.LinearLayout//android.widget.ImageView");
		return element;
	}

	// Check seek bar
	public static MobileElement SeekBar(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByXPath("//android.widget.SeekBar");
		return element;
	}
}
