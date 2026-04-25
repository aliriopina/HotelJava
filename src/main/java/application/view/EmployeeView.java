package application.view;

import application.domain.Employee;
import application.domain.enums.EmployeePosition;
import application.service.outputs.EmployeeService;
import application.util.FormValidationUtil;

import java.util.List;

public class EmployeeView {

    private final EmployeeService employeeService;

    public EmployeeView(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private EmployeePosition selectEmployeePosition() {
        System.out.println("Seleccione el cargo: 1. Recepcionista  2. Administrador  3. Limpieza  4. Seguridad");
        int option = FormValidationUtil.validateInt("Opción");
        return switch (option) {
            case 1 -> EmployeePosition.RECEPCIONISTA;
            case 2 -> EmployeePosition.ADMINISTRADOR;
            case 3 -> EmployeePosition.LIMPIEZA;
            case 4 -> EmployeePosition.SEGURIDAD;
            default -> throw new IllegalArgumentException("Cargo no válido");
        };
    }

    public void createEmployee() {
        System.out.println("Crear empleado");
        try {
            int id = FormValidationUtil.validateInt("Ingrese el id del empleado");
            String name = FormValidationUtil.validateString("Ingrese el nombre");
            String lastName = FormValidationUtil.validateString("Ingrese el apellido");
            String email = FormValidationUtil.validateString("Ingrese el email");
            String password = FormValidationUtil.validateString("Ingrese el password");
            boolean state = FormValidationUtil.validateBoolean("Estado (true/false)");
            EmployeePosition position = selectEmployeePosition();
            double salary = FormValidationUtil.validateDouble("Ingrese el salario");

            Employee created = employeeService.createEmployee(id, name, lastName, email, password, state, position, salary);
            System.out.println("Empleado creado: " + created.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getAllEmployees() {
        System.out.println("Mostrando todos los empleados...");
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee.getId() + " "
                    + employee.getName() + " "
                    + employee.getLastName() + " "
                    + employee.getEmail() + " "
                    + employee.getPosition().getDescription() + " "
                    + employee.getSalary() + " "
                    + employee.getState());
        }
    }

    public void getEmployeeById() {
        System.out.println("Buscar empleado por id");
        Employee employee = employeeService.getEmployeeById(FormValidationUtil.validateInt("Ingrese el id del empleado"))
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        System.out.println(employee.getId() + " "
                + employee.getName() + " "
                + employee.getLastName() + " "
                + employee.getEmail() + " "
                + employee.getPosition().getDescription() + " "
                + employee.getSalary() + " "
                + employee.getState());
    }

    public void updateEmployee() {
        int id = FormValidationUtil.validateInt("Ingrese el id del empleado a actualizar");

        Employee current = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no existe"));

        String name = current.getName();
        String lastName = current.getLastName();
        String email = current.getEmail();
        String password = current.getPassword();
        boolean state = current.getState();
        EmployeePosition position = current.getPosition();
        double salary = current.getSalary();

        System.out.println("Empleado actual: id=" + id + " nombre=" + name + " apellido=" + lastName
                + " email=" + email + " cargo=" + position.getDescription() + " salario=" + salary + " estado=" + state);

        int option = FormValidationUtil.validateInt("1. Nombre  2. Apellido  3. Email  4. Password  5. Estado  6. Cargo  7. Salario");

        switch (option) {
            case 1 -> name = FormValidationUtil.validateString("Nuevo nombre");
            case 2 -> lastName = FormValidationUtil.validateString("Nuevo apellido");
            case 3 -> email = FormValidationUtil.validateString("Nuevo email");
            case 4 -> password = FormValidationUtil.validateString("Nuevo password");
            case 5 -> state = FormValidationUtil.validateBoolean("Nuevo estado (true/false)");
            case 6 -> position = selectEmployeePosition();
            case 7 -> salary = FormValidationUtil.validateDouble("Nuevo salario");
            default -> System.out.println("Seleccione una opción válida");
        }

        employeeService.updateEmployee(id, name, lastName, email, password, state, position, salary);
    }

    public void deleteEmployeeById() {
        employeeService.deleteEmployeeById(FormValidationUtil.validateInt("Ingrese el id del empleado a eliminar"));
    }
}
