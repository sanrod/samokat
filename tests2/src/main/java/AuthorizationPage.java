public class AuthorizationPage  extends PageBase{
    public AuthorizationPage(Browser driver) {
        super(driver);
    }

    WebElementExt Login = new WebElementExt(_driver, false, "//input[@name='login']");
    WebElementExt Pass = new WebElementExt(_driver, false, "//input[@name='passwd']");
    WebElementExt Enter = new WebElementExt(_driver, false, "//button[@type='submit']");
    WebElementExt NotNow = new WebElementExt(_driver, false, "//button[@data-t='button:pseudo']");
}
