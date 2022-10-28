package com.qaprosoft.carina.demo.gui.youtube_compents;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TralierAdvert extends AbstractUIObject{
    @FindBy(xpath = "//div[contains(@class, 'yt-spec-button-shape-next yt-spec-button-shape-next--tonal yt-spec-button-shape-next--overlay-dark yt-spec-button-shape-next--size-m yt-spec-button-shape-next--icon-button')]//a")
    private ExtendedWebElement bannerAdCloser;
    private ExtendedWebElement trailerAdCloser;

    public TralierAdvert(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public TralierAdvert(WebDriver driver) {
        super(driver);
    }

    public void closeAdIfPresent() {
        trailerAdCloser.clickIfPresent();
    }
}





