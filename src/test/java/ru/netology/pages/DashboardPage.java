package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    private SelenideElement subHeading = $("[data-test-id=dashboard] + h1");

    private ElementsCollection cardInfoListItems = $$("ul div[data-test-id]");

    private ElementsCollection depositButtons = $$("ul div[data-test-id] button[data-test-id=action-deposit]");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
        subHeading.shouldHave(text("Ваши карты"));
    }

    public TransferPage depositToCard(int cardItemId) {
        depositButtons.get(cardItemId).click();
        return new TransferPage();
    }

    public int getCardBalanceByIndex(int index) {
        return extractBalance(cardInfoListItems.get(index).text());
    }
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}