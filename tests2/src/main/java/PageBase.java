public abstract class PageBase {
    public Browser _driver;
    private String _url;
    public PageBase(Browser driver)
    {
        _driver = driver;
    }

    public void open()
    {
        _driver.goToUrl(_url);
    }

    public void setUrl(String url)
    {
        _url = url;
    }

    public String getUrl()
    {
        return _url;
    }

    public void Refresh()
    {
        _driver.refresh();
    }


}
