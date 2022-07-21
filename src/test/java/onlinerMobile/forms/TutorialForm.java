package onlinerMobile.forms;

import framework.BaseForm;
import framework.elements.Label;
import org.openqa.selenium.By;

public class TutorialForm extends BaseForm {
    private static final String formLocator = "by.onliner.catalog:id/nextContainer";
    private static final Label lblNextButton = new Label(By.id("by.onliner.catalog:id/nextContainer"));

    public TutorialForm() {
        super(By.id(formLocator),"TUTORIAL FORM");
    }

    public void clickOnNextButton(){
        lblNextButton.click();
    }
}
