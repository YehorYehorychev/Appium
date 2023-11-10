package steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Hooks extends Base {

	AppiumDriverLocalService appiumServer;
	final String projectDir = System.getProperty("user.dir");
	final String userHome = System.getProperty("user.home");
	final String systemName = System.getProperty("os.name");
	final String windowsNpmPath = "/AppData/Roaming/npm/node_modules/";
	final String macOsNpmPath = "/opt/homebrew/lib/node_modules/";
	final String appiumMainPath = "/appium/build/lib/main.js";
	@Before
	public void setUp() throws MalformedURLException {

		if(systemName.contains("Windows")){

			appiumServer = new AppiumServiceBuilder()
					.withAppiumJS(new File(userHome + windowsNpmPath + appiumMainPath))
					.withIPAddress("127.0.0.1").usingPort(4723).build();
		} else {
			appiumServer = new AppiumServiceBuilder()
					.withAppiumJS(new File(macOsNpmPath + appiumMainPath))
					.withIPAddress("127.0.0.1").usingPort(4723).build();
		}

		appiumServer.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setChromedriverExecutable(projectDir+ "/src/test/java/resources/chromedriver113.exe");
		options.setDeviceName("MyEmu");

//      driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
	}
	@After
	public void tearDown(){
		driver.quit();
		appiumServer.stop();
	}
}