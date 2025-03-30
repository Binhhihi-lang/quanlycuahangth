package quanlycuahangth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Employee {
    private String employeeID;
    private String name;
    private String phone;
    private String email;
    private String address;
    private double salary;
    private String workShift;

    public Employee(String employeeID, String name, String phone, String email, String address, double salary, String workShift) {
        this.employeeID = employeeID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.workShift = workShift;
    }

    public static List<Employee> employeelist = new ArrayList<>();

    public static boolean addEmployee(Employee employee) {
        for (Employee e : employeelist) {
            if (e.employeeID.equals(employee.employeeID)) {
                System.out.println("Nhân viên đã tồn tại:" + employee.employeeID);
                return false;
            }
        }
        employeelist.add(employee);
        System.out.println("Thêm nhân viên thành công:" + employee.employeeID);
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
                System.out.println("Cập nhật thông tin nhân viên thành công:" + employeeID);
                return true;
            }
        }
        System.out.println("Không tìm thấy nhân viên :" + employeeID);
        return false;
    }

    public static List<Employee> searchEmployee(String employeeID) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employeelist) {
            if (e.employeeID.equals(employeeID)) {
                System.out.println("Đã tìm thấy nhân viên :");
                result.add(e);
            } else {
                System.out.println("Không tìm thấy nhân viên" + employeeID);
            }
        }
        return result;
    }

    public static boolean deleteEmployee(String employeeID) {
        Iterator<Employee> it = employeelist.iterator();
        while (it.hasNext()) {
            Employee e = it.next();
            if (e.employeeID.equals(employeeID)) {
                it.remove();
                System.out.println("Xóa nhân viên thành công" + employeeID);
                return true;
            }
        }
        System.out.println("Không tìm thấy nhân viên:" + employeeID);
        return false;
    }

    public static List<Employee> listEmployees() {
//        System.out.println("Danh sách nhân viên là :") ;
        return new ArrayList<>(employeelist);
    }
}
