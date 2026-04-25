package application.service;

import application.domain.Employee;
import application.service.outputs.EmployeeService;
import application.service.ports.EmployeeRepositoryPort;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    public EmployeeServiceImpl(EmployeeRepositoryPort employeeRepositoryPort) {
        this.employeeRepositoryPort = employeeRepositoryPort;
    }

    @Override
    public Employee createEmployee(int id, String name, String lastName, String email, String password, boolean state, String position, double salary) {
        if (employeeRepositoryPort.findEmployeeById(id).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con id: " + id);
        }
        Employee employee = new Employee(id, name, lastName, email, password, state, position, salary);
        employeeRepositoryPort.saveEmployee(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(int id, String name, String lastName, String email, String password, boolean state, String position, double salary) {
        Employee employee = employeeRepositoryPort.findEmployeeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        employee.setName(name);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setState(state);
        employee.setPosition(position);
        employee.setSalary(salary);
        employeeRepositoryPort.updateEmployee(id, employee);
        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepositoryPort.findEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepositoryPort.findAllEmployees();
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepositoryPort.deleteEmployeeById(id);
    }
}
