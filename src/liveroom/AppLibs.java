package liveroom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.URL;

public class AppLibs {

	public WebElement findElement(AndroidDriver driver, String localstyle, String value) {

		WebElement result = null;
		switch (localstyle) {
		case "id":
			result = driver.findElement(By.id(value));
			break;
		case "name":
			result = driver.findElement(By.name(value));
			break;
		case "xpath":
			result = driver.findElement(By.xpath(value));
			break;
		case "class":
			result = driver.findElement(By.className(value));
			break;

		}
		return result;
	}

	public List<WebElement> findElements(AndroidDriver driver, String localstyle, String value) {

		List<WebElement> result = null;
		switch (localstyle) {
		case "id":
			result = driver.findElements(By.id(value));
			break;
		case "name":
			result = driver.findElements(By.name(value));
			break;
		case "xpath":
			result = driver.findElements(By.xpath(value));
			break;
		}
		return result;
	}

	public void execute(AndroidDriver driver, WebElement element, String exemethod, String value) {

		switch (exemethod) {
		case "click":
			element.click();
			break;
		case "sendKeys":
			element.sendKeys(value);
			break;
		case "swipe":
			swipefuc(driver, value);
			break;
		}
	}

	// 滑动函数
	public void swipefuc(AndroidDriver driver, String value) {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.swipe(500, 600, 580, 600, 500);//滑动函数
	}

	public WebElement element(AndroidDriver driver, int y) {
		WebElement result = findElement(driver, Xlsfile.readxls("hefanlive_object", 2, y),
				Xlsfile.readxls("hefanlive_object", 3, y));
		return result;
	}

}
