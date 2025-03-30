package quanlycuahangth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Customer(String address, String email, String phone, String name, String customerID) {
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.customerID = customerID;
    }

    private static List<Customer> customerList = new ArrayList<>();

    public static boolean registerCustomer(Customer customer) {
        for (Customer c : customerList) {
            if (c.customerID.equals(customer.customerID)) {
                System.out.println("Khác hàng đã tồn tại");
                return false;
            }
        }
        customerList.add(customer);
        System.out.println("Đăng kí khách hàng thành công:" + customer.customerID);
        return true;
    }

    public static boolean updateCustomer(String customerID, String name, String phone, String email, String address) {
        for (Customer c : customerList) {
            if (c.customerID.equals(customerID)) {
                c.name = name;
                c.phone = phone;
                c.email = email;
                c.address = address;
                System.out.println("Cập nhật thông tin khách hàng thành công" + customerID);
                return true;
            }
        }
        System.out.println("Không tìm thấy khách hàng " + customerID);
        return false;
    }

    public static List<Customer> searchCustomer(String customerID) {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customerList) {
            if (c.customerID.equals(customerID)) {
                result.add(c);
                System.out.println("Đã tìm thấy khách hàng");
            } else {
                System.out.println("Không tìm thấy khách hàng" + customerID);
            }
        }
        return result;
    }

    public static boolean deleteCustomer(String customerID) {
        Iterator<Customer> iterator = customerList.iterator();
        while (iterator.hasNext()) {
            Customer c = iterator.next();
            if (c.customerID.equals(customerID)) {
                iterator.remove();
                System.out.println("Xóa khách hàng thành công");
                return true;
            }
        }
        System.out.println("Không tìm thấy khách hàng" + customerID);
        return false;
    }

    public static List<Customer> listCustomer() {
        return new ArrayList<>(customerList);
    }
}