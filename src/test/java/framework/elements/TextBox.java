package framework.elements;

import org.openqa.selenium.By;

public class TextBox extends BaseElement {

    public TextBox(By by) {
        super(by);
    }

    public TextBox(By by, String name) {
        super(by, name);
    }

    public String getElementType() {
        return getLoc("log.text.box");
    }

    @Override
    public String[] split(String s) {
        return new String[0];
    }

    @Override
    public int size() {
        return 0;
    }

    public void type(final String value) {
        waitForIsElementPresent();
        element.sendKeys(value);
    }
}