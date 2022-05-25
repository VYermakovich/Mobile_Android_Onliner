package onlinerMobile.pageObject.pages;

import framework.elements.Button;
import framework.elements.Checkbox;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class TVPage extends BaseOnlinerPage {
    private static String pageLocator = "//h1[contains(@class,'schema-header__title')][contains(text(),'%s')]";
    private static String checkboxSelect = "//span[contains(@class,'schema-filter__checkbox-text')][contains(text(),'%s')]";
    private static String txtTitleCheck = "//div[@class='schema-product__title']//span[contains(text(),'%s')]";
    private static String filtersXpath = "//div[@id = 'schema-order']//div[contains(text(), '%s')]";

    private static TextBox txtDiagonalFrom = new TextBox(By.xpath("//div[contains(@class,'schema-filter__label')]/..//div[@class='schema-filter-control schema-filter-control_select']//select[contains(@data-bind,'value: facet.value.from')]"));
    private static TextBox txtDiagonalTo = new TextBox(By.xpath("//div[contains(@class,'schema-filter__label')]/..//div[@class='schema-filter-control schema-filter-control_select']//select[contains(@data-bind,'value: facet.value.to')]"));
    private static TextBox txtSelectPriceTo = new TextBox(By.xpath("//span[@class='schema-filter-control__shadow']/../..//input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price'][contains(@data-bind,'value: facet.value.to')]"));
    private static TextBox txtCheckPriceTo = new TextBox(By.xpath("//div[@class='schema-product__group']//div[contains(@class,'schema-product__price')]//span[contains(@data-bind,'format.minPrice')]"));
    private static TextBox txtProductBlock = new TextBox(By.xpath("//div[@id='schema-products']//div[contains(@class,'schema-product__group')]"));
    private static TextBox txtDescription = new TextBox(By.xpath("//div[@id='schema-products']//div[contains(@class,'schema-product__group')]//div[@class='schema-product__description']"));
    private static Button btnCloseFilter = new Button(By.xpath("//div[@class='schema-grid__toggle js-schema-aside-close']"));
    SoftAssert softAssert;

    public TVPage(String pageTitle) {
        super(By.xpath(String.format(pageLocator, pageTitle)), "TV page");
    }


    public void openFilter(String filtersName) {
        Label filters = new Label(By.xpath(String.format(filtersXpath, filtersName)));
        filters.click();
    }

    public void closeFilter() {
        btnCloseFilter.moveAndClickByAction();
    }

    public void selectManufacturer(String manufacturer) {
        Checkbox checkboxManufactorer = new Checkbox(By.xpath(String.format(checkboxSelect, manufacturer)));
        checkboxManufactorer.moveAndClickByAction();
        checkboxManufactorer.isSelected();
    }

    public void selectResolution(String resolution) {
        Checkbox selectResolution = new Checkbox(By.xpath(String.format(checkboxSelect, resolution)));
        selectResolution.moveAndClickByAction();
    }

    public void selectDiagonal(String from, String to) {
        txtDiagonalFrom.moveToElement();
        txtDiagonalFrom.sendKeys(from);
        txtDiagonalTo.sendKeys(to);
    }

    public void selectPrice(String price) {
        txtSelectPriceTo.moveToElement();
        txtSelectPriceTo.sendKeys(price);
    }

    public void validationManufacturer(String title) {
        String[] manufacturer;
        TextBox value = new TextBox(By.xpath(String.format(txtTitleCheck, title)));
        value.getText();
        manufacturer = value.split(" ");
        for (int i = 0; i < manufacturer.length; i++) {
            if (manufacturer[i].equals(title)) {
                softAssert.assertTrue(true);
            }
        }
    }

    public void validationResolution(String resolution) {
        String[] description;
        txtDescription.getText();
        description = txtDescription.split(" ");
        for (int i = 0; i < description.length; i++) {
            if (description[i].equals(resolution)) {
                softAssert.assertTrue(true);
            }
        }
    }

    public void validationDiagonal(String diagonalFrom, String diagonalTo) {
        txtProductBlock.waitForElementsArePresent();
        txtDescription.getText();
        for (int i = 1; i <= txtProductBlock.size(); i++) {
            String[] description;
            description = txtDescription.split("\"");
            int intDiagonalFrom = Integer.parseInt(diagonalFrom);
            int intDiagonalTo = Integer.parseInt(diagonalTo);
            for (int j = 0; j < description.length; j++) {
                double convert = Double.parseDouble(description[0]);
                if (convert >= intDiagonalFrom && convert <= intDiagonalTo) {
                    softAssert.assertTrue(true);
                }
            }
        }
    }

    public void validationPrice(String priceTo) {
        txtProductBlock.waitForElementsArePresent();
        txtCheckPriceTo.getText();
        for (int i = 1; i <= txtProductBlock.size(); i++) {
            String[] price;
            price = txtCheckPriceTo.split(",");
            for (int j = 0; j < price.length; j++) {
                double convert = Double.parseDouble(price[0]);
                int intPriceTo = Integer.parseInt(priceTo);
                if (convert < intPriceTo) ;
                softAssert.assertTrue(true);
            }
        }
    }
}
