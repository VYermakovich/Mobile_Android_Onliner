package framework.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement {


    public Button(By by) {
        super(by);
    }

    public Button(By by, String name) {
        super(by, name);
    }

    protected String getElementType() {
        return getLoc("log.button");
    }

    @Override
    public String[] split(String s) {
        return new String[0];
    }

    @Override
    public int size() {
        return 0;
    }
}