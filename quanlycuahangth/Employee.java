package quanlycuahangth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Employee extends SystemUser {
    private String employeeID;
    private String name;
    private String phone;
    private String email;
    private String address;
    private double salary;
    private String workShift;

    public Employee(String userID, String username, String password, String employeeID, String name, String email, String address, String phone, double salary, String workShift) {
        super(userID, username, password);
        this.employeeID = employeeID;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
        this.workShift = workShift;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public double getSalary() {
        return salary;
    }

    public String getWorkShift() {
        return workShift;
    }

    public static List<Employee> employeelist = new ArrayList<>();

    public static boolean addEmployee(Employee employee) {
        for (Employee e : employeelist) {
            if (e.employeeID.equals(employee.employeeID)) {
                return false;
            }
        }
        employeelist.add(employee);
        return true;
    }

    public static boolean updateEmployee(String employeeID, String name, String phone, String email, String address, double salary, String workShift) {
        for (Employee e : employeelist) {
            if (e.employeeID.equals(employeeID)) {
                e.employeeID = employeeID;
                e.name = name;
                e.phone = phone;
                e.email = email;
                e.address = address;
                e.salary = salary;
                e.workShift = workShift;
                return true;
            }
        }
        return false;
    }

    public static List<Employee> searchEmployee(String employeeID) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employeelist) {
            if (e.employeeID.equals(employeeID)) {
                result.add(e);
                return result;
            }
        }
        return null;
    }

    public static boolean deleteEmployee(String employeeID) {
        Iterator<Employee> it = employeelist.iterator();
        while (it.hasNext()) {
            Employee e = it.next();
            if (e.employeeID.equals(employeeID)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public static List<Employee> listEmployees() {
//        System.out.println("Danh sách nhân viên là :") ;
        return new ArrayList<>(employeelist);
    }
}
