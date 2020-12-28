import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;

public class PostTests extends SuiteBase{
    @Test
    public void CheckingEmail()
    {
        TestData testData = getTestData();
        Assertions.assertNotNull(testData);

        login(testData.UserEmail, testData.UserPass);
        findMessage(testData);

        Assertions.assertEquals(MailPage.Sender.getText(), testData.SenderEmail);
        Assertions.assertTrue(MailPage.Text.getText().contains(testData.TextForCheck));
        Assertions.assertEquals(MailPage.Theme.getText(), testData.TextForCheck);

        Driver.back();
        makeUnreadAllMessages();
        IncomingMailPage.Account.click();
        IncomingMailPage.Exit.click();
    }

    private TestData getTestData()
    {
        try
        {
            TestData testData = new TestData();
            FileReader reader = new FileReader("TestData.json");


            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject =  (JSONObject) jsonParser.parse(reader);

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
        if (AuthorizationPage.NotNow.exists(5))
        {
            AuthorizationPage.NotNow.click();
        }
    }

    private void findMessage(TestData testData)
    {
        var senders = IncomingMailPage.UnreadMessagesSender.convertToListCollection();
        var themes = IncomingMailPage.UnreadMessagesThemes.convertToListCollection();
        for (int i=0; i<senders.stream().count(); i++)
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
