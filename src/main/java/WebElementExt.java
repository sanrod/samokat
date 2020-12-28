import org.openqa.selenium.WebElement;

public class WebElementExt {
    private final Browser _driver;
    private final Boolean _isHidden;
    private final String _xpath;
    private WebElement _element;
    private boolean _reloadObject = true;
    private final  int _timeoutInSec = 10;

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
        return _element != null;
    }

    public void click() {
        CheckCondition(_timeoutInSec);
        checkNull();
        _element.click();
    }
    private void checkNull()
    {
        if(_element == null)
        {
            try {
                throw new Exception(String.format("Element with xpath = (%s) is not found", _xpath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getText() {
        CheckCondition(_timeoutInSec);
        checkNull();
        return _element.getText();
    }

    public void sendKeys(String text) {
        CheckCondition(_timeoutInSec);
        checkNull();
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
