import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Browser {
    private String _mainWindowHandler;
    private WebDriver _driver;

    public void startWebDriver() {
        _driver = startChrome();
        _driver.manage().deleteAllCookies();
        _driver.manage().timeouts().setScriptTimeout(Duration.ofMinutes(1));
        _driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
        _driver.manage().window().maximize();
        _mainWindowHandler = _driver.getWindowHandle();
    }

    private ChromeDriver startChrome() {
        System.setProperty("webdriver.chrome._driver", "distributions\\chromedriver.exe");
        Map<String, Object> prefs = new LinkedHashMap<>();
        prefs.put("download.default_directory", "downloads\\");
        prefs.put("safebrowsing.enabled", "false");
        prefs.put("acceptInsecureCerts", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-extensions");
        options.addArguments("ignore-certificate-errors", "ignore-urlfetcher-cert-requests", "allow-insecure-localhost");
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(capabilities);
    }

    public void switchToNewTab() {
        var handles = _driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(_mainWindowHandler)) {
                _driver.switchTo().window(handle);
                break;
            }
        }
    }

    public WebElement findElement(String xpath, int timeoutInSec) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(_driver)
                .withTimeout(Duration.ofSeconds(timeoutInSec))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        WebElement element = null;
        try {
            element = fluentWait.until(ExpectedConditions.elementToBeClickable(By.ByXPath.xpath(xpath)));
        } catch (Exception e) {
            return null;
        }

        return element;
    }

    public List<WebElement> findElements(String xpath)
    {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(_driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        var elements = fluentWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.ByXPath.xpath(xpath)));
        return elements;
    }

    public List<WebElement> findHiddenElements(String xpath)
    {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(_driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        var elements = fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.ByXPath.xpath(xpath)));
        return elements;
    }

    public void back()
    {
        _driver.navigate().back();
    }

    public String getUrl() {
        return _driver.getCurrentUrl();
    }

    public void refresh() {
        _driver.navigate().refresh();
    }

    public void goToUrl(String url) {
        _driver.navigate().to(url);
    }

    public void stopDriver() {
        _driver.quit();
    }

}
