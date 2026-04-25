package application.view;

import application.domain.Guest;
import application.service.outputs.GuestService;
import application.util.FormValidationUtil;

import java.util.List;

public class GuestView {

    private final GuestService guestService;

    public GuestView(GuestService guestService) {
        this.guestService = guestService;
    }

    public void createGuest() {
        System.out.println("Crear huésped");
        try {
            int id = FormValidationUtil.validateInt("Ingrese el id del huésped");
            String name = FormValidationUtil.validateString("Ingrese el nombre");
            String lastName = FormValidationUtil.validateString("Ingrese el apellido");
            String email = FormValidationUtil.validateString("Ingrese el email");
            String password = FormValidationUtil.validateString("Ingrese el password");
            boolean state = FormValidationUtil.validateBoolean("Estado (true/false)");
            String origin = FormValidationUtil.validateString("Ingrese el origen");
            String guestType = FormValidationUtil.validateString("Ingrese el tipo de huésped");

            Guest created = guestService.createGuest(id, name, lastName, email, password, state, origin, guestType);
            System.out.println("Huésped creado: " + created.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getAllGuests() {
        System.out.println("Mostrando todos los huéspedes...");
        List<Guest> guests = guestService.getAllGuests();
        for (Guest guest : guests) {
            System.out.println(guest.getId() + " "
                    + guest.getName() + " "
                    + guest.getLastName() + " "
                    + guest.getEmail() + " "
                    + guest.getOrigin() + " "
                    + guest.getGuestType() + " "
                    + guest.getState());
        }
    }

    public void getGuestById() {
        System.out.println("Buscar huésped por id");
        Guest guest = guestService.getGuestById(FormValidationUtil.validateInt("Ingrese el id del huésped"))
                .orElseThrow(() -> new IllegalArgumentException("Huésped no encontrado"));
        System.out.println(guest.getId() + " "
                + guest.getName() + " "
                + guest.getLastName() + " "
                + guest.getEmail() + " "
                + guest.getOrigin() + " "
                + guest.getGuestType() + " "
                + guest.getState());
    }

    public void updateGuest() {
        int id = FormValidationUtil.validateInt("Ingrese el id del huésped a actualizar");

        Guest current = guestService.getGuestById(id)
                .orElseThrow(() -> new IllegalArgumentException("Huésped no existe"));

        String name = current.getName();
        String lastName = current.getLastName();
        String email = current.getEmail();
        String password = current.getPassword();
        boolean state = current.getState();
        String origin = current.getOrigin();
        String guestType = current.getGuestType();

        System.out.println("Huésped actual: id=" + id + " nombre=" + name + " apellido=" + lastName
                + " email=" + email + " origen=" + origin + " tipo=" + guestType + " estado=" + state);

        int option = FormValidationUtil.validateInt("1. Nombre  2. Apellido  3. Email  4. Password  5. Estado  6. Origen  7. Tipo");

        switch (option) {
            case 1:
                name = FormValidationUtil.validateString("Nuevo nombre");
                break;
            case 2:
                lastName = FormValidationUtil.validateString("Nuevo apellido");
                break;
            case 3:
                email = FormValidationUtil.validateString("Nuevo email");
                break;
            case 4:
                password = FormValidationUtil.validateString("Nuevo password");
                break;
            case 5:
                state = FormValidationUtil.validateBoolean("Nuevo estado (true/false)");
                break;
            case 6:
                origin = FormValidationUtil.validateString("Nuevo origen");
                break;
            case 7:
                guestType = FormValidationUtil.validateString("Nuevo tipo de huésped");
                break;
            default:
                System.out.println("Seleccione una opción válida");
        }

        guestService.updateGuest(id, name, lastName, email, password, state, origin, guestType);
    }

    public void deleteGuestById() {
        guestService.deleteGuestById(FormValidationUtil.validateInt("Ingrese el id del huésped a eliminar"));
    }
}
