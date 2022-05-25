package framework.elements;

import org.openqa.selenium.By;

public class Checkbox extends BaseElement {

    public Checkbox(By by) {
        super(by);
    }

    public Checkbox(By by, String name) {
        super(by, name);
    }

    public String getElementType() {
        return getLoc("log.check.box");
    }


    public String[] split(String value) {
        return new String[0];
    }

    @Override
    public int size() {
        return 0;
    }
}