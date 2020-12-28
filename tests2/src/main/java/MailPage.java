public class MailPage extends PageBase{
    public MailPage(Browser driver) {
        super(driver);
    }

    WebElementExt Sender = new WebElementExt(_driver, false, "//span[contains(@class, 'Sender-Email')]");
    WebElementExt Theme = new WebElementExt(_driver, false, "//div[contains(@class, 'js-toolbar-subject')]");
    WebElementExt Text = new WebElementExt(_driver, false, "//div[contains(@class, 'message-body-content')]");
}
