package application.configuration;

import application.repository.BedRoomRepository;
import application.repository.BedRoomTypeRepository;
import application.repository.EmployeeRepository;
import application.repository.GuestRepository;
import application.service.BedRoomServiceImp;
import application.service.EmployeeServiceImpl;
import application.service.GuestServiceImpl;
import application.service.outputs.BedRoomService;
import application.service.outputs.EmployeeService;
import application.service.outputs.GuestService;
import application.service.ports.BedRoomRepositoryPort;
import application.service.ports.EmployeeRepositoryPort;
import application.service.ports.GuestRepositoryPort;
import application.userinterface.MenuApp;
import application.view.BedRoomView;
import application.view.EmployeeView;
import application.view.GuestView;

public class Config {

    public static MenuApp createMenuApp() {

        GuestRepositoryPort guestRepositoryPort = new GuestRepository();
        GuestService guestService = new GuestServiceImpl(guestRepositoryPort);
        GuestView guestView = new GuestView(guestService);

        BedRoomRepositoryPort bedRoomRepositoryPort = new BedRoomRepository();
        BedRoomTypeRepository bedRoomTypeRepository = new BedRoomTypeRepository();
        BedRoomService bedRoomService = new BedRoomServiceImp(bedRoomRepositoryPort, bedRoomTypeRepository);
        BedRoomView bedRoomView = new BedRoomView(bedRoomService);

        EmployeeRepositoryPort employeeRepositoryPort = new EmployeeRepository();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepositoryPort);
        EmployeeView employeeView = new EmployeeView(employeeService);

        return new MenuApp(guestView, bedRoomView, employeeView);
    }
}
