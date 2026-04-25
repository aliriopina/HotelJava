package application.service.outputs;

import application.domain.Guest;
import application.domain.enums.GuestType;

import java.util.List;
import java.util.Optional;

public interface GuestService {

    Guest createGuest(int id, String name, String lastName, String email, String password, boolean state, String origin, GuestType guestType);
    Guest updateGuest(int id, String name, String lastName, String email, String password, boolean state, String origin, GuestType guestType);
    Optional<Guest> getGuestById(int id);
    List<Guest> getAllGuests();
    void deleteGuestById(int id);

}
