package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ResultsPage;
import pages.SearchPage;
import tests.data.enums.Airport;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.sleep;

public class TicketSearchTests extends TestBase {

    SearchPage searchPage = new SearchPage();
    ResultsPage resultsPage = new ResultsPage();

    @EnumSource(Airport.class)
    @ParameterizedTest(name = "Поиск билета Москва-{0} в одну сторону")
    @Tag("BLOCKER")
    public void searchForOneWayTicketFromMoscowTest(Airport airport) {

        searchPage.openPage("?params=MOW1")
                .preventAdFromOpening()
                .setDestination(airport.name().toString())
                .setDepartureDate()
                .findTickets();
        sleep(20000);
        resultsPage.checkResultsAppear()
                .checkIsOneWayTrip()
                .checkDestination(airport.description);
    }

    @CsvSource(value = {
            "Ереван, Сочи",
            "Берлин, Амстердам"
    })
    @ParameterizedTest(name = "Поиск билета {0}-{1} туда и обратно")
    @Tag("BLOCKER")
    public void searchForRoundTripTicketTest(String originCity, String destinationCity) {

        searchPage.openPage("")
                .preventAdFromOpening()
                .setOrigin(originCity)
                .setDestination(destinationCity)
                .setDepartureDate()
                .setArrivalDate()
                .findTickets();
        sleep(20000);
        resultsPage.checkResultsAppear()
                .checkIsRoundTrip()
                .checkRoundTripItinerary(originCity, destinationCity);
    }

    static Stream<Arguments> searchForMultiCityTicketTest() {
        return Stream.of(
                Arguments.of(
                        List.of("Москва", "Санкт-Петербург", "Калининград"),
                        List.of("Санкт-Петербург", "Калининград", "Москва")
                ),
                Arguments.of(
                        List.of("Амстердам", "Париж", "Копенгаген"),
                        List.of("Барселона", "Берлин", "Хельсинки")
                )
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Поиск билета по сложному маршруту")
    @Tag("BLOCKER")
    public void searchForMultiCityTicketTest(List<String> originCities, List<String> destinationCities) {
        searchPage.openPage("")
                .preventAdFromOpening()
                .switchToMultiCityForm()
                .addANewFlight()
                .setMultiWayFlight(originCities, destinationCities, 0)
                .setMultiWayFlight(originCities, destinationCities, 1)
                .setMultiWayFlight(originCities, destinationCities, 2)
                .findTickets();
        sleep(20000);
        resultsPage.checkResultsAppear()
                .checkIsMultiWayTrip()
                .checkMultiWayTripItinerary(originCities, destinationCities);
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}
