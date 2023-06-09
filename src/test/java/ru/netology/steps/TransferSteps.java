package ru.netology.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferSteps {
    static private DashboardPage dashboardPage;
    static private LoginPage loginPage;

    static private TransferPage transferPage;


    @Пусть("пользователь залогинен с именем «vasya» и паролем «qwerty123»")
    public void loginWithNameAndPassword() {
        // Write code here that turns the phrase above into concrete actions
        open("http://localhost:9999");
        loginPage = new LoginPage();
        dashboardPage = loginPage
                .validLogin(DataHelper.getAuthInfo())
                .validVerify(DataHelper.getVerificationCodeFor(DataHelper.getAuthInfo()));
    }


    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void transferExistedMoneyAmountCardWithNumberToCardWithIndex(Integer amount, String cardNumber, Integer cardIndex) {
        // Write code here that turns the phrase above into concrete actions
        transferPage = dashboardPage.depositToCard(cardIndex - 1);
        dashboardPage = transferPage.depositMoneyFromCard(amount, cardNumber);
//        dashboardPage.depositFromCardWithNumberToCardWithIndex(amount, cardNumber, cardIndex);
    }
    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void checkCardBalanceByCardIndex(Integer cardIndex, Integer balance) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(balance, dashboardPage.getCardBalanceByIndex(cardIndex - 1));
    }

}
