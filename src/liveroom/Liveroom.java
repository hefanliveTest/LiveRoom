package liveroom;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Liveroom {
	private static AndroidDriver driver;

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "hefan.apk");

		capabilities.setCapability("noSign", "True");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("app", app.getAbsolutePath());

		capabilities.setCapability("appPackage", "com.starunion.hefantv");
		capabilities.setCapability("appActivity", "com.starunion.hefantv.splash.SplashActivity");

		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				// driver = new AndroidDriver(new
				// URL("http://192.168.1.200:4723/wd/hub"),

				capabilities);
		Thread.sleep(1000);

		// 允许弹窗
		// 弹窗是否存在该元素
		while (driver.getPageSource().contains("com.android.packageinstaller:id/permission_allow_button")) {
			clickElement("com.android.packageinstaller:id/permission_allow_button");
		}

		Thread.sleep(1000);
		
		//进入登录页面,点击手机登录
		clickElement("com.starunion.hefantv:id/iv_shoujilogin");
		
		//填写手机号和验证码
		WebElement telNum = driver.findElement(By.id("com.starunion.hefantv:id/et_phoneNum_register"));
		telNum.sendKeys("13300002017");
		WebElement yzm = driver.findElement(By.id("com.starunion.hefantv:id/et_yanzhengma"));
		yzm.sendKeys("1234");
		
		//登录
		clickElement("com.starunion.hefantv:id/btn_next");
		Thread.sleep(2000);	
	}

	private void clickElement(String str) {
		WebElement nokjfs = driver.findElement(By.id(str));
		nokjfs.click();
		
	}

	@Test
	public void inOutRoom() throws InterruptedException {

		int i=1;
	
		//直播间是否存在，存在进入，停留1秒再退出
		while(driver.getPageSource().contains("com.starunion.hefantv:id/id_live_picture") && i<=50) {
			clickElement("com.starunion.hefantv:id/id_live_picture");
			Thread.sleep(1000);//在直播间停留1秒
			clickElement("com.starunion.hefantv:id/live_room_bottom_exit");
			System.out.println("执行了第"+i+"次进出直播间");
			i++;
			Thread.sleep(1000);
		}
		
		if(!driver.getPageSource().contains("com.starunion.hefantv:id/id_live_picture")){
			System.out.println("此刻没有直播间");
		}
		// 3、3s后退出直播间

		System.out.println("进入热门页");

	}

	@AfterTest
	public void tearDown() throws Exception {
		System.out.println("end!!!!");
		// driver.removeApp("io.appium.android.ime");
//		driver.quit();
	}

}
