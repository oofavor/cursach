package task1;

import org.example.task1.pages.LambdaTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LambdaTestTest {
    private final LambdaTest page = new LambdaTest();

    @BeforeEach
    public void openPage() {
        page.openPage();
    }

    @Test
    public void test() {
        List<String> headers = page.getHeaders();

        assertTrue(headers
                    .contains("LambdaTest Sample App"),
            "LambdaTest Sample App doesn't exists in the header elements"
        );

        assertTrue(page
                    .getText()
                    .contains("5 of 5 remaining"),
            "5 of 5 remaining doesn't exists on the page"
        );

        for (int i = 1; i < 6; i++) {
            String got = page.getTaskElementClass(i);
            assertEquals("done-false", got, "Some items happened to be checked");

            int amountBefore = page.getAmountOfUncheckedTaskElements();

            got = page.selectTaskElement(i).getTaskElementClass(i);

            assertEquals("done-true", got, "Some items can't be checked");

            int amountAfter = page.getAmountOfUncheckedTaskElements();

            assertTrue(amountBefore > amountAfter, "Amount of items doesnt decrease after select");
        }

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


        amountBefore = page.getAmountOfUncheckedTaskElements();
        String insertedTask = page.selectTaskElement(6).getTaskElementClass(6);
        assertEquals("done-true", insertedTask,"New task can't be selected");

        amountAfter = page.getAmountOfUncheckedTaskElements();
        assertTrue(amountBefore > amountAfter, "Selecting new task didn't decrease unchecked amount");
    }
}
