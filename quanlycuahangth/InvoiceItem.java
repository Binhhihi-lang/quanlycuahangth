package quanlycuahangth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            if (item.invoiceItemID.equals(invoiceItemID)) {
                return false;
            }
        }
        InvoiceItem newItem = new InvoiceItem(invoiceItemID, invoiceID, productID, price, quantity);
        invoiceItemsList.add(newItem);
        return true;
    }

    public static boolean removeProductFromInvoice(String invoiceItemID) {
        Iterator<InvoiceItem> it = invoiceItemsList.iterator();
        while (it.hasNext()) {
            InvoiceItem item = it.next();
            if (item.invoiceItemID.equals(invoiceItemID)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public static boolean updateInvoiceItem(String invoiceItemID, String invoiceID, String productID, double price, int quantity) {
        for (InvoiceItem item : invoiceItemsList) {
            if (item.invoiceItemID.equals(invoiceItemID)) {
                item.invoiceID = invoiceID;
                item.productID = productID;
                item.price = price;
                item.quantity = quantity;
                return true;
            }
        }
        return false;
    }
}