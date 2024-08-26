package tests.data.enums;

public enum Airport {
    AER("Сочи"),
    KGD("Калининград"),
    EVN("Ереван");

    public final String description;

    Airport(String description) {
        this.description = description;
    }
}
