package onlinerMobile.pageObject.pages;

import framework.elements.Label;
import org.openqa.selenium.By;

public class MainPage extends BaseOnlinerPage {
    private static String pageLocator = "//div[@class='g-top-i']//img[@class='onliner_logo']";
    private static String h2Title= "//h2//a[contains(text(),'%s')]";

    public MainPage() {
        super(By.xpath(pageLocator), "Main page");
    }

    public void clickOnH2(String h2){
        Label lblH2 = new Label(By.xpath(String.format(h2Title,h2)));
        lblH2.clickAndWait();
    }
}