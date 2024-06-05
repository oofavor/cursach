package task2;

import org.example.reusable.Utils;
import org.example.task2.pages.MospolytechPage;
import org.example.task2.pages.MospolytechSchedulePage;
import org.example.task2.pages.SchedulePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MospolytechPageTest {

    @Test
    public void test() {
        MospolytechPage mainPage = new MospolytechPage();
        mainPage.openPage();

        MospolytechSchedulePage secondaryMainPage = mainPage.clickScheduleButton();

        SchedulePage schedulePage = secondaryMainPage.clickOpenSchedulePage();

        Assertions.assertTrue(
                schedulePage.switchToThisPage(),
                "The page didn't get loaded into the new tab"
        );
        schedulePage.inputGroupNumber("221-361");
        Assertions.assertEquals(
                1,
                schedulePage.getAmountOfFoundGroups(),
                "Expected: 1 group found. Actual: found more than 1 group"
        );
        schedulePage.clickSearchByGroupNumber("221-361");

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
}
