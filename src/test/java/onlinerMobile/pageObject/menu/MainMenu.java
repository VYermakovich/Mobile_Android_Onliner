package onlinerMobile.pageObject.menu;

import framework.elements.Button;
import framework.elements.Label;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class MainMenu {
    private static String subsection = "//span[text()='%s']//ancestor::a[@href]";
    private static Label lblSubMenuSections = new Label(By.xpath("//a[@class='header-style__bubble header-style__bubble_primary']"));
    private static Button btmBurgerMenu = new Button(By.xpath("//a[@class='header-style__underlay']"));

    @Step("Open Main Menu")
    public void burgerMenuClick() {
        btmBurgerMenu.moveAndClickByAction();
    }

    @Step("Click on Main menu Section - " + "{subsectionName}")
    public void navigateMainMenuSection(String subsectionName) {
//        Label lblSubMenu = new Label(By.xpath(String.format(subsection,subsectionName)));
//        lblSubMenu.clickAndWait();
        lblSubMenuSections.click();
    }
}