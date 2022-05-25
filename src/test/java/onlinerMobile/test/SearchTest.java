package onlinerMobile.test;

import framework.BaseTest;
import onlinerMobile.pageObject.pages.CatalogPage;
import onlinerMobile.pageObject.pages.MainPage;
import onlinerMobile.pageObject.pages.TVPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BaseTest {

    @Parameters({"manufacturer", "priceTo", "diagonalFrom", "diagonalTo", "resolution"})
    @Test
    public void searchTVTest(String manufacturer, String priceTo, String diagonalFrom, String diagonalTo, String resolution) {
        SoftAssert softAssert = new SoftAssert();

        MainPage mainPage = new MainPage();
        mainPage.clickOnH2("Каталог");

        CatalogPage catalogPage = new CatalogPage("Каталог");
        catalogPage.navigateCatalog("Электроника");
        catalogPage.isCatalogList();
        catalogPage.navigateList("Телевидение и видео", "Телевизоры");

        TVPage tvPage = new TVPage("Телевизоры");
        tvPage.openFilter("Фильтры");
        tvPage.selectManufacturer(manufacturer);
        tvPage.selectDiagonal(diagonalFrom, diagonalTo);
        tvPage.selectResolution(resolution);
        tvPage.selectPrice(priceTo);
        tvPage.closeFilter();
        tvPage.validationManufacturer(manufacturer);
        tvPage.validationDiagonal(diagonalFrom, diagonalTo);
        tvPage.validationResolution(resolution);
        tvPage.validationPrice(priceTo);

        softAssert.assertAll();
    }
}
