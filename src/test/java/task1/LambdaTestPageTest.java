package task1;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.example.task1.pages.LambdaTestPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LambdaTestPageTest {
    private final LambdaTestPage page = new LambdaTestPage();

    @Step("Go to page")
    public void openPage() {
        page.openPage();
        List<String> headers = page.getHeaders();

        assertTrue(headers
                        .contains("LambdaTest Sample App"),
                "LambdaTest Sample App doesn't exists in the header elements"
        );
    }

    @Step("Amount element exists")
    private void amountElementExist() {
        assertTrue(page
                        .getText()
                        .contains("5 of 5 remaining"),
                "5 of 5 remaining doesn't exists on the page"
        );
    }

    @Step("TODO List check")
    private void emptyPage() {
        Allure.step("By default all items are checked");
        Allure.step("Items can be checked");
        for (int i = 1; i < 6; i++) {
            String got = page.getTaskElementClass(i);
            assertEquals("done-false", got, "Some items happened to be checked");

            int amountBefore = page.getAmountOfUncheckedTaskElements();

            got = page.selectTaskElement(i).getTaskElementClass(i);

            assertEquals("done-true", got, "Some items can't be checked");

            int amountAfter = page.getAmountOfUncheckedTaskElements();

            assertTrue(amountBefore > amountAfter, "Amount of items doesnt decrease after select");
        }
    }

    @Step("Add new task")
    private void addNewTask() {
        int amountBefore = page.getAmountOfUncheckedTaskElements();
        int totalAmountBefore = page.getTotalAmountOfTaskElements();

        String newTask = page.insertTaskElement("New task").getTaskElementClass(6);
        assertEquals("done-false",newTask, "New task can't be inserted");

        int amountAfter = page.getAmountOfUncheckedTaskElements();
        int totalAmountAfter = page.getTotalAmountOfTaskElements();

        assertTrue(
                amountBefore < amountAfter,
                "Inserting new task didn't increase unchecked amount"
        );
        assertTrue(
                totalAmountBefore < totalAmountAfter,
                "Inserting new task didn't increase total amount"
        );
    }

    @Step("Click new task")
    private void clickNewTask() {
        int amountBefore = page.getAmountOfUncheckedTaskElements();
        String insertedTask = page.selectTaskElement(6).getTaskElementClass(6);
        assertEquals("done-true", insertedTask,"New task can't be selected");

        int amountAfter = page.getAmountOfUncheckedTaskElements();
        assertTrue(amountBefore > amountAfter, "Selecting new task didn't decrease unchecked amount");

    }

    @Test
    @DisplayName("LambdaPageTest")
    @Description("Testing LambdaTest page")
    public void test() {
        openPage();
        amountElementExist();
        emptyPage();
        addNewTask();
        clickNewTask();
    }
}
