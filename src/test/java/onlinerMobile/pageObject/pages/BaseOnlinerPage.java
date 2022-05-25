package onlinerMobile.pageObject.pages;

import framework.BasePage;
import lombok.Getter;
import onlinerMobile.pageObject.menu.MainMenu;
import org.openqa.selenium.By;

@Getter
public class BaseOnlinerPage extends BasePage {
    private final MainMenu mainMenu = new MainMenu();

    public BaseOnlinerPage(By locator, String pageTitle) {
        super(locator, pageTitle);
    }
}
