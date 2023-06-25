package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue(dateFormatter.currentPlusDays(4));
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        //$(".button_view_extra").click();
        $(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));


    }

    @Test
    void submitFailedNullCity() {

        FormatDate dateFormatter = new FormatDate();
        $("[data-test-id=city] input").setValue("");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue(dateFormatter.currentPlusDays(4));
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city]").shouldHave(Condition.cssClass("input_invalid"));

    }

    @Test
    void submitFailedWrongCity() {

        FormatDate dateFormatter = new FormatDate();
        $("[data-test-id=city] input").setValue("ввввввв");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue(dateFormatter.currentPlusDays(4));
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));

    }

   /* @Test
    void submitFailedNullDate() {

        FormatDate dateFormatter = new FormatDate();
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue("");
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input").shouldHave(Condition.cssClass("input_invalid"));


    }*/

    @Test
    void submitSuccessBoundDate() {

        FormatDate dateFormatter = new FormatDate();
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue(dateFormatter.currentPlusDays(3));
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));

    }

    /* @Test
     void submitFailedBeforeDate() {

         FormatDate dateFormatter = new FormatDate();
         String dateFormat=dateFormatter.currentPlusDays(1);
         $("[data-test-id=city] input").setValue("Томск");
         $("[data-test-id=date] input").doubleClick();
         $("[data-test-id=date] input").sendKeys(" ");
         $("[data-test-id=date] input").setValue(dateFormat);
         $("[data-test-id=name] input").setValue("Иванов Владимир");
         $("[data-test-id=phone] input").setValue("+79201234567");
         $("[data-test-id=agreement]").click();
         $$("button").find(exactText("Забронировать")).click();
         $("[data-test-id=date] .input").shouldHave(Condition.cssClass("input_invalid"));;

     }*/
    @Test
    void submitFailedNullName() {

        FormatDate dateFormatter = new FormatDate();
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue(dateFormatter.currentPlusDays(4));
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name].input .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void submitFailedWrongName() {

        FormatDate dateFormatter = new FormatDate();
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue(dateFormatter.currentPlusDays(4));
        $("[data-test-id=name] input").setValue("Иванов Иван222");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name].input .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void submitFailedNullPhone() {

        FormatDate dateFormatter = new FormatDate();
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue(dateFormatter.currentPlusDays(4));
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone].input .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void submitFailedWrongPhone() {

        FormatDate dateFormatter = new FormatDate();
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue(dateFormatter.currentPlusDays(4));
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+7920123456723");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone].input .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void submitFailedNotCheck() {

        FormatDate dateFormatter = new FormatDate();
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        $("[data-test-id=date] input").setValue(dateFormatter.currentPlusDays(4));
        $("[data-test-id=name] input").setValue("Иванов Владимир");
        $("[data-test-id=phone] input").setValue("+79201234567");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));

    }


}
