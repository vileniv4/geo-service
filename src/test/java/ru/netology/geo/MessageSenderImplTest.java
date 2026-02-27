package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MessageSenderImplTest {

    @Test
    public void givenRussianIp_whenSend_thenReturnsRussianText() {
        // given:
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        // используем anyString() для любого IP
        when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        // when:
        Map<String, String> headers = Map.of("ip", "172.0.0.1");
        String result = messageSender.send(headers);

        // then:
        assertEquals("Добро пожаловать", result);

        verify(geoService, times(1)).byIp(Mockito.anyString());
        verify(localizationService, atLeastOnce()).locale(Country.RUSSIA);
    }

    @Test
    public void givenAmericanIp_whenSend_thenReturnsEnglishText() {
        // given:
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        // используем anyString() для любого IP
        when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("New York", Country.USA, null, 0));
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        // when:
        Map<String, String> headers = Map.of("ip", "96.0.0.1");
        String result = messageSender.send(headers);

        // then:
        assertEquals("Welcome", result);

        verify(geoService, times(1)).byIp(Mockito.anyString());
        verify(localizationService, atLeastOnce()).locale(Country.USA);
    }
}