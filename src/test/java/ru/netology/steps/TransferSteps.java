package ru.netology.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferSteps {
    static private DashboardPage dashboardPage;
    static LoginPage loginPage = new LoginPage();


    @Пусть("пользователь залогинен с именем «vasya» и паролем «qwerty123»")
    public void loginWithNameAndPassword() {
        System.out.println("loginWithNameAndPassword");
        // Write code here that turns the phrase above into concrete actions
        open("http://localhost:9999");
        System.out.println("opened");
        dashboardPage = loginPage
                .validLogin(DataHelper.getAuthInfo())
                .validVerify(DataHelper.getVerificationCodeFor(DataHelper.getAuthInfo()));

//        throw new io.cucumber.java.PendingException();
    }


    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void transferExistedMoneyAmountCardWithNumberToCardWithIndex(Integer amount, String cardNumber, Integer cardIndex) {
        System.out.println("transferExistedMoneyAmountCardWithNumberToCardWithIndex");
        // Write code here that turns the phrase above into concrete actions
        dashboardPage.DepositFromCardWithNumberToCardWithIndex(amount, cardNumber, cardIndex);
//        throw new io.cucumber.java.PendingException();
    }
    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void checkCardBalanceByCardIndex(Integer cardIndex, Integer balance) {
        System.out.println("checkCardBalanceByCardIndex");
        // Write code here that turns the phrase above into concrete actions
        assertEquals(balance, dashboardPage.getCardBalanceByIndex(cardIndex - 1));
//        throw new io.cucumber.java.PendingException();
    }

}
