package task4;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.task4.pages.OsuLeaderboardPage;
import org.example.task4.pages.OsuPlayerPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.AbstractList;

public class OsuPlayerTest {
    // перейти на страницу лидерборда --
    // нажать на первого игрока  -- переход на страницу игрока
    // проверить что переход произведен на необходимого игрока

    public OsuLeaderboardPage leaderboardPage = new OsuLeaderboardPage();
    public OsuPlayerPage playerPage;

    @Step("Open leaderboard page")
    public void openLeaderboardPage() {
        leaderboardPage.openPage();
    }

    @Step("Get randomly chosen username")
    public String getRandomUsername(WebElement playerElement) {
        return leaderboardPage.getUsername(playerElement);
    }

    @Step("Go to person's personal page")
    public void goToPersonalPage(WebElement playerElement) {
        playerPage = leaderboardPage.clickOnPlayer(playerElement);
    }


    @Test
    public void test() {
        openLeaderboardPage();

        WebElement playerElement = leaderboardPage.pickRandomPlayer();

        String username = getRandomUsername(playerElement);

        goToPersonalPage(playerElement);
        Assertions.assertEquals(username.concat(
                " · player info | osu!"),
                playerPage.getTitle(),
                "Usernames in page title mismatch"
        );
        Assertions.assertEquals(username, playerPage.getUsername(), "Usernames mismatch");
    }
}
