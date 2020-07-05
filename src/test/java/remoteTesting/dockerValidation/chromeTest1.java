package remoteTesting.dockerValidation;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;



public class chromeTest1 {
    @Test


    public void test1() throws MalformedURLException {
        URL u = new URL("http://localhost:4444/wd/hub");
        ChromeOptions cap = new ChromeOptions();
        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        RemoteWebDriver driver = new RemoteWebDriver(u,cap);
        driver.get("https://www.facebook.com/");
        System.out.println(driver.getTitle());
    }
}
