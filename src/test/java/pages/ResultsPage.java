package pages;

import com.codeborne.selenide.CollectionCondition;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ResultsPage {

    public ResultsPage checkResultsAppear() {
        $("[data-test-id='ticket-preview']").should(appear);
        $$("[data-test-id='ticket-preview']").shouldHave(CollectionCondition.sizeGreaterThan(0));

        return this;
    }

    public ResultsPage checkIsOneWayTrip() {
        $$("[data-test-id='ticket-preview']").first()
                .$$("[data-test-id='destination-endpoint']").shouldHave(CollectionCondition.size(1));

        return this;
    }

    public ResultsPage checkIsRoundTrip() {
        $$("[data-test-id='ticket-preview']").first()
                .$$("[data-test-id='destination-endpoint']").shouldHave(CollectionCondition.size(2));

        return this;
    }

    public void checkDestination(String destinationCity) {
        $("[data-test-id='destination-endpoint']").shouldHave(text(destinationCity));
    }

    public void checkRoundTripItinerary(String originCity, String destinationCity) {
        checkOneFlight(originCity, destinationCity, 0);
        checkOneFlight(destinationCity, originCity, 1);
    }

    public void checkOneFlight(String originCity, String destinationCity, int i) {
        $$("[data-test-id='origin-endpoint']").get(i).shouldHave(text(originCity));
        $$("[data-test-id='destination-endpoint']").get(i).shouldHave(text(destinationCity));
    }

    public ResultsPage checkIsMultiWayTrip() {
        $$("[data-test-id='ticket-preview']").first()
                .$$("[data-test-id='destination-endpoint']").shouldHave(CollectionCondition.size(3));

        return this;
    }

    public void checkMultiWayTripItinerary(List<String> originCities, List<String> destinationCities) {
        checkOneFlight(originCities.get(0), destinationCities.get(0), 0);
        checkOneFlight(originCities.get(1), destinationCities.get(1), 1);
        checkOneFlight(originCities.get(2), destinationCities.get(2), 2);
    }
}