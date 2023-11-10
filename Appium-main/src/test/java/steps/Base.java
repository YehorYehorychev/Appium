package steps;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;


public class Base {

    //setup system variables :
        // install nodejs + appium (npm install appium@next)
        //JAVA_HOME = C:\Program Files\Java\jdk20
        //MAVEN_HOME = C:\Program Files\apache-maven-3.9.2
        //ANDROID_HOME = C:\Users\viana\AppData\Local\Android\Sdk
        //ANDROID_SDK_ROOT = C:\Users\viana\AppData\Local\Android\Sdk
        //path -> C:\Users\viana\AppData\Local\Android\Sdk\emulator
        //path -> C:\Users\viana\AppData\Local\Android\Sdk\platform-tools
        //path -> C:\Program Files\apache-maven-3.9.2\bin
        //path -> C:\Program Files\Java\jdk-20\bin
        //path -> C:\Program Files\Java\jdk-20\bin
        //path -> C:\Program Files\nodejs\
    public static AndroidDriver driver;

    public void longPressOnElement(WebElement el){
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement)el).getId(),
                        "duration", 2000));
    }
    public void swipeLeft(){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", 100, "top", 100,
                "width", 200, "height", 200,
                "direction", "left",
                "percent", 0.75
        ));
    }

    public void swipeAction(WebElement ele, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of
                ("elementId", ((RemoteWebElement)ele).getId(),
                        "direction", direction,
                        "percent", 0.75
                ));
    }

}