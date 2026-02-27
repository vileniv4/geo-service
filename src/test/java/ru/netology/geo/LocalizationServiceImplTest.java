package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {

    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    public void givenRussia_whenLocale_thenReturnsRussianText() {
        // given:
        Country country = Country.RUSSIA;

        // when:
        String result = localizationService.locale(country);

        // then:
        assertEquals("Добро пожаловать", result);
    }

    @Test
    public void givenUSA_whenLocale_thenReturnsEnglishText() {
        // given:
        Country country = Country.USA;

        // when:
        String result = localizationService.locale(country);

        // then:
        assertEquals("Welcome", result);
    }
}