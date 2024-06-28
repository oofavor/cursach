package task4;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.task4.Beatmap;
import org.example.task4.pages.OsuBeatmapCatalogPage;
import org.example.task4.pages.OsuPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OsuBeatmapCatalogTest {
    // 1) перейти на сайт осу ---- заголовок welcome | osu!
    // 2) нажать на кнопку beatmaps ---- переход на страницу каталога
    // 3) вывести в лог первые 6 песен ---- у каждой битмапы есть статус, название, автор песни, автор битмапа
    //

    public OsuPage mainPage = new OsuPage();
    public OsuBeatmapCatalogPage catalogPage;

    @Step("Go to osu website")
    public void goToOsuWebsite() {
        mainPage.openPage();
    }

    @Step("Go to catalog page")
    public void goToCatalogPage() throws InterruptedException {
        catalogPage = mainPage.openBeatmapCatalog();
    }

    @Step("Log first 6 beatmaps from catalog")
    public List<Beatmap> logFirst6BeatmapsFromCatalog() {
        return catalogPage.getBeatmaps(6);
    }

    @Test
    public void test() throws InterruptedException {
        goToOsuWebsite();
        Assertions.assertEquals("welcome | osu!", mainPage.getTitle(),"the page title doesn't match");

        goToCatalogPage();
        Assertions
                .assertEquals(
                        "beatmap listing | osu!",
                        catalogPage.getTitle(),
                        "the page title doesn't match"
                );

        Allure.step("Check beatmaps have all the information");
        List<Beatmap> list = logFirst6BeatmapsFromCatalog();

        for (Beatmap beatmap : list) {
            Assertions.assertNotEquals("", beatmap.getAuthor());
            Assertions.assertNotEquals("", beatmap.getMapper());
            Assertions.assertNotEquals("", beatmap.getName());
            Assertions.assertNotEquals("", beatmap.getStatus());
        };
    }
}
