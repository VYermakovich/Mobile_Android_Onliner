package onlinerMobile.tests;

import framework.BaseTest;
import onlinerMobile.forms.TutorialForm;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchTest(){
        System.out.println("Hi Android!");
        TutorialForm tutorialForm = new TutorialForm();
        tutorialForm.clickOnNextButton();
        tutorialForm.clickOnNextButton();
        tutorialForm.clickOnNextButton();
        tutorialForm.clickOnNextButton();
        tutorialForm.clickOnNextButton();
    }

}
