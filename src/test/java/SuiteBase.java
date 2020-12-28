import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class SuiteBase {

    public Browser Driver;
    public YandexMainPage YandexMainPage;
    public AuthorizationPage AuthorizationPage;
    public IncomingMailPage IncomingMailPage;
    public MailPage MailPage;

    @BeforeEach
    public void testInitialize()
    {
        Driver = new Browser();
        Driver.startWebDriver();
        YandexMainPage = new YandexMainPage(Driver);
        AuthorizationPage = new AuthorizationPage(Driver);
        IncomingMailPage = new IncomingMailPage(Driver);
        MailPage = new MailPage(Driver);
    }

    @AfterEach
    public void testTearDown()
    {
        Driver.stopDriver();
    }
}
