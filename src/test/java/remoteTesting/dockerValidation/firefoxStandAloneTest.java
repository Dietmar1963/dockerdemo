package remoteTesting.dockerValidation;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class firefoxStandAloneTest {
    public static void main (String[] args) throws MalformedURLException {
        URL u = new URL("http://localhost:4444/wd/hub");
        FirefoxOptions cap = new FirefoxOptions();
        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        RemoteWebDriver driver = new RemoteWebDriver(u,cap);
        driver.get("https://google.com");
        System.out.println(driver.getTitle());
    }
}
