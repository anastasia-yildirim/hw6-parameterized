package tests.data.enums;

public enum Airport {
    //MOW("Москва"),
    //MRV("Минеральные Воды"),
    AER("Сочи"),
    //LED("Санкт-Петербург"),
    KGD("Калининград"),
    //AYT("Анталья"),
    //IZM("Измир"),
    EVN("Ереван");
    //BCN("Барселона"),
    //BER("Берлин"),
    //IST("Стамбул");

    public final String description;

    Airport(String description) {
    this.description = description;
    }
}
