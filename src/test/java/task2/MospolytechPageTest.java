package task2;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.example.reusable.Utils;
import org.example.task2.pages.MospolytechPage;
import org.example.task2.pages.MospolytechSchedulePage;
import org.example.task2.pages.SchedulePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MospolytechPageTest {
    MospolytechPage mainPage = new MospolytechPage();
    MospolytechSchedulePage secondaryMainPage;
    SchedulePage schedulePage;

    @Step("Open page")
    public void openPage() {
        mainPage.openPage();
    }

    @Step("Click on schedule button")
    public void clickOnScheduleButton() {
        secondaryMainPage = mainPage.clickScheduleButton();
    }

    @Step("Open schedule page")
    public void openSchedulePage() {
        schedulePage = secondaryMainPage.clickOpenSchedulePage();
        Assertions.assertTrue(
                schedulePage.switchToThisPage(),
                "The page didn't get loaded into the new tab"
        );
    }

    @Step("Insert group number")
    public void insertGroupNumber() {
        schedulePage.inputGroupNumber("23А-221");
        Assertions.assertEquals(
                1,
                schedulePage.getAmountOfFoundGroups(),
                "Expected: 1 group found. Actual: found more than 1 group"
        );
    }

    @Step("Click on found group")
    public void clickOnFoundGroup() {
        schedulePage.clickSearchByGroupNumber("23А-221");

        Assertions.assertEquals(
                Utils.getCurrentDay(),
                schedulePage.getHighlightedDay(),
                "Wrong day got highlighted"
        );

        Assertions.assertNotEquals(
                "rgba(0, 0, 0, 0)",
                schedulePage.getDayHighlight(Utils.getCurrentDay()),
                "Current day doesn't have highlight"
        );
    }

    @Test
    @DisplayName("Mospolytech test")
    @Description("Testing Mospolytech page")
    public void test() {
        openPage();
        clickOnScheduleButton();
        openSchedulePage();
        insertGroupNumber();
        clickOnFoundGroup();
    }

}
