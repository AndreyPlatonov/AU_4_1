package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CallbackTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void submitSuccessRequest() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        // $(".button_view_extra").click();
        // $(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + dateMeeting), Duration.ofSeconds(15)).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedNullCity() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        $("[data-test-id=city] input").setValue("");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedWrongCity() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        $("[data-test-id=city] input").setValue("ввввввв");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedNullDate() {

        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue("");
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input.input_invalid .input__sub").shouldHave(Condition.exactText("Неверно введена дата")).shouldBe(Condition.visible);


    }

    @Test
    void submitSuccessBoundDate() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(3);

        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + dateMeeting), Duration.ofSeconds(15)).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedBeforeDate() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(1);

        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input.input_invalid .input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedNullName() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name].input_invalid.input .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedWrongName() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Иван222");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name].input.input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedNullPhone() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone].input.input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedWrongPhone() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+7920123456723");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone].input.input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(Condition.visible);

    }

    @Test
    void submitFailedNotCheck() {

        FormatDate dateFormatter = new FormatDate();
        String dateMeeting = dateFormatter.currentPlusDays(4);

        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));

    }


}
