package framework.elements;

import org.openqa.selenium.By;

public class ComboBox extends BaseElement {
    public ComboBox(By by) {
        super(by);
    }

    public ComboBox(By by, String name) {
        super(by, name);
    }

    protected String getElementType() {
        return getLoc("log.combo.box");
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
