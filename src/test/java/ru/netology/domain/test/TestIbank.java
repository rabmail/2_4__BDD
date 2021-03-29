package ru.netology.domain.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
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
    }
    @Test
    void shouldTransferAmountGreaterThanAccountBalance(){

    }

}
