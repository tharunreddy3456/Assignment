package POM;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LandingScreen {
	private static MobileElement element = null;

	// Language list Elements
	public static MobileElement LanguageType(AndroidDriver<?> driver, String language) {
		element = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='" + language + "']");
		return element;
	}

	// Done Button
	public static MobileElement DoneButton(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Done']");
		return element;
	}

	// Selected Language Element
	public static MobileElement SelectedLangText(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Selected\")");
		return element;
	}

	// Scroll to Element based on its Text on the HomePage
	public static void ScrollToElement(AndroidDriver<?> driver, String elementText) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"));");
	}

	// landing page Frame Layout
	public static MobileElement Frame(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByClassName("android.widget.FrameLayout");
		return element;
	}

	// landingQuestion on the landing page
	public static MobileElement LandingQuestion(AndroidDriver<?> driver) {
		element = (MobileElement) driver
				.findElementByXPath("//android.widget.TextView[@text='What kinds of music do you like?']");
		return element;
	}

	// Home Button in bottom menu bar
	public static MobileElement HomeButton(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementByXPath("//android.support.v4.widget.DrawerLayout//android.widget.FrameLayout//android.widget.LinearLayout//android.widget.LinearLayout//android.widget.ImageView");
		return element;
	}

	// Advertisement Pop Up
	public static MobileElement AdCloseButton(AndroidDriver<?> driver) {
		element = (MobileElement) driver.findElementById("google-close-button");
		return element;
	}

}
