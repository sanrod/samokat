import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebElementExtCollection{
    private final Browser _driver;
    private final String _xpath;
    private final boolean _isHidden;

    public WebElementExtCollection(Browser driver, String xpath, boolean isHidden)
    {
        _driver = driver;
        _xpath = xpath;
        _isHidden = isHidden;
    }

    private List<WebElement> _baseCollection;

    private void checkCondition()
    {
        int _timeoutInSec = 10;
        if (_isHidden)
        {
            _baseCollection = _driver.findHiddenElements(_xpath, _timeoutInSec);
            return;
        }
        _baseCollection = _driver.findElements(_xpath, _timeoutInSec);
    }

    public List<WebElementExt> convertToListCollection()
    {
        checkCondition();
        List<WebElementExt> newCollection = new ArrayList<>();
        for (WebElement element: _baseCollection)
        {
            WebElementExt newElement = new WebElementExt(_driver, false, _xpath);
            newElement.setElement(element);
            newCollection.add(newElement);
        }
        return newCollection;
    }
}
