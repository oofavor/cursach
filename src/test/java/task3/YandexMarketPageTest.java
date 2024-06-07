package task3;

import org.example.task3.pages.YanderMarketCatalog;
import org.example.task3.pages.YandexMarketPage;
import org.example.task3.pages.YandexMarketSearched;
import org.junit.jupiter.api.Test;

public class YandexMarketPageTest {
    @Test
    public void test() {
        YandexMarketPage page = new YandexMarketPage();
        page.openPage();

        YanderMarketCatalog catalog = page.openCatalog();
        catalog.openComputerAndLaptopsTab();

        YandexMarketSearched searched = catalog.openLaptopPage();
        searched.selectFirstSearches(5);
        searched.clickShowOnlyUsed();
        searched.selectFirstSearches(10);
    }
}
