package steps;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiDemoTest extends Base{

    @Given("open api-demo app")
    public void open_api_demo_app() throws InterruptedException {
        Activity activity = new Activity("io.appium.android.apis","io.appium.android.apis.ApiDemos");
        driver.startActivity(activity);
        Thread.sleep(1000);
    }

    @Given("drag and drop")
    public void drag_and_drop() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement dragElement = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));

        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) dragElement).getId(),
                "endX", 650,
                "endY", 570
        ));
        Thread.sleep(3000);

//        variant 2:
//        WebElement dropElement = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));

//        int middleXCoordinate_dragElement =dragElement.getLocation().x + (dragElement.getSize().width/2);
//        int middleYCoordinate_dragElement =dragElement.getLocation().y + (dragElement.getSize().height/2);
//        int middleXCoordinate_dropElement =dropElement.getLocation().x + (dropElement.getSize().width/2);
//        int middleYCoordinate_dropElement =dropElement.getLocation().y + (dropElement.getSize().height/2);

//        TouchAction  action =new TouchAction(driver);
//        action.longPress(PointOption.point(middleXCoordinate_dragElement, middleYCoordinate_dragElement))
//                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
//                .moveTo(PointOption.point(middleXCoordinate_dropElement, middleYCoordinate_dropElement))
//                .release()
//                .perform();

        String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(result, "Dropped!");
    }

    @When("long press")
    public void long_press() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        WebElement el = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ExpandableListView/android.widget.TextView[1]"));

        longPressOnElement(el);

        Thread.sleep(2000);

        String menuText = driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(menuText, "Sample menu");
        Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());
    }

    @When("swipe back")
    public void swipe_back(){

        swipeLeft();

        Assert.assertEquals(driver.findElement(AppiumBy.accessibilityId("Animation")).getText(),"Animation");
    }

    @When("swipe left")
    public void swipe_left(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='1. Photos']")).click();
        WebElement firstImage = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"))
                .getAttribute("focusable"),"true");
        swipeAction(firstImage, "left");
        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"))
                .getAttribute("focusable"),"false");
    }

    @When("scroll down page")
    public void scroll_down_page() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
        Thread.sleep(2000);

//        boolean canScrollMore;
//        do{
//            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
//                "left", 100, "top", 100, "width", 200, "height", 200,
//                "direction", "down",
//                "percent", 3.0
//
//        ));
//        }while(canScrollMore);
//
//        Thread.sleep(2000);
    }

    @Given("open app by activity")
    public void open_app_by_activity(){

        // You can open any page off app by using activity and avoid many steps of navigation
        // https://support.testsigma.com/support/solutions/articles/32000019977-how-to-find-app-package-and-app-activity-of-your-android-app
        // adb shell dumpsys window | grep -E 'mCurrentFocus'

        Activity activity = new Activity("io.appium.android.apis","io.appium.android.apis.view.Animation2");
        driver.startActivity(activity);

        Assert.assertEquals(driver.findElement(AppiumBy.accessibilityId("Select an animation:")).getText(), "Select an animation:");
    }
}