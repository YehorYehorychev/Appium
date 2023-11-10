package steps;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class GoogleTest extends Base {

    @Given("open settings")
    public void open_settings() {

        Activity activity = new Activity("com.android.settings","com.android.settings.Settings");
        driver.startActivity(activity);

        String actual = driver.findElement(By.id("com.android.settings:id/homepage_title")).getText();

        Assert.assertEquals(actual, "Settings");
    }

    @And("click on {string}")
    public void clickOn(String locator) throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//*[@text='"+ locator +"']")).click();
        Thread.sleep(50);
    }

    @When("Turn on switch")
    public void turnOnSwitch() {
        WebElement el = driver.findElement(AppiumBy.xpath("//*[@class='android.widget.Switch']"));

        if (!el.getAttribute("checked").equals("true")) {
            el.click();
        }
    }

    @Then("Check switch is on")
    public void checkSwitchIsOn() {
        WebElement el = driver.findElement(AppiumBy.xpath("//*[@class='android.widget.Switch']"));
        Assert.assertEquals(el.getAttribute("checked"), "true");
    }

}