package steps;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class StoreTest extends Base {


    @Given("open store app")
    public static void open_store_app() throws InterruptedException {
        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
        driver.startActivity(activity);
        Thread.sleep(3000);
    }
    @Given("End to End app test")
    public void end_to_end_app_test() throws InterruptedException {

        Thread.sleep(3000);
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Aziz QA");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Angola\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Angola']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(1000);
        String products = "Products";
        String check = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
        Assert.assertEquals(check,products);
        Thread.sleep(3000);
    }


    @Given("find product and choose")
    public void find_product_and_choose() throws InterruptedException {

        Thread.sleep(3000);
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Fufel");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(2000);

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));

        int prodCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for (int i = 0; i < prodCount; i++){
            String prodName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(prodName.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        String actual = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();

        Assert.assertEquals(actual, "Jordan 6 Rings");
    }

    @Given("check toast message")
    public void check_toast_message() throws InterruptedException {

        Thread.sleep(3000);
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(2000);

        String actual = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");

        Assert.assertEquals(actual, "Please enter your name");
    }


    @Test
    public void verifyToastMess() throws InterruptedException {

        Thread.sleep(3000);
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Angola\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Angola']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMess = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMess, "Please enter your name");
    }

    @And("click on {string} button")
    public void click_on_button(String string) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
    }

    @And("switch driver to chrome")
    public void switchDriverToChrome() throws InterruptedException {
        Thread.sleep(5000);
        Set<String> contexts = driver.getContextHandles();
        for (String contextName: contexts){
            System.out.println(contextName);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");
    }

    @And("type {string} in search field")
    public void typeInSearchField(String arg0) throws InterruptedException {
        driver.findElement(By.name("q")).sendKeys(arg0);
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/in/azizdzhon-dzhamolov']")).click();
        Thread.sleep(2000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @And("switch driver to app")
    public void switchDriverToApp() {
        driver.context("NATIVE_APP");
    }
}