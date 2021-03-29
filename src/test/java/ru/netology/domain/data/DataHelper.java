package ru.netology.domain.data;

import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import ru.netology.domain.page.DashboardPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;

    }

    public static AuthInfo getAuthInfo() {

        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;

    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo)
    {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String number;

        public static CardInfo getCardOne() {
            return new CardInfo("5559 0000 0000 0001");
        }

        public static CardInfo getCardTwo() {
            return new CardInfo("5559 0000 0000 0002");
        }
    }

    @Value
    public static class VerificationPage {
        private SelenideElement codeField = $("[data-test-id=code] input");
        private SelenideElement verifyButton = $("[data-test-id=action-verify]");

        public VerificationPage() {
            codeField.shouldBe(visible);
        }

        public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
            codeField.setValue(verificationCode.getCode());
            verifyButton.click();
            return new DashboardPage();
        }

    }
}