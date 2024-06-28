package task3;

import io.qameta.allure.Step;
import org.example.task3.pages.LaptopInfo;
import org.example.task3.pages.YanderMarketCatalog;
import org.example.task3.pages.YandexMarketPage;
import org.example.task3.pages.YandexMarketSearched;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class YandexMarketPageTest {
    YandexMarketPage page = new YandexMarketPage();
    YandexMarketSearched searched = null;

    @Step("Open Yandex Market")
    private void openYandexMarket() {
        YandexMarketPage page = new YandexMarketPage();
        page.openPage();
        Assertions.assertEquals(
                "https://market.yandex.ru/",
                page.getUrl(),
                "The page url isn't yandex market"
        );
    }

    @Step("Navigate to Laptop page")
    private void navigateToLaptop() {
        YanderMarketCatalog catalog = page.openCatalog();
        catalog.openComputerAndLaptopsTab();

        searched = catalog.openLaptopPage();

        String expectedPage = "https://market.yandex.ru/catalog--noutbuki";
        Assertions.assertTrue(searched.getUrl().contains(expectedPage), "The page url isn't laptop catalog");
    }

    @Step("Log first 5 found items")
    private void logFirst5FoundItems() {
        searched.selectFirstSearches(5).forEach(System.out::println);
    }

    @Step("Show only items on resale")
    private void showOnlyItemsOnResale() {
        searched.clickShowOnlyUsed();
        List<LaptopInfo> list = searched.selectFirstSearches(10);
        list.forEach(d -> Assertions.assertTrue(d.getIsResale(), "Some of the items are not on a resale"));
    }

    @Test
    @DisplayName("Yandex market laptop test")
    public void test() {
        openYandexMarket();
        navigateToLaptop();
        logFirst5FoundItems();
        showOnlyItemsOnResale();
    }
}
