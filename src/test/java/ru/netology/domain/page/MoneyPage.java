package ru.netology.domain.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.domain.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MoneyPage {
    private SelenideElement amount = $("[type='text']");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement buttonTransfer = $("[data-test-id='action-transfer']");
    private SelenideElement buttonCancel = $("[data-test-id='action-cancel']");

    public MoneyPage() {

    }

    public DashboardPage transferMoney(int amountTransfer, DataHelper.CardInfo cardInfo) {
        from.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.DELETE);
        amount.setValue(String.valueOf(amountTransfer));
        from.setValue(cardInfo.getNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

}
