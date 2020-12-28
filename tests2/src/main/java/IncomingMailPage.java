public class IncomingMailPage extends PageBase{
    public IncomingMailPage(Browser driver) {
        super(driver);
    }

    WebElementExtCollection UnreadMessagesSender = new WebElementExtCollection(_driver, "//a[contains(@class, 'is-unread')]//span[@class='mail-MessageSnippet-FromText']", false);
    WebElementExtCollection UnreadMessagesThemes = new WebElementExtCollection(_driver, "//a[contains(@class, 'is-unread')]//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']//span[@title]", false);
    WebElementExtCollection CheckStateToUnread = new WebElementExtCollection(_driver, "//span[contains(@class, 'state_toUnread')]", true);
    WebElementExt Account = new WebElementExt(_driver, false, "//div[contains(@class, 'legouser legouser')]");
    WebElementExt Exit = new WebElementExt(_driver, false, "//a[contains(@class, 'action_exit')]");
}
