package ru.netology.domain;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestiBank {

    public String SetDate (int date){
        return LocalDate.now().plusDays(date).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private Faker faker;

    @BeforeEach
    void shouldOpenSetAll() {
        open("http://localhost:9999");
        faker = new Faker(new Locale("ru"));
    }

    @Test
    void shouldPreventSendRequestMultipleTimes() {
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
        String cardNumber = faker.finance().creditCard(CreditCardType.MASTERCARD);
        System.out.println(name);
        System.out.println(phone);
        System.out.println(cardNumber);
    }

//    @Test
//    void shouldTestValidation() {
//        $("[data-test-id=city] input").setValue("Белгород");
//        $("[data-test-id=date] input").setValue(SetDate(3));
//        $("[data-test-id=name] input").setValue("Пупкин Василий");
//        $("[data-test-id=phone] input").setValue("+79102280000");
//        $("[data-test-id=agreement]").click();
//        $$("button").get(1).click();
//        //$("[data-test-id='notification']").waitUntil(visible, 15000).shouldHave(text("Успешно!"));
//        //$("[data-test-id=notification]").shouldHave((visible),Duration.ofSeconds(15));
//        //$(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
//        $("[data-test-id=notification]").shouldBe((visible), Duration.ofSeconds(15));
//    }

//    @Test
//    void shouldTestWorkingDropDownMenuCity() {
//        $("[data-test-id=city] input").sendKeys("Бе", Keys.ARROW_DOWN, Keys.ENTER);
//        $("[data-test-id=date] input").setValue(SetDate(3));
//        $("[data-test-id=name] input").setValue("Пупкин Василий");
//        $("[data-test-id=phone] input").setValue("+79102280000");
//        $("[data-test-id=agreement]").click();
//        $$("button").get(1).click();
//        $("[data-test-id=notification]").shouldBe((visible), Duration.ofSeconds(15));
//    }
//    @Test
//    void shouldTestWorkingDropDownMenuDate() {
//        $("[data-test-id=city] input").sendKeys("Бе", Keys.ARROW_DOWN, Keys.ENTER);
//        $("[data-test-id=date] input").sendKeys(Keys.CONTROL,Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(SetDate(7));
//        $("[data-test-id=name] input").setValue("Пупкин Василий");
//        $("[data-test-id=phone] input").setValue("+79102280000");
//        $("[data-test-id=agreement]").click();
//        $$("button").get(1).click();
//    }


}
