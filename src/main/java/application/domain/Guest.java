package application.domain;

import application.domain.enums.GuestType;

public class Guest extends Person {

    private String origin;
    private GuestType guestType;

    public Guest() {
        super();
    }

    public Guest(int id, String name, String lastName, String email, String password, Boolean state, String origin, GuestType guestType) {
        super(id, name, lastName, email, password, state);
        this.origin = origin;
        this.guestType = guestType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public GuestType getGuestType() {
        return guestType;
    }

    public void setGuestType(GuestType guestType) {
        this.guestType = guestType;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "origin='" + origin + '\'' +
                ", guestType='" + guestType.getDescription() + '\'' +
                "} " + super.toString();
    }
}
