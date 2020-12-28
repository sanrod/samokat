public class YandexMainPage extends PageBase{
    public YandexMainPage(Browser driver) {
        super(driver);
        setUrl("http://yandex.ru");
    }

    public WebElementExt EnterInMail = new WebElementExt(_driver, false, "//a[contains(@class, 'login-enter')]");

}
