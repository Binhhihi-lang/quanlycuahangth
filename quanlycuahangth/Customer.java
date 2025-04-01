package quanlycuahangth;

import quanlycuahangth.quanlylop.InvoiceManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private String email;
    private String address;


    private static List<Customer> customerList = new ArrayList<>();


    public Customer(String userID, String username, String password, String customerID, String name, String phone, String email, String address) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public static boolean registerCustomer(Customer customer) {
        for (Customer c : customerList) {
            if (c.customerID.equals(customer.customerID)) {
                return false;
            }
        }
        customerList.add(customer);
        return true;
    }

    public static boolean updateCustomer(String customerID, String name, String phone, String email, String address) {
        for (Customer c : customerList) {
            if (c.customerID.equals(customerID)) {
                c.name = name;
                c.phone = phone;
                c.email = email;
                c.address = address;
                return true;
            }
        }
        return false;
    }

    public static List<Customer> searchCustomer(String customerID) {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customerList) {
            if (c.customerID.equals(customerID)) {
                result.add(c);
                return result;
            }
        }
        return null;
    }

    public static boolean deleteCustomer(String customerID) {
        Iterator<Customer> iterator = customerList.iterator();
        while (iterator.hasNext()) {
            Customer c = iterator.next();
            if (c.customerID.equals(customerID)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static List<Customer> listCustomer() {
        return new ArrayList<>(customerList);
    }

    public boolean createInvoice(String invoiceID, String customerID, String employeeID, double totalAmount, LocalDate date) {
        return InvoiceManager.createInvoice(invoiceID, customerID, employeeID, totalAmount, date);
    }
}