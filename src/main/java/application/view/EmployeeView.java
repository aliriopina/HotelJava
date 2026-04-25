package application.view;

import application.domain.Employee;
import application.service.outputs.EmployeeService;
import application.util.FormValidationUtil;

import java.util.List;

public class EmployeeView {

    private final EmployeeService employeeService;

    public EmployeeView(EmployeeService employeeService) {
        this.employeeService = employeeService;
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
            String position = FormValidationUtil.validateString("Ingrese el cargo");
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
                    + employee.getPosition() + " "
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
                + employee.getPosition() + " "
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
        String position = current.getPosition();
        double salary = current.getSalary();

        System.out.println("Empleado actual: id=" + id + " nombre=" + name + " apellido=" + lastName
                + " email=" + email + " cargo=" + position + " salario=" + salary + " estado=" + state);

        int option = FormValidationUtil.validateInt("1. Nombre  2. Apellido  3. Email  4. Password  5. Estado  6. Cargo  7. Salario");

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
                position = FormValidationUtil.validateString("Nuevo cargo");
                break;
            case 7:
                salary = FormValidationUtil.validateDouble("Nuevo salario");
                break;
            default:
                System.out.println("Seleccione una opción válida");
        }

        employeeService.updateEmployee(id, name, lastName, email, password, state, position, salary);
    }

    public void deleteEmployeeById() {
        employeeService.deleteEmployeeById(FormValidationUtil.validateInt("Ingrese el id del empleado a eliminar"));
    }
}
