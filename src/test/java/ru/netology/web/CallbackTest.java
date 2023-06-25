package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class CallbackTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }


    
}
