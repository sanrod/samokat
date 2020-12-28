import org.openqa.selenium.WebElement;

public class WebElementExt {
    private final Browser _driver;
    private final Boolean _isHidden;
    private final String _xpath;
    private WebElement _element;
    private boolean _reloadObject = true;

    public WebElementExt(Browser driver, Boolean isHidden, String xpath) {
        _driver = driver;
        _isHidden = isHidden;
        _xpath = xpath;
    }

    public void setElement(WebElement element)
    {
        _element = element;
        _reloadObject = false;
    }
    public Boolean exists(int timeoutInSec)
    {
        CheckCondition(timeoutInSec);
        if (_element != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void click() {
        CheckCondition(30);
        _element.click();
    }

    public String getText() {
        CheckCondition(30);
        return _element.getText();
    }

    public void sendKeys(String text) {
        CheckCondition(30);
        _element.sendKeys(text);
    }

    private void CheckCondition(int timeoutInSec) {
        if (!_reloadObject)
        {
            return;
        }
        if (!_isHidden) {
            _element = _driver.findElement(_xpath, timeoutInSec);
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
