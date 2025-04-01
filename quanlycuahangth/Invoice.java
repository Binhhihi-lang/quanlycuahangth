package quanlycuahangth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Invoice {
    private String invoiceID;
    private String customerID;
    private String employeeID;
    private LocalDate date;
    private double totalAmount;
    private List<InvoiceItem> items;

    private static List<Invoice> invoiceList = new ArrayList<>();

    public Invoice(String invoiceID, String customerID, String employeeID, LocalDate date, double totalAmount) {
        this.invoiceID = invoiceID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.date = date;
        this.totalAmount = totalAmount;
        this.items = new ArrayList<>();
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

    public List<InvoiceItem> getItems() {
        return items;
    }

    // tạo 1 mục mới để tạo ra hóa đơn mới áp dụng thuế hoặc khuyến mãi
    public void addItem(InvoiceItem item) {
        items.add(item);
        totalAmount += item.getPrice() * item.getQuantity();
    }

    public static boolean createInvoice(String invoiceID, String customerID, String employeeID, double totalAmount, LocalDate date) {
        for (Invoice invoice : invoiceList) {
            if (invoice.getInvoiceID().equals(invoiceID)) {
                return false;
            }
        }
        Invoice newInvoice = new Invoice(invoiceID, customerID, employeeID, date, totalAmount);
        invoiceList.add(newInvoice);
        return true;
    }

    public static List<Invoice> searchInvoice(String invoiceID) {
        List<Invoice> result = new ArrayList<>();
        for (Invoice i : invoiceList) {
            if (i.invoiceID.equals(invoiceID)) {
                result.add(i);
                return result;
            }
        }
        return null;
    }

    public static boolean deleteInvoice(String invoiceID) {
        Iterator<Invoice> it = invoiceList.iterator();
        while (it.hasNext()) {
            Invoice invoice = it.next();
            if (invoice.invoiceID.equals(invoiceID)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public static void printInvoice(String invoiceID) {
        for (Invoice invoice : invoiceList) {
            if (invoice.invoiceID.equals(invoiceID)) {
                System.out.println("Invoice ID: " + invoice.invoiceID);
                System.out.println("Customer ID: " + invoice.customerID);
                System.out.println("Employee ID: " + invoice.employeeID);
                System.out.println("Date: " + invoice.date);
                System.out.println("Total Amount: " + invoice.totalAmount);
                System.out.println("Items:");
                for (InvoiceItem item : invoice.getItems()) {
                    System.out.println("  Product ID: " + item.getProductID());
                    System.out.println("  Price: " + item.getPrice());
                    System.out.println("  Quantity: " + item.getQuantity());
                }
                return;
            }
        }
        System.out.println("Hóa đơn không tồn tại");
    }

    public static List<Invoice> listInvoices() {
        return new ArrayList<>(invoiceList);
    }
}