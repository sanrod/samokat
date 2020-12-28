import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PostTests extends SuiteBase{
    @Test
    public void CheckingEmail()
    {
        var testData = getTestData();
        Assertions.assertNotNull(testData);

        login(testData.UserEmail, testData.UserPass);
        findMessage(testData);

        Assertions.assertEquals(MailPage.Sender.getText(), testData.SenderEmail, "Sender email is not equal");
        Assertions.assertTrue(MailPage.Text.getText().contains(testData.TextForCheck), "Message text not contain sequence");
        Assertions.assertEquals(MailPage.Theme.getText(), testData.SenderTheme, "Sender theme is not equals");

        Driver.back();
        makeUnreadAllMessages();
        IncomingMailPage.Account.click();
        IncomingMailPage.Exit.click();
    }

    private TestData getTestData()
    {
        try
        {

            var in = new InputStreamReader(new FileInputStream("TestData.json"), StandardCharsets.UTF_8);
            var jsonObject =  (JSONObject) new JSONParser().parse(in);

            var testData = new TestData();
            testData.UserEmail = (String) jsonObject.get("UserEmail");
            testData.UserPass = (String) jsonObject.get("UserPass");
            testData.SenderName = (String) jsonObject.get("SenderName");
            testData.SenderTheme = (String) jsonObject.get("SenderTheme");
            testData.SenderEmail = (String) jsonObject.get("SenderEmail");
            testData.TextForCheck = (String) jsonObject.get("MessageData");

            return testData;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private void login(String name, String pass)
    {
        YandexMainPage.open();
        YandexMainPage.EnterInMail.click();
        Driver.switchToNewTab();
        AuthorizationPage.Login.sendKeys(name);
        AuthorizationPage.Enter.click();
        AuthorizationPage.Pass.sendKeys(pass);
        AuthorizationPage.Enter.click();
        Assertions.assertTrue(AuthorizationPage.LoginSuccess.exists(5), "login is failed");
        if (AuthorizationPage.NotNow.exists(5))
        {
            AuthorizationPage.NotNow.click();
        }
    }

    private void findMessage(TestData testData)
    {
        var senders = IncomingMailPage.UnreadMessagesSender.convertToListCollection();
        var themes = IncomingMailPage.UnreadMessagesThemes.convertToListCollection();
        for (int i = 0; i< (long) senders.size(); i++)
        {
            var text1 = senders.get(i).getText();
            var text2 = themes.get(i).getText();
            if (text1.equals(testData.SenderName) && text2.equals(testData.SenderTheme))
            {
                senders.get(i).click();
            }
        }
    }
    
    private void makeUnreadAllMessages()
    {
        var checkers = IncomingMailPage.CheckStateToUnread.convertToListCollection();
        for (WebElementExt checker: checkers)
        {
            checker.click();
        }
    }
}
