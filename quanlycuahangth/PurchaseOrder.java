package quanlycuahangth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PurchaseOrder {
    private String purchaseOrderID;
    private String supplierID;
    private String employeeID;
    private LocalDate date;
    private double totalAmount;
    private List<PurchaseOrderItem> items;

    private static List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

    public PurchaseOrder(String purchaseOrderID, String supplierID, String employeeID, LocalDate date, double totalAmount) {
        this.purchaseOrderID = purchaseOrderID;
        this.supplierID = supplierID;
        this.employeeID = employeeID;
        this.date = date;
        this.totalAmount = totalAmount;
        this.items = new ArrayList<>();
    }

    public String getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public String getSupplierID() {
        return supplierID;
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

    public List<PurchaseOrderItem> getItems() {
        return items;
    }

    public void addItem(PurchaseOrderItem item) {
        items.add(item);
        totalAmount += item.getPrice() * item.getQuantity();
    }

    public static boolean createPurchaseOrder(String purchaseOrderID, String supplierID, String employeeID, double totalAmount, LocalDate date) {
        for (PurchaseOrder order : purchaseOrderList) {
            if (order.purchaseOrderID.equals(purchaseOrderID)) {
                return false;
            }
        }
        PurchaseOrder newOrder = new PurchaseOrder(purchaseOrderID, supplierID, employeeID, date, totalAmount);
        purchaseOrderList.add(newOrder);
        return true;
    }

    public static List<PurchaseOrder> searchPurchaseOrder(String purchaseOrderID) {
        List<PurchaseOrder> result = new ArrayList<>();
        for (PurchaseOrder order : purchaseOrderList) {
            if (order.purchaseOrderID.equals(purchaseOrderID)) {
                result.add(order);
                return result;
            }
        }
        return null;
    }

    public static boolean deletePurchaseOrder(String purchaseOrderID) {
        Iterator<PurchaseOrder> it = purchaseOrderList.iterator();
        while (it.hasNext()) {
            PurchaseOrder order = it.next();
            if (order.purchaseOrderID.equals(purchaseOrderID)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public static void printPurchaseOrder(String purchaseOrderID) {
        for (PurchaseOrder order : purchaseOrderList) {
            if (order.purchaseOrderID.equals(purchaseOrderID)) {
                System.out.println("Purchase Order ID: " + order.getPurchaseOrderID());
                System.out.println("Supplier ID: " + order.getSupplierID());
                System.out.println("Employee ID: " + order.getEmployeeID());
                System.out.println("Date: " + order.getDate());
                System.out.println("Total Amount: " + order.getTotalAmount());
                System.out.println("Items:");
                for (PurchaseOrderItem item : order.getItems()) {
                    System.out.println("  Product ID: " + item.getProductID());
                    System.out.println("  Price: " + item.getPrice());
                    System.out.println("  Quantity: " + item.getQuantity());
                }
                return;
            }
        }
        System.out.println("");
    }

    public static List<PurchaseOrder> listPurchaseOrders() {
        return new ArrayList<>(purchaseOrderList);
    }
}