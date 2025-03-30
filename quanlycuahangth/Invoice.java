package quanlycuahangth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String invoiceID;
    private String customerID;
    private String employeeID;
    private LocalDate date;
    private double totalAmount;

    private static List<Invoice> invoiceList = new ArrayList<>();

    public Invoice(String invoiceID, String customerID, String employeeID, LocalDate date, double totalAmount) {
        this.invoiceID = invoiceID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public static boolean createInvoice(String invoiceID, String customerID, String employeeID, double totalAmount, LocalDate date) {
        for (Invoice invoice : invoiceList) {
            if (invoice.getInvoiceID().equals(invoiceID)) {
                return false; // Invoice ID already exists
            }
        }
        Invoice newInvoice = new Invoice(invoiceID, customerID, employeeID, date, totalAmount);
        invoiceList.add(newInvoice);
        return true;
    }

    public static boolean searchInvoice(String invoiceID) {
        for (Invoice invoice : invoiceList) {
            if (invoice.getInvoiceID().equals(invoiceID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean deleteInvoice(String invoiceID) {
        return invoiceList.removeIf(invoice -> invoice.getInvoiceID().equals(invoiceID));
    }

    public static void printInvoice(String invoiceID) {
        for (Invoice invoice : invoiceList) {
            if (invoice.getInvoiceID().equals(invoiceID)) {
                System.out.println("Invoice ID: " + invoice.getInvoiceID());
                System.out.println("Customer ID: " + invoice.getCustomerID());
                System.out.println("Employee ID: " + invoice.getEmployeeID());
                System.out.println("Date: " + invoice.getDate());
                System.out.println("Total Amount: " + invoice.getTotalAmount());
                return;
            }
        }
        System.out.println("Invoice not found.");
    }

    public static List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoiceList);
    }

    public static List<Customer> getTopCustomer() {
        // Assuming a method to get top customers based on the amount spent
        // This is a placeholder implementation
        List<Customer> customers = CustomerManager.getAllCustomers();
        customers.sort((c1, c2) -> Double.compare(getTotalSpentByCustomer(c2.getCustomerID()), getTotalSpentByCustomer(c1.getCustomerID())));
        return customers.subList(0, Math.min(5, customers.size())); // Return top 5 customers
    }

    public static double getTotalRevenue() {
        return invoiceList.stream().mapToDouble(Invoice::getTotalAmount).sum();
    }

    private static double getTotalSpentByCustomer(String customerID) {
        // Placeholder implementation to calculate total amount spent by a customer
        return invoiceList.stream()
                .filter(invoice -> invoice.getCustomerID().equals(customerID))
                .mapToDouble(Invoice::getTotalAmount)
                .sum();
    }
}