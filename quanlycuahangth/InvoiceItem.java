package quanlycuahangth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class InvoiceItem {
    private String invoiceItemID;
    private String invoiceID;
    private String productID;
    private double price;
    private int quantity;

    private static List<InvoiceItem> invoiceItemsList = new ArrayList<>();

    public InvoiceItem(String invoiceItemID, String invoiceID, String productID, double price, int quantity) {
        this.invoiceItemID = invoiceItemID;
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public String getInvoiceItemID() {
        return invoiceItemID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public String getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public static boolean addProductToInvoice(String invoiceItemID, String invoiceID, String productID, double price, int quantity) {
        for (InvoiceItem item : invoiceItemsList) {
            if (item.getInvoiceItemID().equals(invoiceItemID)) {
                return false; // Invoice item ID already exists
            }
        }
        InvoiceItem newItem = new InvoiceItem(invoiceItemID, invoiceID, productID, price, quantity);
        invoiceItemsList.add(newItem);
        return true;
    }

    public static boolean removeProductFromInvoice(String invoiceItemID) {
        return invoiceItemsList.removeIf(item -> item.getInvoiceItemID().equals(invoiceItemID));
    }
}