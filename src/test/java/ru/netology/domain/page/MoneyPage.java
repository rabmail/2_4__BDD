package ru.netology.domain.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
        amount.setValue(String.valueOf(amountTransfer));
        from.setValue(cardInfo.getNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public void errorMassage(){
        $(withText("Ошибка!!! Сумма перевода больше суммы баланса. " +
                "Введите сумму перевода меньшую либо равную сумме баланса")).shouldBe(Condition.visible);
    }
}
