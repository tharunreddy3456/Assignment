package TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.net.HttpURLConnection;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.AfterMethod;

public class SearchWeb {

	public WebDriver driver;

	@Test
	public void runTest() throws IOException {
		// launch google.ca and search for touch tunes
		driver.get("https://google.ca");
		driver.findElement(By.className("gLFyf")).sendKeys("Touchtunes");
		driver.findElement(By.name("btnK")).click();

		// Get all the links from the web page
		List<WebElement> getAllLinks = driver.findElements(By.xpath("//*[@href]"));
		String linkList = "";

		// Save all the href values from the links and save in the csc file
		for (WebElement e : getAllLinks) {
			String link = e.getAttribute("href");
			if (null == link)
				link = e.getAttribute("src");
			System.out.println(e.getTagName() + "=" + link);
			if (link.contains("touchtunes")) {
				linkList = linkList + link + "\r\n";

				// Check if link is broken or valid
				HttpURLConnection httpConnection = (HttpURLConnection) (new URL(link).openConnection());
				httpConnection.setRequestMethod("HEAD");
				httpConnection.connect();
				int responseCode = httpConnection.getResponseCode();
				if (responseCode != 200) {
					System.out.println(link + " is broken link");
				} else {
					System.out.println(link + " is valid link");
				}
			}
		}

		// write the links in the txt file
		try (FileWriter writecsv = new FileWriter("src/link.txt")) {
			writecsv.append(linkList);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void OpenBrowser() {
		// Get the Chrome Driver
		File app = new File("chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", app.getAbsolutePath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();

	}

}