package pages;

import com.codeborne.selenide.Selenide;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    public SearchPage openPage(String path) {
        open(path);

        return this;
    }

    public SearchPage preventAdFromOpening() {
        $("[data-test-id='checkbox']").click();

        return this;
    }

    public SearchPage switchToMultiCityForm() {
        $("[data-test-id='switch-to-multiwayform']").click();

        return this;
    }

    public SearchPage addANewFlight() {
        $("[data-test-id='multiway-add-direction']").click();

        return this;
    }

    public SearchPage setOrigin(String originCity) {
        $("[data-test-id='origin-input']").clear();
        $("[data-test-id='origin-input']").setValue(originCity);
        sleep(1000);
        return this;
    }

    public void setMultiwayOrigin(String originCity, int i) {
        if (i == 0) {
            $("[data-test-id='multiway-origin-input']").clear();
        }
        $$("[data-test-id='multiway-origin-input']").get(i).setValue(originCity);
        sleep(1000);
    }

    public SearchPage setDestination(String destinationCity) {
        $("[data-test-id='destination-input']").setValue(destinationCity);
        sleep(1000);

        return this;
    }

    public void setMultiwayDestination(String destinationCity, int i) {
        $$("[data-test-id='multiway-destination-input']").get(i).setValue(destinationCity);
        sleep(1000);
    }

    public SearchPage setDepartureDate() {
        Selenide.executeJavaScript("arguments[0].click();", $("[data-test-id='start-date-field']"));
        $("[data-test-id='date-10.09.2024']").click();

        return this;
    }

    public void setMultiWayDepartureDate(int i) {
        Selenide.executeJavaScript("arguments[0].click();", $$("[data-test-id='multiway-date']").get(i));
        int day = 29 + i;
        String dateLocator = "[data-test-id='date-" + day + ".08.2024']";
        Selenide.executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", $(dateLocator));
        $(dateLocator).click();
    }

    public SearchPage setMultiWayFlight(List<String> originCities, List<String> destinationCities, int i) {

        setMultiwayOrigin(originCities.get(i), i);
        setMultiwayDestination(destinationCities.get(i), i);
        setMultiWayDepartureDate(i);

        return this;
    }

    public SearchPage setArrivalDate() {
        Selenide.executeJavaScript("arguments[0].click();", $("[data-test-id='end-date-field']"));
        $("[data-test-id='date-14.09.2024']").click();

        return this;
    }

    public void findTickets() {
        $("[data-test-id='form-submit']").click();
    }
}