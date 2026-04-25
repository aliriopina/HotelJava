package application.domain.enums;

public enum GuestType {

    NUEVO("Nuevo"),
    FRECUENTE("Frecuente"),
    VIP("VIP");

    private final String description;

    GuestType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
