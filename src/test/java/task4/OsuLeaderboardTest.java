package task4;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.task4.Record;
import org.example.task4.pages.OsuLeaderboardPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OsuLeaderboardTest {
    // перейти на сайт лидерборда -- открывается сайт лидедорда
    // проверить что на странице 50 записей
    // проверить что у каждого игрока меньше pp

    public OsuLeaderboardPage page = new OsuLeaderboardPage();

    @Step("Open leaderboard page")
    public void openLeaderboardPage() {
        page.openPage();
    }

    @Step("Get records from leaderboard")
    public List<Record> getRecordsFromLeaderboard() {
        return page.getRecords();
    }

    @Test
    public void test() {
        openLeaderboardPage();
        Assertions
                .assertEquals(
                        "performance · rankings | osu!",
                        page.getTitle(),
                        "expected performance · rankings | osu!"
                );

        List<Record> records = getRecordsFromLeaderboard();
        Assertions.assertEquals(50, records.size(), "There are more or less than 50 records on page");

        Allure.step("Check leaderboard sort");
        int prev = records.getFirst().getPerformanceScore();
        for (Record record : records) {
            Assertions.assertFalse(prev < record.getPerformanceScore(), "leaderboard isn't sorted");
        }
    }
}
