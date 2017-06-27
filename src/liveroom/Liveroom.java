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

		// ������
		// �����Ƿ���ڸ�Ԫ��
		while (driver.getPageSource().contains("com.android.packageinstaller:id/permission_allow_button")) {
			clickElement("com.android.packageinstaller:id/permission_allow_button");
		}

		Thread.sleep(1000);
		
		//�����¼ҳ��,����ֻ���¼
		clickElement("com.starunion.hefantv:id/iv_shoujilogin");
		
		//��д�ֻ��ź���֤��
		WebElement telNum = driver.findElement(By.id("com.starunion.hefantv:id/et_phoneNum_register"));
		telNum.sendKeys("13300002017");
		WebElement yzm = driver.findElement(By.id("com.starunion.hefantv:id/et_yanzhengma"));
		yzm.sendKeys("1234");
		
		//��¼
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
	
		//ֱ�����Ƿ���ڣ����ڽ��룬ͣ��1�����˳�
		while(driver.getPageSource().contains("com.starunion.hefantv:id/id_live_picture") && i<=50) {
			clickElement("com.starunion.hefantv:id/id_live_picture");
			Thread.sleep(1000);//��ֱ����ͣ��1��
			clickElement("com.starunion.hefantv:id/live_room_bottom_exit");
			System.out.println("ִ���˵�"+i+"�ν���ֱ����");
			i++;
			Thread.sleep(1000);
		}
		
		if(!driver.getPageSource().contains("com.starunion.hefantv:id/id_live_picture")){
			System.out.println("�˿�û��ֱ����");
		}
		// 3��3s���˳�ֱ����

		System.out.println("��������ҳ");

	}

	@AfterTest
	public void tearDown() throws Exception {
		System.out.println("end!!!!");
		// driver.removeApp("io.appium.android.ime");
//		driver.quit();
	}

}
