package application.domain;

import application.domain.enums.EmployeePosition;

public class Employee extends Person{

    private EmployeePosition position;
    private double salary;

    public Employee(){

        super();
    }

    public Employee(int id, String name, String lastName, String email, String password, Boolean state, EmployeePosition position, double salary) {
        super(id, name, lastName, email, password, state);
        this.position = position;
        this.salary = salary;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "position='" + position.getDescription() + '\'' +
                ", salary=" + salary +
                '}';
    }
}
