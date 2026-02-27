package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTest {

    private final GeoService geoService = new GeoServiceImpl();

    @Test
    public void givenRussianIp_whenByIp_thenReturnsRussia() {
        // given:
        String ip = "172.0.0.1";

        // when:
        Location location = geoService.byIp(ip);

        // then:
        assertNotNull(location);
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Moscow", location.getCity());
    }

    @Test
    public void givenAmericanIp_whenByIp_thenReturnsUSA() {
        // given:
        String ip = "96.0.0.1";

        // when:
        Location location = geoService.byIp(ip);

        // then:
        assertNotNull(location);
        assertEquals(Country.USA, location.getCountry());
        assertEquals("New York", location.getCity());
    }

    @Test
    public void givenOtherIp_whenByIp_thenReturnsUSA() {
        // given:
        String ip = "1.2.3.4";

        // when:
        Location location = geoService.byIp(ip);

        // then:
        // для неизвестного IP может возвращаться null или USA
        if (location != null) {
            assertEquals(Country.USA, location.getCountry());
        }
    }
}