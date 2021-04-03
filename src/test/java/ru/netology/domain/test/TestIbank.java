package ru.netology.domain.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.page.ErrorMessages;
import ru.netology.domain.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIbank {

    @Test
    void shouldTransferMoneyFromOneToTwo() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceOneBeforeTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoBeforeTransfer = dashboardPage.getTwoCardBalance();
        val moneyPage = dashboardPage.twoBill();
        int amount = 5000;
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardOne());
        val balanceOneAfterTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoAfterTransfer = dashboardPage.getTwoCardBalance();
        assertEquals((balanceOneBeforeTransfer - amount), balanceOneAfterTransfer);
        assertEquals((balanceTwoBeforeTransfer + amount), balanceTwoAfterTransfer);
        dashboardPage.oneBill();
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardTwo());

    }

    @Test
    void shouldTransferMoneyFromTwoToOne() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceOneBeforeTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoBeforeTransfer = dashboardPage.getTwoCardBalance();
        val moneyPage = dashboardPage.oneBill();
        int amount = 5000;
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardTwo());
        val balanceOneAfterTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoAfterTransfer = dashboardPage.getTwoCardBalance();
        assertEquals((balanceOneBeforeTransfer + amount), balanceOneAfterTransfer);
        assertEquals((balanceTwoBeforeTransfer - amount), balanceTwoAfterTransfer);
        dashboardPage.twoBill();
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardOne());

    }
    @Test
    void shouldTransferMoneyAmountGreaterAccountBalance() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceOneBeforeTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoBeforeTransfer = dashboardPage.getTwoCardBalance();
        val moneyPage = dashboardPage.oneBill();
        int amount = 20000;
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardTwo());
        val balanceOneAfterTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoAfterTransfer = dashboardPage.getTwoCardBalance();
        assertEquals((balanceOneBeforeTransfer + amount), balanceOneAfterTransfer);
        assertEquals((balanceTwoBeforeTransfer - amount), balanceTwoAfterTransfer);
        dashboardPage.twoBill();
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardOne());
      }

    @Test
    void shouldTransferNegativeAmount() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceOneBeforeTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoBeforeTransfer = dashboardPage.getTwoCardBalance();
        val moneyPage = dashboardPage.oneBill();
        int amount = -1000;
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardTwo());
        val balanceOneAfterTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoAfterTransfer = dashboardPage.getTwoCardBalance();
        assertEquals((balanceOneBeforeTransfer - amount), balanceOneAfterTransfer);
        assertEquals((balanceTwoBeforeTransfer + amount), balanceTwoAfterTransfer);
        dashboardPage.twoBill();
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardOne());
    }
    @Test
    void shouldTransferNegativeAmountError() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceOneBeforeTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoBeforeTransfer = dashboardPage.getTwoCardBalance();
        val moneyPage = dashboardPage.oneBill();
        int amount = -1000;
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardTwo());
        ErrorMessages.errorMassageNegativeNumber();
    }

    @Test
    void shouldTransferMoneyAmountGreaterAccountBalanceError() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceOneBeforeTransfer = dashboardPage.getOneCardBalance();
        val balanceTwoBeforeTransfer = dashboardPage.getTwoCardBalance();
        val moneyPage = dashboardPage.oneBill();
        int amount = 20000;
        moneyPage.transferMoney(amount, DataHelper.CardInfo.getCardTwo());
        ErrorMessages.errorMassageBalance();
    }

}
