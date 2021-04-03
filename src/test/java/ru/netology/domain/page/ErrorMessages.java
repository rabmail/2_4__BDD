package ru.netology.domain.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class ErrorMessages {

    public static void errorMassageBalance (){
        $(withText("Недостаточно средств для проведения операции")).shouldBe(Condition.visible);
    }

    public static void errorMassageNegativeNumber(){
        $(withText("Введено недопустимое значение")).shouldBe(Condition.visible);
    }
}
